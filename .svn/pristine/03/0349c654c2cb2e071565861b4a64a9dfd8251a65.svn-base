package org.web.module.bone.age.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.UserMapper;
import org.web.module.bone.age.domain.User;

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
	public Map<String, Object> getRepeat(User user) {
		return mapper.getRepeat(user);
	}
	public int update(User user) {
		return mapper.update(user);
	}
	

	/**
	 * @author: ChenYan
	 * @date: 2019年4月29日
	 * @param user
	 * @return
	 * @description: 查询是否是用户组电话
	 */
	public List<Map<String, Object>> getUserGroupPhone(User user){
		return mapper.getUserGroupPhone(user);
	}
}
