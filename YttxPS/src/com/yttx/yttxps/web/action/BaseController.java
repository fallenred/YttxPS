package com.yttx.yttxps.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.yttx.yttxps.model.SessionEntity;

public class BaseController {
	
	protected String input;
	
	protected SessionEntity sessionEntity;

	protected Map<String, Object> modelMap;
	
	protected HttpServletRequest request;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	public void setModelMap(Map<String, Object> modelMap) {
		this.modelMap = modelMap;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public SessionEntity getSessionEntity() {
		return sessionEntity;
	}

	public void setSessionEntity(SessionEntity sessionEntity) {
		this.sessionEntity = sessionEntity;
	}
	
	
	

}
