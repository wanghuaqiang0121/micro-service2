package org.web.module.organization.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationSmsMapper;
import org.web.module.organization.domain.OrganizationSms;

@Service
public class OrganizationSmsService {

	@Resource
	private OrganizationSmsMapper mapper;

	public int insert(OrganizationSms organizationSms) {
		return mapper.insert(organizationSms);
	}
	
}
