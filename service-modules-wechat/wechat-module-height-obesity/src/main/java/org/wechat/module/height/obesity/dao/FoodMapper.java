package org.wechat.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.Food;

public interface FoodMapper extends IBaseMapper<Food> {
    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    Food selectByPrimaryKey(Integer id);

    List<Food> selectAll();

    int updateByPrimaryKey(Food record);
}