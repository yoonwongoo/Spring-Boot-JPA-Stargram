package com.yoon.stargram.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.stargram.domain.user.User;
import com.yoon.stargram.domain.user.UserRepository;
import com.yoon.stargram.handler.ex.CustomValidationApiException;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	//.get()무조건찾음.  .orElseThrow() 못찾으면 익셉션처리.
	//영속화 후 -> 영속화 수정 후 -> 더티체킹.
	
	
	@Transactional
	public User userUpdate(int id, User user) {
		
		User userEntity = userRepository.findById(id).orElseThrow(()-> {return new CustomValidationApiException("해당 ID를 찾을 수 없습니다.");});
		
		String Pw = user.getPassword();
		String encPw = bcryptPasswordEncoder.encode(Pw);
		
		
		userEntity.setName(user.getName());
		userEntity.setPassword(encPw);
		userEntity.setWebsite(user.getWebsite());
		userEntity.setBio(user.getBio());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		System.out.println(user+"서비스");
		return userEntity;
		
		 
	}
}
