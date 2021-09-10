package com.jacdong.interview.user.service;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jacdong.interview.user.vo.UserVO;


public interface UserService {
	
	@PostMapping("/user")
	public int create(UserVO userVo);
	
	@PutMapping("/user/update")
	public int update(UserVO userVO);

	@GetMapping("/user/{userId}")
	public UserVO getUser(@PathVariable String userId);

	@DeleteMapping("/user/{userId}")
	public int delUser(@PathVariable("id") String userId) throws InterruptedException;
	
}
