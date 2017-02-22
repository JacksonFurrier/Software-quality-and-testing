package ucoach.auth.restclient.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;


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
		
		if (jsonObj == null)
			throw new Exception("Not a JSON object");

		try {
			return jsonObj.getString(expr);
		} catch (JSONException e) {}
		
		try {
			return Integer.toString(jsonObj.getInt(expr));
		} catch (JSONException e) {}
		
		try {
			return Double.toString(jsonObj.getDouble(expr));
		} catch (JSONException e) {}
		
		return "";
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
}