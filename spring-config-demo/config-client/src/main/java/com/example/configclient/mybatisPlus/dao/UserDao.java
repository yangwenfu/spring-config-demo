package com.example.configclient.mybatisPlus.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.configclient.mybatisPlus.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *Create by yangwenfu on 2018/1/31
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
	/**
	 * 自定义注入方法
	 */
	int deleteAll();

	/**
	 * 用mapper.xml的方式执行sql
	 */

	List<User> selectListBySQL();

	List<User> selectUserList(Page<User> page,int age);
}
