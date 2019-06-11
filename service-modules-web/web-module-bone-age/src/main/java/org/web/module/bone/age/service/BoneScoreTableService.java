package org.web.module.bone.age.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.BoneScoreTableMapper;
import org.web.module.bone.age.domain.BoneScoreTable;

@Service
public class BoneScoreTableService {

	@Autowired
	private BoneScoreTableMapper boneScoreTableMapper;
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param boneScoreTable
	 * @return
	 * @description: 骨头类型列表
	 */
	public List<Map<String, Object>> getBoneType(BoneScoreTable boneScoreTable) {
		return boneScoreTableMapper.getBoneType(boneScoreTable);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param boneScoreTable
	 * @return
	 * @description: 骨头等级列表
	 */
	public List<Map<String, Object>> getBoneGrade(BoneScoreTable boneScoreTable) {
		return boneScoreTableMapper.getBoneGrade(boneScoreTable);
	}
}
