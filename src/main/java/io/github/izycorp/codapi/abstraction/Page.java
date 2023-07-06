package io.github.izycorp.codapi.abstraction;

import org.json.JSONObject;

/**
 * @author iZy
 * @version 1.1
 * @since 1.1
 *
 * This class contains the data of a page if a request succeed
 */
public class Page {

    private final RequestStatus status;

    private final JSONObject data;

    public Page(String rawData) {
        // if rawData can't be parsed to JSONObject, it means that the request is not valid
        JSONObject jsonObj;

        try {
            // Try to parse the raw data to a JSONObject
            jsonObj = new JSONObject(rawData);
        } catch (Exception e) {
            // In case it won't succeed, we create a blank JSONObject
            jsonObj = new JSONObject();
            jsonObj.put("data", new JSONObject());
        }

        // If the response has no status, it means that the request returned nothing
        if(!jsonObj.has("status")) {
            jsonObj.put("status", "error");
            jsonObj.getJSONObject("data").put("type", "Bad Request");
            jsonObj.getJSONObject("data").put("message", "Response returned blank, wrong request");
        }

        // If the response has an error field, it means that the server returned an error
        else if(jsonObj.has("error")) {
            jsonObj.put("data", new JSONObject());
            jsonObj.getJSONObject("data").put("type", jsonObj.getString("error"));
            jsonObj.getJSONObject("data").put("message", jsonObj.get("status") + jsonObj.getString("message") + " - " + jsonObj.getString("timestamp"));
            jsonObj.put("status", "error");
        }

        this.status = RequestStatus.valueOf(String.valueOf(jsonObj.get("status")).toUpperCase());
        // Will return a JSONArray in some cases for unknown reasons (in case of empty data)
        this.data = (jsonObj.get("data") instanceof JSONObject) ? jsonObj.getJSONObject("data") : new JSONObject();
    }

    /**
     * Get the status of the request (success or error)
     * @return the status of the request
     */
    public RequestStatus getStatus() {
        return status;
    }

    /**
     * Get the data caught by the request, contained in a JSONObject
     * @return a not empty JSONObject
     */
    public JSONObject getData() {
        return data;
    }
}
