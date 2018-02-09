package com.example.configclient.secrity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.session.web.http.MultiHttpSessionStrategy;

/**
 *Create by yangwenfu on 2018/1/30
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = SpringSessionConfiguration.SESSION_SECS)
public class SpringSessionConfiguration {

	public static final int SESSION_SECS = 60 * 60 * 24 * 30;

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new CompositeHttpSessionStrategy();
	}

	private static class CompositeHttpSessionStrategy implements MultiHttpSessionStrategy {

		private static final String DEFAULT_SESSION_NAME = "token";

		private HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();

		private CookieHttpSessionStrategy cookieHttpSessionStrategy = new CookieHttpSessionStrategy();

		public CompositeHttpSessionStrategy() {
			this(DEFAULT_SESSION_NAME);
		}

		public CompositeHttpSessionStrategy(String sessionName) {
			headerHttpSessionStrategy.setHeaderName(sessionName);
			DefaultCookieSerializer serializer = new DefaultCookieSerializer();
			serializer.setCookieMaxAge(SESSION_SECS);
			serializer.setUseSecureCookie(true);
			serializer.setUseHttpOnlyCookie(true);
			serializer.setCookieName(sessionName);
			cookieHttpSessionStrategy.setCookieSerializer(serializer);
		}

		@Override
		public String getRequestedSessionId(HttpServletRequest request) {
			// 优先从 header 中获取
			String sessionId = headerHttpSessionStrategy.getRequestedSessionId(request);
			if (sessionId == null) {
				sessionId = cookieHttpSessionStrategy.getRequestedSessionId(request);
			}
			return sessionId;
		}

		@Override
		public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
			headerHttpSessionStrategy.onNewSession(session, request, response);
			cookieHttpSessionStrategy.onNewSession(session, request, response);
		}

		@Override
		public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
			headerHttpSessionStrategy.onInvalidateSession(request, response);
			cookieHttpSessionStrategy.onInvalidateSession(request, response);
		}

		@Override
		public HttpServletRequest wrapRequest(HttpServletRequest request, HttpServletResponse response) {
			return cookieHttpSessionStrategy.wrapRequest(request, response);
		}

		@Override
		public HttpServletResponse wrapResponse(HttpServletRequest request, HttpServletResponse response) {
			return cookieHttpSessionStrategy.wrapResponse(request, response);
		}
	}
}
