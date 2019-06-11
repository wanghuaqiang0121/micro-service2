package org.web.module.base.service.permission.organization;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.organization.OperationalRolePermissionMapper;
import org.web.module.base.domain.permission.organization.OperationalRolePermission;

@Service
public class OperationalRolePermissionService {
	@Resource
	private  OperationalRolePermissionMapper mapper;
	
	public Map<String, Object> getRepeat(OperationalRolePermission operationalRolePermission){
		return mapper.getRepeat(operationalRolePermission);
	}
	
	public int insert(OperationalRolePermission operationalRolePermission) {
		return mapper.insert(operationalRolePermission);
	}

	public int delete(OperationalRolePermission operationalRolePermission) {
		return mapper.delete(operationalRolePermission);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalRolePermission
	 * @return
	 * @description: 查询该角色拥有和未拥有的权限
	 */
	public List<Map<String, Object>> getPermissionOperationIsChoose(OperationalRolePermission operationalRolePermission) {
		return mapper.getPermissionOperationIsChoose(operationalRolePermission);
	}
}
