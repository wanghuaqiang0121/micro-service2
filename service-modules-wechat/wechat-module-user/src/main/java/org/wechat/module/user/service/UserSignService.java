package org.wechat.module.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.UserSignMapper;
import org.wechat.module.user.domain.UserSign;

@Service
public class UserSignService {

	@Resource
	private UserSignMapper userSignMapper;

	public List<Map<String, Object>> getList(UserSign userSign) {
		return userSignMapper.getList(userSign);
	}
}
