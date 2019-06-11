package org.web.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

public class UserBehavioralDevelopmentRecord extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description:
     */
    private static final long serialVersionUID = 6820429801737222127L;

    private Integer id;

    private Integer userId;

    private Integer monthAge;

    private Integer childrenMeasureId;
    @NotNull(message = "{behavioral.development.config.id.is.not.null}", groups = { Update.class })
    private Integer behavioralDevelopmentConfigId;

    private Integer organizationUserId;

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

    public Integer getMonthAge() {
        return monthAge;
    }

    public void setMonthAge(Integer monthAge) {
        this.monthAge = monthAge;
    }

    public Integer getChildrenMeasureId() {
        return childrenMeasureId;
    }

    public void setChildrenMeasureId(Integer childrenMeasureId) {
        this.childrenMeasureId = childrenMeasureId;
    }

    public Integer getBehavioralDevelopmentConfigId() {
        return behavioralDevelopmentConfigId;
    }

    public void setBehavioralDevelopmentConfigId(Integer behavioralDevelopmentConfigId) {
        this.behavioralDevelopmentConfigId = behavioralDevelopmentConfigId;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}