package com.yoon.stargram.handler.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errorMap;
	
	public CustomValidationException(String message, Map<String, String> errorMap) {
		super(message); //부모에게 계속 던져서 마지막에 getMessage를 사용할 수 있다.
		this.errorMap = errorMap;
		 
	}
	
	public CustomValidationException(String message) {
		super(message);
	}
	
	

	public Map<String, String> getErrorMap() {
		
		return errorMap;
	}
	
	
	
}
