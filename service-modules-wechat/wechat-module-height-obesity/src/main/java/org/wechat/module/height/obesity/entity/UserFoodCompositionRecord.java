package org.wechat.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月13日
 * @description: 用户饮食记录表
 */
public class UserFoodCompositionRecord extends BaseEntity {
   
	private static final long serialVersionUID = 876520136672095127L;
	
	private Integer id;
	@NotNull(message = "{user.food.omposition.id.notnull.valid}", groups = {Insert.class })
    private Integer foodCompositionId;
	@NotNull(message = "{user.food.omposition.value.notnull.valid}", groups = {Insert.class })
    private Float value;
   
    private Integer userId;
    @NotBlank(message = "{user.food.omposition.code.notblank.valid}", groups = {Insert.class })
    private String code;
    @NotBlank(message = "{user.food.omposition.name.notblank.valid}", groups = {Insert.class })
    private String name;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDateTime;
    
    /*查询条件*/
    private String foodName;
    @NotBlank(message = "{user.food.omposition.food.code.notblank.valid}", groups = {Insert.class })
    private String foodCompositionCode;

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

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	
	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodCompositionCode() {
		return foodCompositionCode;
	}

	public void setFoodCompositionCode(String foodCompositionCode) {
		this.foodCompositionCode = foodCompositionCode;
	}

	@Override
   	@NotNull(message = "{page.empty}", groups = { SelectAll.class})
   	public Integer getPage() {
   		return super.getPage();
   	}

   	@Override
   	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class })
   	public Integer getPageSize() {
   		return super.getPageSize();
   	} 
}