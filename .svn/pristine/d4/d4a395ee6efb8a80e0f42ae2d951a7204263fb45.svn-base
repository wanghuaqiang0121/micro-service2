package org.web.module.base.service.permission.organization;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.organization.OperationalMenuMapper;
import org.web.module.base.domain.permission.organization.OperationalMenu;

@Service
public class OperationalMenuService {
	@Resource
	private OperationalMenuMapper operationalMenuMapper;
	
	public	int insert(OperationalMenu operationalMenu) {
		return operationalMenuMapper.insert(operationalMenu);

	}

	public	int update(OperationalMenu operationalMenu) {
		return operationalMenuMapper.update(operationalMenu);
	}

	public	int delete(OperationalMenu operationalMenu) {
		return operationalMenuMapper.delete(operationalMenu);
	}

	public	Map<String, Object> getRepeat(OperationalMenu operationalMenu) {
		return operationalMenuMapper.getRepeat(operationalMenu);
	}

	public	Map<String, Object> getOne(OperationalMenu operationalMenu) {
		return operationalMenuMapper.getOne(operationalMenu);
	}

	public	List<Map<String, Object>> getList(OperationalMenu operationalMenu){
		return operationalMenuMapper.getList(operationalMenu);
	}

}
