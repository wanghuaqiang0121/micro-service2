package org.web.module.base.service.permission.team;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.team.OrganizationTeamOperationMapper;
import org.web.module.base.domain.permission.team.OrganizationTeamOperation;

@Service
public class OrganizationTeamOperationService {

	@Autowired
	private OrganizationTeamOperationMapper organizationTeamOperationMapper;

	public int insert(OrganizationTeamOperation organizationTeamOperation) {
		return organizationTeamOperationMapper.insert(organizationTeamOperation);
	}

	public int delete(OrganizationTeamOperation organizationTeamOperation) {
		return organizationTeamOperationMapper.delete(organizationTeamOperation);
	}

	public int update(OrganizationTeamOperation organizationTeamOperation) {
		return organizationTeamOperationMapper.update(organizationTeamOperation);
	}

	public Map<String, Object> getOne(OrganizationTeamOperation organizationTeamOperation) {
		return organizationTeamOperationMapper.getOne(organizationTeamOperation);
	}

	public Map<String, Object> getRepeat(OrganizationTeamOperation organizationTeamOperation) {
		return organizationTeamOperationMapper.getRepeat(organizationTeamOperation);
	}

	public List<Map<String, Object>> getList(OrganizationTeamOperation organizationTeamOperation) {
		return organizationTeamOperationMapper.getList(organizationTeamOperation);
	}

}
