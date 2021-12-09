package com.yoon.stargram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
	
	
	
	@Modifying//insert, update, delete를 할 경우에 네이티브 쿼리로 할 경우에는 해당 어노테이션 필요
	@Query(value="INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES (:fromUserId, :toUserId, now())", nativeQuery = true)
	void subscribe(int fromUserId, int toUserId);
	
	
	@Modifying
	@Query(value="DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId",  nativeQuery = true)
	void unsubscribe(int fromUserId, int toUserId);
}
