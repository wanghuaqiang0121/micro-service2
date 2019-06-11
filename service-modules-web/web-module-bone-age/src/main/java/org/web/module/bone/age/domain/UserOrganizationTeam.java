package org.web.module.bone.age.domain;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2019 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2019年4月8日
 * @description: 用户团队关系关联表
 */
public class UserOrganizationTeam extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer userId;

    private Integer organizationTeamId;

    private Boolean isSign;

    private Boolean isIncrementService;

    private Boolean isSingleService;

    private Boolean isHeightObesity;

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

    public Integer getOrganizationTeamId() {
        return organizationTeamId;
    }

    public void setOrganizationTeamId(Integer organizationTeamId) {
        this.organizationTeamId = organizationTeamId;
    }

    public Boolean getIsSign() {
        return isSign;
    }

    public void setIsSign(Boolean isSign) {
        this.isSign = isSign;
    }

    public Boolean getIsIncrementService() {
        return isIncrementService;
    }

    public void setIsIncrementService(Boolean isIncrementService) {
        this.isIncrementService = isIncrementService;
    }

    public Boolean getIsSingleService() {
        return isSingleService;
    }

    public void setIsSingleService(Boolean isSingleService) {
        this.isSingleService = isSingleService;
    }

    public Boolean getIsHeightObesity() {
        return isHeightObesity;
    }

    public void setIsHeightObesity(Boolean isHeightObesity) {
        this.isHeightObesity = isHeightObesity;
    }
}