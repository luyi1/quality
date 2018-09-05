package com.ge.digital.quality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.digital.quality.mapper.CustomJpaRepository;
import com.ge.digital.quality.query.GasUsageQuery;
import com.ge.digital.quality.vo.GasUsageVO;

@Service
public class GasUsageService {

	@Autowired
	CustomJpaRepository customJpaRepository;
	
	@Autowired
	RedisService redisService;

	public List<GasUsageVO> findGasUsage(GasUsageQuery query) throws Exception {
		List<GasUsageVO> vos= customJpaRepository.findGasUsage(query);
		redisService.setQualityGasusageResult(vos);
		return vos;
	}

}
