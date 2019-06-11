package org.wechat.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.organization.dao.OrganizationSiteMapper;
import org.wechat.module.organization.domain.OrganizationSite;

@Service
public class OrganizationSiteService {

	@Resource
	private OrganizationSiteMapper organizationSiteMapper;


	public List<Map<String, Object>> getList(OrganizationSite organizationSite) {
		return organizationSiteMapper.getList(organizationSite);
	}

}
