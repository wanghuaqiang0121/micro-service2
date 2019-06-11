package org.web.module.base.service.permission.team;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.team.OrganizationTeamPermissionMapper;
import org.web.module.base.domain.permission.team.OrganizationTeamPermission;

@Service
public class OrganizationTeamPermissionService {

	@Autowired
	private OrganizationTeamPermissionMapper organizationTeamPermissionMapper;

	public int insert(OrganizationTeamPermission organizationTeamPermission) {
		return organizationTeamPermissionMapper.insert(organizationTeamPermission);
	}

	public int delete(OrganizationTeamPermission organizationTeamPermission) {
		return organizationTeamPermissionMapper.delete(organizationTeamPermission);
	}

	public int update(OrganizationTeamPermission organizationTeamPermission) {
		return organizationTeamPermissionMapper.update(organizationTeamPermission);
	}

	public Map<String, Object> getOne(OrganizationTeamPermission organizationTeamPermission) {
		return organizationTeamPermissionMapper.getOne(organizationTeamPermission);
	}

	public Map<String, Object> getRepeat(OrganizationTeamPermission organizationTeamPermission) {
		return organizationTeamPermissionMapper.getRepeat(organizationTeamPermission);
	}

	public List<Map<String, Object>> getList(OrganizationTeamPermission organizationTeamPermission) {
		return organizationTeamPermissionMapper.getList(organizationTeamPermission);
	}

}
