package com.yoon.stargram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{//웹 설정파일

	@Value("${file.path}")
	private String uploadFolder;
	
	
	
	@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			// TODO Auto-generated method stub
			WebMvcConfigurer.super.addResourceHandlers(registry);
			
			registry
					.addResourceHandler("/upload/**")//jsp에서 이런 패턴이 들어오면 발동 
					.addResourceLocations("file:///"+uploadFolder)
					.setCachePeriod(60*10*6)
					.resourceChain(true)
					.addResolver(new PathResourceResolver());
		}
}
