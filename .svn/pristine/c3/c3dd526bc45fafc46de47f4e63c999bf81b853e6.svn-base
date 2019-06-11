package org.wechat.module.user.service.itv;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.itv.OrganizationMapper;
import org.wechat.module.user.domain.itv.Organization;

@Service
public class OrganizationService {

	@Resource
	private OrganizationMapper organizationMapper;

	public Map<String, Object> getOne(Organization organization) {
		return organizationMapper.getOne(organization);
	}

	public List<Map<String, Object>> getList(Organization organization) {
		return organizationMapper.getList(organization);
	}

}
