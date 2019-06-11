package org.wechat.module.user.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.TeamOrganizationServicePackageMapper;
import org.wechat.module.user.domain.TeamOrganizationServicePackage;

@Service
public class TeamOrganizationServicePackageService {

	@Resource
	private TeamOrganizationServicePackageMapper teamOrganizationServicePackageMapper;

	public Map<String, Object> getOne(TeamOrganizationServicePackage teamOrganizationServicePackage) {
		return teamOrganizationServicePackageMapper.getOne(teamOrganizationServicePackage);
	}
}
