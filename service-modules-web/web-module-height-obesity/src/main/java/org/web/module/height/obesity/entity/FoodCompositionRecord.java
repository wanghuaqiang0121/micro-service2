package org.web.module.height.obesity.entity;

import org.service.core.entity.BaseEntity;

public class FoodCompositionRecord extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description:
     */
    private static final long serialVersionUID = -8350666697363566181L;

    private Integer id;

    private Integer foodId;

    private String codeType;

    private String codeName;

    private String codeUnit;

    private Float value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
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

    public String getCodeUnit() {
        return codeUnit;
    }

    public void setCodeUnit(String codeUnit) {
        this.codeUnit = codeUnit;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}