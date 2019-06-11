package org.web.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

public class UserRecipesProject extends BaseEntity {

    private static final long serialVersionUID = -9212333022201116048L;

    private Integer id;

    @NotNull(message = "{children.user.id.notblank.valid}", groups = { Insert.class })
    private Integer userId;

    @NotNull(message = "{children.user.childrenmeasureid.notblank.valid}", groups = { Insert.class })
    private Integer childrenMeasureId;

    @NotNull(message = "{food.recipes.config.id.notblank.valid}", groups = { Insert.class })
    private Integer foodRecipesConfigId;

    private Date createDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChildrenMeasureId() {
        return childrenMeasureId;
    }

    public void setChildrenMeasureId(Integer childrenMeasureId) {
        this.childrenMeasureId = childrenMeasureId;
    }

    public Integer getFoodRecipesConfigId() {
        return foodRecipesConfigId;
    }

    public void setFoodRecipesConfigId(Integer foodRecipesConfigId) {
        this.foodRecipesConfigId = foodRecipesConfigId;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}