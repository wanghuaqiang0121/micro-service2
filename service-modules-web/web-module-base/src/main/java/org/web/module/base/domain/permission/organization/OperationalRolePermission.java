package org.web.module.base.domain.permission.organization;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 角色权限关联表
 */
public class OperationalRolePermission extends BaseEntity {
	
	private static final long serialVersionUID = 3368490452404124600L;

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @description: 查询该角色是否拥有的权限
	 */
	public interface GetPermissionOperationIsChoose{};
	private Integer id;
	@NotNull(message = "{operational.role.permission.operational.permission.id.notnull.valid}", groups = { Insert.class})
	private Integer operationalPermissionId;
	@NotNull(message = "{operational.role.permission.operational.role.id.notnull.valid}", groups = { Insert.class,GetPermissionOperationIsChoose.class})
	private Integer operationalRoleId;
	
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
	
	
	public Integer getOperationalRoleId() {
		return operationalRoleId;
	}
	public void setOperationalRoleId(Integer operationalRoleId) {
		this.operationalRoleId = operationalRoleId;
	}
	@Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class,GetPermissionOperationIsChoose.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,GetPermissionOperationIsChoose.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}
	
	

}
