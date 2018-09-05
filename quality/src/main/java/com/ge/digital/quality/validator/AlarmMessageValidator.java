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
import com.ge.digital.quality.excel.entity.AlarmMessageExcelSupport;
import com.ge.digital.quality.mapper.AlarmMessageRepository;
import com.ge.digital.schedule.entity.Line;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;
import com.ge.digital.schedule.excelutil.MasterDataValidatorI;
import com.ge.digital.schedule.mapper.LineRepository;

@Component
public class AlarmMessageValidator implements MasterDataValidatorI<AlarmMessageExcelSupport> {
	@Autowired
	AlarmMessageRepository alarmMessageRepository;
	@Autowired
	LineRepository lineRepository;
	@Override
	public List<AlarmMessageExcelSupport> validateUpload(List<? extends ExcelUploadSupport> list) {
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
			AlarmMessageExcelSupport am = (AlarmMessageExcelSupport) excelObj;
			String mdJobKey = excelObj.getCombinedKey();
			Integer keyCount = keyMap.get(mdJobKey);
			if (keyCount != null && keyCount > 1) {
				excelObj.addError(I18NHelper.getI18NErrorMsg(RestResponseCode.UPLOAD_RECORD_ALREADY_EXIST));
			}
			List<Line> lines = lineRepository.findByLine(am.getLine());
			Long lineId = -1l;
			if (!lines.isEmpty()) {
				lineId = lines.get(0).getId();
			} else {
				excelObj.addError(I18NHelper.getI18NErrorMsg(RestResponseCode.LINE_NOT_EXIST));
			}
			List<AlarmMessage> alarmMessage =
					 alarmMessageRepository.findByLineAndAlarmCode(am.getLine(),am.getAlarmCode());
			if (!alarmMessage.isEmpty()) {
				excelObj.setId(alarmMessage.get(0).getId());
			}
		}
		return (List<AlarmMessageExcelSupport>) list;
	}

	@Override
	public List<AlarmMessageExcelSupport> validateUploadDelete(List<? extends ExcelUploadSupport> list) {
		List<AlarmMessageExcelSupport> deletelist = new ArrayList<>();

		for (Object object : list) {
			AlarmMessageExcelSupport am = (AlarmMessageExcelSupport) object;
			List<AlarmMessage> alarmMessage =
					 alarmMessageRepository.findByLineAndAlarmCode(am.getLine(),am.getAlarmCode());
			
			if (!alarmMessage.isEmpty()) {
				am.setId(alarmMessage.get(0).getId());
				deletelist.add(am);

			}
		}
		return deletelist;
	}
}
