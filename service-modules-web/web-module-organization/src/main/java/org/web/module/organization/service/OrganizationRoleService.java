package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationRoleMapper;
import org.web.module.organization.domain.ModuleOperationalRole;
import org.web.module.organization.domain.OrganizationRole;

@Service
public class OrganizationRoleService {

	@Resource
	private OrganizationRoleMapper organizationRoleMapper;

	public int insert(OrganizationRole organizationRole) {
		return organizationRoleMapper.insert(organizationRole);
	}

	public List<Map<String, Object>> getList(OrganizationRole organizationRole) {
		return organizationRoleMapper.getList(organizationRole);
	}

	public int update(OrganizationRole organizationRole) {
		return organizationRoleMapper.update(organizationRole);
	}

	public Map<String, Object> getRepeat(OrganizationRole organizationRole) {
		return organizationRoleMapper.getRepeat(organizationRole);
	}

	public int batchDelete(OrganizationRole organizationRole) {
		return organizationRoleMapper.batchDelete(organizationRole);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationRoles
	 * @return
	 * @description: 批量新增
	 */
	public int batchInsert(List<OrganizationRole> organizationRoles) {
		return organizationRoleMapper.batchInsert(organizationRoles);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param moduleOperationalRole
	 * @return
	 * @description: 查询机构在该模块下拥有和未拥有的权限列表
	 */
	public List<Map<String, Object>> getModuleOperationalRoleList(ModuleOperationalRole moduleOperationalRole) {
		return organizationRoleMapper.getModuleOperationalRoleList(moduleOperationalRole);
	}
}
