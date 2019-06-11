package org.web.module.height.obesity.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.WhoStandardBmiSd;

public interface WhoStandardBmiSdMapper extends IBaseMapper<WhoStandardBmiSd> {
    int deleteByPrimaryKey(Integer id);

    int insert(WhoStandardBmiSd record);

    WhoStandardBmiSd selectByPrimaryKey(Integer id);

    List<WhoStandardBmiSd> selectAll();

    int updateByPrimaryKey(WhoStandardBmiSd record);

    Map<String, Object> getBmiSd(WhoStandardBmiSd record);

    Map<String, Object> getBmiStandValue(WhoStandardBmiSd record);

	Map<String, Object> getBmiStandardValue(WhoStandardBmiSd whoStandardBmiSd);
}