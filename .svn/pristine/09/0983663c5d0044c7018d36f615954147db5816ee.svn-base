package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationModuleMapper;
import org.web.module.organization.domain.OrganizationModule;

@Service
public class OrganizationModuleService {
	@Resource
	private OrganizationModuleMapper organizationModuleMapper;
	
	public int insert(OrganizationModule organizationModule){
		return organizationModuleMapper.insert(organizationModule);
	}
	
	public int update(OrganizationModule organizationModule){
		return organizationModuleMapper.update(organizationModule);
	}
	
	public List<Map<String, Object>> getList(OrganizationModule organizationModule){
		return organizationModuleMapper.getList(organizationModule);
	}
	

	public Map<String, Object> getRepeat(OrganizationModule organizationModule) {
		return organizationModuleMapper.getRepeat(organizationModule);
	}

	public Map<String, Object> getOne(OrganizationModule organizationModule) {
		return organizationModuleMapper.getOne(organizationModule);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationModule
	 * @return
	 * @description:  查询机构关联和未关联的模块列表
	 */
	public List<Map<String, Object>> getOrganizationModuleIsChoose(OrganizationModule organizationModule){
		return organizationModuleMapper.getOrganizationModuleIsChoose(organizationModule);
	}
}
