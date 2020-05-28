package com.controllers;

public class JSONResponse {

	private String responseStatus;
	private Object jsonData;
	private String message;
	
	public JSONResponse(String responseStatus, Object jsonData, String message) {
		super();
		this.responseStatus = responseStatus;
		this.jsonData = jsonData;
		this.message = message;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public Object getJsonData() {
		return jsonData;
	}

	public void setJsonData(Object jsonData) {
		this.jsonData = jsonData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
	
}
