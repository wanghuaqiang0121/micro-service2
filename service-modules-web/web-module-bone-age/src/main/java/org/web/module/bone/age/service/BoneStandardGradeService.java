package org.web.module.bone.age.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.BoneStandardGradeMapper;
import org.web.module.bone.age.domain.BoneStandardGrade;

@Service
public class BoneStandardGradeService {

	@Autowired
	private BoneStandardGradeMapper boneStandardGradeMapper;

	public List<Map<String, Object>> getList(BoneStandardGrade boneStandardGrade) {
		return boneStandardGradeMapper.getList(boneStandardGrade);
	}
	
}
