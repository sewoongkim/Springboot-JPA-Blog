package com.cos.blog.test;

import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
// import lombok.Getter;
// import lombok.Setter;
// import lombok.RequiredArgsConstructor;

// @Getter
// @Setter
//@RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Getter
//@Setter
public class Member {
	private int id;
	private String userName;
	private String password;
	private String email;

}
