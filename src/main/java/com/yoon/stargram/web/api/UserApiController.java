package com.yoon.stargram.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.stargram.config.auth.PrincipalDetails;
import com.yoon.stargram.domain.user.User;
import com.yoon.stargram.handler.ex.CustomValidationApiException;
import com.yoon.stargram.service.SubscribeService;
import com.yoon.stargram.service.UserService;
import com.yoon.stargram.web.dto.CMRespDto;
import com.yoon.stargram.web.dto.subscribe.SubscribeDto;
import com.yoon.stargram.web.dto.user.UserUpdateDto;

@RestController
public class UserApiController {
	
	
	@Autowired
	private UserService userService;

	
	
	@Autowired
	private SubscribeService subscribeService;
	  
	
	
	@GetMapping("/api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		List<SubscribeDto> subscribeDto = subscribeService.subscribeList(pageUserId, principalDetails.getUser().getId());
		System.out.println(subscribeDto.toString());
		return new ResponseEntity<>(new CMRespDto<>(1, "구독정보불러오기성공", subscribeDto), HttpStatus.OK);
		
	}

	
	
	
	
	
	
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable int id, 
			@Valid UserUpdateDto userUpdateDto, 
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails){
		
	
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
		
			for(FieldError error : bindingResult.getFieldErrors()) {
				
				errorMap.put(error.getField(), error.getDefaultMessage());
			
				
			}
				throw new CustomValidationApiException("이름과 패스워드는 빈칸 일 수 없습니다", errorMap);
		}
		else {
		
		User userEntity = userService.userUpdate(id, userUpdateDto.toEntity());

		principalDetails.setUser(userEntity);//여기서 세션정보변경 서비스에서 컨트롤러로 넘어올때 트랙잭션 커밋되지만 세션은 안바뀜.
		
		
		return new CMRespDto<>(1,"회원수정성공",userEntity);
		}
	
 }

}