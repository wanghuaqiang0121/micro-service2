package org.web.module.base.domain.permission.organization;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 权限菜单关联表
 */
public class OperationalPermissionMenu extends BaseEntity {
	
	private static final long serialVersionUID = 7798452263370536342L;

	public interface GetPermissionMenuIsChoose{};
	private Integer id;
	@NotNull(message = "{operational.permission.menu.operational.permission.id.notnull.valid}", groups = { Insert.class,GetPermissionMenuIsChoose.class})
	private Integer operationalPermissionId;
	@NotNull(message = "{operational.permission.menu.operational.menu.id.notnull.valid}", groups = { Insert.class})
	private Integer operationalMenuId;
	
	private String code;
	private String name;
	
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOperationalPermissionId() {
		return operationalPermissionId;
	}
	public void setOperationalPermissionId(Integer operationalPermissionId) {
		this.operationalPermissionId = operationalPermissionId;
	}
	
	
	public Integer getOperationalMenuId() {
		return operationalMenuId;
	}
	public void setOperationalMenuId(Integer operationalMenuId) {
		this.operationalMenuId = operationalMenuId;
	}
	@Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class,GetPermissionMenuIsChoose.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,GetPermissionMenuIsChoose.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}
	
	

}
