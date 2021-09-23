package com.yoon.stargram.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.yoon.stargram.domain.user.User;


import lombok.Data;

@Data
public class UserUpdateDto {
	
	@NotBlank
	private String name;//필수
	@NotBlank
	private String password;//필수
	
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	//코드수정필요.. 
	public User toEntity() {
		
		return User.builder()
				.name(name)
				.password(password)
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	} 

}
