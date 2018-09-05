package com.ge.digital.quality.mapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.digital.quality.dto.FirstStoveDTO;
import com.ge.digital.quality.entity.InspectionDetail;
import com.ge.digital.quality.entity.QualityItem;
import com.ge.digital.quality.query.GasUsageQuery;
import com.ge.digital.quality.query.PageQuery;
import com.ge.digital.quality.query.PageResult;
import com.ge.digital.quality.query.QualityQuery;
import com.ge.digital.quality.query.StoveStatisticsQuery;
import com.ge.digital.quality.service.RedisService;
import com.ge.digital.quality.util.BeanCopyUtils;
import com.ge.digital.quality.vo.GasUsageVO;
import com.ge.digital.quality.vo.QualityResultVO;
import com.ge.digital.quality.vo.StoveStatisticsVO;
import com.google.gson.Gson;

@Component
public class CustomJpaRepository {

	private static final Logger log = LoggerFactory.getLogger(CustomJpaRepository.class);

	@Autowired
	WipTaskRepository wipTaskRepository;

	@Autowired
	ViewRepository viewRepository;

	@Autowired
	EntityManager entityManager;

	@Autowired
	RedisService redisService;

	@Autowired
	InspectionDetailRepository inspectionDetailRepository;

	@Autowired
	QualityItemRepository qualityItemRepository;

