package org.web.module.organization.user.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.user.domain.OrganizationUserModule;

public interface OrganizationUserModuleMapper extends IBaseMapper<OrganizationUserModule> {

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationUserModule
	* @return 
	* @date 2018年4月18日
	* @version 1.0
	* @description 机构用户在该机构下拥有和未拥有的模块列表
	*/
	List<Map<String, Object>> haveAndNotHaveModules(OrganizationUserModule organizationUserModule);

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationUserModule
	* @return 
	* @date 2018年4月18日
	* @version 1.0
	* @description 机构用户在该机构下拥有的模块列表
	*/
	List<Map<String, Object>> haveModules(OrganizationUserModule organizationUserModule);

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationUserModule
	* @return 
	* @date 2018年4月18日
	* @version 1.0
	* @description 机构拥有的模块列表
	*/
	List<Map<String, Object>> organizationHaveModules(OrganizationUserModule organizationUserModule);
}