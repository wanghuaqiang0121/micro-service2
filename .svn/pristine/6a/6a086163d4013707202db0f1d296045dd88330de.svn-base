package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.UserExaminationRecordMapper;
import org.wechat.module.height.obesity.entity.UserExaminationRecord;

@Service
public class UserExaminationRecordService {
	@Resource
	private UserExaminationRecordMapper userExaminationRecordMapper ;
	/**
	 * @author: ChenYan
	 * @date: 2018年12月13日
	 * @param examinationCodeTables
	 * @return
	 * @description: 批量新增检查检验记录
	 */
	public	int batchInsert(List<UserExaminationRecord> userExaminationRecord) {
		return userExaminationRecordMapper.batchInsert(userExaminationRecord);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年12月13日
	 * @param userExaminationRecord
	 * @return
	 * @description: 历史检查报告列表
	 */
	public List<Map<String, Object>> getList(UserExaminationRecord userExaminationRecord){
		return userExaminationRecordMapper.getList(userExaminationRecord);
	}
}
