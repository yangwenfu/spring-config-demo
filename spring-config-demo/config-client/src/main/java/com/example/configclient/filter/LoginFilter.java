package com.example.configclient.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.configclient.secrity.AuthenticationAdapter;
import com.example.configclient.secrity.ThreadContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *Create by yangwenfu on 2018/1/29
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/get/*")
public class LoginFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	private AuthenticationAdapter authenticationAdapter;

	public LoginFilter(AuthenticationAdapter authenticationAdapter) {
		this.authenticationAdapter = authenticationAdapter;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//		System.out.println(servletRequest);
//		System.out.println(servletResponse);
//		System.out.println(filterChain);
//		filterChain.doFilter(servletRequest,servletResponse);//这个要有不然不会请求到路径 一般都是写在 finally

		try {
			Object currentUserId = this.authenticationAdapter.getCurrentUserId();
			if (currentUserId != null) {
				ThreadContextUtils.setUserId(currentUserId == null ? "unknown" : String.valueOf(currentUserId));
			}
		} catch (Exception var8) {
			logger.error("com.ylfin.core.tool.ThreadContextUtils 设置 userId 失败", var8);
		} finally {
			filterChain.doFilter(request, response);
		}


	}


}
