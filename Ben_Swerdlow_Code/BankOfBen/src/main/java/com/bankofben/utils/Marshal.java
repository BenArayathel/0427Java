package com.bankofben.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bankofben.exceptions.BusinessException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Marshal {
	
	final static Logger loggy = Logger.getLogger(Marshal.class);
	
	final static ObjectMapper objMapper = new ObjectMapper();
	
	public Map<String, String> getRequestBodyNameValuePairs(String[] properties, HttpServletRequest request) throws IOException {
		
		Map<String, String> respMap = new HashMap<>(); 
		
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = objMapper.readTree(body);
		
		for (String property : properties) {
			respMap.put(property, bodyObj.get(property).textValue());
		}
		
		return respMap;
	}
	
	public String getRequestBodyNameValuePair(String property, HttpServletRequest request) throws IOException, BusinessException {
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		loggy.info("This is the body "+body);
		JsonNode bodyObj = objMapper.readTree(body);
		loggy.info("This is the body object "+bodyObj);
		String respString = bodyObj.get(property).textValue();
		loggy.info("This is the value "+respString);
		if (respString==null) {
			throw new BusinessException("Could not find property "+property+" in request body.");
		}
		loggy.info(respString);
		return respString;
	}

}
