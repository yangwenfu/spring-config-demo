package com.example.configclient.secrity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 *Create by yangwenfu on 2018/1/30
 */
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable(); // 禁止 csrf
		configureAccess(http);
		configureExceptionHandling(http);
		configureLogin(http);
		configureLogout(http);
	}

	private void configureAccess(HttpSecurity http) throws Exception {
		http
				.authorizeRequests().antMatchers("/api/**").authenticated(); // 所有 /api/** 路径都需要身份认证

	}

	private void configureExceptionHandling(HttpSecurity http) throws Exception {
		http
				.exceptionHandling().authenticationEntryPoint(new Http401AuthenticationEntryPoint(""));
	}

	private void configureLogin(HttpSecurity http) throws Exception {
		http
				.apply(new JsonFormLoginConfigurer<>()).loginProcessingUrl("/login")
				.successHandler(new ForwardAuthenticationSuccessHandler("/api/token"))
				.failureHandler(new WalletAuthenticationFailureHandler("https://www.baidu.com/"));
	}

	private void configureLogout(HttpSecurity http) throws Exception {
		http.logout().logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)).and();
	}


	@Bean
	public WalletUserDetailService userDetailService() {
		return new WalletUserDetailService();
	}

	@Bean
	public SpringSecurityAuthAdapter authAdapter() {
		return new SpringSecurityAuthAdapter();
	}

	@Bean
	public YlfinUserPasswordEncoder passwordEncoder() {
		return new YlfinUserPasswordEncoder();
	}
}