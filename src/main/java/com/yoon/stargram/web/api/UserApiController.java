package com.yoon.stargram.web.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.stargram.domain.user.User;
import com.yoon.stargram.web.dto.user.UserUpdateDto;

@RestController
public class UserApiController {

	
	@PutMapping("/api/user/{id}")
	public String update(@PathVariable int id, UserUpdateDto userUpdateDto) {
		
		
		System.out.println(userUpdateDto+"Dto확인.");
		
		return "Ok";
	}
}
