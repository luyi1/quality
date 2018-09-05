package com.ge.digital.quality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ge.digital.quality.query.QualityQuery;
import com.ge.digital.quality.service.QualityService;

@RestController
@RequestMapping("/api/quality")
public class QualityController {

	@Autowired
	QualityService qualityService;

	public Object find(QualityQuery qualityQuery) {
		return null;
	}

}
