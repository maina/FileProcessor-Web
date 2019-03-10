package io.tulaa.fileprocessor.utils;

import java.util.Map;


public class ApiResponse {
    private Map<String, Object> data;
    private String message;
    private boolean success;


    public ApiResponse(Boolean success, String message, Map<String, Object> data) {
        this.success = success;
        this.message = message;
        this.setData(data);
    }

    public ApiResponse() {

    }


    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

	public void setMessage(String message) {
		this.message = message;
	}
}
