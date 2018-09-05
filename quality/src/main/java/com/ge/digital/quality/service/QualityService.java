package com.ge.digital.quality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.digital.quality.mapper.InspectionSummaryRepository;
import com.ge.digital.quality.query.QualityQuery;
import com.ge.digital.quality.vo.QualityResultVO;

@Service
public class QualityService {

	@Autowired
	InspectionSummaryRepository inspectionSummaryRepository;

	public List<QualityResultVO> findByPage(final QualityQuery qualityQuery) throws Exception {

		return null;
	}

}
