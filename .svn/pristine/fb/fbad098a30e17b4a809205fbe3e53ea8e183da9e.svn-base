package org.wechat.module.user.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.UserGroupMapper;
import org.wechat.module.user.domain.UserGroup;

@Service
public class UserGroupService {

	@Resource
	private UserGroupMapper userGroupMapper;

	public Map<String, Object> getRepeat(UserGroup userGroup) {
		return userGroupMapper.getRepeat(userGroup);
	}

	public Integer insert(UserGroup userGroup) {
		return userGroupMapper.insert(userGroup);
	}
}
