package com.yoon.stargram.web.dto.user;

import com.yoon.stargram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@AllArgsConstructor 
@NoArgsConstructor
@Builder
@Data
public class UserProfileDto {
	
	private int imageCount;
	private boolean pageOwner;//프로필의 주인인지 아닌지 
	private User user;
	
	
	
	private int subscribeCount;//구독 수 
	private boolean subscribeState;//구독 상태.
	
}
