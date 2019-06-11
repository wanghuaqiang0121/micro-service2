package org.web.module.organization.user.sevice;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.organization.user.dao.OrganizationUserMapper;
import org.web.module.organization.user.domain.OrganizationUser;

@Service
public class OrganizationUserService {
	@Autowired
	private OrganizationUserMapper organizationUserMapper;
	
	public int insert(OrganizationUser organizationUser) {
		return organizationUserMapper.insert(organizationUser);

	}

	public int update(OrganizationUser organizationUser) {
		return organizationUserMapper.update(organizationUser);
	}

	public int delete(OrganizationUser organizationUser) {
		return organizationUserMapper.delete(organizationUser);
	}

	public Map<String, Object> getRepeat(OrganizationUser organizationUser) {
		return organizationUserMapper.getRepeat(organizationUser);
	}

	public Map<String, Object> getOne(OrganizationUser organizationUser) {
		return organizationUserMapper.getOne(organizationUser);
	}

	public List<Map<String, Object>> getList(OrganizationUser organizationUser) {
		return organizationUserMapper.getList(organizationUser);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @return
	 * @description: 查询机构用户模块菜单列表
	 */
	public List<Map<String, Object>> getOrganizationUserModuleMenuList(OrganizationUser organizationUser){
		return organizationUserMapper.getOrganizationUserModuleMenuList(organizationUser);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @return
	 * @description: 查询超级管理员模块菜单列表
	 */
	public List<Map<String, Object>> getManagerModuleMenuList(OrganizationUser organizationUser){
		return organizationUserMapper.getManagerModuleMenuList(organizationUser);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @return
	 * @description: 查询机构用户的模块角色列表
	 */
	public List<Map<String, Object>> getOrganizationUserModuleRoleList(OrganizationUser organizationUser){
		return organizationUserMapper.getOrganizationUserModuleRoleList(organizationUser);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @return
	 * @description: 查询超级管理员角色列表
	 */
	public List<Map<String, Object>> getManagerRoleList(OrganizationUser organizationUser){
		return organizationUserMapper.getManagerRoleList(organizationUser);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @return
	 * @description: 查询机构用户模块权限列表
	 */
	public List<Map<String, Object>> getOrganizationUserModulePermissionList(OrganizationUser organizationUser){
		return organizationUserMapper.getOrganizationUserModulePermissionList(organizationUser);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @return
	 * @description: 查询超级管理员模块权限列表
	 */
	public List<Map<String, Object>> getManagerPermissionList(OrganizationUser organizationUser){
		return organizationUserMapper.getManagerPermissionList(organizationUser);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @return
	 * @description: 查询机构用户模块操作列表
	 */
	public List<Map<String, Object>> getOrganizationUserModuleOperationList(OrganizationUser organizationUser){
		return organizationUserMapper.getOrganizationUserModuleOperationList(organizationUser);
	}

	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @return
	 * @description: 查询超级管理员模块操作列表
	 */
	public List<Map<String, Object>> getManagerOperationList(OrganizationUser organizationUser){
		return organizationUserMapper.getManagerOperationList(organizationUser);
	}

	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param user
	 * @return
	 * @description: 查询账号
	 */
	public Map<String, Object> getUserByAccount(OrganizationUser organizationUser){
		return organizationUserMapper.getUserByAccount(organizationUser);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param user
	 * @return
	 * @description: 查询账号和密码
	 */
	public Map<String, Object> getUserByAccountAndPassword(OrganizationUser organizationUser){
		return organizationUserMapper.getUserByAccountAndPassword(organizationUser);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @return
	 * @description: -查询机构用户的机构列表
	 */
	public List<Map<String, Object>> getOrganzationUserOrganzationList(OrganizationUser organizationUser){
		return organizationUserMapper.getOrganzationUserOrganzationList(organizationUser);
	}


}
