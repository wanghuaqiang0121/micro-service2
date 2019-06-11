package org.web.module.height.obesity.entity;

import org.service.core.entity.BaseEntity;

public class ChildrenHighRiskFactor extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月21日
     * @description:
     */
    private static final long serialVersionUID = -7471641844400799681L;

    private Integer id;

    private String code;

    private String name;

    private Float conditionValue;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Float getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(Float conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}