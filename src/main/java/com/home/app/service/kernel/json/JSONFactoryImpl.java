package com.home.app.service.kernel.json;

import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class JSONFactoryImpl implements JSONFactory {

	public JSONArray createJSONArray() {
		return new JSONSmartArrayImpl();
	}

	public JSONArray createJSONArray(String json) throws JSONException {
		return new JSONSmartArrayImpl(json);
	}

	public JSONArray createJSONArray(File json) throws JSONException {
		return new JSONSmartArrayImpl(json);
	}

	public JSONObject createJSONObject() {
		return new JSONSmartObjectImpl();
	}

	public JSONObject createJSONObject(String json) throws JSONException {
		return new JSONSmartObjectImpl(json);
	}

}