package ucoach.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

	JSONObject jsonObj = null;
	JSONArray jsonArr = null;
	
	/**
	 * Load JSON string
	 * @param json
	 */
	public void loadJson(String json) {
		
		// Load JSON object
		if (json.startsWith("{")) {
			jsonObj = new JSONObject(json);
		}
		
		// Load JSON array
		if (json.startsWith("[")) {
			jsonArr = new JSONArray(json);
		}
	}
	
	/**
	 * Get JSON element
	 * @param expr
	 * @return
	 * @throws Exception 
	 */
	public String getElement(String expr) throws Exception {

		JSONObject localJsonObj = jsonObj;
		
		if (localJsonObj == null)
			throw new Exception("Not a JSON object");

		String[] splitted = expr.split("/");
		int length = splitted.length;
		if (length == 1) {
			return getSingleElement(localJsonObj, expr);
		}

		for (int i = 0; i < length - 1; i++) {
			localJsonObj = localJsonObj.getJSONObject(splitted[i]);
		}

		return getSingleElement(localJsonObj, splitted[length - 1]);
	}
	
	/**
	 * Count how many element in JSON list
	 * @return
	 * @throws Exception 
	 */
	public int countList() throws Exception {
		if (jsonArr == null)
			throw new Exception("Not a JSON object");
		
		return jsonArr.length();
	}

	/**
	 * Get single element
	 * @param expr
	 * @return
	 */
	private String getSingleElement(JSONObject jsonObj, String expr) {
		try {
			return jsonObj.getString(expr);
		} catch (JSONException e) {}
		
		try {
			return Integer.toString(jsonObj.getInt(expr));
		} catch (JSONException e) {}
		
		try {
			return Double.toString(jsonObj.getDouble(expr));
		} catch (JSONException e) {}
		
		try {
			return jsonObj.getJSONObject(expr).toString();
		} catch (JSONException e) {}

		return "";
	}
}
