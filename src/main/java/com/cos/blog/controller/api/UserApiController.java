package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.services.UserService;

@RestController
public class UserApiController {

		@Autowired
		private UserService userService; 
		
		@Autowired 
		private AuthenticationManager authenticationManager;

		
		@PostMapping("/auth/joinProc")
		public ResponseDto<Integer> save(@RequestBody User user) {
			System.out.println("UserApiController : save 호출됨");
			int result = userService.회원가입(user);
			return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
		}
		
		@ PutMapping("/user")
		//key-value, x-www-form-urlencoded'
		public  ResponseDto<Integer> update(@RequestBody User user) {
			userService.회원수정(user);
			
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
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
