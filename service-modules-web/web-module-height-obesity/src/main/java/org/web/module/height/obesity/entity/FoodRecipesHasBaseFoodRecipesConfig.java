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
public class FoodRecipesHasBaseFoodRecipesConfig extends BaseEntity {

	private static final long serialVersionUID = -7656707979421578287L;

	private Integer id;
	private Integer foodRecipesConfigId;
	@NotBlank(message = "{food.recipes.code.notblank.valid}", groups = { Insert.class ,Update.class})
	private String foodRecipesCode;
	@NotBlank(message = "{food.recipes.name.notblank.valid}", groups = { Insert.class,Update.class })
	private String foodRecipesName;
	@Valid
	@NotNull(message = "{food.recipes.config.has.base.food.list.notnull.valid}", groups = { Insert.class ,Update.class})
	private List<FoodRecipesConfigHasBaseFood> foodRecipesConfigHasBaseFoods;

	private String name;
	private String codeType;

	public Integer getId() {
		return id;
	}

	/**
	 * @return the codeType
	 */
	public String getCodeType() {
		return codeType;
	}

	/**
	 * @param codeType the codeType to set
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
