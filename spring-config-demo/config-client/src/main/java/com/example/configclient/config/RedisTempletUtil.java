package com.example.configclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 *Create by yangwenfu on 2018/1/29
 */

public class RedisTempletUtil {
	private StringRedisTemplate stringRedisTemplate;

	public RedisTempletUtil(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

	public void sava(){
		BoundSetOperations<String, String> yang =
				stringRedisTemplate.boundSetOps("yang");
		yang.add("hello");
	}

}
