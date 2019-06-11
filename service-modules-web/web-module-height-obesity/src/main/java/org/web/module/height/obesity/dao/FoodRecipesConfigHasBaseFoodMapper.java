package org.web.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.FoodRecipesConfigHasBaseFood;
import org.web.module.height.obesity.entity.FoodRecipesHasBaseFoodRecipesConfig;

public interface FoodRecipesConfigHasBaseFoodMapper extends IBaseMapper<FoodRecipesConfigHasBaseFood> {

	int batchInsert(List<FoodRecipesConfigHasBaseFood> foodRecipesConfigHasBaseFood);
    int delete(FoodRecipesHasBaseFoodRecipesConfig foodRecipesHasBaseFoodRecipesConfig);
}