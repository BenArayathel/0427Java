package com.pone.v1.jsonmodels;

public class JSONResponse {

	private String status;
	private Object data;
	private String message;
	
	public JSONResponse() {
		// TODO Auto-generated constructor stub
	}

	public JSONResponse(String status, Object data, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
