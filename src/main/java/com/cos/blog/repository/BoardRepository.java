package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.Board;

//DAO
// 자동으로 bean등록한다.
// @Repository //생략가능
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
	
