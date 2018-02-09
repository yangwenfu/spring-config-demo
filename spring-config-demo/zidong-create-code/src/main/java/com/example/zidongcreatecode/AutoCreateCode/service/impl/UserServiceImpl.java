package com.example.zidongcreatecode.AutoCreateCode.service.impl;

import com.example.zidongcreatecode.AutoCreateCode.entity.User;
import com.example.zidongcreatecode.AutoCreateCode.mapper.UserDao;
import com.example.zidongcreatecode.AutoCreateCode.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang
 * @since 2018-01-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
	
}
