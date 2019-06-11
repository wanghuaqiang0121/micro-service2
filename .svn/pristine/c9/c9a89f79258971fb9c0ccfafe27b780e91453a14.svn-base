package org.web.module.base.service.permission.organization;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.organization.OperationalRoleMapper;
import org.web.module.base.domain.permission.organization.OperationalRole;

@Service
public class OperationalRoleService {
	
	@Resource
	private OperationalRoleMapper operationalRoleMapper;

	public Map<String, Object> getRepeat(OperationalRole operationalRole) {
		return operationalRoleMapper.getRepeat(operationalRole);
	}

	public int insert(OperationalRole operationalRole) {
		return operationalRoleMapper.insert(operationalRole);
	}

	public Map<String, Object> getOne(OperationalRole operationalRole) {
		return operationalRoleMapper.getOne(operationalRole);
	}

	public int update(OperationalRole operationalRole) {
		return operationalRoleMapper.update(operationalRole);
	}

	public List<Map<String, Object>> getList(OperationalRole operationalRole) {
		return operationalRoleMapper.getList(operationalRole);
	}

	
	
}
