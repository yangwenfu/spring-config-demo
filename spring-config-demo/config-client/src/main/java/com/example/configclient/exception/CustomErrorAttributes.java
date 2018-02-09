package com.example.configclient.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 *Create by yangwenfu on 2018/1/30
 */

@Order(-2147483648)
public class CustomErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered {
	private static final String ERROR_ATTRIBUTE = CustomErrorAttributes.class.getName() + ".ERROR";

	public CustomErrorAttributes() {
	}

	public int getOrder() {
		return -2147483648;
	}

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		this.storeErrorAttributes(request, ex);
		return null;
	}

	private void storeErrorAttributes(HttpServletRequest request, Exception ex) {
		request.setAttribute(ERROR_ATTRIBUTE, ex);
	}

	public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
		Map<String, Object> errorAttributes = new LinkedHashMap();
		errorAttributes.put("timestamp", new Date());
		this.addStatus(errorAttributes, requestAttributes);
		this.addErrorCode(errorAttributes, requestAttributes, this.getError(requestAttributes));//多加上这个方法来实现添加自定义的errorCode 其他没变
		this.addErrorDetails(errorAttributes, requestAttributes, includeStackTrace);
		this.addPath(errorAttributes, requestAttributes);
		return errorAttributes;
	}

	private void addStatus(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
		Integer status = (Integer)this.getAttribute(requestAttributes, "javax.servlet.error.status_code");
		if (status == null) {
			errorAttributes.put("status", 999);
			errorAttributes.put("error", "None");
		} else {
			errorAttributes.put("status", status);

			try {
				errorAttributes.put("error", HttpStatus.valueOf(status).getReasonPhrase());
			} catch (Exception var5) {
				errorAttributes.put("error", "Http Status " + status);
			}

		}
	}

	private void addErrorCode(Map<String, Object> errorAttributes, RequestAttributes requestAttributes, Throwable error) {
		String code = (String)this.getAttribute(requestAttributes, "javax.servlet.error.error_code");
		if (code == null && error instanceof ErrorCoded) {
			ErrorCoded errorCoded = (ErrorCoded)error;
			code = errorCoded.getErrorCode();
		}

		if (StringUtils.hasText(code)) {
			errorAttributes.put("errorCode", code);
		}

	}

	private void addErrorDetails(Map<String, Object> errorAttributes, RequestAttributes requestAttributes, boolean includeStackTrace) {
		Throwable error = this.getError(requestAttributes);
		if (error != null) {
			while(true) {
				if (!(error instanceof ServletException) || error.getCause() == null) {
					errorAttributes.put("exception", error.getClass().getName());
					this.addErrorMessage(errorAttributes, error);
					if (includeStackTrace) {
						this.addStackTrace(errorAttributes, error);
					}
					break;
				}

				error = error.getCause();
			}
		}

		Object message = this.getAttribute(requestAttributes, "javax.servlet.error.message");
		if ((!StringUtils.isEmpty(message) || errorAttributes.get("message") == null) && !(error instanceof BindingResult)) {
			errorAttributes.put("message", StringUtils.isEmpty(message) ? "No message available" : message);
		}

	}

	private void addErrorMessage(Map<String, Object> errorAttributes, Throwable error) {
		BindingResult result = this.extractBindingResult(error);
		if (result == null) {
			if (error instanceof ErrorCodedException) {
				errorAttributes.put("message", error.getMessage());
			} else {
				errorAttributes.put("message", "系统忙, 请稍后再试");
				errorAttributes.put("errorMessage", error.getMessage());
			}
		} else {
			if (result.getErrorCount() > 0) {
				errorAttributes.put("errors", result.getAllErrors());
				errorAttributes.put("message", this.getBindingResultMessage(result));
			} else {
				errorAttributes.put("message", "参数绑定失败");
			}

		}
	}

	private String getBindingResultMessage(BindingResult result) {
		StringBuilder sb = new StringBuilder();

		for(Iterator var3 = result.getAllErrors().iterator(); var3.hasNext(); sb.append("\n")) {
			ObjectError objectError = (ObjectError)var3.next();
			if (objectError instanceof FieldError) {
				sb = this.appendFieldError(sb, (FieldError)objectError);
			} else {
				sb = this.appendObjectError(sb, objectError);
			}
		}

		return sb.toString();
	}

	private StringBuilder appendFieldError(StringBuilder sb, FieldError fieldError) {
		return sb.append(fieldError.getObjectName()).append(".").append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage());
	}

	private StringBuilder appendObjectError(StringBuilder sb, ObjectError objectError) {
		return sb.append(objectError.getObjectName()).append(":").append(objectError.getDefaultMessage());
	}

	private BindingResult extractBindingResult(Throwable error) {
		if (error instanceof BindingResult) {
			return (BindingResult)error;
		} else {
			return error instanceof MethodArgumentNotValidException ? ((MethodArgumentNotValidException)error).getBindingResult() : null;
		}
	}

	private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
		StringWriter stackTrace = new StringWriter();
		error.printStackTrace(new PrintWriter(stackTrace));
		stackTrace.flush();
		errorAttributes.put("trace", stackTrace.toString());
	}

	private void addPath(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
		String path = (String)this.getAttribute(requestAttributes, "javax.servlet.error.request_uri");
		if (path != null) {
			errorAttributes.put("path", path);
		}

	}

	public Throwable getError(RequestAttributes requestAttributes) {
		Throwable exception = (Throwable)this.getAttribute(requestAttributes, ERROR_ATTRIBUTE);
		if (exception == null) {
			exception = (Throwable)this.getAttribute(requestAttributes, "javax.servlet.error.exception");
		}

		return exception;
	}

	private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
		return (T) requestAttributes.getAttribute(name, 0);
	}

}
