package com.ge.digital.quality.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.digital.quality.excel.entity.AlarmBlackListExcelSupport;
import com.ge.digital.quality.excel.entity.AlarmMessageExcelSupport;
import com.ge.digital.quality.excel.entity.ProcessParameterThresholdExcelSupport;
import com.ge.digital.quality.excel.entity.ProcessTimeThresholdExcelSupport;
import com.ge.digital.quality.excel.entity.QualityItemExcelSupport;
import com.ge.digital.quality.excel.entity.QualitySpecificationExcelSupport;
import com.ge.digital.schedule.excelutil.MasterDataValidatorI;

@Component
public class MdQualityValidatorFactory {
	@Autowired
	AlarmMessageValidator alarmMessageValidator;
	@Autowired
	AlarmBlackListValidator alarmBlackListValidator;
	@Autowired
	ProcessTimeThresholdValidator processTimeThresholdValidator;
	@Autowired
	ProcessParameterThresholdValidator processParameterThresholdValidator;
	@Autowired
	QualityItemValidator qualityItemValidator;
	@Autowired
	QualitySpecificationValidator qualitySpecificationValidator;
	public MasterDataValidatorI getValidator(Class clazz) {
		if (clazz.equals(AlarmMessageExcelSupport.class)) {
			return alarmMessageValidator;
		}else if(clazz.equals(AlarmBlackListExcelSupport.class))
		{
			return alarmBlackListValidator;
		}else if(clazz.equals(ProcessTimeThresholdExcelSupport.class))
		{
			return processTimeThresholdValidator;
		}else if(clazz.equals(ProcessParameterThresholdExcelSupport.class))
		{
			return processParameterThresholdValidator;
		}else if(clazz.equals(QualityItemExcelSupport.class))
		{
			return qualityItemValidator;
		}else if(clazz.equals(QualitySpecificationExcelSupport.class))
		{
			return qualitySpecificationValidator;
		}
		
		return alarmMessageValidator;
	}
}
