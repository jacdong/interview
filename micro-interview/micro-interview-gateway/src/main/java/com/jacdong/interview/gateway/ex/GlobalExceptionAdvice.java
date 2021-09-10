package com.jacdong.interview.gateway.ex;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: GlobalExceptionAdvice
 * @Description: 全局异常捕获
 * @author DongBin
 * @date 2021-09-07 10:46:36
 */
@Slf4j
@ControllerAdvice//切面增强Controller
public class GlobalExceptionAdvice {
    
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public R error(Exception e){
        log.error(ExceptionUtils.getMessage(e));
        e.printStackTrace();
        return R.error().message("执行全局异常处理...");
    }

    //特定异常
    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseBody
    public R error(MyBatisSystemException e){
        log.error(ExceptionUtils.getMessage(e));
        e.printStackTrace();
        return R.error().message("执行MyBatisSystemException异常处理...");
    }

    //自定义异常处理
    @ExceptionHandler(GlobalException.class)
    @ResponseBody//为了返回数据
    public R error(GlobalException e){
        log.error(ExceptionUtils.getMessage(e));
        e.printStackTrace();
        return R.error().code(e.getCode().longValue()).message(e.getMsg());
    }
}