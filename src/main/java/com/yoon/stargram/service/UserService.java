package com.yoon.stargram.service;





import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.stargram.domain.subscribe.SubscribeRepository;
import com.yoon.stargram.domain.user.User;
import com.yoon.stargram.domain.user.UserRepository;
import com.yoon.stargram.handler.ex.CustomException;
import com.yoon.stargram.handler.ex.CustomValidationApiException;
import com.yoon.stargram.web.dto.user.UserProfileDto;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SubscribeRepository subscribeRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	//.get()무조건찾음.  .orElseThrow() 못찾으면 익셉션처리.
	//영속화 후 -> 영속화 수정 후 -> 더티체킹.
	
	//select * 
	//from image
	//where userId = userId;
	
	@Transactional(readOnly = true)
	public UserProfileDto profile(int pageUserId, int id) {
		
		UserProfileDto userProfileDto = new UserProfileDto();
		
		 User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{throw new CustomException("해당프로필을 찾을 수 없습니다.");});
		 
		 int subscribeState = subscribeRepository.subscribeState(id, pageUserId);
		
		 

		 int subscribeCount = subscribeRepository.subscribeCount(pageUserId);
	
		 userProfileDto.setUser(userEntity);
		 userProfileDto.setPageOwner(pageUserId == id);
		 userProfileDto.setImageCount(userEntity.getImages().size());
		 userProfileDto.setSubscribeCount(subscribeCount);
		 userProfileDto.setSubscribeState(subscribeState == 1 ? true : false);
		 

		 return userProfileDto;
	}	
	
	
	
	
	
	@Transactional
	public User userUpdate(int id, User user) {
		
		User userEntity = userRepository.findById(id).orElseThrow(()-> {return new CustomValidationApiException("해당 ID를 찾을 수 없습니다.");});
		
		if(!userEntity.getPassword().equals(user.getPassword())) {
			String Pw = user.getPassword();
			String encPw = bcryptPasswordEncoder.encode(Pw);	
	
		
		
		userEntity.setName(user.getName());
		userEntity.setPassword(encPw);
		userEntity.setWebsite(user.getWebsite());
		userEntity.setBio(user.getBio());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		 
	
		}else {		
			
		userEntity.setName(user.getName());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setBio(user.getBio());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
			
		}
		return userEntity;
	
		
		
		
		
		
	}
}
