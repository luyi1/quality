package com.ge.digital.quality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.digital.quality.mapper.CustomJpaRepository;
import com.ge.digital.quality.query.PageResult;
import com.ge.digital.quality.query.StoveStatisticsQuery;

@Service
public class StoveStatisticsService {

	@Autowired
	CustomJpaRepository customJpaRepository;

	public PageResult findByPage(StoveStatisticsQuery query) throws Exception {
		return customJpaRepository.findStoveStatistics(query);
	}

}
