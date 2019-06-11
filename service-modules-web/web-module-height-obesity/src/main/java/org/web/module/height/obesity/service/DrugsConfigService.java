package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.DrugsConfigMapper;
import org.web.module.height.obesity.entity.DrugsConfig;

@Service
public class DrugsConfigService {
	
	@Resource
	private  DrugsConfigMapper  drugsConfigMapper;

	  public int insert(DrugsConfig drugsConfig){
	        return drugsConfigMapper.insert(drugsConfig);
	    }
	    public int update(DrugsConfig drugsConfig){
	        return drugsConfigMapper.update(drugsConfig);
	    }
	    public int delete(DrugsConfig drugsConfig){
	        return drugsConfigMapper.delete(drugsConfig);
	    }
	    public Map<String, Object> getOne(DrugsConfig drugsConfig) {
	        return drugsConfigMapper.getOne(drugsConfig);
	    }
	    public List<Map<String, Object>> getList(DrugsConfig drugsConfig) {
	        return drugsConfigMapper.getList(drugsConfig);
	    }
}
