package com.yoon.stargram.web.dto;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CMRespDto<T> {
	
	private int code;//1 성공 -1실패
	
	private String message;
	
	private T data;

}
