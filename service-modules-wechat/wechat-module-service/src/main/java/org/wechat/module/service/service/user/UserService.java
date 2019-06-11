package org.wechat.module.service.service.user;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.user.UserMapper;
import org.wechat.module.service.domain.user.User;

@Service
public class UserService {
	@Resource
	private UserMapper userMapper;

	public Map<String, Object> getOne(User user) {
		return userMapper.getOne(user);
	}

	public Map<String, Object> getWechat(User user) {
		return userMapper.getWechat(user);
	}
	
}
