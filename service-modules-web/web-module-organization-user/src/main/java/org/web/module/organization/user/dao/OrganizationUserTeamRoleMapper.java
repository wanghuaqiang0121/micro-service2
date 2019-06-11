package org.web.module.organization.user.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.user.domain.OrganizationUserTeamRole;

public interface OrganizationUserTeamRoleMapper extends IBaseMapper<OrganizationUserTeamRole> {
	
	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param organizationOrganizationTeam
	 * @return
	 * @date 2018年3月20日
	 * @version 1.0
	 * @description 删除 (删除权限)
	 */
	public int deleteRole(OrganizationUserTeamRole organizationOrganizationTeam);

	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param organizationOrganizationTeam
	 * @return
	 * @date 2018年3月21日
	 * @version 1.0
	 * @description 查询机构用户在这个团队的权限(是否存在)
	 */
	public List<Map<String, Object>> getOrganizationUserRoleIsChoose(OrganizationUserTeamRole organizationOrganizationTeam);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationOrganizationTeam
	 * @return
	 * @description: 删除用户在团队所有角色
	 */
	public int deleteByDoctorDoctorTeamId(OrganizationUserTeamRole organizationOrganizationTeam);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationOrganizationTeam
	 * @return
	 * @description: 查询机构用户在这个团队的权限
	 */
	public List<Map<String, Object>> getOrganizationUserRoles(
			OrganizationUserTeamRole organizationOrganizationTeam);

	
}
