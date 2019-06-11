package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.FoodRecipesHasBaseFoodRecipesConfigMapper;
import org.web.module.height.obesity.entity.FoodRecipesHasBaseFoodRecipesConfig;

@Service
public class FoodRecipesHasBaseFoodRecipesConfigService {

	@Resource
	private FoodRecipesHasBaseFoodRecipesConfigMapper foodRecipesHasBaseFoodRecipesConfigMapper;

	public int insert(FoodRecipesHasBaseFoodRecipesConfig foodRecipesHasBaseFoodRecipesConfig) {
		return foodRecipesHasBaseFoodRecipesConfigMapper.insert(foodRecipesHasBaseFoodRecipesConfig);
	}

	public List<Map<String, Object>> getList(FoodRecipesHasBaseFoodRecipesConfig record) {
		return foodRecipesHasBaseFoodRecipesConfigMapper.getList(record);
	}

	public List<Map<String, Object>> getMealTimes(FoodRecipesHasBaseFoodRecipesConfig record) {
		return foodRecipesHasBaseFoodRecipesConfigMapper.getMealTimes(record);
	}
	public int delete(FoodRecipesHasBaseFoodRecipesConfig foodRecipesHasBaseFoodRecipesConfig) {
		return foodRecipesHasBaseFoodRecipesConfigMapper.delete(foodRecipesHasBaseFoodRecipesConfig);
	}
}
