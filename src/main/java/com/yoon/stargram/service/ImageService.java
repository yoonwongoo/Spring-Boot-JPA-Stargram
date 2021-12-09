package com.yoon.stargram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yoon.stargram.config.auth.PrincipalDetails;
import com.yoon.stargram.domain.iamge.Image;
import com.yoon.stargram.domain.iamge.ImageRepository;
import com.yoon.stargram.handler.ex.CustomValidationException;
import com.yoon.stargram.web.dto.image.ImageUploadDto;






@Service
public class ImageService {
	
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Value("${file.path}")//application.yml에 정의해놓은 이름이다.
	private String uploadFolder;//경로값이 들어온다.


	
	
	
	public void ImageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails ) {//사진업로드
		if(imageUploadDto.getFile().isEmpty()) {
			
			throw new CustomValidationException("이미지가 등록실패!");
		
		}	
	UUID uuid = UUID.randomUUID();//랜덤으로 만들고 겹치지 않게 만들어줌.
	String imageFileName =uuid+"_" +imageUploadDto.getFile().getOriginalFilename();//실제 파일명이 들어옴.
	
	
	Path imageFilePath = Paths.get(uploadFolder+imageFileName);//경로+파일명
	
	
	//통신이나 I/O을 할 떄는 컴파일에러가 아닌 런타임에러가 발샐하니까 에러를 컴파일러가 못잡으니까 개발자가 런타임에러를 잡아준다.
	try {
		Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	Image image =imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
	
	Image imageEntity = imageRepository.save(image); //save함수는 리턴을 T로 되니까 imageEntity가 들고 있는다.
	
	}





}
