package org.web.module.bone.age.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.BaseAgeOrder;

public interface BaseAgeOrderMapper extends IBaseMapper<BaseAgeOrder> {
    int delete(BaseAgeOrder ageOrder);

    int insert(BaseAgeOrder ageOrder);

    Map<String, Object> getOne(BaseAgeOrder ageOrder);

    List<BaseAgeOrder> selectAll();

    int update(BaseAgeOrder ageOrder);

	Map<String, Object> getTW2BoneScores(BaseAgeOrder ageOrder);
}