package org.web.module.base.dao.permission.team;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.base.domain.permission.team.OrganizationTeamPermissionMenu;

public interface OrganizationTeamPermissionMenuMapper extends IBaseMapper<OrganizationTeamPermissionMenu> {

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamPermissionMenu
	 * @return
	 * @description: 团队权限（拥有未拥有）的菜单
	 */
	List<Map<String, Object>> getTeamPermissionHaveAndNotHaveMenus(
			OrganizationTeamPermissionMenu organizationTeamPermissionMenu);
}