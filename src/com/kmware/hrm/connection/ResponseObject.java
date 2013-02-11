package com.kmware.hrm.connection;

import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ResponseObject {

	private static final String LOGTAG = "eFarmer.ResponseObject";

	protected JSONObject responseObj;

	protected SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");

	public ResponseObject() {
		responseObj = new JSONObject();
	}

	public ResponseObject(String str) throws JSONException {
		responseObj = new JSONObject(str);
	}

	protected ResponseObject(JSONObject obj) {
		responseObj = obj;
	}

	public String toString() {
		if (responseObj != null) {
			return responseObj.toString();
		} else {
			return "null";
		}
	}

	protected JSONObject getJsonObject(String fieldName) {
		try {
			if (responseObj != null) {
				return responseObj.getJSONObject(fieldName);
			} else {
				return null;
			}
		} catch (JSONException e) {
			Log.e(LOGTAG, "Error processing JSON object ", e);
			return null;
		}
	}

	protected JSONArray getJSONArray(String fieldName) {
		try {
			if (responseObj != null) {
				return responseObj.getJSONArray(fieldName);
			} else {
				return null;
			}
		} catch (JSONException e) {
			Log.e(LOGTAG, "Error processing JSON object ", e);
			return null;
		}
	}

	JSONObject getResponseObject() {
		return responseObj;
	}
}
