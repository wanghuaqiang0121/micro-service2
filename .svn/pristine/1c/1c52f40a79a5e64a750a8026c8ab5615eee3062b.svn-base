package org.web.module.bone.age.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.BaseAgeOrderMapper;
import org.web.module.bone.age.domain.BaseAgeOrder;

@Service
public class AgeOrderService {
	@Resource
	private BaseAgeOrderMapper mapper;

	public Map<String, Object> getOne(BaseAgeOrder ageOrder) {
		return mapper.getOne(ageOrder);
	}

	public int update(BaseAgeOrder ageOrder) {
		return mapper.update(ageOrder);
	}

	public int insert(BaseAgeOrder ageOrder) {
		return mapper.insert(ageOrder);
	}

	public Map<String, Object> getTW2BoneScores(BaseAgeOrder ageOrder) {
		return mapper.getTW2BoneScores(ageOrder);
	}
	
}
