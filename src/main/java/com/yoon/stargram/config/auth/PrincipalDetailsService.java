package com.yoon.stargram.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yoon.stargram.domain.user.User;
import com.yoon.stargram.domain.user.UserRepository;



@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	
	@Autowired
	private UserRepository userRepository;
	
	//리턴이 성공적으로 되면 세션을 만든다. 세션에 user 정보가 들어있으므로 활용가능.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userEntity = userRepository.findByUsername(username);
		
		System.out.println( userEntity);
		if(userEntity ==null) {
			return null;	
			
		}else {
			
			PrincipalDetails principal = new PrincipalDetails(userEntity);
			
			System.out.println(principal.getUser());
			return new PrincipalDetails(userEntity);
		}
		
		
	}

}
