package com.jacdong.interview.user.holder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jacdong.interview.user.vo.LoginUserVO;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;

/**
 * 
 * @author DongBin
 *
 */
@Component
public class LoginUserHolder {

    public LoginUserVO getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
        LoginUserVO userDTO = new LoginUserVO();
        userDTO.setUsername(userJsonObject.getStr("user_name"));
        userDTO.setUserId(userJsonObject.getStr("id"));
        userDTO.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
//        userDTO.setRole(userJsonObject.getStr("authorities"));
        return userDTO;
    }
}
