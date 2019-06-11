package org.web.module.height.obesity.entity;

import org.service.core.entity.BaseEntity;

public class UserFoodCompositionRecord extends BaseEntity {
	private static final long serialVersionUID = 6071336883285713047L;

	private Integer id;

    private Integer foodCompositionId;

    private Integer userId;

    private String code;

    private String name;

    private String createDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodCompositionId() {
        return foodCompositionId;
    }

    public void setFoodCompositionId(Integer foodCompositionId) {
        this.foodCompositionId = foodCompositionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }
}