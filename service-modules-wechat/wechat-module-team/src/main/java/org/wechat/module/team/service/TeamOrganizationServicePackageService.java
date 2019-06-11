package org.wechat.module.team.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.team.dao.TeamOrganizationServicePackageMapper;
import org.wechat.module.team.domain.TeamOrganizationServicePackage;

@Service
public class TeamOrganizationServicePackageService {

	@Resource
	private TeamOrganizationServicePackageMapper teamOrganizationServicePackageMapper;

	
	public Map<String, Object> getOne(TeamOrganizationServicePackage teamOrganizationServicePackage) {
		return teamOrganizationServicePackageMapper.getOne(teamOrganizationServicePackage);
	}

	
	public List<Map<String, Object>> getList(TeamOrganizationServicePackage teamOrganizationServicePackage) {
		return teamOrganizationServicePackageMapper.getList(teamOrganizationServicePackage);
	}
	
}
