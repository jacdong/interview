package com.jacdong.interview.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jacdong.interview.common.CommonResult;
import com.jacdong.interview.user.common.ApiVersion;

/**
 * 
 * @ClassName: HelloController
 * @Description: TODO
 * @author DongBin
 * @date 2021-09-06 04:52:25
 */
@ApiVersion(1)
@RequestMapping("/{version}")
@RestController
public class HelloController {

    @GetMapping("/hello")
    @SentinelResource(value = "hello",fallback = "handleFallback",blockHandler="handleException")
    public CommonResult hello() {
    	System.out.println("---------------------------");
    	int i = 1/0;
        return new CommonResult(200, "hello world","say hello to people.");
        
        
    }

    
    public CommonResult handleFallback() {
    	return new CommonResult(200,"降级成功.","降级");
    }
    
    
    public CommonResult handleException(BlockException exception) {
    	return new CommonResult(200,"限流成功.","限流");
    }
}
