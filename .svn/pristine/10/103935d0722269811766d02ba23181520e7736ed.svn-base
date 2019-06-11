package org.web.module.height.obesity.dao;

import java.util.List;
import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.UserSecondarySexCharactersRecord;

public interface UserSecondarySexCharactersRecordMapper extends IBaseMapper<UserSecondarySexCharactersRecord> {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSecondarySexCharactersRecord record);

    UserSecondarySexCharactersRecord selectByPrimaryKey(Integer id);

    List<UserSecondarySexCharactersRecord> selectAll();

    int updateByPrimaryKey(UserSecondarySexCharactersRecord record);

    int deleteByChildrenMeasureId(UserSecondarySexCharactersRecord userSecondarySexCharactersRecord);
}