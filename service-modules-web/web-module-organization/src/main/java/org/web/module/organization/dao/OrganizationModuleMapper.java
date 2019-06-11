package org.web.module.organization.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.domain.OrganizationModule;

public interface OrganizationModuleMapper extends IBaseMapper<OrganizationModule>{
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationModule
	 * @return
	 * @description: 查询机构关联和未关联的模块列表
	 */
	public List<Map<String, Object>> getOrganizationModuleIsChoose(OrganizationModule organizationModule);

}
