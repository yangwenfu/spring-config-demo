package com.example.configclient.exception;

/**
 *Create by yangwenfu on 2018/1/30
 */
public class ServiceException extends ErrorCodedException{

	private static final long serialVersionUID = 8950477389029992890L;
	public static final String DEFAULT_ERROR_CODE = "BIZ_ERROR";

	public ServiceException(String message) {
		this("BIZ_ERROR", message);//如果想把我们自己定义的errorCode 需要重写 ErrorAttributes 他的默认实现是DefaultErrorAttributes  参照这个写
	}

	public ServiceException(String errorCode, String message) {
		super(errorCode, message);
	}

	public ServiceException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}
}
