package org.web.module.team.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.team.domain.OrganizationUserTeam;

public interface OrganizationUserTeamMapper extends IBaseMapper<OrganizationUserTeam> {
  
/**
 * 
 * @author: ChenYan
 * @date: 2018年10月10日
 * @param doctorDoctorTeam
 * @return
 * @description: 团队成员列表
 */
	List<Map<String, Object>> getTeamDoctors(OrganizationUserTeam organizationUserTeam);
/**
 * 
 * @author: ChenYan
 * @date: 2018年10月10日
 * @param doctorDoctorTeam
 * @return
 * @description: 团队拥有的机构用户
 */
	List<Map<String, Object>> getOrganizationUserMembers(OrganizationUserTeam organizationUserTeam);
}