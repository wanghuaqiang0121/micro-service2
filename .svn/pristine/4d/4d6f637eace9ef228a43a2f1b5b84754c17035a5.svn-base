package org.web.module.base.domain.permission.team;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 角色权限
 */
public class OrganizationTeamRolePermission extends BaseEntity {
	
	private static final long serialVersionUID = 2082271226813540577L;

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @description: 团队角色（拥有未拥有）的权限
	 */
	public interface HaveAndNotHavePermission {

	}

	private Integer id;
	@NotNull(message = "{organizationTeamRolePermission.organizationTeamRoleId.is.not.null}", groups = { Insert.class })
	private Integer organizationTeamRoleId;
	@NotNull(message = "{organizationTeamRolePermission.organizationTeamPermissionId.is.not.null}", groups = {
			Insert.class })
	private Integer organizationTeamPermissionId;
	private String name;
	private String code;

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

	public Integer getOrganizationTeamRoleId() {
		return organizationTeamRoleId;
	}

	public void setOrganizationTeamRoleId(Integer organizationTeamRoleId) {
		this.organizationTeamRoleId = organizationTeamRoleId;
	}

	public Integer getOrganizationTeamPermissionId() {
		return organizationTeamPermissionId;
	}

	public void setOrganizationTeamPermissionId(Integer organizationTeamPermissionId) {
		this.organizationTeamPermissionId = organizationTeamPermissionId;
	}
	 @Override
	   	@NotNull(message = "{page.empty}", groups = { SelectAll.class,HaveAndNotHavePermission.class})
	   	public Integer getPage() {
	   		return super.getPage();
	   	}

	   	@Override
	   	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,HaveAndNotHavePermission.class })
	   	public Integer getPageSize() {
	   		return super.getPageSize();
	   	}
}