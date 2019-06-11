package org.wechat.module.service.service.user;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.user.UserServiceMapper;
import org.wechat.module.service.domain.user.UserService;

@Service
public class UserServiceService {

	@Resource
	private UserServiceMapper userServiceMapper;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月18日
	 * @param userService
	 * @return
	 * @description: 扣次数
	 */
	public int lockTime(UserService userService) {
		return userServiceMapper.lockTime(userService);
	}

	public Map<String, Object> getOne(UserService userService) {
		return userServiceMapper.getOne(userService);
	}


}
