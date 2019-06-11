package org.wechat.module.height.obesity.entity;

import org.service.core.entity.BaseEntity;

public class FoodRecipesConfigHasBaseFood extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description: TODO
     */
    private static final long serialVersionUID = -8558584574039985117L;

    private Integer id;
    private Integer foodRecipesHasBaseFoodRecipesConfigId;
    private Integer foodRecipesConfigId;

    private Integer foodId;

    private Float number;

    public Integer getId() {
        return id;
    }

    /**
     * @return the foodRecipesHasBaseFoodRecipesConfigId
     */
    public Integer getFoodRecipesHasBaseFoodRecipesConfigId() {
        return foodRecipesHasBaseFoodRecipesConfigId;
    }

    /**
     * @param foodRecipesHasBaseFoodRecipesConfigId the
     *                                              foodRecipesHasBaseFoodRecipesConfigId
     *                                              to set
     */
    public void setFoodRecipesHasBaseFoodRecipesConfigId(Integer foodRecipesHasBaseFoodRecipesConfigId) {
        this.foodRecipesHasBaseFoodRecipesConfigId = foodRecipesHasBaseFoodRecipesConfigId;
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

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }
}