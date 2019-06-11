package org.web.module.organization.user.sevice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.user.dao.OrganizationUserTeamRoleMapper;
import org.web.module.organization.user.domain.OrganizationUserTeamRole;

@Service
public class OrganizationUserTeamRoleService {
	
	@Resource
	private OrganizationUserTeamRoleMapper organizationOrganizationTeamMapper;
	
	public	int insert(OrganizationUserTeamRole organizationOrganizationTeam) {
		return organizationOrganizationTeamMapper.insert(organizationOrganizationTeam);

	}

	

	public	int delete(OrganizationUserTeamRole organizationOrganizationTeam) {
		return organizationOrganizationTeamMapper.delete(organizationOrganizationTeam);
	}
	
	/**
	 * 
	* @author <font color="green"><b>Chen.Yan</b></font>
	* @param organizationOrganizationTeam
	* @return 
	* @date 2018年3月21日
	* @version 1.0
	* @description 删除 (删除权限)
	 */
	public int deleteRole(OrganizationUserTeamRole organizationOrganizationTeam){
		return organizationOrganizationTeamMapper.deleteRole(organizationOrganizationTeam);
	}

	public	Map<String, Object> getRepeat(OrganizationUserTeamRole organizationOrganizationTeam) {
		return organizationOrganizationTeamMapper.getRepeat(organizationOrganizationTeam);
	}	
	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param organizationOrganizationTeam
	 * @return
	 * @date 2018年3月21日
	 * @version 1.0
	 * @description 查询机构用户在这个团队的权限(是否存在)
	 */
	public List<Map<String, Object>> getOrganizationUserRoleIsChoose(OrganizationUserTeamRole organizationOrganizationTeam){
		return organizationOrganizationTeamMapper.getOrganizationUserRoleIsChoose(organizationOrganizationTeam);
	}


	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationOrganizationTeam
	 * @return
	 * @description:  删除用户在团队所有角色
	 */
	public int deleteByDoctorDoctorTeamId(OrganizationUserTeamRole organizationOrganizationTeam) {
		return organizationOrganizationTeamMapper.deleteByDoctorDoctorTeamId(organizationOrganizationTeam);
	}



	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationOrganizationTeam
	* @return 
	* @date 2018年5月8日
	* @version 1.0
	* @description 查询医生所在团队角色
	*/
	public List<Map<String, Object>> getOrganizationUserRoles(
			OrganizationUserTeamRole organizationOrganizationTeam) {
		return organizationOrganizationTeamMapper.getOrganizationUserRoles(organizationOrganizationTeam);
	}
}
