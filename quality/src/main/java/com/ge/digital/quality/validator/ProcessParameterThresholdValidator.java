package com.ge.digital.quality.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.digital.gearbox.common.message.I18NHelper;
import com.ge.digital.gearbox.common.response.RestResponseCode;
import com.ge.digital.quality.entity.ProcessParamThreshold;
import com.ge.digital.quality.excel.entity.ProcessParameterThresholdExcelSupport;
import com.ge.digital.quality.excel.entity.ProcessTimeThresholdExcelSupport;
import com.ge.digital.quality.mapper.ProcessParamThresholdRepository;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;
import com.ge.digital.schedule.excelutil.MasterDataValidatorI;
import com.ge.digital.schedule.mapper.LineRepository;

@Component
public class ProcessParameterThresholdValidator implements MasterDataValidatorI<ProcessParameterThresholdExcelSupport> {
	@Autowired
	ProcessParamThresholdRepository processParameterThresholdRepository;
	@Autowired
	LineRepository lineRepository;
	@Override
	public List<ProcessParameterThresholdExcelSupport> validateUpload(List<? extends ExcelUploadSupport> list) {
		Map<String, Integer> keyMap = new HashMap<>();
		for (ExcelUploadSupport line : list) {
			String lineKey = line.getCombinedKey();
			if (!keyMap.containsKey(lineKey)) {
				keyMap.put(lineKey, 1);
			} else {
				keyMap.put(lineKey, 2);
			}
		}
		for (Object object : list) {
			ExcelUploadSupport excelObj = (ExcelUploadSupport) object;
			ProcessParameterThresholdExcelSupport am = (ProcessParameterThresholdExcelSupport) excelObj;
			String mdJobKey = excelObj.getCombinedKey();
			Integer keyCount = keyMap.get(mdJobKey);
			if (keyCount != null && keyCount > 1) {
				excelObj.addError(I18NHelper.getI18NErrorMsg(RestResponseCode.UPLOAD_RECORD_ALREADY_EXIST));
			}

			List<ProcessParamThreshold> processTimeThresholds =
					processParameterThresholdRepository.findByPartNumberAndLineAndItemAndSequenceNo(am.getPartNumber(),am.getLine(),am.getItem(),am.getSequenceNo());
			if (!processTimeThresholds.isEmpty()) {
				excelObj.setId(processTimeThresholds.get(0).getId());
			}
		}
		return (List<ProcessParameterThresholdExcelSupport>) list;
	}

	@Override
	public List<ProcessParameterThresholdExcelSupport> validateUploadDelete(List<? extends ExcelUploadSupport> list) {
		List<ProcessParameterThresholdExcelSupport> deletelist = new ArrayList<>();

		for (Object object : list) {
			ProcessParameterThresholdExcelSupport am = (ProcessParameterThresholdExcelSupport) object;
			List<ProcessParamThreshold> processTimeThresholds =
					processParameterThresholdRepository.findByPartNumberAndLineAndItemAndSequenceNo(am.getPartNumber(),am.getLine(),am.getItem(),am.getSequenceNo());
			if (!processTimeThresholds.isEmpty()) {
				am.setId(processTimeThresholds.get(0).getId());
				deletelist.add(am);

			}
		}
		return deletelist;
	}
}
