package com.example.configclient.secrity;

/**
 *Create by yangwenfu on 2018/1/30
 */
public interface AuthenticationAdapter<U, T> {
	T getCurrentUserId();

	U getCurrentUserInfo();
}