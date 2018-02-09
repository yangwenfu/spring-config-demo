package com.example.configclient.secrity;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 *Create by yangwenfu on 2018/1/30
 */
class JsonFormLoginConfigurer<H extends HttpSecurityBuilder<H>> extends
		AbstractAuthenticationFilterConfigurer<H, JsonFormLoginConfigurer<H>, JsonUsernamePasswordAuthenticationFilter> {
	public JsonFormLoginConfigurer() {
		super(new JsonUsernamePasswordAuthenticationFilter(), "/login");
		getAuthenticationFilter().setUsernameParameter("username");
		getAuthenticationFilter().setPasswordParameter("password");
	}

	@Override
	protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
		return new AntPathRequestMatcher(loginProcessingUrl, "POST");
	}
}