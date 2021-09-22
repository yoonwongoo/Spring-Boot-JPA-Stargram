package com.yoon.stargram.web.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.yoon.stargram.domain.user.User;

import lombok.Data;

@Data
public class SignUpDto {

	
	//회원가입시 사용되는 Dto
	@Size(min = 0, max=20)
	@NotBlank(message ="20자 이하 username을 입력해주세요")
	private String username;
	
	@NotBlank(message = "비밀번호를 입력해주세요")
	private String password;
	
	@NotBlank
	@Email(message="이메일 형식에 맞지 않습니다")
	private String email;
	
	@Size(min = 0, max=20)
	@NotBlank(message="이름을 입력해주세요")
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
