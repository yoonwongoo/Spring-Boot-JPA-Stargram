package com.yoon.stargram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.stargram.domain.user.User;
import com.yoon.stargram.domain.user.UserRepository;
import com.yoon.stargram.web.AuthController;


@Service //loc 등록 및 트랜잭션관리.
public class AuthService {
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
	@Autowired
	private BCryptPasswordEncoder bcryEncoder;

	@Autowired
	private UserRepository  userRepository ;
	
	
	
	
	@Transactional 
	public User join(User user) throws RuntimeException{

		String encPw = bcryEncoder.encode(user.getPassword());
		user.setPassword(encPw);
		user.setRole("ROLE_USER");
		User userEntity = userRepository.save(user); //save함수는 파라미터로 받은 객체 그 객체의 타입을 반환한다.
		
		log.info(user.toString());
		
		return userEntity;
		
	}

}
