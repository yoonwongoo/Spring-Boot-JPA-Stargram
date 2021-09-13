package com.yoon.stargram.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yoon.stargram.domain.user.User;
import com.yoon.stargram.service.AuthService;
import com.yoon.stargram.web.dto.SignUpDto;

@Controller
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
	public String signUp(SignUpDto signUpDto) { 
		
		User user = signUpDto.toEntity();
		
		authService.join(user);
		
		log.info("회원가입 signUp메서드작동");
		return "auth/signin";
	}
}
