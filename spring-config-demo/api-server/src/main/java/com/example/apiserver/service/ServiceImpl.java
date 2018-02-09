package com.example.apiserver.service;


import api.Service;
import entity.User;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *Create by yangwenfu on 2018/2/2
 */
@RestController
public class ServiceImpl implements Service {
	@Override
	public String get(@RequestParam("mobile") String mobile) {
		String s = mobile;
		return s;
	}

	@Override
	public User save(@RequestBody User user) {
		User user1 = new User();
		user1.setName(user.getName());
		user1.setAge(user.getAge());
		return user1;
	}

	@Override
	public void say() {
		System.out.println("hello3");
	}
}
