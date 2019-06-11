package org.wechat.module.service.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.user.UserUserTypeMapper;
import org.wechat.module.service.domain.user.UserUserType;

@Service
public class UserUserTypeService {
	@Resource
	private UserUserTypeMapper userTypeMapper;
	
	public int batchInsert(List<UserUserType> userUserType){
		return userTypeMapper.batchInsert(userUserType);
	}
	
	public int delete(UserUserType userUserType){
		return userTypeMapper.delete(userUserType);
	}
}
