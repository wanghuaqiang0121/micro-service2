package org.web.module.height.obesity.dao;

import java.util.List;
import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.UserBehavioralDevelopmentRecord;

public interface UserBehavioralDevelopmentRecordMapper extends IBaseMapper<UserBehavioralDevelopmentRecord> {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBehavioralDevelopmentRecord record);

    UserBehavioralDevelopmentRecord selectByPrimaryKey(Integer id);

    List<UserBehavioralDevelopmentRecord> selectAll();

    int updateByPrimaryKey(UserBehavioralDevelopmentRecord record);

	int deleteByChildrenMeasureId(UserBehavioralDevelopmentRecord userBehavioralDevelopmentRecord);
}