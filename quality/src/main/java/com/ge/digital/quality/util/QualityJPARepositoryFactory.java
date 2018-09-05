package com.ge.digital.quality.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ge.digital.quality.mapper.AlarmBlackListRepository;
import com.ge.digital.quality.mapper.AlarmMessageRepository;
import com.ge.digital.quality.mapper.ProcessParamThresholdRepository;
import com.ge.digital.quality.mapper.ProcessTimeThresholdRepository;
import com.ge.digital.quality.mapper.QualityItemRepository;
import com.ge.digital.quality.mapper.QualitySpecificationRepository;
import com.ge.digital.schedule.excelutil.MasterDataType;

@Component
public class QualityJPARepositoryFactory {
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
	public JpaRepository getRepository(String type) {
		if (type.equals(MasterDataType.AlarmMessage.getValue())) {
			return alarmMessageRepository;
		} else if(type.equals(MasterDataType.AlarmBlackList.getValue()))
		{
			return alarmBlackListRepository;
		}else if(type.equals(MasterDataType.ProcessTimeThreshold.getValue()))
		{
			return processTimeThresholdRepository;
		}else if(type.equals(MasterDataType.ProcessParamThreshold.getValue()))
		{
			return processParamThresholdRepository;
		}else if(type.equals(MasterDataType.QualityItem.getValue()))
		{
			return qualityItemRepository;
		}else if(type.equals(MasterDataType.QualitySpecification.getValue()))
		{
			return qualitySpecificationRepository;
		}
		return null;
	}
}
