package org.wechat.module.height.obesity.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.WhoStandardWeightSd;

public interface WhoStandardWeightSdMapper extends IBaseMapper<WhoStandardWeightSd> {
    int deleteByPrimaryKey(Integer id);

    int insert(WhoStandardWeightSd record);

    WhoStandardWeightSd selectByPrimaryKey(Integer id);

    List<WhoStandardWeightSd> selectAll();

    int updateByPrimaryKey(WhoStandardWeightSd record);

    Map<String, Object> getWeightSd(WhoStandardWeightSd record);
}