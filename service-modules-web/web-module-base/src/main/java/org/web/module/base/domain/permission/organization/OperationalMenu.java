package org.web.module.base.domain.permission.organization;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 机构菜单表
 */
public class OperationalMenu extends BaseEntity {
	
	private static final long serialVersionUID = -611888835573589117L;
	private Integer id;
	private Integer pid;
	@Length(min = 0, max = 32, message = "{operation.menu.name.length}", groups = { Insert.class })
	@NotBlank(message = "{operation.menu.name.notblank.valid}", groups = { Insert.class })
	private String name;
	@Length(min = 0, max = 128, message = "{operation.menu.code.length}", groups = { Insert.class })
	@NotBlank(message = "{operation.menu.code.notblank.valid}", groups = { Insert.class })
	private String code;
	@NotNull(message = "operation.menu.isUsed.notnull.valid}", groups = { Insert.class })
	private Boolean isUsed;
	private String icon;
	private Date createDate;
	private String remark;
	private Integer type;
	private Integer sort;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
