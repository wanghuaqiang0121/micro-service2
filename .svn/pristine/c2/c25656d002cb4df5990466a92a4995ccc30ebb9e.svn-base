package org.web.module.bone.age.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2019 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2019年3月4日
 * @description: 远程骨龄疑问表
 */
public class RemoteQuestion extends BaseEntity {
   
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer launchOrganizationUserId;

    private String launchOrganizationUserName;

    private String content;
   // @NotNull(message = "{remote.question.type.notnull.valid}", groups = { Insert.class })
    private Integer type;

    private Date createTime;
   // @NotNull(message = "{remote.question.remote.bone.age.order.id.notnull.valid}", groups = { Insert.class })
    private Integer baseRemoteBoneAgeOrderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLaunchOrganizationUserId() {
        return launchOrganizationUserId;
    }

    public void setLaunchOrganizationUserId(Integer launchOrganizationUserId) {
        this.launchOrganizationUserId = launchOrganizationUserId;
    }

    public String getLaunchOrganizationUserName() {
        return launchOrganizationUserName;
    }

    public void setLaunchOrganizationUserName(String launchOrganizationUserName) {
        this.launchOrganizationUserName = launchOrganizationUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBaseRemoteBoneAgeOrderId() {
        return baseRemoteBoneAgeOrderId;
    }

    public void setBaseRemoteBoneAgeOrderId(Integer baseRemoteBoneAgeOrderId) {
        this.baseRemoteBoneAgeOrderId = baseRemoteBoneAgeOrderId;
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