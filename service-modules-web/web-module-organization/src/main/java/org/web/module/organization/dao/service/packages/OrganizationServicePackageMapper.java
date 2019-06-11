package org.web.module.organization.dao.service.packages;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.domain.service.packages.OrganizationServicePackage;

public interface OrganizationServicePackageMapper extends IBaseMapper<OrganizationServicePackage> {
  
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationServicePackage
	* @return 
	* @date 2018年3月22日
	* @version 1.0
	* @description 查询父机构服务包列表
	*/
	List<Map<String, Object>> getParentOrganizationPackages(OrganizationServicePackage organizationServicePackage);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationServicePackage
	 * @return
	 * @description: 查询某团队拥有和未拥有的服务包列表
	 */
	List<Map<String, Object>> getOrganizationServiceAuthorizationTeamPackages(
			OrganizationServicePackage organizationServicePackage);

}