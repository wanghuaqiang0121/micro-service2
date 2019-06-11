package org.web.module.bone.age.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.BoneScoreTable;

public interface BoneScoreTableMapper extends IBaseMapper<BoneScoreTable> {
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param boneScoreTable
	 * @return
	 * @description: 骨头类型列表
	 */
	List<Map<String, Object>> getBoneType(BoneScoreTable boneScoreTable);
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param boneScoreTable
	 * @return
	 * @description: 骨头等级列表
	 */
	List<Map<String, Object>> getBoneGrade(BoneScoreTable boneScoreTable);
}