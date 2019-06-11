package org.web.module.height.obesity.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.ChildrenFamilyMapper;
import org.web.module.height.obesity.entity.ChildrenFamily;

@Service
public class ChildrenFamilyService {
	@Resource
	private ChildrenFamilyMapper mapper;

	public int insert(ChildrenFamily childrenFamily) {
		return mapper.insert(childrenFamily);
	}

	public int update(ChildrenFamily childrenFamily) {
		return mapper.update(childrenFamily);
	}

	public Map<String, Object> getOne(ChildrenFamily childrenfamily) {
		return mapper.getOne(childrenfamily);
	}
}
