package com.example.configclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 *Create by yangwenfu on 2018/1/29
 */
@Configuration
@AutoConfigureAfter({RedisAutoConfiguration.class}) //这几种注解必须配合META-INF/spring.factories 使用 否则报错
@ConditionalOnBean({StringRedisTemplate.class})
public class RedisConnection {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Bean
	public RedisTempletUtil redisTempletUtil(StringRedisTemplate stringRedisTemplate){
		System.out.println(stringRedisTemplate);
		return new RedisTempletUtil(stringRedisTemplate);
	}

	@Bean
	public A a(){
		return new A();
	}
}
