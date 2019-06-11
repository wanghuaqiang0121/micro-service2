package org.service.task.delay.entity.user;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月17日
 * @description: 微信消息模板
 */
public class UserRelation extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotNull(message = "{user.relation.user.id.not.null}", groups = { Insert.class })
	private Integer userId;
	@NotNull(message = "{user.relation.team.id.not.null}", groups = { Insert.class })
	private Integer organizationTeamId;
	private Boolean isSign;
	private Boolean isIncrementService;
	private Boolean isSingleService;
	private Integer time;

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

	public Integer getTime() {
		if (time > 0) {
			return time * 1000;
		}
		return 1;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}
