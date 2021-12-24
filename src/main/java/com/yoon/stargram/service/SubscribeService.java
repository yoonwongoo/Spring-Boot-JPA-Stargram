 package com.yoon.stargram.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.yoon.stargram.domain.subscribe.SubscribeRepository;
import com.yoon.stargram.handler.ex.CustomApiException;
import com.yoon.stargram.web.dto.subscribe.SubscribeDto;




@Service
public class SubscribeService {
	
	@Autowired
	private SubscribeRepository subscribeRepository;
	
	
	
	@Autowired
	private EntityManager em;
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> subscribeList(int pageUserId, int principalId){
		 
		StringBuffer sb = new StringBuffer();//가변적이므로 String클래스는 지양하자. 메모리영역 너무 차지한다.
		
		sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
		sb.append("if((SELECT 1 FROM subscribe where fromUserId =? AND toUserId = u.id), 1,0)subscribeState, ");
		sb.append("if((?=u.id), 1,0)equalUserState ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("Where s.fromUserId = ?");//세미클로 ㄴㄴㄴㄴㄴㄴㄴㄴㄴ
		
		//37,36의?에는 pageUserId이다
		//?에는 principalId이다.
		//쿼리문 작성 
		System.out.println(sb.toString());
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
		
		
		//qlrm라이브러리 필요.
		//쿼리실행.
		JpaResultMapper jpaRe = new JpaResultMapper();
		
		List<SubscribeDto>subscribeDtoRe = jpaRe.list(query, SubscribeDto.class);//쿼리던지도 dto타입으로 결과를 리턴받겠다.
		System.out.println(subscribeDtoRe);
		
		return subscribeDtoRe;
	}
	
	
	
	
	
	
	
	
	
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
