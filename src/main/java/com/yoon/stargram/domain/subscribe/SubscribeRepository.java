package com.yoon.stargram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
	
	
	
	@Modifying//insert, update, delete를 할 경우에 .
	@Query(value="INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES (:fromUserId, :toUserId, now())", nativeQuery = true)
	void subscribe(int fromUserId, int toUserId);//1 , -1 변경된 튜플만큼 리턴, -1은 오류
	
	
	@Modifying
	@Query(value="DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId",  nativeQuery = true)
	void unsubscribe(int fromUserId, int toUserId);//1, -1

}