	/**
	 * 真空爐统计
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageResult findStoveStatistics(StoveStatisticsQuery query) throws Exception {
		// 返回值的结果集
		List<StoveStatisticsVO> vos = new ArrayList<>();
		String pageBefore = ",ROW_NUMBER() OVER(ORDER BY [taskEndDate] DESC) AS num ";
		String pageAfter = "";
		StringBuffer before = new StringBuffer();
		before.append(
				"select * from (SELECT DISTINCT tjoin.line,convert(varchar(200),tjoin.Classification) as Classification,tjoin.partNumber,tjoin.partName,"
						+ "tjoin.taskEndDate,tjoin.materialCode FROM (SELECT uwt.line,umview.Classification,uwt.partNumber,uwt.taskEndDate,uwt.partName,umview.materialCode"
						+ " FROM udtWip_Task uwt INNER  JOIN udtWip_ProcessCardInfo uwpci ON "
						+ "uwt.processCardNumber = uwpci.processCardNumber INNER  JOIN "
						+ "udvMa_AllMaterialProperyWithCode_v umview ON umview.line ="
						+ " uwt.line AND umview.materialCode = uwt.materialCode WHERE taskType='14' ");
		StringBuffer where = new StringBuffer();
		where.append(query.toWhere());
		StringBuffer after = new StringBuffer();
		after.append(
				") AS tjoin GROUP BY tjoin.line,tjoin.Classification,tjoin.partNumber,tjoin.taskEndDate,tjoin.partName,tjoin.materialCode) as t");
		StringBuffer page = new StringBuffer();
		page.append(" where t.num between ").append((query.getPageIndex() - 1) * query.getPageSize()).append(" and ")
				.append(query.getPageSize() * query.getPageIndex());
		StringBuffer sql = new StringBuffer();
		sql.append(before).append(where).append(after);
		Query query2 = entityManager.createNativeQuery(sql.toString());
		List list = query2.getResultList();
		Map<String, List<FirstStoveDTO>> result = new HashMap<>();
		for (Object obj : list) {
			Object[] array = (Object[]) obj;
			FirstStoveDTO dto = new FirstStoveDTO();
			dto.setLine((String) array[0]);
			dto.setClassification((String) array[1]);
			dto.setPartNumber((String) array[2]);
			dto.setTaskEndDate((Date) array[4]);
			dto.setPartName((String) array[3]);
			dto.setMaterialCode((String) array[5]);
			if (!result.containsKey(dto.getUniqueCode())) {
				List<FirstStoveDTO> dtos = new ArrayList<>();
				dtos.add(dto);
				result.put(dto.getUniqueCode(), dtos);
			} else {
				result.get(dto.getUniqueCode()).add(dto);
			}
		}
		// 获取map中的所有key
		Set<String> keys = result.keySet();
		for (String key : keys) {
			StoveStatisticsVO vo = new StoveStatisticsVO();
			//
			List<FirstStoveDTO> dtos = result.get(key);
			if (null == dtos || dtos.size() <= 0) {
				log.error("dtos is null,key:{}", key);
				continue;
			}
			//
			vo.setClassification(dtos.get(0).getClassification());
			vo.setLine(dtos.get(0).getLine());
			vo.setPartNumber(dtos.get(0).getPartNumber());
			vo.setPartName(dtos.get(0).getPartName());
			vo.setMaterialCode(dtos.get(0).getMaterialCode());
			// 查询装炉量
			Integer loadQuantity = viewRepository.findByMaterialCodeAndLine(vo.getMaterialCode(), vo.getLine());
			if (null != loadQuantity) {
				vo.setLoadQuantity(loadQuantity);
			}
			// 设置总炉数
			Integer totalCount = wipTaskRepository.findTotalCount(vo.getLine(), vo.getPartNumber());
			vo.setTotalStove(totalCount);
			// 设置生产总量
			vo.setTotalAmount(totalCount * vo.getLoadQuantity());
			// 各产线各零件每日生产的炉数
			List<Date> dates = getBetweenDates(query.getFromDate(), query.getToDate());
			for (Date date : dates) {
				Integer count = wipTaskRepository.findDaliyCount(getBeginDate(date), getEndDate(date), vo.getLine(),
						vo.getPartNumber());
				vo.getDaliy().put(getTitle(date), count);
			}
			vos.add(vo);
		}
		redisService.setQualityQueryResult(vos);
		PageResult pageResult = new PageResult();
		pageResult.setData(vos);
		return pageResult;
	}

	public String getBeginDate(Date date) {
		String prefix = DateUtils.formatDate(date, "yyyy-MM-dd");
		String[] format = new String[1];
		format[0] = "yyyy-MM-dd HH:mm:ss";
		String str = prefix + " 00:00:00";
		return str;
	}

	/**
	 * 质量结果查询
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findQuality(QualityQuery query) throws Exception {
		// 2.2. 根据输入的查询条件取得符合条件的质量结果数据

		Map<String, Object> map = new HashMap<>();
		StringBuffer columns = new StringBuffer();
		columns.append(" qqis.inspectionNo,qqis.line,qqis.loadNumber,qqis.inspectionTime,"
				+ "qqis.predictionResult,qqis.inspectionResult,"
				+ "duwpc.batchNumber,duwpc.furnaceNumber,duwpc.materialCode,"
				+ "duwpc.partNumber,duwpc.partName,duwpc.size,uehtd.lineExitDate,"
				+ "uwei.recipeNumber,qqis.predictionTime");
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(columns).append(" from ");
		StringBuffer tables = new StringBuffer();
		tables.append("PR_Quality.qa_InspectionSummary as qqis")
				.append(" inner join dbo.udtWip_ProcessCardInfo as duwpc on duwpc.processCardNumber= qqis.processCardNumber")
				.append(" inner join dbo.udtEcm_HeatTreamentTimeData as uehtd on CAST(uehtd.line as varchar)=qqis.line and CAST(uehtd.loadnumber as varchar)=qqis.loadnumber")
				.append(" inner join dbo.udtWip_ECMInteraction uwei on uwei.line=qqis.line and uwei.loadnumber=qqis.loadnumber where 1=1 ");
		sql.append(tables).append(query.toWhere());
		log.info("findQuality sql:{}", sql.toString());
		Query query2 = entityManager.createNativeQuery(sql.toString());
		List<QualityResultVO> vos = new ArrayList<>();
		List<Object[]> result = query2.getResultList();
		List<Map<String, Object>> convertResult = new ArrayList();
		for (Object[] array : result) {
			Map<String, Object> mapResult = new HashMap<>();
			mapResult.put("inspectionNo", array[0]);
			mapResult.put("line", array[1]);
			mapResult.put("loadNumber", array[2]);
			mapResult.put("inspectionTime", array[3]);
			mapResult.put("predictionResult", array[4]);
			mapResult.put("inspectionResult", array[5]);
			mapResult.put("batchNumber", array[6]);
			mapResult.put("furnaceNumber", array[7]);
			mapResult.put("materialCode", array[8]);
			mapResult.put("partNumber", array[9]);
			mapResult.put("partName", array[10]);
			mapResult.put("size", array[11]);
			mapResult.put("lineExitDate", array[12]);
			mapResult.put("recipeNumber", array[13]);
			mapResult.put("predictionTime", array[14]);
			convertResult.add(mapResult);
		}
		vos = convertQueryListToVos(convertResult);
		// 列表数据
		map.put("datas", vos);
		Map<String, Object> accuracyMap = accuracy(vos);
		map.putAll(accuracyMap);
		Map<String, Object> statisticsMap = statistics(vos);
		map.putAll(statisticsMap);
		// 各检测项目质量状况
		map.put("inspectionState", itemInspectionState(vos));
		return map;
	}

	/**
	 * 2.5. 各检测项目质量状况
	 * 
	 * @param inspections
	 * @return
	 */
	public Map<String, Map<String, Double>> itemInspectionState(List<QualityResultVO> inspections) {
		// 2.5.1. 【2.2】取得的符合条件的检测结果数据，结合质量检测结果详细表取得对应的检测结果详细数据
		Map<String, QualityResultVO> inspectionMap = new HashMap<>();
		List<String> inspectionNos = new ArrayList<>();
		for (QualityResultVO vo : inspections) {
			inspectionMap.put(vo.getInspectionNo(), vo);
			inspectionNos.add(vo.getInspectionNo());
		}
		List<InspectionDetail> list = inspectionDetailRepository.findByInspectionNoIn(inspectionNos);
		// 2.5.2. 将质量检测结果详细数据按项目编号进行分组，得到各检测项目的检测结果集合
		Map<String, List<InspectionDetail>> groupByItem = new HashMap<>();
		for (InspectionDetail detail : list) {
			if (groupByItem.containsKey(detail.getItemCode())) {
				groupByItem.get(detail.getItemCode()).add(detail);
			} else {
				List<InspectionDetail> groupList = new ArrayList<>();
				groupList.add(detail);
				groupByItem.put(detail.getItemCode(), groupList);
			}
		}
		// 2.5.3. 将各检测项目的检测结果集合按照质检日期进行分组
		Map<String, Map<String, List<InspectionDetail>>> groupByDate = new HashMap<>();
		Set<String> itemCodeKeys = groupByItem.keySet();
		for (String key : itemCodeKeys) {
			Map<String, List<InspectionDetail>> daliy = new HashMap<>();
			List<InspectionDetail> deteils = groupByItem.get(key);
			for (InspectionDetail detail : deteils) {
				QualityResultVO vo = inspectionMap.get(detail.getInspectionNo());
				Date date = vo.getInspectionTime();
				if (null == date)
					date = vo.getPredictionTime();
				String dateFormat = DateFormatUtils.format(date, "yyyy-MM-dd");
				if (daliy.containsKey(dateFormat)) {
					daliy.get(dateFormat).add(detail);
				} else {
					List<InspectionDetail> dailyLsit = new ArrayList<>();
					dailyLsit.add(detail);
					daliy.put(dateFormat, dailyLsit);
				}
			}
			groupByDate.put(key, daliy);
		}
		// 统计不合格比率
		Map<String, Map<String, Double>> resultMap = new HashMap<>();
		for (String key : itemCodeKeys) {
			Map<String, List<InspectionDetail>> map = groupByDate.get(key);
			Set<String> dailyKeys = map.keySet();
			Map<String, Double> dailyResultMap = new HashMap<>();
			for (String dailyKey : dailyKeys) {
				int top = 0;
				int bottom = 0;
				List<InspectionDetail> dailyList = map.get(dailyKey);
				for (InspectionDetail detail : dailyList) {
					if ((StringUtils.isBlank(detail.getItemInspectionResult())
							&& StringUtils.equals(detail.getItemPredictionResult(), "2"))
							|| StringUtils.equals(detail.getItemInspectionResult(), "1")) {
						top++;
					}
					if (StringUtils.equals(detail.getItemInspectionResult(), "2")
							|| (StringUtils.isBlank(detail.getItemInspectionResult())
									&& StringUtils.equals(detail.getItemPredictionResult(), "2"))) {
						bottom++;
					}
				}
				Double rate = new Double(0.0);
				if (bottom > 0 && top > 0)
					rate = new Double(top) / new Double(bottom);
				dailyResultMap.put(dailyKey, rate);
			}
			QualityItem qualityItem = qualityItemRepository.findByItemCode(key);
			resultMap.put(qualityItem.getItemName(), dailyResultMap);
		}
		return resultMap;
	}

