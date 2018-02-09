package com.example.configclient.exception;

/**
 *Create by yangwenfu on 2018/1/30
 */
public interface ErrorCoded {
	String ATTR_ERROR_CODE = "javax.servlet.error.error_code";

	String getErrorCode();
}
