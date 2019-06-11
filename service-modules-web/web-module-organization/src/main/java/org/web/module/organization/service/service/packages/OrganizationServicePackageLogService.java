package org.web.module.organization.service.service.packages;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.service.packages.OrganizationServicePackageLogMapper;
import org.web.module.organization.domain.service.packages.OrganizationServicePackageLog;

@Service
public class OrganizationServicePackageLogService {

	@Resource
	private OrganizationServicePackageLogMapper mapper;

	public int insert(OrganizationServicePackageLog packagelog) {
		return mapper.insert(packagelog);
	}
	
}
