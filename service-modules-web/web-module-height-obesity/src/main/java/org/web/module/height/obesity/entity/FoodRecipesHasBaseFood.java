package org.web.module.height.obesity.entity;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

public class FoodRecipesHasBaseFood extends BaseEntity {
	private static final long serialVersionUID = -8150966492074563819L;

	private Integer id;

    private Integer foodRecipesCode;

    private Integer foodCode;

    public Integer getId() {
        return id;
    }

	public Integer getFoodRecipesCode() {
		return foodRecipesCode;
	}

	public void setFoodRecipesCode(Integer foodRecipesCode) {
		this.foodRecipesCode = foodRecipesCode;
	}

	public Integer getFoodCode() {
		return foodCode;
	}

	public void setFoodCode(Integer foodCode) {
		this.foodCode = foodCode;
	}

	public void setId(Integer id) {
		this.id = id;
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