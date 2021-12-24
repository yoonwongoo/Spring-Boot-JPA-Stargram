package com.yoon.stargram.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yoon.stargram.config.auth.PrincipalDetails;
import com.yoon.stargram.service.UserService;

import com.yoon.stargram.web.dto.user.UserProfileDto;




@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	

	
	
	
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails ) {
		UserProfileDto userProfile = userService.profile(pageUserId, principalDetails.getUser().getId());
		
		
		model.addAttribute("userProfile", userProfile);
		
		return "user/profile";
		
		
	}
	
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
    
		return "user/update";
	}
}
