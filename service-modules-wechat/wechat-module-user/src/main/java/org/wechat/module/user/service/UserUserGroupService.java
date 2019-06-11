package org.wechat.module.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.UserUserGroupMapper;
import org.wechat.module.user.domain.UserUserGroup;

@Service
public class UserUserGroupService {

	@Resource
	private UserUserGroupMapper userUserGroupMapper;

	public int insert(UserUserGroup userUserGroup) {
		return userUserGroupMapper.insert(userUserGroup);
	}

	public int delete(UserUserGroup userUserGroup) {
		return userUserGroupMapper.delete(userUserGroup);
	}

	public Map<String, Object> getRepeat(UserUserGroup userUserGroup) {
		return userUserGroupMapper.getRepeat(userUserGroup);
	}

	public List<Map<String, Object>> getList(UserUserGroup baseUserUserGroup) {
		return userUserGroupMapper.getList(baseUserUserGroup);
	}

}
