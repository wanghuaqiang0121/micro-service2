package org.web.module.base.service.permission.team;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.team.OrganizationTeamPermissionOperationMapper;
import org.web.module.base.domain.permission.team.OrganizationTeamPermissionOperation;

@Service
public class OrganizationTeamPermissionOperationService {

	@Autowired
	private OrganizationTeamPermissionOperationMapper organizationTeamPermissionOperationMapper;

	public int insert(OrganizationTeamPermissionOperation organizationTeamPermissionOperation) {
		return organizationTeamPermissionOperationMapper.insert(organizationTeamPermissionOperation);
	}

	public int delete(OrganizationTeamPermissionOperation organizationTeamPermissionOperation) {
		return organizationTeamPermissionOperationMapper.delete(organizationTeamPermissionOperation);
	}

	public Map<String, Object> getRepeat(OrganizationTeamPermissionOperation organizationTeamPermissionOperation) {
		return organizationTeamPermissionOperationMapper.getRepeat(organizationTeamPermissionOperation);
	}

	public List<Map<String, Object>> getTeamPermissionHaveAndNotHaveOperations(
			OrganizationTeamPermissionOperation organizationTeamPermissionOperation) {
		return organizationTeamPermissionOperationMapper
				.getTeamPermissionHaveAndNotHaveOperations(organizationTeamPermissionOperation);
	}
}
