package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserMapper;
import org.web.module.height.obesity.entity.User;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月27日
 * @description: UserService
 */
@Service
public class UserService {
	@Resource
	private UserMapper mapper;

	public List<Map<String, Object>> getList(User user) {
		return mapper.getList(user);
	}
	public Map<String,Object> getOne(User user){
		return mapper.getOne(user);
	}
}
