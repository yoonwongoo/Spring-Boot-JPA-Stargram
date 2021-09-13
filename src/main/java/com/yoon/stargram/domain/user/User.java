package com.yoon.stargram.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length = 20)
	private String username;
	
	private String password;
	
	private String email;
	
	private String name;
	
	private String website;
	
	private String bio;//자기소개
	
	private String phone;
	
	private String gender;
	
	private String profileImageUrl;//작성자 사진.
	
	
	private String role;//권한
	
	private LocalDateTime createDate;
	
	@PrePersist //DB에 INSERT되기 직전에 실행.
	public void createDate() {
		this.createDate = LocalDateTime.now();//static메서드이기때문에 바로접근 가능.
		//즉 
		
	}
}