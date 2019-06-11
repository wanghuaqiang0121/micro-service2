package org.web.module.bone.age.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.StandardHeightPercentile;

public interface StandardHeightPercentileMapper extends IBaseMapper<StandardHeightPercentile> {
    int deleteByPrimaryKey(Integer id);

    int insert(StandardHeightPercentile record);

    StandardHeightPercentile selectByPrimaryKey(Integer id);

    List<StandardHeightPercentile> selectAll();

    int updateByPrimaryKey(StandardHeightPercentile record);
    
    Map<String,Object> getPercentile(StandardHeightPercentile record);
}