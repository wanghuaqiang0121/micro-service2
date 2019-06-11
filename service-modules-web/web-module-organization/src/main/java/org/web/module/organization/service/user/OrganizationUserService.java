package org.web.module.organization.service.user;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.user.OrganizationUserMapper;
import org.web.module.organization.domain.user.OrganizationUser;

@Service
public class OrganizationUserService {
	@Resource
	private OrganizationUserMapper organizationUserMapper;
	
	public int insert(OrganizationUser organizationUser) {
		return organizationUserMapper.insert(organizationUser);
	}
	
	public int update(OrganizationUser organizationUser) {
		return organizationUserMapper.update(organizationUser);
	}

	public int delete(OrganizationUser organizationUser) {
		return organizationUserMapper.delete(organizationUser);
	}

	public Map<String, Object> getRepeat(OrganizationUser organizationUser) {
		return organizationUserMapper.getRepeat(organizationUser);
	}

	public Map<String, Object> getOne(OrganizationUser organizationUser) {
		return organizationUserMapper.getOne(organizationUser);
	}

	public List<Map<String, Object>> getList(OrganizationUser organizationUser) {
		return organizationUserMapper.getList(organizationUser);
	}
	
}
