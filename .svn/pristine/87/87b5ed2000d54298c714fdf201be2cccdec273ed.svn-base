package org.wechat.module.user.service.itv;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.itv.UserItvAccountMapper;
import org.wechat.module.user.domain.itv.UserItvAccount;

@Service
public class UserItvAccountService {

	@Resource
	private UserItvAccountMapper itvAccountMapper;

	
	public List<Map<String, Object>> getList(UserItvAccount userItvAccount) {
		return itvAccountMapper.getList(userItvAccount);
	}

	
	public int insert(UserItvAccount userItvAccount) {
		return itvAccountMapper.insert(userItvAccount);
	}


	public Map<String, Object> getRepeat(UserItvAccount userItvAccount) {
		return itvAccountMapper.getRepeat(userItvAccount);
	}

	public Map<String, Object> getOne(UserItvAccount userItvAccount) {
		return itvAccountMapper.getOne(userItvAccount);
	}

	public int delete(UserItvAccount userItvAccount) {
		return itvAccountMapper.delete(userItvAccount);
	}
	
}
