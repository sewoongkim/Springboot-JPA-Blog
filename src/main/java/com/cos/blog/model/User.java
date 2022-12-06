package com.cos.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@EntityListeners(value = {AuditingEntityListener.class})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User  Class가 Mysql에 테이블이 생성된다.
// @DynamicInsert //  insert시에 null인 필드를 제외 

public class User {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;  //sequence. auto_increment 
	//프로젝트에서 연결된 DB의  넘버링 전략을 따라간다.
	
	@Column(nullable=false, length= 100, unique=true)
	private String username; 
	
	@Column(nullable=false, length=100)
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	// @ColumnDefault("'user'")
	//private String role; //Enum을 쓰는게 좋다.  //admin, user, manager
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@Column(nullable=false, length=50)
	private String oauth;  // kakao, google
	
	@CreatedDate //시간이 자동입력
	private LocalDateTime createDate;

}
