package org.web.module.organization.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationSmsInfoMapper;
import org.web.module.organization.domain.OrganizationSmsInfo;

@Service
public class OrganizationSmsInfoService {
	@Resource
	private OrganizationSmsInfoMapper mapper;

	public Map<String, Object> getOne(OrganizationSmsInfo organizationSmsInfo) {
		return mapper.getOne(organizationSmsInfo);
	}

	public int update(OrganizationSmsInfo organizationSmsInfo) {
		return mapper.update(organizationSmsInfo);
	}

	public int insert(OrganizationSmsInfo organizationSmsInfo) {
		return mapper.insert(organizationSmsInfo);
	}

	
}
