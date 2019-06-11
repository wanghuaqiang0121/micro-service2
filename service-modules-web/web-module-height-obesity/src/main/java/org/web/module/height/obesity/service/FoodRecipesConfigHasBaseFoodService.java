package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.FoodRecipesConfigHasBaseFoodMapper;
import org.web.module.height.obesity.entity.FoodRecipesConfigHasBaseFood;
import org.web.module.height.obesity.entity.FoodRecipesHasBaseFoodRecipesConfig;

@Service
public class FoodRecipesConfigHasBaseFoodService {

	@Resource
	private FoodRecipesConfigHasBaseFoodMapper foodRecipesConfigHasBaseFoodMapper;

	public int batchInsert(List<FoodRecipesConfigHasBaseFood> foodRecipesConfigHasBaseFood) {
		return foodRecipesConfigHasBaseFoodMapper.batchInsert(foodRecipesConfigHasBaseFood);
	}

	public List<Map<String, Object>> getList(FoodRecipesConfigHasBaseFood record) {
		return foodRecipesConfigHasBaseFoodMapper.getList(record);
	}
	
	public int delete(FoodRecipesHasBaseFoodRecipesConfig foodRecipesHasBaseFoodRecipesConfig) {
		return foodRecipesConfigHasBaseFoodMapper.delete(foodRecipesHasBaseFoodRecipesConfig);
	}
}
