package org.web.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月11日
 * @description: 行为发育配置表
 */
public class BehavioralDevelopmentConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotBlank(message = "{behavioral.development.config.name.notblank.valid}", groups = { Insert.class })
	private String name;
	@NotNull(message = "{behavioral.development.config.start.notnull.valid}", groups = { Insert.class })
	private Integer monthAgeStart;
	@NotNull(message = "{behavioral.development.config.end.notnull.valid}", groups = { Insert.class })
	private Integer monthAgeEnd;
	//@NotBlank(message = "{behavioral.development.config.picture.notblank.valid}", groups = { Insert.class })
	private String picture;

	private String remark;

	private Date createDateTime;
	private Integer monthAge;
	private Integer childrenMeasureId;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
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