	/**
	 * 2.3. 计算预测精度
	 * 
	 * @param vos
	 * @return
	 */
	public Map<String, Object> accuracy(List<QualityResultVO> vos) {
		Map<String, Object> map = new HashMap<>();
		int forecastAccuracyStatisticsObjectNumber = 0;
		int predictTheExactNumberOfFurnaces = 0;

		for (QualityResultVO vo : vos) {
			String inspectionResult = vo.getInspectionResult();
			String predictionResult = vo.getPredictionResult();
			// 计算预测精度
			if (StringUtils.equals(inspectionResult, "1")
					|| StringUtils.equals(inspectionResult, "2") && StringUtils.equals(predictionResult, "1")
					|| StringUtils.equals(predictionResult, "2")) {
				forecastAccuracyStatisticsObjectNumber++;
			}
			if (StringUtils.equals(predictionResult, "1") && StringUtils.equals(inspectionResult, "2")
					|| StringUtils.equals(inspectionResult, "2") && StringUtils.equals(predictionResult, "2")) {
				predictTheExactNumberOfFurnaces++;
			}
		}
		// 预测精度统计对象炉数
		map.put("forecastAccuracyStatisticsObjectNumber", forecastAccuracyStatisticsObjectNumber);
		// 预测准确炉数
		map.put("predictTheExactNumberOfFurnaces", predictTheExactNumberOfFurnaces);
		// 预测准确率
		Double rate = 0.0;
		if (predictTheExactNumberOfFurnaces > 0 && forecastAccuracyStatisticsObjectNumber > 0) {
			rate = new Double(predictTheExactNumberOfFurnaces) / new Double(forecastAccuracyStatisticsObjectNumber);
		}
		map.put("forecastAccuracy", rate);
		return map;
	}

