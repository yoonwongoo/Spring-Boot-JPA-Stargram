package com.yoon.stargram.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoon.stargram.domain.user.User;
import com.yoon.stargram.handler.ex.CustomValidationException;
import com.yoon.stargram.handler.ex.SignUpTestException;
import com.yoon.stargram.service.AuthService;
import com.yoon.stargram.web.dto.SignUpDto;

@Controller//ioc등록 및 html파일을 리턴하는 컨트롤러.
public class AuthController {

	@Autowired
	private AuthService authService;
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	
/*	private AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	} AuthService도 ioc에 있기떄문에 스프링이 알아서 주입을 해줌.
*/
	@GetMapping("/auth/signin")
	public String signInForm() {
		
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signUpForm() {
		
		return "auth/signup";
	}

	
 	@PostMapping("/auth/signup")
	public  String signUp(@Valid SignUpDto signUpDto, BindingResult bindingResult) { 
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
		
			for(FieldError error : bindingResult.getFieldErrors()) {
				
				errorMap.put(error.getField(), error.getDefaultMessage());
				
		
			}
			throw new CustomValidationException("유효성 검사 실패입니다.", errorMap);  
			
			}else{
			
					User user = signUpDto.toEntity();
					authService.join(user);
	
	
			return "redirect:/auth/signin";
			
		}
	
		
		
	}
	
}
