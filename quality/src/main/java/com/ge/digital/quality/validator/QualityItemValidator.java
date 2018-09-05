package com.ge.digital.quality.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.digital.gearbox.common.message.I18NHelper;
import com.ge.digital.gearbox.common.response.RestResponseCode;
import com.ge.digital.quality.entity.AlarmMessage;
import com.ge.digital.quality.entity.QualityItem;
import com.ge.digital.quality.excel.entity.QualityItemExcelSupport;
import com.ge.digital.quality.mapper.QualityItemRepository;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;
import com.ge.digital.schedule.excelutil.MasterDataValidatorI;
import com.ge.digital.schedule.mapper.LineRepository;

@Component
public class QualityItemValidator implements MasterDataValidatorI<QualityItemExcelSupport> {
	@Autowired
	QualityItemRepository qualityItemRepository;
	@Autowired
	LineRepository lineRepository;
	@Override
	public List<QualityItemExcelSupport> validateUpload(List<? extends ExcelUploadSupport> list) {
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
			QualityItemExcelSupport am = (QualityItemExcelSupport) excelObj;
			String mdJobKey = excelObj.getCombinedKey();
			Integer keyCount = keyMap.get(mdJobKey);
			if (keyCount != null && keyCount > 1) {
				excelObj.addError(I18NHelper.getI18NErrorMsg(RestResponseCode.UPLOAD_RECORD_ALREADY_EXIST));
			}
			
			QualityItem qualityItem =
					qualityItemRepository.findByItemCode(am.getItemCode());
			if (qualityItem!=null) {
				excelObj.setId(qualityItem.getId());
			}
		}
		return (List<QualityItemExcelSupport>) list;
	}

	@Override
	public List<QualityItemExcelSupport> validateUploadDelete(List<? extends ExcelUploadSupport> list) {
		List<QualityItemExcelSupport> deletelist = new ArrayList<>();

		for (Object object : list) {
			QualityItemExcelSupport am = (QualityItemExcelSupport) object;
			QualityItem qualityItem =
					qualityItemRepository.findByItemCode(am.getItemCode());
			if (qualityItem!=null) {
				am.setId(qualityItem.getId());
				deletelist.add(am);
			}

		}
		return deletelist;
	}
}
