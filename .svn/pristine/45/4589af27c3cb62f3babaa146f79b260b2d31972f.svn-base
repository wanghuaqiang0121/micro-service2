package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserTriageRecordMapper;
import org.web.module.height.obesity.entity.UserTriageRecord;

@Service
public class UserTriageRecordService {

	@Resource
	private UserTriageRecordMapper mapper;

	public List<Map<String, Object>> getTodayList(UserTriageRecord userTriageRecord) {
		return mapper.getTodayList(userTriageRecord);
	}

	public List<Map<String, Object>> getList(UserTriageRecord userTriageRecord) {
		return mapper.getList(userTriageRecord);
	}

	public int insert(UserTriageRecord userTriageRecord) {
		return mapper.insert(userTriageRecord);
	}

	public int update(UserTriageRecord userTriageRecord) {
		return mapper.update(userTriageRecord);
	}

	public Map<String, Object> getOne(UserTriageRecord userTriageRecord) {
		return mapper.getOne(userTriageRecord);
	}
	
}
