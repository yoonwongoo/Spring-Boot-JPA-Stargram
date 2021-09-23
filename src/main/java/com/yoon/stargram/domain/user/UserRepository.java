package com.yoon.stargram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	//Jpa query method;
	User findByUsername(String username);
	
	
}
