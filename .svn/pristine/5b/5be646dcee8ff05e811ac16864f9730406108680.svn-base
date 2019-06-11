package org.wechat.module.user.service.itv;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.itv.OrganizationUserItvAccountMapper;
import org.wechat.module.user.domain.itv.OrganizationUserItvAccount;

@Service
public class OrganizationUserItvAccountService {

	@Resource
	private OrganizationUserItvAccountMapper mapper;

	public Map<String, Object> getRepeat(OrganizationUserItvAccount organizationUserItvAccount) {
		return mapper.getRepeat(organizationUserItvAccount);
	}

	
	public int insert(OrganizationUserItvAccount organizationUserItvAccount) {
		return mapper.insert(organizationUserItvAccount);
	}

	public List<Map<String, Object>> getList(OrganizationUserItvAccount organizationUserItvAccount) {
		return  mapper.getList(organizationUserItvAccount);
	}

	public Map<String, Object> getOne(OrganizationUserItvAccount organizationUserItvAccount) {
		return mapper.getOne(organizationUserItvAccount);
	}

	public int delete(OrganizationUserItvAccount organizationUserItvAccount) {
		return mapper.delete(organizationUserItvAccount);
	}
	
	
}
