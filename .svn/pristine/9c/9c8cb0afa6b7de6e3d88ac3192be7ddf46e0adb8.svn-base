package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserSecondarySexCharactersRecordMapper;
import org.web.module.height.obesity.entity.UserSecondarySexCharactersRecord;

@Service
public class UserSecondarySexCharactersRecordService {

	@Resource
	UserSecondarySexCharactersRecordMapper mapper;

	public List<Map<String, Object>> getList(UserSecondarySexCharactersRecord entity) {
		return mapper.getList(entity);
	}

	public int insert(UserSecondarySexCharactersRecord userSecondarySexCharactersRecord) {
		return mapper.insert(userSecondarySexCharactersRecord);
	}

	public Map<String, Object> getOne(UserSecondarySexCharactersRecord userSecondarySexCharactersRecord) {
		return mapper.getOne(userSecondarySexCharactersRecord);
	}

	public int update(UserSecondarySexCharactersRecord userSecondarySexCharactersRecord) {
		return mapper.update(userSecondarySexCharactersRecord);
	}

	public int deleteByChildrenMeasureId(UserSecondarySexCharactersRecord userSecondarySexCharactersRecord) {
		return mapper.deleteByChildrenMeasureId(userSecondarySexCharactersRecord);
	}

}
