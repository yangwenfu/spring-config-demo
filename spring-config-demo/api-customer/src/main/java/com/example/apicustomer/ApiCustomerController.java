package com.example.apicustomer;

import api.Service;
import com.example.apicustomer.feign.ApiCustomerFeign;
import entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *Create by yangwenfu on 2018/2/5
 */
@RestController
@RequestMapping("/api")
public class ApiCustomerController {

	@Autowired
	private ApiCustomerFeign apiCustomerFeign;
	@Autowired
	private Service service;

	@GetMapping
	public void say(){
		apiCustomerFeign.say();
	}

	@PostMapping
	public User saveUser(@RequestBody User user){
		User save = apiCustomerFeign.save(user);
		return save;
	}
	@GetMapping("/get")
	public String get(@RequestParam("mobile") String mobile){
		String s = apiCustomerFeign.get(mobile);
		return s;
	}
}
