package com.example.configclient.secrity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *Create by yangwenfu on 2018/1/30
 */
class SpringSecurityAuthAdapter implements AuthenticationAdapter<CurrentUser, String> {

	@Override
	public String getCurrentUserId() {
		CurrentUser currentUser = getCurrentUserInfo();
		if (currentUser != null) {
			return currentUser.getUserId();
		}
		return null;
	}

	@Override
	public CurrentUser getCurrentUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof CurrentUser) {
			return (CurrentUser) principal;
		}
		return null;
	}
}