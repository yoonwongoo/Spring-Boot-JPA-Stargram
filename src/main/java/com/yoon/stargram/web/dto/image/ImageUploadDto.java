package com.yoon.stargram.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import com.yoon.stargram.domain.iamge.Image;
import com.yoon.stargram.domain.user.User;

import lombok.Data;

//이미지를 받을 dto.
@Data
public class ImageUploadDto {
	
	private MultipartFile file;  //이미지를 받는파일 타입.
	private String caption;// 사진 설명
	
	
	
	
	
	public Image toEntity(String postImageUrl, User user) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.user(user)
				.build();
		
	}
}