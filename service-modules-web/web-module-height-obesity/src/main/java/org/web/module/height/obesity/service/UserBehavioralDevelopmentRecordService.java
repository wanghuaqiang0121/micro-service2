package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserBehavioralDevelopmentRecordMapper;
import org.web.module.height.obesity.entity.UserBehavioralDevelopmentRecord;

@Service
public class UserBehavioralDevelopmentRecordService {

	@Autowired
	private UserBehavioralDevelopmentRecordMapper mapper;

	public int deleteByChildrenMeasureId(UserBehavioralDevelopmentRecord userBehavioralDevelopmentRecord) {
		return mapper.deleteByChildrenMeasureId(userBehavioralDevelopmentRecord);
	}

	public int insert(UserBehavioralDevelopmentRecord userBehavioralDevelopmentRecord) {
		return mapper.insert(userBehavioralDevelopmentRecord);
	}

	public Map<String, Object> getOne(UserBehavioralDevelopmentRecord userBehavioralDevelopmentRecord) {
		return mapper.getOne(userBehavioralDevelopmentRecord);
	}

	public List<Map<String, Object>> getList(UserBehavioralDevelopmentRecord userBehavioralDevelopmentRecord) {
		return mapper.getList(userBehavioralDevelopmentRecord);
	}
}
