package org.web.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2019 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2019年1月8日
 * @description: 肥胖专案管理记录
 */
public class UserObesityManagerRecord extends BaseEntity {

    private static final long serialVersionUID = -3249698929540549745L;

    private Integer id;

    @NotNull(message = "{children.user.id.notblank.valid}", groups = { Insert.class })
    private Integer userId;

    @NotNull(message = "{children.user.childrenmeasureid.notblank.valid}", groups = { Insert.class })
    private Integer childrenMeasureId;

    private Integer sex;

    private Integer monthAge;

    private Float height;

    private Float weight;

    private Float bmi;

    private Float bmiStandard;

    private Float overproofBmi;

    private Date endTime;

    private Integer organizationUserId;
    
    private Integer organizationTeamId;

    private String remark;

    private Date crateDateTime;

    /* 增加查询字段 */
    private String name;
    private String phone;
    private String idCard;

    public Integer getOrganizationTeamId() {
		return organizationTeamId;
	}

	public void setOrganizationTeamId(Integer organizationTeamId) {
		this.organizationTeamId = organizationTeamId;
	}

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getMonthAge() {
        return monthAge;
    }

    public void setMonthAge(Integer monthAge) {
        this.monthAge = monthAge;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    public Float getBmiStandard() {
        return bmiStandard;
    }

    public void setBmiStandard(Float bmiStandard) {
        this.bmiStandard = bmiStandard;
    }

    public Float getOverproofBmi() {
        return overproofBmi;
    }

    public void setOverproofBmi(Float overproofBmi) {
        this.overproofBmi = overproofBmi;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCrateDateTime() {
        return crateDateTime;
    }

    public void setCrateDateTime(Date crateDateTime) {
        this.crateDateTime = crateDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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