package com.example.configclient.demo;

import javax.servlet.http.HttpSession;

import api.Service;
import com.example.configclient.config.A;
import com.example.configclient.config.RedisTempletUtil;
import com.example.configclient.exception.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 如果想用配置刷新的功能 要加上注解@RefreshScope才会起作用的的 还要加上的actuator
 * http://localhost:2222/refresh 用postman调用这个post方法 就可以获取到了最新的配置
 * 如果不想手动访问这个路径可以把他配置在github上的githook
 *Create by yangwenfu on 2018/1/25
 */
@RestController
@RefreshScope
public class ConfigClientController {
	@Value("${config}")
	private String config;
	@Value("${public}")
	private String p;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTempletUtil redisTempletUtil;
	@Autowired
	private A a;


	@GetMapping("/api/get")
	public String getConfig(){
		redisTempletUtil.sava();
		System.out.println("_ _ _ _ ________");
		System.out.println(stringRedisTemplate);
		BoundValueOperations<String, String> hh = stringRedisTemplate.boundValueOps("hh");
		hh.set("456");
		System.out.println("~~~~~~~~~~~~~~~");
		a.say();
		return config+"----"+p;
	}

	@GetMapping("/api/getException")
	public void getException(){
		throw new ServiceException("yihcang");

	}

	@PostMapping("/api/token")
	public String token(HttpSession session) {
		return session.getId();
	}


}
