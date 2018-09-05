package com.ge.digital.quality.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ge.digital.gearbox.common.autoIncrKey.BizAutoIncrKey;
import com.ge.digital.gearbox.common.autoIncrKey.BizAutoIncrService;
import com.ge.digital.quality.entity.AlarmBlackList;
import com.ge.digital.quality.entity.AlarmMessage;
import com.ge.digital.quality.entity.ProcessParamThreshold;
import com.ge.digital.quality.entity.ProcessTimeThreshold;
import com.ge.digital.quality.entity.QualityItem;
import com.ge.digital.quality.entity.QualitySpecification;
import com.ge.digital.quality.excel.entity.AlarmBlackListExcelSupport;
import com.ge.digital.quality.excel.entity.AlarmMessageExcelSupport;
import com.ge.digital.quality.mapper.AlarmBlackListRepository;
import com.ge.digital.quality.mapper.AlarmMessageRepository;
import com.ge.digital.quality.mapper.ProcessParamThresholdRepository;
import com.ge.digital.quality.mapper.ProcessTimeThresholdRepository;
import com.ge.digital.quality.mapper.QualityItemRepository;
import com.ge.digital.quality.mapper.QualitySpecificationRepository;
import com.ge.digital.quality.util.ClassHelper;
import com.ge.digital.quality.util.QualityJPARepositoryFactory;
import com.ge.digital.quality.validator.MdQualityValidatorFactory;
import com.ge.digital.schedule.entity.ModelBase;
import com.ge.digital.schedule.excelutil.ExcelHelper;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;
import com.ge.digital.schedule.excelutil.MasterDataType;

@Component
public class UploadingService {
	Map<Long, List<? extends ModelBase>> allMap = new HashMap<>();
	Map<Long, List<? extends ExcelUploadSupport>> allMapc = new HashMap<>();
	@Autowired
	BizAutoIncrService bizAutoIncrService;
	@Autowired
	QualityJPARepositoryFactory JPARepositoryFactory;
	@Autowired
	MdQualityValidatorFactory mdValidatorFactory;

	@Autowired
	AlarmMessageRepository alarmMessageRepository;
	@Autowired
	AlarmBlackListRepository alarmBlackListRepository;
	@Autowired
	ProcessTimeThresholdRepository processTimeThresholdRepository;
	@Autowired
	ProcessParamThresholdRepository processParamThresholdRepository;
	@Autowired
	QualityItemRepository qualityItemRepository;
	@Autowired
	QualitySpecificationRepository qualitySpecificationRepository;

	


	@Transactional
	public List<? extends ExcelUploadSupport> excel2Object(File file, Class<? extends ExcelUploadSupport> clazz)
			throws IOException {
		// TODO Auto-generated method stub
		List<? extends ExcelUploadSupport> objects = new ExcelHelper().readExcel2Obj(file, clazz, 1, 0, false);
		objects = mdValidatorFactory.getValidator(clazz).validateUpload(objects);
		return objects;
	}

	@Transactional
	public List<? extends ExcelUploadSupport> excel2Object(MultipartFile ofile,
			Class<? extends ExcelUploadSupport> clazz) throws IOException {
		Date newDate = new Date();
		File file = File.createTempFile(newDate.getTime() + "_uploadoder", ".tmp");
		ofile.transferTo(file);
		List<? extends ExcelUploadSupport> objects = new ExcelHelper().readExcel2Obj(file, clazz, 1, 0, false);
		objects = mdValidatorFactory.getValidator(clazz).validateUpload(objects);
		Long batch_upload_id = new Long(bizAutoIncrService.nextSerial(BizAutoIncrKey.BATCH_UPDATE_ID.getValue()));
		allMapc.put(batch_upload_id, objects);
		if (!objects.isEmpty()) {
			objects.get(0).setBatchUploadID(batch_upload_id);
		}
		return objects;
	}

