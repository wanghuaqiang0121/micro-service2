package org.web.module.base.service.permission.team;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.team.OrganizationTeamPermissionMenuMapper;
import org.web.module.base.domain.permission.team.OrganizationTeamPermissionMenu;

@Service
public class OrganizationTeamPermissionMenuService {

	@Autowired
	private OrganizationTeamPermissionMenuMapper organizationTeamPermissionMenuMapper;

	public int insert(OrganizationTeamPermissionMenu organizationTeamPermissionMenu) {
		return organizationTeamPermissionMenuMapper.insert(organizationTeamPermissionMenu);
	}

	public int delete(OrganizationTeamPermissionMenu organizationTeamPermissionMenu) {
		return organizationTeamPermissionMenuMapper.delete(organizationTeamPermissionMenu);
	}

	public Map<String, Object> getRepeat(OrganizationTeamPermissionMenu organizationTeamPermissionMenu) {
		return organizationTeamPermissionMenuMapper.getRepeat(organizationTeamPermissionMenu);
	}

	public List<Map<String, Object>> getTeamPermissionHaveAndNotHaveMenus(
			OrganizationTeamPermissionMenu organizationTeamPermissionMenu) {
		return organizationTeamPermissionMenuMapper
				.getTeamPermissionHaveAndNotHaveMenus(organizationTeamPermissionMenu);
	}
}
