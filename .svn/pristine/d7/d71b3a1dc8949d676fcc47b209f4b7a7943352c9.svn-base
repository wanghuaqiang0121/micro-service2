package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.FoodRecipesConfigMapper;
import org.web.module.height.obesity.entity.FoodRecipesConfig;

@Service
public class FoodRecipesConfigService {

	@Resource
	private FoodRecipesConfigMapper foodRecipesConfigMapper;

	public List<Map<String, Object>> getList(FoodRecipesConfig foodRecipesConfig) {
		return foodRecipesConfigMapper.getList(foodRecipesConfig);
	}

	public int insert(FoodRecipesConfig foodRecipesConfig) {
		return foodRecipesConfigMapper.insert(foodRecipesConfig);
	}

	public Map<String, Object> getRandomConfig(FoodRecipesConfig record) {
		return foodRecipesConfigMapper.getRandomConfig(record);
	}
	
	public Map<String, Object> getOne(FoodRecipesConfig foodRecipesConfig) {
		return foodRecipesConfigMapper.getOne(foodRecipesConfig);
	}
	
	public int delete(FoodRecipesConfig foodRecipesConfig) {
		return foodRecipesConfigMapper.delete(foodRecipesConfig);
	}
}
