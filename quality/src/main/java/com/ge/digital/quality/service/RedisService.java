package com.ge.digital.quality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.digital.gearbox.common.redis.RedisClient;
import com.ge.digital.quality.vo.GasUsageVO;
import com.ge.digital.quality.vo.StoveStatisticsVO;

@Service
public class RedisService {

	public static final String QUALITY_ORDER_RESULT = "quality_query_result";// 质检结果
	
	public static final String QUALITY_GASUSAGE_RESULT="quality_gasusage_result";

	@Autowired
	RedisClient redisClient;

	public void setQualityQueryResult(List<StoveStatisticsVO> vos) throws Exception {
		redisClient.setList(QUALITY_ORDER_RESULT, vos);
	}

	public <T> List<T> getQualityQueryResult() {
		return redisClient.getList(QUALITY_ORDER_RESULT);
	}

	public String getUsername(String sessionId) throws Exception {
		return redisClient.get(sessionId);
	}

	public void setQualityGasusageResult(List<GasUsageVO> vos) throws Exception{
		redisClient.setList(QUALITY_GASUSAGE_RESULT, vos);
	}
	public <T> List<T> getGasusageResult() {
		return redisClient.getList(QUALITY_GASUSAGE_RESULT);
	} 
}
