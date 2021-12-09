package com.yoon.stargram;


import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ControllerTest {
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(ControllerTest.class);
	
	
	@GetMapping("/get1")//queryString으로
	public String get1(String get1) {
		return get1+"get1요청옴";
	}
	
	
	
	@GetMapping("/get2/{get2}") //pathVariable로 받음.
	public String get2(@PathVariable String get2) {
		return get2+"get2요청옴";
	}
	
	@PostMapping("/post1") //x-www-form-urlencoded contentType springboot의 기본전략임.
	public String post1(String post1) {
		System.out.println(post1);
		return"post1요청";
	}
	
	@PostMapping("/post2") //text/plain 평문양식은 타입이 정해져있지도 않으니까 @RequestBody필요.
	public String post2(@RequestBody String post2) {
		System.out.println(post2);
		return"post2요청";
	}
	
	@PostMapping("/post3") //application/json은 "key":"value"의 타입임.@RequestBody필요.
	public String post3(@RequestBody String post3) { 
		log.info(post3);
		return"post3요청";
		
	}	
	
	@PostMapping("/post4") //application/json은 "key":"value"의 타입임.@RequestBody필요.
	public String post3(@RequestBody ModelTest mt) { 
		log.info(mt.getPost4());
		return"post3요청";
	}
}