	/**
	 * 2.4. 质量汇总信息
	 * 
	 * @param vos
	 * @return
	 */
	public Map<String, Object> statistics(List<QualityResultVO> vos) {
		Map<String, Object> result = new HashMap<>();
		Map<String, List<QualityResultVO>> groupByDate = new HashMap<>();
		int totalQualifiedNumber = 0;
		int totalDisqualifiedNumber = 0;
		for (QualityResultVO vo : vos) {
			Date date = vo.getInspectionTime();
			if (null == date) {
				date = vo.getPredictionTime();
			}
			String dailyStr = DateUtils.formatDate(date, "yyyy-MM-dd");
			if (groupByDate.containsKey(dailyStr)) {
				groupByDate.get(dailyStr).add(vo);
			} else {
				List<QualityResultVO> dailyList = new ArrayList<>();
				dailyList.add(vo);
				groupByDate.put(dailyStr, dailyList);
			}
		}
		Set<String> dailyKeys = groupByDate.keySet();
		Map<String, Double> dailyRateMap = new HashMap<>();
		for (String key : dailyKeys) {
			List<QualityResultVO> dailyList = groupByDate.get(key);
			int qualifiedNumber = 0;
			int disqualifiedNumber = 0;
			for (QualityResultVO vo : dailyList) {
				if (StringUtils.equals(vo.getInspectionResult(), "1") || (StringUtils.isBlank(vo.getInspectionResult())
						&& StringUtils.equals(vo.getPredictionResult(), "1"))) {
					qualifiedNumber++;
				}
				if (StringUtils.equals(vo.getInspectionResult(), "2") || (StringUtils.isBlank(vo.getInspectionResult())
						&& StringUtils.equals(vo.getPredictionResult(), "2"))) {
					disqualifiedNumber++;
				}
			}
			Double rate = 0.0;
			if (0 < disqualifiedNumber && 0 < qualifiedNumber) {
				rate = new Double(disqualifiedNumber) / new Double(qualifiedNumber);
			}
			dailyRateMap.put(key, rate);
		}
		// 合格数量
		result.put("qualifiedNumber", totalQualifiedNumber);
		// 不合格数量
		result.put("disqualifiedNumber", totalDisqualifiedNumber);
		// 不合格率
		result.put("dailyDisqualifiedRates", dailyRateMap);
		return result;
	}

