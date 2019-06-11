package org.web.module.organization.user.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.user.domain.OrganizationUserType;

public interface OrganizationUserTypeMapper extends IBaseMapper<OrganizationUserType>  {

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationUserType
	 * @return
	 * @description: 查询机构没有的人群标签
	 */
	List<Map<String, Object>> getOrganizationUserTypeIsNull(OrganizationUserType organizationUserType);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationUserType
	 * @return
	 * @description: 机构拥有和未拥有的人群类型
	 */
	List<Map<String, Object>> getOrganizationUserTypehaveAndNotHaveList(OrganizationUserType organizationUserType);

}
