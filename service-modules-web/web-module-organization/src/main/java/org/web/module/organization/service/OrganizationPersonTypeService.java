package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationPersonTypeMapper;
import org.web.module.organization.domain.OrganizationPersonType;

@Service
public class OrganizationPersonTypeService {

	@Resource
	private OrganizationPersonTypeMapper organizationPersonTypeMapper;

	public List<Map<String, Object>> getList(OrganizationPersonType organizationPersonType) {
		return organizationPersonTypeMapper.getList(organizationPersonType);
	}
	public Map<String, Object> getOne(OrganizationPersonType organizationPersonType) {
		return organizationPersonTypeMapper.getOne(organizationPersonType);
	}
}
