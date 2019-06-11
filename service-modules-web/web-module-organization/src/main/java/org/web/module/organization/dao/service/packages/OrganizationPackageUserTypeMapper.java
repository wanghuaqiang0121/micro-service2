package org.web.module.organization.dao.service.packages;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.domain.service.packages.OrganizationPackageUserType;

public interface OrganizationPackageUserTypeMapper extends IBaseMapper<OrganizationPackageUserType> {
  

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param oPackageUserType
	* @return 
	* @date 2018年3月20日
	* @version 1.0
	* @description 删除服务包所有使用人群关联
	*/
	int deleteByOrganizationPackageId(OrganizationPackageUserType oPackageUserType);

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param organizationPackageUserType
	* @return 
	* @date 2018年3月20日
	* @version 1.0
	* @description 批量添加服务包使用人群关联
	*/
	int batchInsert(List<OrganizationPackageUserType> organizationPackageUserType);
}