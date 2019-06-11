package org.web.module.base.dao.permission.team;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.base.domain.permission.team.OrganizationTeamRolePermission;

public interface OrganizationTeamRolePermissionMapper extends IBaseMapper<OrganizationTeamRolePermission> {
    
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamRolePermission
	 * @return
	 * @description: 团队角色（拥有未拥有）的权限
	 */
	List<Map<String, Object>> getTeamRoleHaveAndNotHavePermissions(
			OrganizationTeamRolePermission organizationTeamRolePermission);
}