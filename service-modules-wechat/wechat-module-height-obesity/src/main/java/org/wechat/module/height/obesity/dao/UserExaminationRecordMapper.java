package org.wechat.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.UserExaminationRecord;

public interface UserExaminationRecordMapper extends IBaseMapper<UserExaminationRecord> {
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月13日
	 * @param examinationCodeTables
	 * @return
	 * @description: 批量新增检查检验记录
	 */
	int batchInsert( List<UserExaminationRecord> userExaminationRecord);
}