package org.web.module.bone.age.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.BoneAgeOrderLogMapper;
import org.web.module.bone.age.domain.BoneAgeOrderLog;

@Service
public class BoneAgeOrderLogService {
	@Autowired
	private BoneAgeOrderLogMapper boneAgeOrderLogMapper;
	
	public int insert(BoneAgeOrderLog boneAgeOrderLog){
		return boneAgeOrderLogMapper.insert(boneAgeOrderLog);
	}
}
