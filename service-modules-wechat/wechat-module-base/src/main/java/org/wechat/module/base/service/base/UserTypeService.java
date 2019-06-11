package org.wechat.module.base.service.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.base.dao.base.UserTypeMapper;
import org.wechat.module.base.domain.base.UserType;

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
