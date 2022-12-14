package com.cos.blog.controller.api;

import javax.persistence.EntityListeners;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.services.BoardService;
import com.cos.blog.services.UserService;

@EntityListeners(value = {AuditingEntityListener.class})
@RestController
public class BoardApiController {

		@Autowired
		private BoardService boardService;
				
		@PostMapping("/api/board")
		public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
			System.out.println("BoardApiController : 글쓰기 호출됨");
			System.out.println(board.getContent());
			boardService.글쓰기(board, principal.getUser());
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		}

		@DeleteMapping("/api/board/{id}")
		public ResponseDto<Integer> deleteById(@PathVariable int id){
			boardService.글삭제하기(id);
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		}

		@PutMapping("/api/board/{id}")
		public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){
			boardService.글수정하기(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		}

		 @PostMapping("/api/board/{boardId}/reply") 
		 public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto ) {
			 System.out.println(replySaveRequestDto);
			 boardService.댓글쓰기2(replySaveRequestDto); 
			 return new  ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
		 }
		 
			@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
			public ResponseDto<Integer> replySave(@PathVariable int replyId) {
				System.out.println("replyId : " + replyId);
				boardService.댓글삭제(replyId);
				return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
			}
		


		//스프링 시큐리티 이용해서 로그인!!
		/*
		* @Autowired
		 * private HttpSession session;
		 * @PostMapping("/api/user/login") public ResponseDto<Integer>
		 * login(@RequestBody User user) {
		 * System.out.println("UserApiController : login 호출됨"); User principal =
		 * userService.로그인(user); // principal (접근주체)
		 * 
		 * if( principal != null){ session.setAttribute("principal", principal); }
		 * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
		 */ 
	}
