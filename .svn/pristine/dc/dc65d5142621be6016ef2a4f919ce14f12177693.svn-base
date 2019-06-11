package org.web.module.base.service.permission.organization;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.organization.OperationalPermissionMapper;
import org.web.module.base.domain.permission.organization.OperationalPermission;

@Service
public class OperationalPermissionService {

	@Resource
	private OperationalPermissionMapper operationalPermissionMapper;

	public Map<String, Object> getRepeat(OperationalPermission operationalPermission) {
		return operationalPermissionMapper.getRepeat(operationalPermission);
	}

	public int insert(OperationalPermission operationalPermission) {
		return operationalPermissionMapper.insert(operationalPermission);
	}

	public Map<String, Object> getOne(OperationalPermission operationalPermission) {
		return operationalPermissionMapper.getOne(operationalPermission);
	}

	public int update(OperationalPermission operationalPermission) {
		return operationalPermissionMapper.update(operationalPermission);
	}

	public List<Map<String, Object>> getList(OperationalPermission operationalPermission) {
		return operationalPermissionMapper.getList(operationalPermission);
	}
}
