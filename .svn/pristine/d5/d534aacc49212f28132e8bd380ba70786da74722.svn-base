package org.wechat.module.user.dao.itv;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.user.domain.itv.OrganizationUser;

public interface OrganizationUserMapper extends IBaseMapper<OrganizationUser> {

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationUser
	 * @return
	 * @description: 根据电话号码查询医生
	 */
	Map<String, Object> getDoctorByPhone(OrganizationUser organizationUser);
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationUser
	 * @return
	 * @description: 用户登录信息
	 */
	Map<String, Object> getLoginMsg(OrganizationUser organizationUser);
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationUser
	 * @return
	 * @description: 查询医生所在机构对应的团队
	 */
	List<Map<String, Object>> getOrganizationAndTeams(OrganizationUser organizationUser);

}
