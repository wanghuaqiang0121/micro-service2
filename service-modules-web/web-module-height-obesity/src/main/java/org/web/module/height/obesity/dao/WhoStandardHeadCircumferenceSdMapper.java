package org.web.module.height.obesity.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.WhoStandardHeadCircumferenceSd;

public interface WhoStandardHeadCircumferenceSdMapper extends IBaseMapper<WhoStandardHeadCircumferenceSd> {
    int deleteByPrimaryKey(Integer id);

    int insert(WhoStandardHeadCircumferenceSd record);

    WhoStandardHeadCircumferenceSd selectByPrimaryKey(Integer id);

    List<WhoStandardHeadCircumferenceSd> selectAll();

    int updateByPrimaryKey(WhoStandardHeadCircumferenceSd record);
    
    Map<String,Object> getHeadCircumferenceSd(WhoStandardHeadCircumferenceSd record);
}