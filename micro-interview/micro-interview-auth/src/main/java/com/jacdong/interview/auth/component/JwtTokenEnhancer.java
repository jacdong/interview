package com.jacdong.interview.auth.component;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.jacdong.interview.auth.domain.SecurityUser;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: JwtTokenEnhancer
 * @Description: TODO
 * @author DongBin
 * @date 2021-09-02 04:46:52
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
		Map<String, Object> info = new HashMap<>();
		// 把用户ID设置到JWT中
		info.put("id", securityUser.getId());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
}
