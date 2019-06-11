package org.web.module.organization.user.sevice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.user.dao.OrganizationUserTeamMapper;
import org.web.module.organization.user.domain.OrganizationUserTeam;

@Service
public class OrganizationUserTeamService {

	@Resource
	private OrganizationUserTeamMapper doctorDoctorTeamMapper;
	
	
	public int insert(OrganizationUserTeam doctorDoctorTeam){
		return doctorDoctorTeamMapper.insert(doctorDoctorTeam);
	}


	public Map<String, Object> getRepeat(OrganizationUserTeam doctorDoctorTeam) {
		return doctorDoctorTeamMapper.getRepeat(doctorDoctorTeam);
	}


	public int delete(OrganizationUserTeam doctorDoctorTeam) {
		return doctorDoctorTeamMapper.delete(doctorDoctorTeam);
	}


	public List<Map<String, Object>> getList(OrganizationUserTeam doctorDoctorTeam) {
		return doctorDoctorTeamMapper.getList(doctorDoctorTeam);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param doctorDoctorTeam
	 * @return
	 * @description: 团队成员列表
	 */
	public List<Map<String, Object>> getTeamDoctors(OrganizationUserTeam doctorDoctorTeam) {
		return doctorDoctorTeamMapper.getTeamDoctors(doctorDoctorTeam);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param doctorDoctorTeam
	 * @return
	 * @description: 团队拥有的机构用户
	 */
	public List<Map<String, Object>> getOrganizationUserMembers(OrganizationUserTeam doctorDoctorTeam) {
		return doctorDoctorTeamMapper.getOrganizationUserMembers(doctorDoctorTeam);
	}
	
}
