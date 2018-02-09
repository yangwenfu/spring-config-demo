package com.example.configclient.secrity;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *Create by yangwenfu on 2018/1/30
 */

class WalletUserDetailService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(WalletUserDetailService.class);

//	@com.alibaba.dubbo.config.annotation.Reference
//	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
//		UserInfoDto userInfoDto = userService.findUserByMobileWithCredentials(username);
//		if (userInfoDto == null) {
//			logger.info("找不到手机号码为 {} 的用户", username);
//			throw new UsernameNotFoundException(username);
//		}
		UserInfoDto userInfoDto = new UserInfoDto();
		SpringSecurityUserAdapter springSecurityUserAdapter = new SpringSecurityUserAdapter(userInfoDto.getMobile(), userInfoDto.getLoginPassword(), Collections.emptyList());
		springSecurityUserAdapter.setUserId(userInfoDto.getUserId());
		springSecurityUserAdapter.setMobile(userInfoDto.getMobile());
		springSecurityUserAdapter.setRealName(userInfoDto.getUserName());
		return springSecurityUserAdapter;
	}
}
