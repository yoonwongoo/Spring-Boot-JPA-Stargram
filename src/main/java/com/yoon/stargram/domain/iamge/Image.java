package com.yoon.stargram.domain.iamge;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.yoon.stargram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private String caption;//사진 설명글
	
	
	private String postImageUrl;//사진을 전송받아서 서버에 그 사진을 서버의 특정 폴더에 저장 후에 DB에 그 저장된 경로를 insert 사진은 서버에 db에는 사진의 경로만 저장.
	
	
	@JoinColumn(name="userId")
	@ManyToOne
	private User user;
	
	
	//이미지 좋아요 컬럼도 필요하다
	
	
	//댓글
	
	
	private LocalDateTime createDate;
	
	
	@PrePersist
	public void createDate() {
		this.createDate=LocalDateTime.now();
		
	}
}