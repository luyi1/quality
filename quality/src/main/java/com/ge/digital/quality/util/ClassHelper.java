package com.ge.digital.quality.util;

import com.ge.digital.quality.excel.entity.AlarmBlackListExcelSupport;
import com.ge.digital.quality.excel.entity.AlarmMessageExcelSupport;
import com.ge.digital.quality.excel.entity.ProcessParameterThresholdExcelSupport;
import com.ge.digital.quality.excel.entity.ProcessTimeThresholdExcelSupport;
import com.ge.digital.quality.excel.entity.QualityItemExcelSupport;
import com.ge.digital.quality.excel.entity.QualitySpecificationExcelSupport;
import com.ge.digital.schedule.excelutil.MasterDataType;

public class ClassHelper {
	public static Class getClassFromType(String type) {
		if (type.equals(MasterDataType.AlarmMessage.getValue())) {
			return AlarmMessageExcelSupport.class;
		}else if(type.equals(MasterDataType.AlarmBlackList.getValue()))
		{
			return AlarmBlackListExcelSupport.class;
		}else if(type.equals(MasterDataType.ProcessTimeThreshold.getValue()))
		{
			return ProcessTimeThresholdExcelSupport.class;
		}else if(type.equals(MasterDataType.ProcessParamThreshold.getValue()))
		{
			return ProcessParameterThresholdExcelSupport.class;
		}else if(type.equals(MasterDataType.QualityItem.getValue()))
		{
			return QualityItemExcelSupport.class;
		}else if(type.equals(MasterDataType.QualitySpecification.getValue()))
		{
			return QualitySpecificationExcelSupport.class;
		}
			
		return null;
	}
}
