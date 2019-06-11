package org.web.module.organization.user.sevice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.user.dao.OrganizationUserModuleMapper;
import org.web.module.organization.user.domain.OrganizationUserModule;

@Service
public class OrganizationUserModuleService {

	@Resource
	private OrganizationUserModuleMapper mapper;

	public Map<String, Object> getRepeat(OrganizationUserModule organizationUserModule) {
		return mapper.getRepeat(organizationUserModule);
	}

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationUserModule
	* @return 
	* @date 2018年4月18日
	* @version 1.0
	* @description 添加机构用户模块关联
	*/
	public int insert(OrganizationUserModule organizationUserModule) {
		return mapper.insert(organizationUserModule);
	}

	public List<Map<String, Object>> getList(OrganizationUserModule organizationUserModule) {
		return mapper.getList(organizationUserModule);
	}

	public int update(OrganizationUserModule organizationUserModule) {
		return mapper.update(organizationUserModule);
	}

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationUserModule
	* @return 
	* @date 2018年4月18日
	* @version 1.0
	* @description 机构用户在该机构下拥有和未拥有的模块列表
	*/
	public List<Map<String, Object>> haveAndNotHaveModules(OrganizationUserModule organizationUserModule) {
		return mapper.haveAndNotHaveModules(organizationUserModule);
	}

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationUserModule
	* @return 
	* @date 2018年4月18日
	* @version 1.0
	* @description 机构用户在该机构下拥有的模块列表
	*/
	public List<Map<String, Object>> haveModules(OrganizationUserModule organizationUserModule) {
		return mapper.haveModules(organizationUserModule);
	}

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationUserModule
	* @return 
	* @date 2018年4月18日
	* @version 1.0
	* @description 机构拥有的模块列表
	*/
	public List<Map<String, Object>> organizationHaveModules(OrganizationUserModule organizationUserModule) {
		return mapper.organizationHaveModules(organizationUserModule);
	}
	
}
