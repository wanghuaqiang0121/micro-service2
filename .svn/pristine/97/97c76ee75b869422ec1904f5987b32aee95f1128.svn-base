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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.FoodRecipesConfig;
import org.web.module.height.obesity.entity.FoodRecipesConfigHasBaseFood;
import org.web.module.height.obesity.entity.FoodRecipesHasBaseFoodRecipesConfig;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.service.FoodRecipesConfigHasBaseFoodService;
import org.web.module.height.obesity.service.FoodRecipesConfigService;
import org.web.module.height.obesity.service.FoodRecipesHasBaseFoodRecipesConfigService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController
public class FoodRecipesConfigController {

	@Resource
	private FoodRecipesConfigService foodRecipesConfigService;

	@Resource
	private FoodRecipesHasBaseFoodRecipesConfigService foodRecipesHasBaseFoodRecipesConfigService;

	@Resource
	private FoodRecipesConfigHasBaseFoodService foodRecipesConfigHasBaseFoodService;

	/**
	 * @author: ChenYan
	 * @date: 2019年1月9日
	 * @param foodRecipesConfig
	 * @param result
	 * @return
	 * @description: 食谱配置列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = {
			"web-module-height-obesity:food-recipes-config:get-list" })
	@GetMapping(value = { "/food/recipes/configs" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) FoodRecipesConfig foodRecipesConfig,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		Page<?> page = PageHelper.startPage(foodRecipesConfig.getPage(), foodRecipesConfig.getPageSize());
		List<Map<String, Object>> foodRecipesConfigList = foodRecipesConfigService.getList(foodRecipesConfig);
		if (foodRecipesConfigList != null && !foodRecipesConfigList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), foodRecipesConfigList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2019年1月10日
	 * @param foodRecipesConfig
	 * @param result
	 * @return
	 * @description: 新增菜谱配置
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-height-obesity:food-recipes-config:insert" })
	@PostMapping(value = { "/food/recipes/config" })
	@Transactional
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody FoodRecipesConfig foodRecipesConfig,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {

		/* 获取配置与菜品关联表集合 */
		List<FoodRecipesHasBaseFoodRecipesConfig> foodRecipesHasBaseFoodRecipesConfigs = foodRecipesConfig
				.getFoodRecipesHasBaseFoodRecipesRonfigs();
		/* 新增菜谱配置 */
		if (foodRecipesConfigService.insert(foodRecipesConfig) > 0) {
			/* 设置配置与菜品关联表外键id */
			FoodRecipesHasBaseFoodRecipesConfig foodRecipesHasBaseFoodRecipesConfig = new FoodRecipesHasBaseFoodRecipesConfig();
			foodRecipesHasBaseFoodRecipesConfig.setFoodRecipesConfigId(foodRecipesConfig.getId());
			/* for循环添加关联表 */
			for (int i = 0; i < foodRecipesHasBaseFoodRecipesConfigs.size(); i++) {
				/* 设置菜code */
				foodRecipesHasBaseFoodRecipesConfig
						.setFoodRecipesCode(foodRecipesHasBaseFoodRecipesConfigs.get(i).getFoodRecipesCode());
				/* 设置菜名 */
				foodRecipesHasBaseFoodRecipesConfig
						.setFoodRecipesName(foodRecipesHasBaseFoodRecipesConfigs.get(i).getFoodRecipesName());
				/* 获取菜里的食材集合 */
				List<FoodRecipesConfigHasBaseFood> foodRecipesConfigHasBaseFoods = foodRecipesHasBaseFoodRecipesConfigs
						.get(i).getFoodRecipesConfigHasBaseFoods();
				/* 添加关联表 */
				if (foodRecipesHasBaseFoodRecipesConfigService.insert(foodRecipesHasBaseFoodRecipesConfig) < 0) {
					throw new RuntimeException();
				}
				/* 设置食材所属的菜id */
				for (FoodRecipesConfigHasBaseFood foodRecipesConfigHasBaseFood : foodRecipesConfigHasBaseFoods) {
					foodRecipesConfigHasBaseFood
							.setFoodRecipesHasBaseFoodRecipesConfigId(foodRecipesHasBaseFoodRecipesConfig.getId());
				}
				/* 批量添加食材与菜的关联表 */
				if (foodRecipesConfigHasBaseFoodService
						.batchInsert(foodRecipesConfigHasBaseFoods) != foodRecipesConfigHasBaseFoods.size()) {
					throw new RuntimeException();
				}
			}
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	
	/**
	 * @author: ChenYan
	 * @date: 2019年2月11日
	 * @param id
	 * @param foodRecipesConfig
	 * @param result
	 * @return
	 * @description: 查询详情
	 */
	@RequiresAuthentication(ignore=true,level = Level.OPERATION, value = { "web-module-height-obesity:food-recipes-config:get-detail" })
	@GetMapping(value = { "/food/recipes/config/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class })FoodRecipesConfig foodRecipesConfig, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		foodRecipesConfig.setId(id);
		Map<String, Object> foodRecipesConfigMap = foodRecipesConfigService.getOne(foodRecipesConfig);
		if (foodRecipesConfigMap != null ) {
			return new JsonApi(ApiCodeEnum.OK, foodRecipesConfigMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年2月11日
	 * @param id
	 * @param foodRecipesConfig
	 * @param result
	 * @return
	 * @description: 删除
	 */
	@RequiresAuthentication(ignore=true,level = Level.OPERATION, value = { "web-module-height-obesity:food-recipes-config:delete" })
	@DeleteMapping(value = { "/food/recipes/config/{id}" })
	@Transactional
	public JsonApi delete(@PathVariable("id") Integer id, @Validated({ BaseEntity.Delete.class }) FoodRecipesConfig foodRecipesConfig, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		foodRecipesConfig.setId(id);
		FoodRecipesHasBaseFoodRecipesConfig foodRecipesHasBaseFoodRecipesConfig = new FoodRecipesHasBaseFoodRecipesConfig();
		foodRecipesHasBaseFoodRecipesConfig.setFoodRecipesConfigId(id);
		
		if (foodRecipesConfigHasBaseFoodService.delete(foodRecipesHasBaseFoodRecipesConfig)<0) {
			return new JsonApi(ApiCodeEnum.FAIL);
		}
		if (foodRecipesHasBaseFoodRecipesConfigService.delete(foodRecipesHasBaseFoodRecipesConfig)<0) {
			throw new RuntimeException();
		}
		if (foodRecipesConfigService.delete(foodRecipesConfig)<0) {
			throw new RuntimeException();
		}
			return new JsonApi(ApiCodeEnum.OK);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年2月11日
	 * @param id
	 * @param foodRecipesConfig
	 * @param result
	 * @return
	 * @description: 修改数据
	 */
	@RequiresAuthentication(ignore=true,level = Level.OPERATION, value = { "web-module-height-obesity:food-recipes-config:update" })
	@PutMapping(value = { "/food/recipes/config/{id}" })
	@Transactional
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class })  @RequestBody FoodRecipesConfig foodRecipesConfig, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
	
		/*先删除*/
		FoodRecipesConfig foodRecipes=new FoodRecipesConfig();
		foodRecipes.setId(id);
		FoodRecipesHasBaseFoodRecipesConfig foodRecipesHasBaseFoodRecipesConfig = new FoodRecipesHasBaseFoodRecipesConfig();
		foodRecipesHasBaseFoodRecipesConfig.setFoodRecipesConfigId(id);
		
		if (foodRecipesConfigHasBaseFoodService.delete(foodRecipesHasBaseFoodRecipesConfig)<0) {
			return new JsonApi(ApiCodeEnum.FAIL);
		}
		if (foodRecipesHasBaseFoodRecipesConfigService.delete(foodRecipesHasBaseFoodRecipesConfig)<0) {
			throw new RuntimeException();
		}
		if (foodRecipesConfigService.delete(foodRecipes)<0) {
			throw new RuntimeException();
		}
		
		/*后新增*/	
		/* 获取配置与菜品关联表集合 */
		List<FoodRecipesHasBaseFoodRecipesConfig> foodRecipesHasBaseFoodRecipesConfigs = foodRecipesConfig
				.getFoodRecipesHasBaseFoodRecipesRonfigs();
		/* 新增菜谱配置 */
		if (foodRecipesConfigService.insert(foodRecipesConfig) > 0) {
			/* 设置配置与菜品关联表外键id */
			FoodRecipesHasBaseFoodRecipesConfig foodRecipesHasBaseFoodRecipe= new FoodRecipesHasBaseFoodRecipesConfig();
			foodRecipesHasBaseFoodRecipe.setFoodRecipesConfigId(foodRecipesConfig.getId());
			/* for循环添加关联表 */
			for (int i = 0; i < foodRecipesHasBaseFoodRecipesConfigs.size(); i++) {
				/* 设置菜code */
				foodRecipesHasBaseFoodRecipe
						.setFoodRecipesCode(foodRecipesHasBaseFoodRecipesConfigs.get(i).getFoodRecipesCode());
				/* 设置菜名 */
				foodRecipesHasBaseFoodRecipe
						.setFoodRecipesName(foodRecipesHasBaseFoodRecipesConfigs.get(i).getFoodRecipesName());
				/* 获取菜里的食材集合 */
				List<FoodRecipesConfigHasBaseFood> foodRecipesConfigHasBaseFoods = foodRecipesHasBaseFoodRecipesConfigs
						.get(i).getFoodRecipesConfigHasBaseFoods();
				/* 添加关联表 */
				if (foodRecipesHasBaseFoodRecipesConfigService.insert(foodRecipesHasBaseFoodRecipe) < 0) {
					throw new RuntimeException();
				}
				/* 设置食材所属的菜id */
				for (FoodRecipesConfigHasBaseFood foodRecipesConfigHasBaseFood : foodRecipesConfigHasBaseFoods) {
					foodRecipesConfigHasBaseFood
							.setFoodRecipesHasBaseFoodRecipesConfigId(foodRecipesHasBaseFoodRecipe.getId());
				}
				/* 批量添加食材与菜的关联表 */
				if (foodRecipesConfigHasBaseFoodService
						.batchInsert(foodRecipesConfigHasBaseFoods) != foodRecipesConfigHasBaseFoods.size()) {
					throw new RuntimeException();
				}
			}
			return new JsonApi(ApiCodeEnum.OK);
		}
		throw new RuntimeException();
	}
}