	/**
	 * @param batchUploadId
	 * @param isDelete
	 */
	@Transactional
	public void saveObject(Long batchUploadId, boolean isDelete, String type) {
		List<? extends ExcelUploadSupport> objects = (List<ExcelUploadSupport>) allMapc.get(batchUploadId);
		for (ExcelUploadSupport object : objects) {
			// convertObject(object,type);
			ModelBase target = convertObject(type, object);
			if (!StringUtils.isEmpty(object.getId())) {
				if (!isDelete) {
					updateObject(type, target);
				} else {
					JPARepositoryFactory.getRepository(type).delete(target.getId());
				}
			} else {
				updateObject(type, target);
			}
		}
		allMapc.remove(batchUploadId);
	}
	
	@Transactional
	private void updateObject(String type, ModelBase target) {
		JPARepositoryFactory.getRepository(type).save(target);
	}

	private ModelBase convertObject(String type, ExcelUploadSupport object) {

		ModelBase target = null;



		if(type.equals(MasterDataType.AlarmMessage.getValue()))
		{
			 target=new AlarmMessage();
		}else if(type.equals(MasterDataType.AlarmBlackList.getValue()))
		{
			 target=new AlarmBlackList();
		}else if(type.equals(MasterDataType.ProcessTimeThreshold.getValue()))
		{
			 target=new ProcessTimeThreshold();
		}else if(type.equals(MasterDataType.ProcessParamThreshold.getValue()))
		{
			 target=new ProcessParamThreshold();
		}else if(type.equals(MasterDataType.QualityItem.getValue()))
		{
			 target=new QualityItem();
		}else if(type.equals(MasterDataType.QualitySpecification.getValue()))
		{
			 target=new QualitySpecification();

		}
		BeanUtils.copyProperties(object, target);

		return target;
	}

	@Transactional
	public File objects2excel(String type) {
		List objects = JPARepositoryFactory.getRepository(type).findAll();
		return new ExcelHelper().export2Obj(ClassHelper.getClassFromType(type), objects, false).getAbsoluteFile();
	}

	public List findAll(String type) {
		return JPARepositoryFactory.getRepository(type).findAll();
	}

	@Transactional
	public List<? extends ExcelUploadSupport> excel2ObjectDelete(MultipartFile ofile,
			Class<? extends ExcelUploadSupport> clazz) throws IOException {

		File file = File.createTempFile(String.valueOf(new Date().getTime()) + "_uploadoder", ".tmp");
		ofile.transferTo(file);
		List<? extends ExcelUploadSupport> objects = new ExcelHelper().readExcel2Obj(file, clazz, 1, 0, false);
		objects = mdValidatorFactory.getValidator(clazz).validateUploadDelete(objects);
		Long batch_upload_id = new Long(bizAutoIncrService.nextSerial(BizAutoIncrKey.BATCH_UPDATE_ID.getValue()));
		allMapc.put(batch_upload_id, objects);
		if (!objects.isEmpty()) {
			objects.get(0).setBatchUploadID(batch_upload_id);
		}
		return objects;
	}

	public List<Date> findLastUpdateOn() {

		Date last_update_on1 = alarmMessageRepository.findLastestUpdate();
		Date last_update_on2 = alarmBlackListRepository.findLastestUpdate();
		Date last_update_on3 = processParamThresholdRepository.findLastestUpdate();
		Date last_update_on4 = qualityItemRepository.findLastestUpdate();
		Date last_update_on5 = qualitySpecificationRepository.findLastestUpdate();
		Date last_update_on6 = processTimeThresholdRepository.findLastestUpdate();
		List<Date> datelist = new ArrayList();
		datelist.add(last_update_on1);
		datelist.add(last_update_on2);
		datelist.add(last_update_on3);
		datelist.add(last_update_on4);
		datelist.add(last_update_on5);
		datelist.add(last_update_on6);

		return datelist;
	}
}
