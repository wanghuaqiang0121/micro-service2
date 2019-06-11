package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationMapper;
import org.web.module.organization.domain.Organization;

@Service
public class OrganizationService {

	@Resource
	private OrganizationMapper organizationMapper;

	public int insert(Organization organization) {
		return organizationMapper.insert(organization);
	}

	public int update(Organization organization) {
		return organizationMapper.update(organization);
	}

	public Map<String, Object> getRepeat(Organization organization) {
		return organizationMapper.getRepeat(organization);
	}

	public Map<String, Object> getOne(Organization organization) {
		return organizationMapper.getOne(organization);
	}

	public List<Map<String, Object>> getList(Organization organization) {
		return organizationMapper.getList(organization);
	}

}
