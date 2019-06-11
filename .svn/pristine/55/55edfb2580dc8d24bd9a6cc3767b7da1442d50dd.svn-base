package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserObesityManagerRecordMapper;
import org.web.module.height.obesity.entity.UserObesityManagerRecord;

@Service
public class UserObesityManagerRecordService {

	@Resource
	private UserObesityManagerRecordMapper userObesityManagerRecordMapper;

	/**
	 * @author: ChenYan
	 * @date: 2019年1月8日
	 * @param userObesityManagerRecord
	 * @return
	 * @description: 查询用户专案管理列表
	 */
	public List<Map<String, Object>> getListByUserId(UserObesityManagerRecord userObesityManagerRecord) {
		return userObesityManagerRecordMapper.getListByUserId(userObesityManagerRecord);
	}

	public List<Map<String, Object>> getList(UserObesityManagerRecord userObesityManagerRecord) {
		return userObesityManagerRecordMapper.getList(userObesityManagerRecord);
	}

	public int insert(UserObesityManagerRecord userObesityManagerRecord) {
		return userObesityManagerRecordMapper.insert(userObesityManagerRecord);
	}

}
