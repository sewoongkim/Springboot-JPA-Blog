package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/**허용
//그냥 주소가 / 이면 index.jsp허용
//static이하에 있는 /js/**, /css/**, /image/** 
@Controller
public class UserController {

	@GetMapping("/auth/joinForm") 
	public String join() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm") 
	public String loginForm() {
		System.out.println("로그인 요청");
		return "user/loginForm";
	}	

	@GetMapping("/user/updateForm") 
	public String updateForm() {
		return "user/updateForm";
	}
}
