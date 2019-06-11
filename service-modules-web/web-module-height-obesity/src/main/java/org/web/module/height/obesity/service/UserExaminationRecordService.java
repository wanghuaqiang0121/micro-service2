package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserExaminationRecordMapper;
import org.web.module.height.obesity.entity.UserExaminationRecord;

@Service
public class UserExaminationRecordService {

	@Resource
	private UserExaminationRecordMapper mapper;

	public int beatchInsert(List<UserExaminationRecord> userExaminationRecordList) {
		return mapper.beatchInsert(userExaminationRecordList);
	}


	public Map<String, Object> getOne(UserExaminationRecord userExaminationRecord) {
		return mapper.getOne(userExaminationRecord);
	}


	public int update(UserExaminationRecord userExaminationRecord) {
		return mapper.update(userExaminationRecord);
	}

	
}
