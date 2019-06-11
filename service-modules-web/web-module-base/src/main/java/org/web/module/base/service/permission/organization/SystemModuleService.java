package org.web.module.base.service.permission.organization;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.organization.SystemModuleMapper;
import org.web.module.base.domain.permission.organization.SystemModule;

@Service
public class SystemModuleService {

	@Resource
	private SystemModuleMapper systemModuleMapper;

	public Map<String, Object> getRepeat(SystemModule systemModule) {
		return systemModuleMapper.getRepeat(systemModule);
	}

	public int insert(SystemModule systemModule) {
		return systemModuleMapper.insert(systemModule);
	}

	public Map<String, Object> getOne(SystemModule systemModule) {
		return systemModuleMapper.getOne(systemModule);
	}

	public int update(SystemModule systemModule) {
		return systemModuleMapper.update(systemModule);
	}

	public List<Map<String, Object>> getList(SystemModule systemModule) {
		return systemModuleMapper.getList(systemModule);
	}
}
