package org.wechat.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.WhoStandardHeadCircumferenceSdTemp;

public interface WhoStandardHeadCircumferenceSdTempMapper extends IBaseMapper<WhoStandardHeadCircumferenceSdTemp> {
    int deleteByPrimaryKey(Integer id);

    int insert(WhoStandardHeadCircumferenceSdTemp record);

    WhoStandardHeadCircumferenceSdTemp selectByPrimaryKey(Integer id);

    List<WhoStandardHeadCircumferenceSdTemp> selectAll();

    int updateByPrimaryKey(WhoStandardHeadCircumferenceSdTemp record);
}