package org.wechat.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.WhoStandardHeightWeightSdTemp;

public interface WhoStandardHeightWeightSdTempMapper extends IBaseMapper<WhoStandardHeightWeightSdTemp> {
    int deleteByPrimaryKey(Integer id);

    int insert(WhoStandardHeightWeightSdTemp record);

    WhoStandardHeightWeightSdTemp selectByPrimaryKey(Integer id);

    List<WhoStandardHeightWeightSdTemp> selectAll();

    int updateByPrimaryKey(WhoStandardHeightWeightSdTemp record);
}