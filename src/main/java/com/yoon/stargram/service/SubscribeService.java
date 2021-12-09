 package com.yoon.stargram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.yoon.stargram.domain.subscribe.SubscribeRepository;
import com.yoon.stargram.handler.ex.CustomApiException;




@Service
public class SubscribeService {
	
	@Autowired
	private SubscribeRepository subscribeRepository;
	
	
	
	@Transactional
	public void subscribe(int fromUserId, int toUserId) {
		
		try {
			subscribeRepository.subscribe(fromUserId, toUserId);
			//try 하다가 터질 시 알아서 예외터져서 알아서 진행.
		} catch (Exception e) {
			throw new CustomApiException("이미 구독하셨습니다");
		}
		
		
		
		
	}
	
	@Transactional
	public void unSubscribe(int fromUserId, int toUserId) {
		
		subscribeRepository.unsubscribe(fromUserId, toUserId);
		
		
	}
}
