package org.web.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.InfluenceHeightDisease;

public interface InfluenceHeightDiseaseMapper extends IBaseMapper<InfluenceHeightDisease>{
	
	public int batchInsert(List<InfluenceHeightDisease> influenceHeightDisease);

}	
