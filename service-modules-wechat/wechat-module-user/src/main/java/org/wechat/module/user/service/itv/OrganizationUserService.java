package org.wechat.module.user.service.itv;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.itv.OrganizationUserMapper;
import org.wechat.module.user.domain.itv.OrganizationUser;


@Service
public class OrganizationUserService {
	@Resource
	private OrganizationUserMapper organizationUserMapper;

	
	public Map<String, Object> getDoctorByPhone(OrganizationUser organizationUser) {
		return organizationUserMapper.getDoctorByPhone(organizationUser);
	}

	public Map<String, Object> getLoginMsg(OrganizationUser organizationUser) {
		return organizationUserMapper.getLoginMsg(organizationUser);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationUser
	 * @return
	 * @description: 查询医生所在机构对应的团队
	 */
	public List<Map<String, Object>> getOrganizationAndTeams(OrganizationUser organizationUser) {
		return organizationUserMapper.getOrganizationAndTeams(organizationUser);
	}

	
}
