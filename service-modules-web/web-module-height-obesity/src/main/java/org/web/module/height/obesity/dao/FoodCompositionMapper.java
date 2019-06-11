package org.web.module.height.obesity.dao;

import java.util.List;
import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.FoodComposition;

public interface FoodCompositionMapper extends IBaseMapper<FoodComposition> {
    int deleteByPrimaryKey(Integer id);

    int insert(FoodComposition record);

    FoodComposition selectByPrimaryKey(Integer id);

    List<FoodComposition> selectAll();

    int updateByPrimaryKey(FoodComposition record);
}