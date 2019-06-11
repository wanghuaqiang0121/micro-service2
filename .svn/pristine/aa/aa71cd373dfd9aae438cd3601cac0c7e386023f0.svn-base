package org.web.module.organization.dao.service.packages;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.domain.service.packages.OrganizationPackageService;

public interface OrganizationPackageServiceMapper extends IBaseMapper<OrganizationPackageService> {
 

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param oPackageService
	* @return 
	* @date 2018年3月20日
	* @version 1.0
	* @description 删除服务包所有服务关联
	*/
	int deleteByOrganizationPackageId(OrganizationPackageService oPackageService);

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationPackageService
	* @return 
	* @date 2018年3月20日
	* @version 1.0
	* @description 批量添加机构服务包和服务关联
	*/
	int batchInsert(List<OrganizationPackageService> organizationPackageService);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationPackageService
	 * @return
	 * @description:  查询服务包没有关联的服务列表
	 */
	List<Map<String, Object>> getOrganizationPackageIsNullService(
			OrganizationPackageService organizationPackageService);

	
}