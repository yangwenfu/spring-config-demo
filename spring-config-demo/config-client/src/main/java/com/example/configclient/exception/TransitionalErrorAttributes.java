package com.example.configclient.exception;

import java.util.Map;


import org.springframework.web.context.request.RequestAttributes;

/**
 *Create by yangwenfu on 2018/1/30
 */
public class TransitionalErrorAttributes extends CustomErrorAttributes {
	public TransitionalErrorAttributes() {
	}

	public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
		Throwable error = this.getError(requestAttributes);
//		if (error instanceof BaseException) {
//			BaseException baseException = (BaseException)error;
//			DisplayableError exceptionError = baseException.getError();
//			if (exceptionError != null) {
//				requestAttributes.setAttribute("javax.servlet.error.error_code", exceptionError.getErrorCode(), 0);
//				String message = exceptionError.getDisplayMsg();
//				if (message != null) {
//					requestAttributes.setAttribute("javax.servlet.error.message", message, 0);
//				}
//			}
//		}

		return super.getErrorAttributes(requestAttributes, includeStackTrace);
	}
}
