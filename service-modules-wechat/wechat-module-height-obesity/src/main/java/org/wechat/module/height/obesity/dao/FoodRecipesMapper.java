package org.wechat.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.FoodRecipes;

public interface FoodRecipesMapper extends IBaseMapper<FoodRecipes> {
    int deleteByPrimaryKey(Integer id);

    int insert(FoodRecipes record);

    FoodRecipes selectByPrimaryKey(Integer id);

    List<FoodRecipes> selectAll();

    int updateByPrimaryKey(FoodRecipes record);
}