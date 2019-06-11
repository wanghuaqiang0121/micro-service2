package org.web.module.height.obesity.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.ChildrenMaternityMapper;
import org.web.module.height.obesity.entity.ChildrenMaternity;

@Service
public class ChildrenMaternityService {
	@Resource
	private ChildrenMaternityMapper mapper;

	public Map<String, Object> getOne(ChildrenMaternity childrenMaternity) {
		return mapper.getOne(childrenMaternity);
	}

	public int insert(ChildrenMaternity childrenMaternity) {
		return mapper.insert(childrenMaternity);
	}

	public int update(ChildrenMaternity childrenMaternity) {
		return mapper.update(childrenMaternity);
	}
}
