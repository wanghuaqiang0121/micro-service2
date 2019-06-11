package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.InfluenceHeightDiseaseMapper;
import org.web.module.height.obesity.entity.InfluenceHeightDisease;

@Service
public class InfluenceHeightDiseaseService {
	
	@Resource
	private InfluenceHeightDiseaseMapper influenceHeightDiseaseMapper;
	
	public int batchInsert(List<InfluenceHeightDisease> influenceHeightDisease) {
		return influenceHeightDiseaseMapper.batchInsert(influenceHeightDisease);
	}
	
	public List<Map<String, Object>> getList(InfluenceHeightDisease influenceHeightDisease) {
		return influenceHeightDiseaseMapper.getList(influenceHeightDisease);
	}
	
	  public int delete(InfluenceHeightDisease influenceHeightDisease){
	        return influenceHeightDiseaseMapper.delete(influenceHeightDisease);
	    }
}
