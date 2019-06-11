package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserChoiceRecordMapper;
import org.web.module.height.obesity.entity.UserChoiceRecord;

@Service
public class UserChoiceRecordService {

	@Resource
	private UserChoiceRecordMapper mapper;

	public List<Map<String, Object>> getList(UserChoiceRecord userChoiceRecord) {
		return mapper.getList(userChoiceRecord);
	}

	public int beatchInsert(List<UserChoiceRecord> userChoiceRecordList) {
		return mapper.beatchInsert(userChoiceRecordList);
	}

	public int delete(UserChoiceRecord userChoiceRecord) {
		return mapper.delete(userChoiceRecord);
	}

	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月25日
	 * @param userChoiceRecord
	 * @return
	 * @description: 删除问题code为5的数据
	 */
	public int deleteCodeRqFive(UserChoiceRecord userChoiceRecord) {
		return mapper.deleteCodeRqFive(userChoiceRecord);
	}
	
}
