package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.WhoStandardHeadCircumferenceSdMapper;
import org.web.module.height.obesity.entity.WhoStandardHeadCircumferenceSd;

@Service
public class WhoStandardHeadCircumferenceSdService {

	@Resource
	private WhoStandardHeadCircumferenceSdMapper whoStandardHeadCircumferenceSdMapper;
	
	public List<Map<String,Object>> getList(WhoStandardHeadCircumferenceSd entity){
		return whoStandardHeadCircumferenceSdMapper.getList(entity);
	}
}
