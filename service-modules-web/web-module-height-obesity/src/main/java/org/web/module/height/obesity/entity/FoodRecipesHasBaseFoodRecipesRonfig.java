package org.web.module.height.obesity.entity;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2019 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2019年1月9日
 * @description: 配置与菜品关联表
 */
public class FoodRecipesHasBaseFoodRecipesRonfig extends BaseEntity {

	private static final long serialVersionUID = -7656707979421578287L;

	private Integer id;
	private Integer foodRecipesConfigId;
	 @NotBlank(message = "{food.recipes.code.notblank.valid}", groups = { Insert.class })
	private String foodRecipesCode;
	 @NotBlank(message = "{food.recipes.name.notblank.valid}", groups = { Insert.class })
	private String foodRecipesName;
	@Valid
	@NotNull(message="{food.recipes.config.has.base.food.list.notnull.valid}",groups= {Insert.class})
	 private List<FoodRecipesConfigHasBaseFood> foodRecipesConfigHasBaseFoods;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFoodRecipesConfigId() {
		return foodRecipesConfigId;
	}

	public void setFoodRecipesConfigId(Integer foodRecipesConfigId) {
		this.foodRecipesConfigId = foodRecipesConfigId;
	}

	public String getFoodRecipesCode() {
		return foodRecipesCode;
	}

	public void setFoodRecipesCode(String foodRecipesCode) {
		this.foodRecipesCode = foodRecipesCode;
	}

	public String getFoodRecipesName() {
		return foodRecipesName;
	}

	public void setFoodRecipesName(String foodRecipesName) {
		this.foodRecipesName = foodRecipesName;
	}

	
	public List<FoodRecipesConfigHasBaseFood> getFoodRecipesConfigHasBaseFoods() {
		return foodRecipesConfigHasBaseFoods;
	}

	public void setFoodRecipesConfigHasBaseFoods(List<FoodRecipesConfigHasBaseFood> foodRecipesConfigHasBaseFoods) {
		this.foodRecipesConfigHasBaseFoods = foodRecipesConfigHasBaseFoods;
	}

	@Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}

}
