package com.ge.digital.quality.rest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.ge.digital.quality.util.HttpClientUtil;

@Component
public class RestWipService {

	public JSONObject fetchProcessCard(String processCard) throws JSONException {
		// String result = HttpClientUtil.doPostJson("", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("materielBatchNumber", "materielBatchNumber");
		jsonObject.put("nfNumber", "nfNumber");
		jsonObject.put("materielCode", "materielCode");
		jsonObject.put("partNumber", "partNumber");
		jsonObject.put("spec", "spec");
		jsonObject.put("inspectionNo", "inspectionNo");
		return jsonObject;
	}

	public JSONObject fetchEcmAlternation(String line, String loadNumber) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("line", "line");
		jsonObject.put("loadNumber", "loadNumber");
		jsonObject.put("secPartNumber", "secPartNumber");
		return jsonObject;
	}

}
