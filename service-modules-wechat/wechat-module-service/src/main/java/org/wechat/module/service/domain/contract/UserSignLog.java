package org.wechat.module.service.domain.contract;

import java.util.Date;

import org.service.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年9月30日
 * @description:用户签约日志
 */
public class UserSignLog extends BaseEntity {

	private static final long serialVersionUID = 5103166748964521374L;

	private Integer id;

	private Integer type;

	private Integer userSignId;

	private Integer organizationUserId;

	private Integer status;

	private String explain;

	private String record;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserSignId() {
		return userSignId;
	}

	public void setUserSignId(Integer userSignId) {
		this.userSignId = userSignId;
	}

	public Integer getOrganizationUserId() {
		return organizationUserId;
	}

	public void setOrganizationUserId(Integer organizationUserId) {
		this.organizationUserId = organizationUserId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}