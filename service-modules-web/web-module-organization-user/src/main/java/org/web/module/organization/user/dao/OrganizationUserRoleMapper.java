package org.web.module.organization.user.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.user.domain.OrganizationUserRole;

public interface OrganizationUserRoleMapper extends IBaseMapper<OrganizationUserRole> {

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationUserRole
	 * @return
	 * @description: -查询机构用户角色拥有和未拥有的角色
	 */
	List<Map<String, Object>> getHaveAndNotHave(OrganizationUserRole organizationUserRole);
	
}