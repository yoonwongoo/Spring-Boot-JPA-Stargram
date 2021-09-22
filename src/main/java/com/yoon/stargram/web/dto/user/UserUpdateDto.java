package com.yoon.stargram.web.dto.user;

import com.yoon.stargram.domain.user.User;


import lombok.Data;

@Data
public class UserUpdateDto {
	
	
	private String name;
	private String password;
	private String website;
	private String bio;
	private String phone;
	private String gender;
	

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
