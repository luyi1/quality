package com.ge.digital.quality.rest;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.digital.gearbox.common.response.NormalResponse;
import com.ge.digital.quality.entity.ProductionProc;
import com.ge.digital.quality.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

@Component
public class RestIntegrationService {

	private static final Logger log = LoggerFactory.getLogger(RestIntegrationService.class);

	@Autowired
	Gson gson;

	public ProductionProc findByLoadNumber(String loadNumber) {
		Map<String, String> param = new HashMap<>();
		param.put("loadNumber", loadNumber);
		String result = HttpClientUtil.doPost("http://localhost:8089/api/prodcutionProc/findByLoadNumber", param);
		log.info(result);
		if (StringUtils.isNotBlank(result)) {
			JSONObject jsonObject = new JSONObject(result);

			JSONObject body = jsonObject.optJSONObject("body");
			if (null == body)
				return new ProductionProc();
			ProductionProc proc = gson.fromJson(body.toString(), ProductionProc.class);
			return proc;
		}
		return new ProductionProc();
	}

}
