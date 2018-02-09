package com.example.configclient.mybatisPlus.service.impl;


import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.configclient.mybatisPlus.dao.UserDao;
import com.example.configclient.mybatisPlus.entity.User;
import com.example.configclient.mybatisPlus.service.IUserService;

import org.springframework.stereotype.Service;

/**
 *Create by yangwenfu on 2018/1/31
 * ServiceImpl<UserDao, User> 这个方法封装了通用的增删改查等
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {




	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<User> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}

	@Override
	public Page<User> selectUserPage(Page<User> page, int age) {
		page.setRecords(baseMapper.selectUserList(page,age));

		return page;
	}
}
