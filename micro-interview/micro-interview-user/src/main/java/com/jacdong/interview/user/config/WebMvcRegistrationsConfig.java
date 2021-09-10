package com.jacdong.interview.user.config;


import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.jacdong.interview.user.common.ApiRequestMappingHandlerMapping;

/**
 * 
 * @ClassName: WebMvcRegistrationsConfig
 * @Description: TODO
 * @author DongBin
 * @date 2021-09-09 10:53:57
 */
@Configuration
public class WebMvcRegistrationsConfig implements WebMvcRegistrations {
	@Override
	public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
		return new ApiRequestMappingHandlerMapping();
	}
}
