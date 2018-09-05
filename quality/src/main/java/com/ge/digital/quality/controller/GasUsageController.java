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
import com.ge.digital.quality.query.GasUsageQuery;
import com.ge.digital.quality.service.GasUsageService;
import com.ge.digital.quality.service.RedisService;
import com.ge.digital.quality.util.ExportExcelUtils;
import com.ge.digital.quality.vo.ExcelData;
import com.ge.digital.quality.vo.GasUsageVO;
import com.ge.digital.quality.vo.StoveStatisticsVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("乙炔")
@RestController
@RequestMapping("/api/gasusage")
public class GasUsageController {

	private static final Logger log = LoggerFactory.getLogger(GasUsageController.class);

	@Autowired
	GasUsageService gasUsageService;

	@Autowired
	RedisService redisService;

	@ApiOperation("findGasUsage")
	@GetMapping("")
	public Object findGasUsage(GasUsageQuery query) {
		try {
			if (null == query.getLineExitDateFrom() && null == query.getLineExitDateTo()) {
					
			} else {
				if (query.getLineExitDateFrom().getTime() > query.getLineExitDateTo().getTime()) {
					return new BizErrorResponse(RestResponseCode.QUALITY_QUALITY_TIME_EXCEPTION);
				}
			}
			NormalResponse normalResponse = new NormalResponse();
			normalResponse.setBody(gasUsageService.findGasUsage(query));
			return normalResponse;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("findGasUsage has an error,{}", e);
			return new BizErrorResponse(RestResponseCode.ERROR);
		}
	}

	@ApiOperation("")
	@GetMapping("/excel")
	public Object excel(HttpServletResponse response) {
		try {
			List<GasUsageVO> vos = redisService.getGasusageResult();
			List<String> excelTitles = new ArrayList<>();
			excelTitles.add("产线号");
			excelTitles.add("热处理炉号");
			excelTitles.add("乙炔使用量(l)");
			excelTitles.add("渗碳炉氮气使用量(l)");
			excelTitles.add("批次号");
			
			excelTitles.add("物料编码");
			excelTitles.add("零件图号");
			excelTitles.add("零件名称");
		
	
		
			excelTitles.add("工艺号");
			excelTitles.add("下线日期");
		
			if (vos != null && vos.size() > 0) {
				ExcelData excelData = new ExcelData();
				excelData.setTitles(excelTitles);
				List<List<Object>> datas = new ArrayList<>();
				for (GasUsageVO vo : vos) {
					datas.add(vo.values());
					excelData.setRows(datas);
				}
				ExportExcelUtils.exportExcel(response, "乙炔使用量统计.xlsx", excelData);
			}
			return new NormalResponse();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new BizErrorResponse();
		}
	}

}
