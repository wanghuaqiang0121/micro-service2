package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationSiteMapper;
import org.web.module.organization.domain.OrganizationSite;

@Service
public class OrganizationSiteService {

	@Resource
	private OrganizationSiteMapper organizationSiteMapper;

	public Map<String, Object> getRepeat(OrganizationSite organizationSite) {
		return organizationSiteMapper.getRepeat(organizationSite);
	}

	public int insert(OrganizationSite organizationSite) {
		return organizationSiteMapper.insert(organizationSite);
	}

	public Map<String, Object> getOne(OrganizationSite organizationSite) {
		return organizationSiteMapper.getOne(organizationSite);
	}

	public List<Map<String, Object>> getList(OrganizationSite organizationSite) {
		return organizationSiteMapper.getList(organizationSite);
	}

	public int update(OrganizationSite organizationSite) {
		return organizationSiteMapper.update(organizationSite);
	}
}
