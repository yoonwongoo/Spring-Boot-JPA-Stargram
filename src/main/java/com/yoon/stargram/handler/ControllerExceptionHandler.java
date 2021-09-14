package com.yoon.stargram.handler;





import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.stargram.handler.ex.CustomValidationException;
import com.yoon.stargram.util.Script;


@RestController
@ControllerAdvice//exception 다 낚아챔.
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationExcepion(CustomValidationException e) {
	
	return Script.back(e.getErrorMap().toString());
	

	}
}