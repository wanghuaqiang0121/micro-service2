package org.web.module.team.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.team.dao.OrganizationTeamRoleMapper;
import org.web.module.team.domain.OrganizationTeamRole;

@Service
public class OrganizationTeamRoleService {

	@Resource
	private OrganizationTeamRoleMapper organizationTeamRoleMapper;

	public List<Map<String, Object>> getList(OrganizationTeamRole organizationTeamRole) {
		return organizationTeamRoleMapper.getList(organizationTeamRole);
	}
}
