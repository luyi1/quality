package com.ge.digital.quality.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.digital.gearbox.common.message.I18NHelper;
import com.ge.digital.gearbox.common.response.RestResponseCode;
import com.ge.digital.quality.entity.QualityItem;
import com.ge.digital.quality.entity.QualitySpecification;
import com.ge.digital.quality.excel.entity.QualityItemExcelSupport;
import com.ge.digital.quality.excel.entity.QualitySpecificationExcelSupport;
import com.ge.digital.quality.mapper.QualitySpecificationRepository;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;
import com.ge.digital.schedule.excelutil.MasterDataValidatorI;
import com.ge.digital.schedule.mapper.LineRepository;

@Component
public class QualitySpecificationValidator implements MasterDataValidatorI<QualitySpecificationExcelSupport> {
	@Autowired
	QualitySpecificationRepository qualitySpecificationRepository;
	@Autowired
	LineRepository lineRepository;
	@Override
	public List<QualitySpecificationExcelSupport> validateUpload(List<? extends ExcelUploadSupport> list) {
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
			QualitySpecificationExcelSupport am = (QualitySpecificationExcelSupport) excelObj;
			String mdJobKey = excelObj.getCombinedKey();
			Integer keyCount = keyMap.get(mdJobKey);
			if (keyCount != null && keyCount > 1) {
				excelObj.addError(I18NHelper.getI18NErrorMsg(RestResponseCode.UPLOAD_RECORD_ALREADY_EXIST));
			}
			
			List<QualitySpecification> qualityItem =
					qualitySpecificationRepository.findByPartNumberAndItemCode(am.getPartNumber(),am.getItemCode());
			if (!qualityItem.isEmpty()) {
				excelObj.setId(qualityItem.get(0).getId());
			}
		}
		return (List<QualitySpecificationExcelSupport>) list;
	}

	@Override
	public List<QualitySpecificationExcelSupport> validateUploadDelete(List<? extends ExcelUploadSupport> list) {
		List<QualitySpecificationExcelSupport> deletelist = new ArrayList<>();

		for (Object object : list) {
			QualitySpecificationExcelSupport am = (QualitySpecificationExcelSupport) object;
			List<QualitySpecification> qualitySpec =
					qualitySpecificationRepository.findByPartNumberAndItemCode(am.getPartNumber(),am.getItemCode());
			
			if (!qualitySpec.isEmpty()) {
				am.setId(qualitySpec.get(0).getId());
				deletelist.add(am);
			}

		}
		return deletelist;
	}
}
