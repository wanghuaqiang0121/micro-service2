package org.web.module.height.obesity.controller.configuration;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.FoodRecipes;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.service.FoodRecipesService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController 
public class FoodRecipesController {
	
	@Resource
	private FoodRecipesService  foodRecipesService;
	
	/**
	 * @author: ChenYan
	 * @date: 2019年1月9日
	 * @param foodRecipes
	 * @param result
	 * @return
	 * @description: 查询列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-height-obesity:food-recipes:get-list" })
	@GetMapping(value = { "/food/recipes" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class })  FoodRecipes foodRecipes, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		Page<?> page = PageHelper.startPage(foodRecipes.getPage(), foodRecipes.getPageSize());
		List<Map<String, Object>> foodRecipesList = foodRecipesService.getList(foodRecipes);
		if (foodRecipesList != null && !foodRecipesList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), foodRecipesList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2019年1月9日
	 * @param foodRecipes
	 * @param result
	 * @return
	 * @description: 查询菜名下的食物列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-height-obesity:food-recipes:get-food-recipe-foods" })
	@GetMapping(value = { "/food/recipe/foods" })
	public JsonApi getFoodRecipefoods(@Validated({ BaseEntity.SelectAll.class })  FoodRecipes foodRecipes, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		Page<?> page = PageHelper.startPage(foodRecipes.getPage(), foodRecipes.getPageSize());
		List<Map<String, Object>> foodRecipesList = foodRecipesService.getFoodRecipefoods(foodRecipes);
		if (foodRecipesList != null && !foodRecipesList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), foodRecipesList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

}
