package org.wechat.module.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.OrganizationPackageServiceMapper;
import org.wechat.module.user.domain.OrganizationPackageService;

@Service
public class OrganizationPackageServiceService {

	@Resource
	private OrganizationPackageServiceMapper organizationPackageServiceMapper;

	public List<Map<String, Object>> getList(OrganizationPackageService packageService) {
		return organizationPackageServiceMapper.getList(packageService);
	}
}
