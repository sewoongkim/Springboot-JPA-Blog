package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;


//DAO
// 자동으로 bean등록한다.
// @Repository //생략가능
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
	
