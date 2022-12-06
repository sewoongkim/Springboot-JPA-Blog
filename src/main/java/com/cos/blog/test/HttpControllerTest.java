package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;

//사용자가 요청 -> 응답 (HTML파일)

//사용자가 요청 --> 응답 (Data)
@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest : ";


	@GetMapping("/http/lombok")
	public String getlombokTest() {
		//Member m = new Member (3,"ssar","1234","email");
		Member m = Member.builder().password("11").userName("dd").email("22@ee.com").build();
		System.out.println(TAG + "getter :" + m.getUserName());
		m.setUserName("cc");
		System.out.println(TAG + "getter :" + m.getUserName());
		return "lombok test  완료";
	}
	//인터넷 브라우저 요청은 무조건  get요청밖에 할 수 없다.
	//http://localhost:8080/http/get
	@GetMapping("/http/get")
	public String getTest(@RequestParam int id, @ RequestParam String userName, @ RequestParam String password, @ RequestParam String email  ) {
		 return "get 요청  입니다." + id + "," + userName+ "," + password+ "," + email;
	 }
	
	@GetMapping("/http/getMember") // MessageConverter (스프링부트)
	public String getTest2(Member m ) {
		System.out.println(TAG + "getter :" + m.getId());
		m.setId(5000);
		System.out.println(TAG + "getter :" + m.getId());
	    return "get 요청  입니다." + m.getId() + "," + m.getUserName() + "," + m.getPassword() + "," + m.getEmail();
	 }	
	 //
	//http://localhost:8080/http/post
	@PostMapping("/http/post")
	 public String postTest(Member m ) {
		return "post 요청  입니다." + m.getId() + "," + m.getUserName() + "," + m.getPassword() + "," + m.getEmail();
	 }

	@PostMapping("/http/RequestBodypost") //text/plain
	 public String postTest(@RequestBody String bodystring ) {
		return "post 요청  입니다." + bodystring;
	 }
	 
	@PostMapping("/http/RequestBodyJsonpost") //application/JSON // MessageConverter (스프링부트)
	 public String RequestBodyJsonpostTest(@RequestBody Member m ) {
		return "post 요청  입니다." + m.getId() + "," + m.getUserName() + "," + m.getPassword() + "," + m.getEmail();
	 }
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put요청  입니다." + m.getId() + "," + m.getUserName() + "," + m.getPassword() + "," + m.getEmail();
		 
	 }

	@DeleteMapping("/http/delete")
	public String DeleteTest() {
		 return "delete  요청";
		 
	 }
	 
}
