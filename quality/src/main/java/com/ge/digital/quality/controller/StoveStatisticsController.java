package com.ge.digital.quality.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ge.digital.gearbox.common.response.BizErrorResponse;
import com.ge.digital.gearbox.common.response.NormalResponse;
import com.ge.digital.gearbox.common.response.RestResponseCode;
import com.ge.digital.quality.query.PageResult;
import com.ge.digital.quality.query.StoveStatisticsQuery;
import com.ge.digital.quality.service.RedisService;
import com.ge.digital.quality.service.StoveStatisticsService;
import com.ge.digital.quality.util.ExportExcelUtils;
import com.ge.digital.quality.vo.ExcelData;
import com.ge.digital.quality.vo.StoveStatisticsVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("真空炉统计")
@RequestMapping("/api/stoveStatistics")
public class StoveStatisticsController {

	private static final Logger log = LoggerFactory.getLogger(StoveStatisticsController.class);

	@Autowired
	StoveStatisticsService stoveStatisticsService;

	@Autowired
	RedisService redisService;

	@ApiOperation("")
	@GetMapping("/")
	public Object findByPage(StoveStatisticsQuery query) {
		try {
			NormalResponse normalResponse = new NormalResponse();
			if (query.getFromDate() == null || query.getToDate() == null) {
				return new BizErrorResponse(RestResponseCode.ERROR);
			}
			if (query.getToDate().getTime() <= query.getFromDate().getTime()) {
				return new BizErrorResponse(RestResponseCode.ERROR);
			}
			PageResult result = stoveStatisticsService.findByPage(query);
			normalResponse.setBody(result);
			return normalResponse;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("confirm has an error,{}", e);
			return new BizErrorResponse();
		}
	}

	@ApiOperation("")
	@GetMapping("/excel")
	public Object excel(HttpServletResponse response) {
		try {
			List<StoveStatisticsVO> vos = redisService.getQualityQueryResult();
			if (vos != null && vos.size() > 0) {
				ExcelData excelData = new ExcelData();
				excelData.setTitles(vos.get(0).getExcelTitles());
				List<List<Object>> datas = new ArrayList<>();
				for (StoveStatisticsVO vo : vos) {
					datas.add(vo.getValues());
					excelData.setRows(datas);
				}
				ExportExcelUtils.exportExcel(response, "真空炉统计.xlsx", excelData);
			}
			return new NormalResponse();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new BizErrorResponse();
		}
	}

}
