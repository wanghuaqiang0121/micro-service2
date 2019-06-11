package org.web.module.height.obesity.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.UserExaminationMasterRecord;
import org.web.module.height.obesity.entity.UserExaminationRecord;

public interface UserExaminationRecordMapper extends IBaseMapper<UserExaminationRecord> {
	int deleteByPrimaryKey(Integer id);

	int insert(UserExaminationRecord record);

	UserExaminationRecord selectByPrimaryKey(Integer id);

	List<UserExaminationRecord> selectAll();

	int updateByPrimaryKey(UserExaminationRecord record);

	Map<String, Object> getUserExaminationByCode(UserExaminationRecord record);

	int beatchInsert(List<UserExaminationRecord> userExaminationRecordList);

	Map<String, Object> getOne(UserExaminationMasterRecord userExaminationMasterRecord);

}