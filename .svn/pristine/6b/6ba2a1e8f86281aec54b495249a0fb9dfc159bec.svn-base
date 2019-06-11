package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.FoodRecipesMapper;
import org.web.module.height.obesity.entity.FoodRecipes;

@Service
public class FoodRecipesService {
	
	@Resource
	private FoodRecipesMapper  foodRecipesMapper;
	
	/**
	 * @author: ChenYan
	 * @date: 2019年1月9日
	 * @param foodRecipes
	 * @return
	 * @description: 查询菜名下的食物列表
	 */
   public List<Map<String, Object>> getFoodRecipefoods(FoodRecipes foodRecipes){
	   return foodRecipesMapper.getFoodRecipefoods(foodRecipes);
   }
   
   public List<Map<String, Object>> getList(FoodRecipes foodRecipes){
	   return foodRecipesMapper.getList(foodRecipes);
   }

}
