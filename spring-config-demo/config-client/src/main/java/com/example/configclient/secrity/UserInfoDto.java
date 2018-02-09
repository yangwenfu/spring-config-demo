package com.example.configclient.secrity;

import java.util.Date;

/**
 *Create by yangwenfu on 2018/1/30
 */

public class UserInfoDto {
	private static final long serialVersionUID = 1L;
	private String userId="123456";
	private String mobile="15558127163";
	private String userName="yangwenfu";
	private String loginPassword="123456";

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
}
