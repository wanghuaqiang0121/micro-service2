package org.web.module.team.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.team.dao.OrganizationOrganizationTeamRoleMapper;
import org.web.module.team.domain.OrganizationOrganizationTeamRole;
@Service
public class OrganizationOrganizationTeamRoleService {
	
	@Resource
	private OrganizationOrganizationTeamRoleMapper organizationOrganizationTeamRoleMapper;

	public List<Map<String, Object>> getList(OrganizationOrganizationTeamRole organizationOrganizationTeamRole) {
		return organizationOrganizationTeamRoleMapper.getList(organizationOrganizationTeamRole);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @return
	 * @description:  查询团队角色列表
	 */
	public List<Map<String, Object>> getOrganizationOrganizationTeamRoles(
			OrganizationOrganizationTeamRole organizationOrganizationTeamRole) {
		return organizationOrganizationTeamRoleMapper.getOrganizationOrganizationTeamRoles(organizationOrganizationTeamRole);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @return
	 * @description: 查询团队团队长角色列表
	 */
	public List<Map<String, Object>> getOrganizationOrganizationTeamManagerRoles(
			OrganizationOrganizationTeamRole organizationOrganizationTeamRole) {
		return organizationOrganizationTeamRoleMapper.getOrganizationOrganizationTeamManagerRoles(organizationOrganizationTeamRole);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @return
	 * @description: 查询团队权限列表
	 */
	public List<Map<String, Object>> getOrganizationOrganizationTeamPermissions(
			OrganizationOrganizationTeamRole organizationOrganizationTeamRole) {
		return organizationOrganizationTeamRoleMapper.getOrganizationOrganizationTeamPermissions(organizationOrganizationTeamRole);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @return
	 * @description: 查询团队团队长权限列表
	 */
	public List<Map<String, Object>> getOrganizationOrganizationTeamManagerPermissions(
			OrganizationOrganizationTeamRole organizationOrganizationTeam) {
		return organizationOrganizationTeamRoleMapper.getOrganizationOrganizationTeamManagerPermissions(organizationOrganizationTeam);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @return
	 * @description: 查询团队菜单列表
	 */
	public List<Map<String, Object>> getOrganizationOrganizationTeamMenus(
			OrganizationOrganizationTeamRole organizationOrganizationTeamRole) {
		return organizationOrganizationTeamRoleMapper.getOrganizationOrganizationTeamMenus(organizationOrganizationTeamRole);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @return
	 * @description:   查询团队团队长菜单列表
	 */
	public List<Map<String, Object>> getOrganizationOrganizationTeamManagerMenus(
			OrganizationOrganizationTeamRole organizationOrganizationTeamRole) {
		return organizationOrganizationTeamRoleMapper.getOrganizationOrganizationTeamManagerMenus(organizationOrganizationTeamRole);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @return
	 * @description:  查询团队操作列表
	 */
	public List<Map<String, Object>> getOrganizationOrganizationTeamOperation(
			OrganizationOrganizationTeamRole organizationOrganizationTeam) {
		return organizationOrganizationTeamRoleMapper.getOrganizationOrganizationTeamOperation(organizationOrganizationTeam);
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @return
	 * @description:  查询团队团队长操作列表
	 */
	public List<Map<String, Object>> getOrganizationOrganizationTeamManagerOperation(
			OrganizationOrganizationTeamRole organizationOrganizationTeamRole) {
		return organizationOrganizationTeamRoleMapper.getOrganizationOrganizationTeamManagerOperation(organizationOrganizationTeamRole);
	}

}
