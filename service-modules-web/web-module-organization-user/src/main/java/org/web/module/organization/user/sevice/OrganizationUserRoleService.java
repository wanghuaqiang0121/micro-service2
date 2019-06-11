package org.web.module.organization.user.sevice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.user.dao.OrganizationUserRoleMapper;
import org.web.module.organization.user.domain.OrganizationUserRole;

@Service
public class OrganizationUserRoleService {

	@Resource
	private OrganizationUserRoleMapper organizationUserRoleMapper;

	public int update(OrganizationUserRole organizationUserRole) {
		return organizationUserRoleMapper.update(organizationUserRole);
	}

	public int insert(OrganizationUserRole organizationUserRole) {
		return organizationUserRoleMapper.insert(organizationUserRole);
	}

	public Map<String, Object> getRepeat(OrganizationUserRole organizationUserRole) {
		return organizationUserRoleMapper.getRepeat(organizationUserRole);
	}

	public List<Map<String, Object>> getList(OrganizationUserRole organizationUserRole) {
		return organizationUserRoleMapper.getList(organizationUserRole);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationUserRole
	 * @return
	 * @description: 查询机构用户角色拥有和未拥有的角色
	 */
	public List<Map<String, Object>> getHaveAndNotHave(OrganizationUserRole organizationUserRole) {
		return organizationUserRoleMapper.getHaveAndNotHave(organizationUserRole);
	}


}
