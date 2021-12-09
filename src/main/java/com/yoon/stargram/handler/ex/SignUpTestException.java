package com.yoon.stargram.handler.ex;

import org.springframework.dao.DataIntegrityViolationException;

public class SignUpTestException extends DataIntegrityViolationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SignUpTestException(String msg) {
		super(msg);
		
	}

}
