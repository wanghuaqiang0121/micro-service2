package org.wechat.module.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.UserOrganizationTeamMapper;
import org.wechat.module.user.domain.UserOrganizationTeam;

@Service
public class UserOrganizationTeamService {
	@Resource
	private UserOrganizationTeamMapper userOrganizationTeamMapper;

	public List<Map<String, Object>> getList(UserOrganizationTeam userOrganizationTeam) {
		return userOrganizationTeamMapper.getList(userOrganizationTeam);
	}
	
}
