package com.example.configclient.exception;

import javax.servlet.Servlet;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *Create by yangwenfu on 2018/1/30
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnMissingBean(
		value = {ErrorAttributes.class},
		search = SearchStrategy.CURRENT
)
//@ConditionalOnProperty(
//		prefix = "ylfin.mvc.error-attributes",
//		name = {"type"},
//		havingValue = "custom"
//)
@AutoConfigureBefore({ErrorMvcAutoConfiguration.class})
public class CustomErrorMvcAutoConfiguration {
	public CustomErrorMvcAutoConfiguration() {
	}

	@Bean
//	@ConditionalOnMissingClass({"com.xinyunlian.jinfu.common.exception.BaseException"})
	public CustomErrorAttributes customErrorAttributes() {
		return new CustomErrorAttributes();
	}

//	@Bean
//	@ConditionalOnClass(
//			name = {"com.xinyunlian.jinfu.common.exception.BaseException"}
//	)
//	public TransitionalErrorAttributes transitionalErrorAttributes() {
//		return new TransitionalErrorAttributes();
//	}
}
