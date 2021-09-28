package com.yoon.stargram.handler;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.stargram.handler.ex.CustomApiException;
import com.yoon.stargram.handler.ex.CustomValidationApiException;
import com.yoon.stargram.handler.ex.CustomValidationException;
import com.yoon.stargram.util.Script;
import com.yoon.stargram.web.dto.CMRespDto;


@RestController
@ControllerAdvice//exception 다 낚아챔.
public class ControllerExceptionHandler {
	
	/*
	 * 1.클라이언트에게는 Script로 보내주는것이 좋고
	 * 2.android나 Ajax통신은 CMDto로 보내주는것이 좋음.
	 * */
	@ExceptionHandler(CustomValidationException.class)
	public String validationExcepion(CustomValidationException e) {
	
	return Script.back(e.getErrorMap().toString());  
	

	}
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiExcepion(CustomValidationApiException e) {
	
	return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	

	}
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> apiExcepion(CustomApiException e) {
	
	return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	
	
}
}