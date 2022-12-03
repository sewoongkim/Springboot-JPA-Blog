package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


//html파일이 아니라 data를 리턴해주는 controller 
//요청: 웹브라우저 
// user 객체 = 자바 오브젝트
// 변환 (웹브라우저가 이해할 수 있는 데이터) -> jaon(Gson 라이브러리)
// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
// 만약에 자바 오브젝트를 리턴하게 되면 Message convert가 Jackson  라이브러이를 호출해서
// user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	// save 함수는 id를 전달하지 않으면 insert를 해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이가 없으면 insert를 해요.
	// email, password

	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id){
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당  id는 존재하지 않습니다.";
		}
		return  ("삭제 되었습니다. id : " + id);
	}
	
	@Transactional //함수 종료시 자동 commit 됨
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		// json 데이터 요청 => Java Object(MessageConverter의 Jackson라이브러리가 로 변환해서 받아줘요.)
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());

		User user = userRepository.findById(id).orElseThrow(()->{  //영속화
			return new IllegalArgumentException("수정에 실패하였습니다." + id) ;
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		user.setRole(RoleType.ADMIN);

		 // userRepository.save(user);
		
		// requestUser.setId(id);
		// requestUser.setUsername("aaa");
		// userRepository.save(requestUser);
		return user;
	}
	
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2개에 데이터를 리턴받아 볼 예정
	@GetMapping("dummy/user")
	public List<User> PageList(@PageableDefault(size=2, sort="id", direction =Sort.Direction.DESC) Pageable pageable){
		Page<User> PagingUser = userRepository.findAll(pageable);
		List<User> users = PagingUser.getContent();
		return users;
	}
	
	//한 페이지당 2개에 데이터를 리턴받아 볼 예정
	@GetMapping("dummy/userListInfo")
	public Page<User> PageListInfo(@PageableDefault(size=2, sort="id", direction =Sort.Direction.DESC) Pageable pageable){
		Page<User> users = userRepository.findAll(pageable);
		return users;
	}

	// {id} 주소로 파라미터를 전달 받을 수 있음.
	//http://localhost:8000/blog/dummy/user/3
	//그럼 return null이 리턴 되자니.. 그럼 프로그램에 문제가 있지 않겠니?
	//Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서  return해
	@GetMapping("/dummy/user/{id}")
	public User detaile(@PathVariable int id) {
		
//		User user = userRepository.findById(id).get();
		
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//				@Override
//				public User get() {
//					return new User();
//				}			
//		});
		
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//
//			@Override
//			public IllegalArgumentException get() {
//				return new  IllegalArgumentException("해당 유저는 없습니다.  :" + id) ;
//			}
//		});
		
//    람다식		
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("해당 유저는 없습니다. 람다  :" + id) ;
		});
		return user;
	}
	
	
	//http://localhost:8000/blog/dummy/join (요청)
	//http의 Body에 username,password,email 
	@PostMapping("/dummy/joinRequestParam") 
	public String joinRequestParam(@RequestParam("username") String username, @RequestParam("password") String password,  @RequestParam("email") String email) {
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		System.out.println("email : " + email);
		return "회원 가입이 완료 되었습니다";
	} 

	@PostMapping("/dummy/join") 
	public String join(String username, String password,  String email) {  //Key=value (약속)
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		System.out.println("email : " + email);
		return "회원 가입이 완료 되었습니다";
		
	} 
	
	@PostMapping("/dummy/joinObject") 
	public String joinObject(User user) {  //Key=value (약속)
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "joinObject 회원 가입이 완료 되었습니다";
	} 

}
