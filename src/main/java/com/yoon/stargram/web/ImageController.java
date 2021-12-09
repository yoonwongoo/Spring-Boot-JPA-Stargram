package com.yoon.stargram.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yoon.stargram.config.auth.PrincipalDetails;
import com.yoon.stargram.service.ImageService;
import com.yoon.stargram.web.dto.image.ImageUploadDto;


@Controller
public class ImageController {
	
	
	@Autowired
	private ImageService imageService;
	
	
	@GetMapping({"/", "/image/story"})
	public String story() {
		
		return "image/story";
	}
	
	 
	@GetMapping("/image/popular")
	public String popular() {
		
		return "image/popular";
	}
	
	
	@GetMapping("image/upload")
	public String uplaod() {
		
		return "image/upload";
	}
	
	@PostMapping("/image")
	public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principaldetails) {
		
		System.out.println(imageUploadDto.toString());
		
		imageService.ImageUpload(imageUploadDto, principaldetails);
		
		return "redirect:/user/"+principaldetails.getUser().getId();
	}
}
