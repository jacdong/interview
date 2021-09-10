package com.jacdong.interview.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jacdong.interview.user.mapper.LoginUserMapper;
import com.jacdong.interview.user.service.LoginService;
import com.jacdong.interview.user.vo.LoginUserVO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LoginUserMapper loginMapper;

	@Override
	public LoginUserVO login(LoginUserVO loginUserVO) {
		
		String orginPassword = loginUserVO.getPassword();
		String encodingPwd = passwordEncoder.encode(orginPassword);
		LoginUserVO resultLoginUserVO = loginMapper.user(loginUserVO.getUsername(), encodingPwd);

		return resultLoginUserVO;
	}

}
