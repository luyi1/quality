package com.ge.digital.quality;

import java.util.Date;
import java.util.List;

import org.apache.http.client.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ge.digital.quality.entity.WipProcessCardInfo;
import com.ge.digital.quality.mapper.AlarmBlackListRepository;
import com.ge.digital.quality.mapper.AlarmMessageRepository;
import com.ge.digital.quality.mapper.AlarmRecordRepository;
import com.ge.digital.quality.mapper.CustomJpaRepository;
import com.ge.digital.quality.mapper.InspectionDetailRepository;
import com.ge.digital.quality.mapper.InspectionSummaryRepository;
import com.ge.digital.quality.mapper.WipProcessCardInfoRepository;
import com.ge.digital.quality.query.PageResult;
import com.ge.digital.quality.query.StoveStatisticsQuery;
import com.ge.digital.quality.mapper.ProcessParamActualRepository;
import com.ge.digital.quality.mapper.ProcessParamThresholdRepository;
import com.ge.digital.quality.mapper.ProcessTimeThresholdRepository;
import com.ge.digital.quality.mapper.QualityItemRepository;
import com.ge.digital.quality.mapper.QualitySpecificationRepository;
import com.ge.digital.quality.service.EticketService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QualityApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(QualityApplicationTests.class);

	@Autowired
	AlarmBlackListRepository alarmBlackListRepository;
	@Autowired
	AlarmMessageRepository alarmMessageRepository;
	@Autowired
	AlarmRecordRepository alarmRecordRepository;
	@Autowired
	InspectionDetailRepository inspectionDetailRepository;
	@Autowired
	InspectionSummaryRepository inspectionSummaryRepository;
	@Autowired
	ProcessParamActualRepository processParamActualRepository;
	@Autowired
	ProcessParamThresholdRepository processParamThresholdRepository;
	@Autowired
	ProcessTimeThresholdRepository processTimeThresholdRepository;
	@Autowired
	QualityItemRepository qualityItemRepository;
	@Autowired
	QualitySpecificationRepository qualitySpecificationRepository;
	@Autowired
	EticketService etickerService;
	@Autowired
	WipProcessCardInfoRepository processCardInfoRepository;
	@Autowired
	Gson gson;
	@Autowired
	CustomJpaRepository customJpaRepository;

	@Test
	public void contextLoads() throws Exception {
		StoveStatisticsQuery query = new StoveStatisticsQuery();
		Date[] range = new Date[2];
		String[] formats = new String[1];
		String format = "yyyy-MM-dd HH:mm:ss";
		formats[0] = format;
		String beginDate = "2018-08-15 00:00:00";
		String endDate = "2018-08-30 00:00:00";
		range[0] = DateUtils.parseDate(beginDate, formats);
		range[1] = DateUtils.parseDate(endDate, formats);
		PageResult result = customJpaRepository.findStoveStatistics(query);
		log.info("result:{}", gson.toJson(result));
	}

}
