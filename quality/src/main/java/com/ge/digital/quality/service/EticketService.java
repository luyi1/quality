package com.ge.digital.quality.service;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.json.JSONException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.digital.quality.entity.InspectionDetail;
import com.ge.digital.quality.entity.InspectionSummary;
import com.ge.digital.quality.entity.ProcessParamActual;
import com.ge.digital.quality.entity.QualityItem;
import com.ge.digital.quality.entity.WipECMInteraction;
import com.ge.digital.quality.entity.WipHeatTreamentdatetimeData;
import com.ge.digital.quality.entity.WipProcessCardInfo;
import com.ge.digital.quality.mapper.AlarmMessageRepository;
import com.ge.digital.quality.mapper.AlarmRecordRepository;
import com.ge.digital.quality.mapper.CustomJpaRepository;
import com.ge.digital.quality.mapper.InspectionDetailRepository;
import com.ge.digital.quality.mapper.InspectionSummaryRepository;
import com.ge.digital.quality.mapper.ProcessParamActualRepository;
import com.ge.digital.quality.mapper.QualityItemRepository;
import com.ge.digital.quality.mapper.WipECMInteractionRepository;
import com.ge.digital.quality.mapper.WipHeatTreamentdatetimeDataRepository;
import com.ge.digital.quality.mapper.WipProcessCardInfoRepository;
import com.ge.digital.quality.mapper.WipTaskRepository;
import com.ge.digital.quality.query.PageResult;
import com.ge.digital.quality.query.QualityQuery;
import com.ge.digital.quality.rest.RestIntegrationService;
import com.ge.digital.quality.vo.AlarmVO;
import com.ge.digital.quality.vo.Eticket;
import com.ge.digital.quality.vo.EticketConfirmVO;
import com.ge.digital.quality.vo.InspectionDetailVO;
import com.ge.digital.quality.vo.WipHeatTreamentdatetimeDataVO;

@Service
public class EticketService {

	@Autowired
	InspectionSummaryRepository inspectionSummaryRepository;

	@Autowired
	ProcessParamActualRepository processParamActualRepository;

	@Autowired
	InspectionDetailRepository inspectionDetailRepository;

	@Autowired
	AlarmRecordRepository alarmRecordRepository;

	@Autowired
	AlarmMessageRepository alarmMessageRepository;

	@Autowired
	RestIntegrationService restIntegrationService;

	@Autowired
	QualityItemRepository qualityItemRepository;

	@Autowired
	WipProcessCardInfoRepository wipProcessCardInfoRepository;

	@Autowired
	WipTaskRepository wipTaskRepository;

	@Autowired
	WipECMInteractionRepository wipECMInteractionRepository;

	@Autowired
	WipHeatTreamentdatetimeDataRepository wipHeatTreamentdatetimeDataRepository;

	@Autowired
	RedisService redisService;

	@Autowired
	CustomJpaRepository customJpaRepository;

