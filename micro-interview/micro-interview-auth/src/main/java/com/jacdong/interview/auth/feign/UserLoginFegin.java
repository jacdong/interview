package com.jacdong.interview.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.jacdong.interview.user.service.LoginService;

/**
 * 
 * @ClassName: UserLoginFegin
 * @Description: TODO
 * @author DongBin
 * @date 2021-09-09 10:42:27
 */

@FeignClient(name = "micro-interview-user")
public interface UserLoginFegin extends LoginService {

}
