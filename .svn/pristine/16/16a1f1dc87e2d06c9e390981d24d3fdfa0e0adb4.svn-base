package org.wechat.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.organization.dao.OrganizationMapper;
import org.wechat.module.organization.domain.Organization;


@Service
public class OrganizationService {
	
	@Resource
	private OrganizationMapper organizationMapper;


	public List<Map<String, Object>> getList(Organization organization) {
		return organizationMapper.getList(organization);
	}
	
}
