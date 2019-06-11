package org.web.module.base.service.permission.team;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.team.OrganizationTeamMenuMapper;
import org.web.module.base.domain.permission.team.OrganizationTeamMenu;

@Service
public class OrganizationTeamMenuService {

	@Autowired
	private OrganizationTeamMenuMapper organizationTeamMenuMapper;

	public int insert(OrganizationTeamMenu organizationTeamMenu) {
		return organizationTeamMenuMapper.insert(organizationTeamMenu);
	}

	public int delete(OrganizationTeamMenu organizationTeamMenu) {
		return organizationTeamMenuMapper.delete(organizationTeamMenu);
	}

	public int update(OrganizationTeamMenu organizationTeamMenu) {
		return organizationTeamMenuMapper.update(organizationTeamMenu);
	}

	public Map<String, Object> getOne(OrganizationTeamMenu organizationTeamMenu) {
		return organizationTeamMenuMapper.getOne(organizationTeamMenu);
	}

	public Map<String, Object> getRepeat(OrganizationTeamMenu organizationTeamMenu) {
		return organizationTeamMenuMapper.getRepeat(organizationTeamMenu);
	}

	public List<Map<String, Object>> getList(OrganizationTeamMenu organizationTeamMenu) {
		return organizationTeamMenuMapper.getList(organizationTeamMenu);
	}

}
