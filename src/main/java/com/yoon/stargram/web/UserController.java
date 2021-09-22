package com.yoon.stargram.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yoon.stargram.config.auth.PrincipalDetails;



@Controller
public class UserController {

	@GetMapping("/user/profile")
	public String profile() {
		
		return "user/profile";
		
		
	}
	
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		
		
		return "user/update";
	}
}
