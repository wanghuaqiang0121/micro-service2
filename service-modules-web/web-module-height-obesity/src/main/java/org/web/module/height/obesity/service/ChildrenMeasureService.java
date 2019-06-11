package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.ChildrenMeasureMapper;
import org.web.module.height.obesity.entity.ChildrenMeasure;

@Service
public class ChildrenMeasureService {
	@Resource
	private ChildrenMeasureMapper mapper;

	public Map<String, Object> getOne(ChildrenMeasure childrenMeasure) {
		return mapper.getOne(childrenMeasure);
	}

	public int insert(ChildrenMeasure childrenMeasure) {
		return mapper.insert(childrenMeasure);
	}

	public Map<String, Object> getNewOne(ChildrenMeasure childrenMeasure) {
		return mapper.getNewOne(childrenMeasure);
	}

	public int update(ChildrenMeasure childrenMeasure) {
		return mapper.update(childrenMeasure);
	}

	public List<Map<String, Object>> getList(ChildrenMeasure childrenMeasure) {
		return mapper.getList(childrenMeasure);
	}

	public Map<String, Object> isExistsUserTeamRelation(ChildrenMeasure childrenMeasure) {
		return mapper.isExistsUserTeamRelation(childrenMeasure);
	}

	public int insertUserTeamRelation(ChildrenMeasure childrenMeasure) {
		return mapper.insertUserTeamRelation(childrenMeasure);
	}

	public int updateUserTeamRelation(ChildrenMeasure childrenMeasure) {
		return mapper.updateUserTeamRelation(childrenMeasure);
	}
	public Map<String, Object>  getSynchronizationData(ChildrenMeasure childrenMeasure){
		return mapper.getSynchronizationData(childrenMeasure);
	}
	    
	public    Map<String, Object> getSynchronizationMeasure(ChildrenMeasure childrenMeasure){
		return mapper.getSynchronizationMeasure(childrenMeasure);
	}
	
	public Map<String, Object> getSynchronizationHealthcare(ChildrenMeasure childrenMeasure){
		return mapper.getSynchronizationHealthcare(childrenMeasure);
	}

	public Map<String, Object> getSynchronizationInfo(ChildrenMeasure childrenMeasure){
		return mapper.getSynchronizationInfo(childrenMeasure);
	}
	
	public Map<String, Object> getBoneAge(ChildrenMeasure childrenMeasure){
		return mapper.getBoneAge(childrenMeasure);
	}
}
