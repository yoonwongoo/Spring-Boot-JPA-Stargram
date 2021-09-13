package com.yoon.stargram.web.dto;

import com.yoon.stargram.domain.user.User;

import lombok.Data;

@Data
public class SignUpDto {

	
	//회원가입시 사용되는 Dto
	private String username;
	private String password;
	private String email;
	private String name;
	
	
	public User toEntity() {//빌더패턴
		return User.builder()

		.username(username)
		.password(password)
		.email(email)
		.name(name)
		.build();
		

	} 
}
