package org.web.module.height.obesity.dao;

import java.util.List;
import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.UserChoiceRecord;

public interface UserChoiceRecordMapper extends IBaseMapper<UserChoiceRecord> {
    int deleteByPrimaryKey(Integer id);

    int insert(UserChoiceRecord record);

    UserChoiceRecord selectByPrimaryKey(Integer id);

    List<UserChoiceRecord> selectAll();

    int updateByPrimaryKey(UserChoiceRecord record);

	int beatchInsert(List<UserChoiceRecord> userChoiceRecordList);

	int deleteCodeRqFive(UserChoiceRecord userChoiceRecord);
}