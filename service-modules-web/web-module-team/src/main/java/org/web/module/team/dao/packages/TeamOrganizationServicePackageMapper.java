package org.web.module.team.dao.packages;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.team.domain.packages.TeamOrganizationServicePackage;

public interface TeamOrganizationServicePackageMapper extends IBaseMapper<TeamOrganizationServicePackage> {
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param teamOrganizationServicePackage
	* @return 
	* @date 2018年3月21日
	* @version 1.0
	* @description 查询机构服务包对应团队授权操作
	*/
	List<Map<String, Object>> getTeamAuthorizeList(TeamOrganizationServicePackage teamOrganizationServicePackage);

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param teamOrganizationServicePackage
	* @return 
	* @date 2018年3月22日
	* @version 1.0
	* @description 根据机构服务包id查询是否有团队使用
	*/
	List<Map<String, Object>> getListByOrganizationServicePackageId(
			TeamOrganizationServicePackage teamOrganizationServicePackage);
}