package org.web.module.base.service.permission.team;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.team.OrganizationTeamRoleMapper;
import org.web.module.base.domain.permission.team.OrganizationTeamRole;

@Service
public class OrganizationTeamRoleService {

	@Autowired
	private OrganizationTeamRoleMapper organizationTeamRoleMapper;

	public int insert(OrganizationTeamRole organizationTeamRole) {
		return organizationTeamRoleMapper.insert(organizationTeamRole);
	}

	public int update(OrganizationTeamRole organizationTeamRole) {
		return organizationTeamRoleMapper.update(organizationTeamRole);
	}

	public int delete(OrganizationTeamRole organizationTeamRole) {
		return organizationTeamRoleMapper.delete(organizationTeamRole);
	}

	public Map<String, Object> getOne(OrganizationTeamRole organizationTeamRole) {
		return organizationTeamRoleMapper.getOne(organizationTeamRole);
	}

	public Map<String, Object> getRepeat(OrganizationTeamRole organizationTeamRole) {
		return organizationTeamRoleMapper.getRepeat(organizationTeamRole);
	}

	public List<Map<String, Object>> getList(OrganizationTeamRole organizationTeamRole) {
		return organizationTeamRoleMapper.getList(organizationTeamRole);
	}

}
