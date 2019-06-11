package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationTypeMapper;
import org.web.module.organization.domain.OrganizationType;


@Service
public class OrganizationTypeService {

	@Resource
	private OrganizationTypeMapper organizationTypeMapper;

	public int insert(OrganizationType organizationType) {
		return organizationTypeMapper.insert(organizationType);
	}


	public int update(OrganizationType organizationType) {
		return organizationTypeMapper.update(organizationType);
	}


	public int delete(OrganizationType organizationType) {
		return organizationTypeMapper.delete(organizationType);
	}

	
	public Map<String, Object> getOne(OrganizationType organizationType) {
		return organizationTypeMapper.getOne(organizationType);
	}


	public Map<String, Object> getRepeat(OrganizationType organizationType) {
		return organizationTypeMapper.getRepeat(organizationType);
	}


	

	public List<Map<String, Object>> getList(OrganizationType organizationType) {
		return organizationTypeMapper.getList(organizationType);
	}
	
}
