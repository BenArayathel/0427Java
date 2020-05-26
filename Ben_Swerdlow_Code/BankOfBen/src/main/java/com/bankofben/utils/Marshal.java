package com.bankofben.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Marshal {
	
	public Map<String, String> getRequestBodyNameValuePairs(String[] properties, HttpServletRequest request) throws IOException {
		
		Map<String, String> respMap = new HashMap<>(); 
		
		ObjectMapper objMapper = new ObjectMapper();
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = objMapper.readTree(body);
		
		for (String property : properties) {
			respMap.put(property, bodyObj.get(property).textValue());
		}
		
		return respMap;
	}
	
	public String getRequestBodyNameValuePair(String property, HttpServletRequest request) throws IOException {
		ObjectMapper objMapper = new ObjectMapper();
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = objMapper.readTree(body);
		return bodyObj.get(property).textValue();
	}

}
