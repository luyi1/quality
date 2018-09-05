package com.ge.digital.quality.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ge.digital.gearbox.common.response.BizErrorResponse;
import com.ge.digital.gearbox.common.response.NormalResponse;
import com.ge.digital.gearbox.common.response.RestResponseCode;
import com.ge.digital.quality.query.QualityQuery;
import com.ge.digital.quality.service.EticketService;
import com.ge.digital.quality.vo.EticketConfirmVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("电子工票")
@RestController
@RequestMapping("/api/eticket")
public class ETicketController {

	private static final Logger log = LoggerFactory.getLogger(ETicketController.class);

	@Autowired
	EticketService eticketService;

	@ApiOperation("质检查询")
	@GetMapping
	public Object eTickers(QualityQuery query) {
		try {
			if (query.verifyDate()) {
				return new BizErrorResponse(RestResponseCode.QUALITY_QUALITY_TIME_ISNULL_EXCEPTION);
			}
			if (query.getLineExitDateFrom().getTime() >= query.getLineExitDateTo().getTime()) {
				return new BizErrorResponse(RestResponseCode.QUALITY_QUALITY_LINEEXITDATE_NOTMATCH_EXCEPTION);
			}
			if (query.getInspectionTimeFrom().getTime() >= query.getInspectionTimeTo().getTime()) {
				return new BizErrorResponse(RestResponseCode.QUALITY_QUALITY_LINEEXITDATE_NOTMATCH_EXCEPTION);
			}
			NormalResponse normalResponse = new NormalResponse();
			Map<String, Object> map = eticketService.findQuality(query);
			normalResponse.setBody(map);
			return normalResponse;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("eTickers has an error,{}", e);
			return new BizErrorResponse(RestResponseCode.ERROR);
		}
	}

	@ApiOperation("查询电子工票")
	@GetMapping("/{inspectionNo}")
	public Object findETicker(@PathVariable String inspectionNo, HttpServletRequest request) {
		try {
			NormalResponse normalResponse = new NormalResponse();
			Map<String, Object> map = eticketService.findeTicket(inspectionNo, request.getSession().getId());
			normalResponse.setBody(map);
			return normalResponse;
		} catch (IllegalIdentifierException ife) {
			// TODO: handle exception
			log.error("findETicker has an error,{}", ife);
			return new BizErrorResponse(ife.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("findETicker has an error,{}", e);
			return new BizErrorResponse(RestResponseCode.ERROR);
		}
	}

	@ApiOperation("确认点检人")
	@PostMapping("/confirm")
	public Object confirm(@RequestBody EticketConfirmVO vo) {
		try {
			NormalResponse normalResponse = new NormalResponse();
			normalResponse.setBody(eticketService.confirm(vo));
			return normalResponse;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("confirm has an error,{}", e);
			return new BizErrorResponse();
		}
	}

}
