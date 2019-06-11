package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.UserExaminationMasterRecordMapper;
import org.wechat.module.height.obesity.entity.UserExaminationMasterRecord;

@Service
public class UserExaminationMasterRecordService {
	
	@Resource
	private UserExaminationMasterRecordMapper  userExaminationMasterRecordMapper;
	
	public int insert(UserExaminationMasterRecord userExaminationMasterRecord) {
		return userExaminationMasterRecordMapper.insert(userExaminationMasterRecord);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月18日
	 * @param userExaminationMasterRecord
	 * @return
	 * @description: 检验检查报告列表
	 */
	public  List<Map<String, Object>> getList(UserExaminationMasterRecord userExaminationMasterRecord){
		return userExaminationMasterRecordMapper.getList(userExaminationMasterRecord);
	}

	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月19日
	 * @param userExaminationMasterRecord
	 * @return
	 * @description: 查询检查报告详情
	 */
	public Map<String, Object> getOne(UserExaminationMasterRecord userExaminationMasterRecord) {
		return userExaminationMasterRecordMapper.getOne(userExaminationMasterRecord);
	}
}
