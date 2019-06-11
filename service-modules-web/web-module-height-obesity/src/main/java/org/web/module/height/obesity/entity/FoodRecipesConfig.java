package org.web.module.height.obesity.entity;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月25日
 * @description: 食谱配置
 */
public class FoodRecipesConfig extends BaseEntity {

    private static final long serialVersionUID = 8355130168009537364L;

    private Integer id;
    @NotNull(message = "{food.recipes.config.sex.notnull.valid}", groups = { Insert.class ,Update.class})
    private Integer sex;
    @NotNull(message = "{food.recipes.config.start.notnull.valid}", groups = { Insert.class,Update.class })
    private Integer monthAgeStart;
    @NotNull(message = "{food.recipes.config.end.notnull.valid}", groups = { Insert.class ,Update.class})
    private Integer monthAgeEnd;
    @NotBlank(message = "{food.recipes.config.code.type.notblank.valid}", groups = { Insert.class,Update.class })
    private String codeType;
    @NotBlank(message = "{food.recipes.config.code.name.notblank.valid}", groups = { Insert.class,Update.class })
    private String codeName;

    private Integer monthAge;
    @Valid
    @NotNull(message = "{food.recipes.has.base.food.recipes.configs.list.notnull.valid}", groups = { Insert.class ,Update.class})
    private List<FoodRecipesHasBaseFoodRecipesConfig> foodRecipesHasBaseFoodRecipesRonfigs;
    @NotBlank(message = "{food.recipes.config.name.notblank.valid}", groups = { Insert.class ,Update.class})
    private String name;

    public Integer getId() {
        return id;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getMonthAgeStart() {
        return monthAgeStart;
    }

    public void setMonthAgeStart(Integer monthAgeStart) {
        this.monthAgeStart = monthAgeStart;
    }

    public Integer getMonthAgeEnd() {
        return monthAgeEnd;
    }

    public void setMonthAgeEnd(Integer monthAgeEnd) {
        this.monthAgeEnd = monthAgeEnd;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Integer getMonthAge() {
        return monthAge;
    }

    public void setMonthAge(Integer monthAge) {
        this.monthAge = monthAge;
    }

    public List<FoodRecipesHasBaseFoodRecipesConfig> getFoodRecipesHasBaseFoodRecipesRonfigs() {
        return foodRecipesHasBaseFoodRecipesRonfigs;
    }

    public void setFoodRecipesHasBaseFoodRecipesRonfigs(
            List<FoodRecipesHasBaseFoodRecipesConfig> foodRecipesHasBaseFoodRecipesRonfigs) {
        this.foodRecipesHasBaseFoodRecipesRonfigs = foodRecipesHasBaseFoodRecipesRonfigs;
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