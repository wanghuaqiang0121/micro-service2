package org.web.module.team.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.team.dao.OrganizationUserTeamMapper;
import org.web.module.team.domain.OrganizationUserTeam;

@Service
public class OrganizationUserTeamService {

	@Resource
	private OrganizationUserTeamMapper organizationUserTeamMapper;
	
	
	public int insert(OrganizationUserTeam organizationUserTeam){
		return organizationUserTeamMapper.insert(organizationUserTeam);
	}


	public Map<String, Object> getRepeat(OrganizationUserTeam organizationUserTeam) {
		return organizationUserTeamMapper.getRepeat(organizationUserTeam);
	}


	public Map<String, Object> getOne(OrganizationUserTeam organizationUserTeam){
		return organizationUserTeamMapper.getOne(organizationUserTeam);
	}
	public int delete(OrganizationUserTeam organizationUserTeam) {
		return organizationUserTeamMapper.delete(organizationUserTeam);
	}


	public List<Map<String, Object>> getList(OrganizationUserTeam organizationUserTeam) {
		return organizationUserTeamMapper.getList(organizationUserTeam);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param doctorDoctorTeam
	 * @return
	 * @description: 团队成员列表
	 */
	public List<Map<String, Object>> getTeamDoctors(OrganizationUserTeam organizationUserTeam) {
		return organizationUserTeamMapper.getTeamDoctors(organizationUserTeam);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param doctorDoctorTeam
	 * @return
	 * @description: 团队拥有的机构用户
	 */
	public List<Map<String, Object>> getOrganizationUserMembers(OrganizationUserTeam organizationUserTeam) {
		return organizationUserTeamMapper.getOrganizationUserMembers(organizationUserTeam);
	}
	
}
