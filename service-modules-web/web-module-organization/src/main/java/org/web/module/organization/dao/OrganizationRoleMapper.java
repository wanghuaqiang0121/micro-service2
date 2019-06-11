package org.web.module.organization.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.domain.ModuleOperationalRole;
import org.web.module.organization.domain.OrganizationRole;

public interface OrganizationRoleMapper extends IBaseMapper<OrganizationRole> {

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param moduleOperationalRole
	 * @return
	 * @description: 查询机构在该模块下拥有和未拥有的权限列表
	 */
	public List<Map<String, Object>> getModuleOperationalRoleList(ModuleOperationalRole moduleOperationalRole);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationRole
	 * @return
	 * @description: 批量删除
	 */
	public int batchDelete(OrganizationRole organizationRole);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationRoles
	 * @return
	 * @description: 批量新增
	 */
	public int batchInsert(List<OrganizationRole> organizationRoles);
}