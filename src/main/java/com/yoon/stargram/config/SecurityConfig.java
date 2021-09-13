package com.yoon.stargram.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@EnableWebSecurity//해당 파일로 시큐리티를 활성화.
@Configuration //ioc등록.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	
	//configuration을 읽어서 SecurityConfig가 읽어서 등록될 때 @Bean을 읽어서 return을 해서 IOC가 new BCryptPasswordEncoder()를 들고 있는다.
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);// 이 메서드 때문에 로그인폼이 계속 뜨는거임.
		http.authorizeRequests() //HttpServletRequest를 이용한다.
			.antMatchers("/", "/user/**", "/image/**", "/subscribe/**","/comment/**").authenticated()//인증이 필요하다.antMachers는 특정경로지정.
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/auth/signin")
			.defaultSuccessUrl("/");
		http.csrf().disable();

	}

}
