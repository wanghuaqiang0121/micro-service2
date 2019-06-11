package org.wechat.module.service.service.contract;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.contract.UserSignMapper;
import org.wechat.module.service.domain.contract.UserSign;

@Service
public class UserSignService {

	@Resource
	private UserSignMapper userSignMapper;

	public int insert(UserSign userSign) {
		return userSignMapper.insert(userSign);
	}

	public int update(UserSign userSign) {
		return userSignMapper.update(userSign);
	}
	public Map<String, Object> getOne(UserSign userSign) {
		return userSignMapper.getOne(userSign);
	}

	
}
