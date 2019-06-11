package org.wechat.module.team.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.team.dao.OrganizationTeamMapper;
import org.wechat.module.team.domain.OrganizationTeam;

@Service
public class OrganizationTeamService {

	@Resource
	private OrganizationTeamMapper organizationTeamMapper;

	
	public Map<String, Object> getOne(OrganizationTeam organizationTeam) {
		return organizationTeamMapper.getOne(organizationTeam);
	}

	
	public List<Map<String, Object>> getList(OrganizationTeam organizationTeam) {
		return organizationTeamMapper.getList(organizationTeam);
	}
	
}
