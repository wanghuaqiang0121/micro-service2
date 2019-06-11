package org.web.module.bone.age.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.UserUserTypeMapper;
import org.web.module.bone.age.domain.UserUserType;

@Service
public class UserUserTypeService {
	
	@Resource
	private UserUserTypeMapper mapper;
	
	public List<Map<String, Object>> getList(UserUserType userUserType) {
		return mapper.getList(userUserType);
	}
	
	public int insert(UserUserType userUserType) {
		return mapper.insert(userUserType);
	}
	public int delete(UserUserType userUserType) {
		return mapper.delete(userUserType);
	}
}
