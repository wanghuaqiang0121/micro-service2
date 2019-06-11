package org.wechat.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.organization.dao.OrganizationServicePackageMapper;
import org.wechat.module.organization.domain.OrganizationServicePackage;

@Service
public class OrganizationServicePackageService {

	@Resource
	private OrganizationServicePackageMapper organizationServicePackageMapper;

	
	public Map<String, Object> getOne(OrganizationServicePackage organizationServicePackage) {
		return organizationServicePackageMapper.getOne(organizationServicePackage);
	}

	
	public List<Map<String, Object>> getList(OrganizationServicePackage organizationServicePackage) {
		return organizationServicePackageMapper.getList(organizationServicePackage);
	}
	
	
}
