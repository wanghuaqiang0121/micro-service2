package org.web.module.height.obesity.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月25日
 * @description: 
 */
public class FoodRecipesConfigHasBaseFood extends BaseEntity {

	private static final long serialVersionUID = -8558584574039985117L;

	private Integer id;

	private Integer foodRecipesHasBaseFoodRecipesConfigId;
	@NotBlank(message = "{food.recipes.code.notblank.valid}", groups = { Insert.class ,Update.class})
	private String foodRecipesCode;
	@NotBlank(message = "{food.recipes.name.notblank.valid}", groups = { Insert.class,Update.class })
	private String foodRecipesName;
	@NotBlank(message = "{food.code.notblank.valid}", groups = { Insert.class,Update.class })
	private String foodCode;
	@NotBlank(message = "{food.name.notblank.valid}", groups = { Insert.class ,Update.class})
	private String foodName;
	//@NotNull(message="{food.number.notnull.valid}",groups= {Insert.class,Update.class})
	private Float number;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}



	public Integer getFoodRecipesHasBaseFoodRecipesConfigId() {
		return foodRecipesHasBaseFoodRecipesConfigId;
	}

	public void setFoodRecipesHasBaseFoodRecipesConfigId(Integer foodRecipesHasBaseFoodRecipesConfigId) {
		this.foodRecipesHasBaseFoodRecipesConfigId = foodRecipesHasBaseFoodRecipesConfigId;
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

	public String getFoodCode() {
		return foodCode;
	}

	public void setFoodCode(String foodCode) {
		this.foodCode = foodCode;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
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