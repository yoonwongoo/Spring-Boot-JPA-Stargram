package com.yoon.stargram.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.stargram.config.auth.PrincipalDetails;
import com.yoon.stargram.handler.ex.CustomApiException;
import com.yoon.stargram.handler.ex.CustomValidationApiException;
import com.yoon.stargram.service.SubscribeService;
import com.yoon.stargram.web.dto.CMRespDto;

@RestController
public class SubscribeApiController {
	
	
	@Autowired
	private SubscribeService subscribeSerive;
	
	
	@PostMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
		
		if(principalDetails.getUser().getId()==toUserId) {
			
			throw new CustomValidationApiException("본인을 구독할 수 없습니다.");
		}
		subscribeSerive.subscribe(principalDetails.getUser().getId(), toUserId);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "구독하기성공", null), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
		
		subscribeSerive.unSubscribe(principalDetails.getUser().getId(), toUserId);

		
		return new ResponseEntity<>(new CMRespDto<>(-1, "구독취소성공", null), HttpStatus.OK);

	}
	

}
