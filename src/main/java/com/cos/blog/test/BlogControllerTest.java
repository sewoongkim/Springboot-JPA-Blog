package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//스프링이 com.cos.blog  패키지 이하를 스캔하여 모든 파일을 메모리에 new 하는 것이 아니고요.
//특정
public class BlogControllerTest {

	@GetMapping("/test/hello")
	public String hello(){
		return "<h1> hello spring boot </h1>";
		}
}
