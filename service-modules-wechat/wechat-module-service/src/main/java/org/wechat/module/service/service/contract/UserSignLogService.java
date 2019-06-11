package org.wechat.module.service.service.contract;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.contract.UserSignLogMapper;
import org.wechat.module.service.domain.contract.UserSignLog;

@Service
public class UserSignLogService {

	@Resource
	private UserSignLogMapper signLogMapper;

	public int insert(UserSignLog userSignLog) {
		return signLogMapper.insert(userSignLog);
	}
	
}
