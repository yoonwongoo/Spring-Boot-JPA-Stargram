package com.yoon.stargram.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.stargram.config.auth.PrincipalDetails;
import com.yoon.stargram.domain.user.User;
import com.yoon.stargram.handler.ex.CustomValidationApiException;
import com.yoon.stargram.handler.ex.CustomValidationException;
import com.yoon.stargram.service.UserService;
import com.yoon.stargram.web.dto.CMRespDto;
import com.yoon.stargram.web.dto.user.UserUpdateDto;

@RestController
public class UserApiController {
	
	
	@Autowired
	private UserService userService;

	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable int id, 
			@Valid UserUpdateDto userUpdateDto, 
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails){
		
	
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
		
			for(FieldError error : bindingResult.getFieldErrors()) {
				
				errorMap.put(error.getField(), error.getDefaultMessage());
				
				System.out.println("======================================");
				System.out.println(error.getDefaultMessage());
				System.out.println(error.getField());
				System.out.println("======================================");
				
			}
				throw new CustomValidationApiException("이름과 패스워드는 빈칸 일 수 없습니다", errorMap);
		}
		else {
		
		User userEntity = userService.userUpdate(id, userUpdateDto.toEntity());
		System.out.println(userEntity+"컨트롤러");
		principalDetails.setUser(userEntity);//여기서 세션정보변경 서비스에서 컨트롤러로 넘어올때 트랙잭션 커밋되지만 세션은 안바뀜.
		
		
		return new CMRespDto<>(1,"회원수정성공",userEntity);
		}
	
 }

}