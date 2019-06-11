package org.web.module.height.obesity.dao;

import java.util.List;
import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.WhoStandardWeightSdTemp;

public interface WhoStandardWeightSdTempMapper extends IBaseMapper<WhoStandardWeightSdTemp> {
    int deleteByPrimaryKey(Integer id);

    int insert(WhoStandardWeightSdTemp record);

    WhoStandardWeightSdTemp selectByPrimaryKey(Integer id);

    List<WhoStandardWeightSdTemp> selectAll();

    int updateByPrimaryKey(WhoStandardWeightSdTemp record);
}