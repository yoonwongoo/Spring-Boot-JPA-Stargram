package com.yoon.stargram.web.dto.subscribe;



import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class SubscribeDto {

	
	
	private int id;
	private String username;
	private String profileImageUrl;
	private BigInteger subscribeState;
	private BigInteger equalUserState;
	
	
}