	public List<QualityResultVO> convertQueryListToVos(List<Map<String, Object>> vos) throws Exception {
		String[] dateFormat = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };
		BeanInfo beanInfo = Introspector.getBeanInfo(QualityResultVO.class);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		List<QualityResultVO> results = new ArrayList<>();
		for (Map<String, Object> map : vos) {
			QualityResultVO vo = new QualityResultVO();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				if (propertyDescriptor.getName().toLowerCase().equals("class"))
					continue;
				if (propertyDescriptor.getName().equals("orgInspectionResult"))
					continue;
				if (propertyDescriptor.getName().equals("orgPredictionResult"))
					continue;
				Object value = map.get(propertyDescriptor.getName());
				if (propertyDescriptor.getPropertyType() == String.class) {
					if (value == null)
						propertyDescriptor.getWriteMethod().invoke(vo, "");
					else {
						propertyDescriptor.getWriteMethod().invoke(vo, value.toString());
					}
				} else {
					if (value != null)
						if (value instanceof String) {
							propertyDescriptor.getWriteMethod().invoke(vo,
									DateUtils.parseDate(value.toString(), dateFormat));
						} else
							propertyDescriptor.getWriteMethod().invoke(vo, value);
				}
			}
			results.add(vo);
		}
		return results;
	}

	public String getEndDate(Date date) {
		String prefix = DateUtils.formatDate(date, "yyyy-MM-dd");
		String[] format = new String[1];
		format[0] = "yyyy-MM-dd HH:mm:ss";
		String str = prefix + " 23:59:59";
		return str;
	}

	public String getTitle(Date date) {
		String str = DateUtils.formatDate(date, "MM月dd日");
		return str;
	}

	/**
	 * 获取两个日期之间的日期
	 * 
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return 日期集合
	 */
	private List<Date> getBetweenDates(Date start, Date end) {
		List<Date> result = new ArrayList<Date>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		/*
		 * Calendar tempEnd = Calendar.getInstance();
		 * tempStart.add(Calendar.DAY_OF_YEAR, 1); tempEnd.setTime(end); while
		 * (tempStart.before(tempEnd)) { result.add(tempStart.getTime());
		 * tempStart.add(Calendar.DAY_OF_YEAR, 1); }
		 */
		while (start.getTime() <= end.getTime()) {
			result.add(new Date(tempStart.getTime().getTime()+8*60*60*1000));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
			start = tempStart.getTime();
		}
		return result;
	}

	public List<GasUsageVO> findGasUsage(GasUsageQuery query) throws Exception {
		StringBuffer columns = new StringBuffer();
		columns.append(
				"qgu.line,qgu.loadNumber,qgu.acetyleneUsage,qgu.nitrogenUsage,uwpci.batchNumber,uwpci.materialCode,uwpci.partNumber,uwpci.partName,uehttd.lineExitDate,uwei.recipeNumber");
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		StringBuffer tables = new StringBuffer();
		tables.append(" from PR_Quality.qa_GasUsage as qgu")
				.append(" inner join dbo.udtWip_ProcessCardInfo as uwpci on uwpci.processCardNumber=qgu.processCardNumber")
				.append(" inner join dbo.udtEcm_HeatTreamentTimeData as uehttd on qgu.line=uehttd.line and uehttd.loadNumber=qgu.loadNumber")
				.append(" inner join dbo.udtWip_ECMInteraction as uwei on uwei.line=qgu.line and qgu.loadNumber=uwei.loadNumber where 1=1 ");
		sql.append(columns).append(tables).append(query.toWhere());
		Query _query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> result = _query.getResultList();
		List<GasUsageVO> vos = BeanCopyUtils.convertDataResultToVos(result, columns.toString(), GasUsageVO.class);
		return vos;
	}

}
