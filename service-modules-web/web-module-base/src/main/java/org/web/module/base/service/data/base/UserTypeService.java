package org.web.module.base.service.data.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.data.base.UserTypeMapper;
import org.web.module.base.domain.data.base.UserType;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月16日
 * @description: UserTypeService
 */
@Service
public class UserTypeService {

	@Resource
	private UserTypeMapper userTypeMapper;

	public int insert(UserType userType) {
		return userTypeMapper.insert(userType);
	}

	public int update(UserType userType) {
		return userTypeMapper.update(userType);
	}

	public int delete(UserType userType) {
		return userTypeMapper.delete(userType);
	}

	public Map<String, Object> getOne(UserType userType) {
		return userTypeMapper.getOne(userType);
	}

	public Map<String, Object> getRepeat(UserType userType) {
		return userTypeMapper.getRepeat(userType);
	}

	public List<Map<String, Object>> getList(UserType userType) {
		return userTypeMapper.getList(userType);
	}

}
