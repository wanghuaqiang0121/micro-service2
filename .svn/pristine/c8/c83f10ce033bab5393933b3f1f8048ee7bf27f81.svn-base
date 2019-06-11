package org.web.module.base.service.permission.organization;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.organization.ModuleOperationalRoleMapper;
import org.web.module.base.domain.permission.organization.ModuleOperationalRole;

@Service
public class ModuleOperationalRoleService {
	@Resource
	private ModuleOperationalRoleMapper mapper;

	public Map<String, Object> getRepeat(ModuleOperationalRole moduleOperationalRole) {
		return mapper.getRepeat(moduleOperationalRole);
	}

	public int insert(ModuleOperationalRole moduleOperationalRole) {
		return mapper.insert(moduleOperationalRole);
	}

	public int delete(ModuleOperationalRole moduleOperationalRole) {
		return mapper.delete(moduleOperationalRole);
	}

	public List<Map<String, Object>> getList(ModuleOperationalRole moduleOperationalRole) {
		return mapper.getList(moduleOperationalRole);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月16日
	 * @param moduleOperationalRole
	 * @return {@link List}
	 * @description: 查询拥有和未拥有的列表
	 */
	public List<Map<String, Object>> getModuleRoleIsChoose(ModuleOperationalRole moduleOperationalRole) {
		return mapper.getModuleRoleIsChoose(moduleOperationalRole);
	}

}
