package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationTeamMapper;
import org.web.module.organization.domain.OrganizationTeam;

@Service
public class OrganizationTeamService {
  @Resource
  private OrganizationTeamMapper organizationTeamMapper;
  
  public	int insert(OrganizationTeam organizationTeam) {
		return organizationTeamMapper.insert(organizationTeam);

	}

	public	int update(OrganizationTeam organizationTeam) {
		return organizationTeamMapper.update(organizationTeam);
	}

	public	int delete(OrganizationTeam organizationTeam) {
		return organizationTeamMapper.delete(organizationTeam);
	}

	public	Map<String, Object> getRepeat(OrganizationTeam organizationTeam) {
		return organizationTeamMapper.getRepeat(organizationTeam);
	}

	public	Map<String, Object> getOne(OrganizationTeam organizationTeam) {
		return organizationTeamMapper.getOne(organizationTeam);
	}

	public	List<Map<String, Object>> getList(OrganizationTeam organizationTeam){
		return organizationTeamMapper.getList(organizationTeam);
	}
}
