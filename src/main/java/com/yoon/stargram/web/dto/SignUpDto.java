package com.yoon.stargram.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.yoon.stargram.domain.user.User;

import lombok.Data;

@Data
public class SignUpDto {

	
	//회원가입시 사용되는 Dto
	@Size(min = 0, max=20)
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
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
