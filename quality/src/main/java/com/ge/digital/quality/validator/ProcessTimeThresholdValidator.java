package com.ge.digital.quality.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.digital.gearbox.common.message.I18NHelper;
import com.ge.digital.gearbox.common.response.RestResponseCode;
import com.ge.digital.quality.entity.ProcessTimeThreshold;
import com.ge.digital.quality.excel.entity.ProcessTimeThresholdExcelSupport;
import com.ge.digital.quality.mapper.ProcessTimeThresholdRepository;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;
import com.ge.digital.schedule.excelutil.MasterDataValidatorI;
import com.ge.digital.schedule.mapper.LineRepository;

@Component
public class ProcessTimeThresholdValidator implements MasterDataValidatorI<ProcessTimeThresholdExcelSupport> {
	@Autowired
	ProcessTimeThresholdRepository processTimeThresholdRepository;
	@Autowired
	LineRepository lineRepository;
	@Override
	public List<ProcessTimeThresholdExcelSupport> validateUpload(List<? extends ExcelUploadSupport> list) {
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
			ProcessTimeThresholdExcelSupport am = (ProcessTimeThresholdExcelSupport) excelObj;
			String mdJobKey = excelObj.getCombinedKey();
			Integer keyCount = keyMap.get(mdJobKey);
			if (keyCount != null && keyCount > 1) {
				excelObj.addError(I18NHelper.getI18NErrorMsg(RestResponseCode.UPLOAD_RECORD_ALREADY_EXIST));
			}

			List<ProcessTimeThreshold> processTimeThresholds =
					processTimeThresholdRepository.findByPartNumber(excelObj.getCombinedKey());
			if (!processTimeThresholds.isEmpty()) {
				excelObj.setId(processTimeThresholds.get(0).getId());
			}
		}
		return (List<ProcessTimeThresholdExcelSupport>) list;
	}

	@Override
	public List<ProcessTimeThresholdExcelSupport> validateUploadDelete(List<? extends ExcelUploadSupport> list) {
		List<ProcessTimeThresholdExcelSupport> deletelist = new ArrayList<>();

		for (Object object : list) {
			ProcessTimeThresholdExcelSupport am = (ProcessTimeThresholdExcelSupport) object;
			List<ProcessTimeThreshold> processTimeThresholds =
					processTimeThresholdRepository.findByPartNumber(am.getCombinedKey());
			
			if (!processTimeThresholds.isEmpty()) {
				am.setId(processTimeThresholds.get(0).getId());
				deletelist.add(am);

			}
		}
		return deletelist;
	}
}
