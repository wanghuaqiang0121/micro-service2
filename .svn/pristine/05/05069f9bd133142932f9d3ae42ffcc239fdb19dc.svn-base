package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserExaminationMasterRecordMapper;
import org.web.module.height.obesity.entity.UserExaminationMasterRecord;

@Service
public class UserExaminationMasterRecordService {

	@Resource
	private UserExaminationMasterRecordMapper mapper;

	public List<Map<String, Object>> getList(UserExaminationMasterRecord userExaminationMasterRecord) {
		return mapper.getList(userExaminationMasterRecord);
	}

	public int insert(UserExaminationMasterRecord userExaminationMasterRecord) {
		return mapper.insert(userExaminationMasterRecord);
	}
	
}
