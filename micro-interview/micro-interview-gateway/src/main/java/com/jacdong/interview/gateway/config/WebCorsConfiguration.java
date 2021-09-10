package com.jacdong.interview.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class WebCorsConfiguration {
	/**
	 * 
	 * @Title: corsWebFilter
	 * @Description: 解决跨域问题
	 * @return
	 * @author DongBin
	 * @date 2021-09-06 04:42:55
	 */
	@Bean
	public CorsWebFilter corsWebFilter() {
		// config the cors configuration source
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		// 1 config
		// allow all the header go into web cors
		corsConfiguration.addAllowedHeader("*");
		// all all the request
		corsConfiguration.addAllowedMethod("*");
		// allow all the request
		corsConfiguration.addAllowedOrigin("*");
		// allow cors with cookie
		corsConfiguration.setAllowCredentials(true);
		// 2 all the path are be allowed cors
		source.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsWebFilter(source);
	}
}
