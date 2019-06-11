package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationWechatInfoMapper;
import org.web.module.organization.domain.OrganizationWechatInfo;

@Service
public class OrganizationWechatInfoService {

	@Resource
	private OrganizationWechatInfoMapper organizationWechatInfoMapper;

	public List<Map<String, Object>> getList(OrganizationWechatInfo organizationWechatInfo) {
		return organizationWechatInfoMapper.getList(organizationWechatInfo);
	}

	public int insert(OrganizationWechatInfo organizationWechatInfo) {
		return organizationWechatInfoMapper.insert(organizationWechatInfo);
	}

	public Map<String, Object> getOne(OrganizationWechatInfo organizationWechatInfo) {
		return organizationWechatInfoMapper.getOne(organizationWechatInfo);
	}

	public int update(OrganizationWechatInfo organizationWechatInfo) {
		return organizationWechatInfoMapper.update(organizationWechatInfo);
	}
}
