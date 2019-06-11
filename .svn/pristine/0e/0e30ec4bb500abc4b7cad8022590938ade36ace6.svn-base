package org.web.module.base.service.permission.team;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.team.OrganizationTeamRolePermissionMapper;
import org.web.module.base.domain.permission.team.OrganizationTeamRolePermission;

@Service
public class OrganizationTeamRolePermissionService {

	@Autowired
	private OrganizationTeamRolePermissionMapper mapper;

	public int insert(OrganizationTeamRolePermission organizationTeamRolePermission) {
		return mapper.insert(organizationTeamRolePermission);
	}

	public int delete(OrganizationTeamRolePermission organizationTeamRolePermission) {
		return mapper.delete(organizationTeamRolePermission);
	}

	public Map<String, Object> getRepeat(OrganizationTeamRolePermission organizationTeamRolePermission) {
		return mapper.getRepeat(organizationTeamRolePermission);
	}

	public List<Map<String, Object>> getTeamRoleHaveAndNotHavePermissions(
			OrganizationTeamRolePermission organizationTeamRolePermission) {
		return mapper.getTeamRoleHaveAndNotHavePermissions(organizationTeamRolePermission);
	}

}
