package com.cos.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User  Class가 Mysql에 테이블이 생성된다.
@EntityListeners(value = {AuditingEntityListener.class})
public class Board {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)		
		private int id;
		
		@Column(nullable = false, length = 100)
		private String title;
		
		@Lob //대용량 데이터
		private String content; //섬머노트 라이브러리 <html>태그가 썩여서 디자인이 됨
		
		// @ColumnDefault("0")
		private int count; //조회수
		
		@ManyToOne(fetch = FetchType.EAGER.EAGER) // Many = Board, User = One
		@JoinColumn(name="userId")
		private User user; //DB는 오브젝트를 저장할 수 없다, 자바는 오브젝트를 저장할 수 없다.
		
		@OneToMany (mappedBy = "board", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE) //mappedBy 연관관계의 주인이 아니다 (난 FK가 아니다.)
		//persist
		@JsonIgnoreProperties({"board"})
		@OrderBy("id desc")
		private List<Reply> replys;
		 
		@CreatedDate //시간이 자동입력
		private LocalDateTime createDate;
}