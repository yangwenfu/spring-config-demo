package com.example.configclient.mybatisPlus.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.configclient.mybatisPlus.entity.User;

/**
 *Create by yangwenfu on 2018/1/31
 */
public interface IUserService extends IService<User> {
	boolean deleteAll();

	List<User> selectListBySQL();

	Page<User> selectUserPage(Page<User> page, int age);
}
