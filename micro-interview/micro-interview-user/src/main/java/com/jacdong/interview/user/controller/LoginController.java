package com.jacdong.interview.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacdong.interview.user.service.LoginService;
import com.jacdong.interview.user.vo.LoginUserVO;


@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/user")
	public LoginUserVO user(@RequestBody LoginUserVO loginUserVO){
		
		LoginUserVO resultLoginUserVO = loginService.login(loginUserVO);
		
		return resultLoginUserVO;
	}
	
}
