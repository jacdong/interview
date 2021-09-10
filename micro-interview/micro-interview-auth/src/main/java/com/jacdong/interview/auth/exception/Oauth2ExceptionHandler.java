package com.jacdong.interview.auth.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jacdong.interview.common.CommonResult;


/**
 * 
 * @ClassName: Oauth2ExceptionHandler
 * @Description: 全局处理Oauth2抛出的异常
 * @author DongBin
 * @date 2021-09-09 10:42:17
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public CommonResult handleOauth2(OAuth2Exception e) {
        return CommonResult.failed(e.getMessage());
    }
}
