package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.NameRemarkMapper;
import org.web.module.height.obesity.entity.NameRemark;
@Service
public class NameRemarkService {
	
	@Resource
	private NameRemarkMapper mapper;
	  public Map<String, Object> getOne(NameRemark nameRemark) {
	        return mapper.getOne(nameRemark);
	    }
	    public List<Map<String, Object>> getList(NameRemark nameRemark) {
	        return mapper.getList(nameRemark);
	    }
}