	/**
	 * 
	 * 
	 * 
	 * @param inspectionNo
	 *            质检单号
	 * @return
	 * @throws JSONException
	 */
	public Map<String, Object> findeTicket(String inspectionNo,String sessionId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		// SAGW特殊工序监控卡
		Eticket eticket = new Eticket();
		// 从【质量检测结果概要】表查询检测结果总体信息
		// 质量检测结果概要表：质检单号,产线号,热处理炉号,预测时间,预测结果,检测时间,检测结果,检测人,点检人,点检时间,点检备注
		InspectionSummary inspectionSummary = inspectionSummaryRepository.findByInspectionNo(inspectionNo);
		if (null == inspectionSummary) {
			throw new IllegalIdentifierException("未找到对应质检单");
		}
		if (StringUtils.isNotBlank(inspectionSummary.getConfirmStaff())) {
			map.put("confirmStaff", inspectionSummary.getConfirmStaff());
		} else {
			map.put("confirmStaff", redisService.getUsername(sessionId));
		}
		if (null != inspectionSummary.getConfirmTime()) {
			map.put("confirmTime", inspectionSummary.getConfirmTime());
		} else {
			map.put("confirmTime", null);
		}
		if (StringUtils.isNotBlank(inspectionSummary.getConfirmRemarks())) {
			map.put("confirmRemarks", inspectionSummary.getConfirmRemarks());
		} else {
			map.put("confirmRemarks", "");
		}
		BeanUtils.copyProperties(inspectionSummary, eticket);
		// 流转卡信息表：来料批次号，正火炉号，物料编码，零件图号，规格
		WipProcessCardInfo wipProcessCardInfo = wipProcessCardInfoRepository
				.findByProcessCardNumber(inspectionSummary.getProcessCardNumber());
		if (null != wipProcessCardInfo) {
			eticket.setWipBatchNumber(wipProcessCardInfo.getBatchNumber() == null ? null
					: wipProcessCardInfo.getBatchNumber().toString());
			eticket.setWipFurnaceNumber(wipProcessCardInfo.getFurnaceNumber());
			eticket.setWipMaterielNumber(wipProcessCardInfo.getMaterialCode());
			eticket.setWipSize(wipProcessCardInfo.getSize());
			eticket.setWipPartNumber(wipProcessCardInfo.getPartNumber());
		}
		// ECM交互表.产线号 = 质量检测结果概要表.产线号 AND ECM交互表.ECM生产炉号 = 质量检测结果概要表.热处理炉号
		WipECMInteraction wipECMInteraction = wipECMInteractionRepository.findByLineAndLoadNumber(eticket.getLine(),
				eticket.getLoadNumber());
		if (null != wipECMInteraction)
			eticket.setRecipeNumber(wipECMInteraction.getRecipeNumber());
		map.put("inspectionSummary", eticket);
		// 从【热处理工艺过程】表查询工序信息
		WipHeatTreamentdatetimeData wipHeatTreamentdatetimeData = wipHeatTreamentdatetimeDataRepository
				.findByLoadnumberAndLine(eticket.getLoadNumber(), eticket.getLine());
		if (null != wipHeatTreamentdatetimeData)
			eticket.setLineExitTime(wipHeatTreamentdatetimeData.getLineexitdate());
		map.put("productionProc", convertToVO(wipHeatTreamentdatetimeData));
		// 从【ECM工艺参数值】表查询工艺参数信息
		List<ProcessParamActual> processParamActuals = processParamActualRepository
				.findByLineAndLoadNumber(eticket.getLine(), eticket.getLoadNumber());
		map.put("processParamActual", processParamActuals);
		// 1.4. 从【质量检测结果详细】表查询金相检测详细信息
		List<InspectionDetailVO> vos = new ArrayList<>();
		List<InspectionDetail> inspectionDetails = inspectionDetailRepository
				.findByInspectionNo(eticket.getInspectionNo());
		for (InspectionDetail inspectionDetail : inspectionDetails) {
			InspectionDetailVO vo = new InspectionDetailVO();
			QualityItem qualityItem = qualityItemRepository.findByItemCode(inspectionDetail.getItemCode());
			vo.setName(qualityItem.getItemName());
			vo.setItemPredictionResult(inspectionDetail.getItemPredictionResult());
			vo.setItemInspectionResult(inspectionDetail.getItemInspectionResult());
			vos.add(vo);
		}
		map.put("inspectionDetails", vos);
		// 1.5. 从【ECM报警记录】表查询金相检测详细信息
		List<AlarmVO> list = alarmRecordRepository.findMechanicalPhase(eticket.getLine(), eticket.getLoadNumber());
		map.put("alarms", list);
		return map;
	}

	public WipHeatTreamentdatetimeDataVO convertToVO(WipHeatTreamentdatetimeData data) throws Exception {

		String[] dateformats = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-d HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-d" };
		BeanInfo sourceBeanInfo = Introspector.getBeanInfo(WipHeatTreamentdatetimeData.class);
		Map<String, Object> sourceValues = new HashMap<>();
		PropertyDescriptor[] sourcepd = sourceBeanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : sourcepd) {
			if (propertyDescriptor.getName().toLowerCase().equals("delflag"))
				continue;
			Object object = propertyDescriptor.getReadMethod().invoke(data);
			sourceValues.put(propertyDescriptor.getName(), object);
		}
		WipHeatTreamentdatetimeDataVO vo = new WipHeatTreamentdatetimeDataVO();
		BeanInfo beanInfo = Introspector.getBeanInfo(WipHeatTreamentdatetimeDataVO.class);
		PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : pd) {
			if (propertyDescriptor.getName().equals("class"))
				continue;
			if (propertyDescriptor.getPropertyType() == Date.class) {
				Object obj = sourceValues.get(propertyDescriptor.getName().toLowerCase());
				String dateValue = "";
				if (obj != null)
					dateValue = obj.toString();
				propertyDescriptor.getWriteMethod().invoke(vo, DateUtils.parseDate(dateValue, dateformats));
			} else {
				propertyDescriptor.getWriteMethod().invoke(vo,
						sourceValues.get(propertyDescriptor.getName().toLowerCase()));
			}
		}
		return vo;
	}

	public Map<String, Object> findQuality(QualityQuery query) throws Exception {
		return customJpaRepository.findQuality(query);
	}

	public InspectionSummary findByInspectionNo(String inspectionNo) {
		return inspectionSummaryRepository.findByInspectionNo(inspectionNo);
	}

	public Date confirm(EticketConfirmVO vo) {
		Date date = new Date();
		InspectionSummary inspectionSummary = inspectionSummaryRepository.findByInspectionNo(vo.getInspectionNo());
		inspectionSummary.setConfirmStaff(vo.getUsername());
		inspectionSummary.setConfirmTime(date);
		inspectionSummary.setConfirmRemarks(vo.getContent());
		inspectionSummaryRepository.save(inspectionSummary);
		return date;
	}

}
