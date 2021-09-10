package com.jacdong.interview.user.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jacdong.interview.user.vo.LoginUserVO;

public interface LoginService {

	@PostMapping("/login/user")
	public LoginUserVO login(@RequestBody LoginUserVO loginUserVO);
}
