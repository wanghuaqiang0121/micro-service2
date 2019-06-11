package org.web.module.base.domain.permission.organization;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 模块角色关联表
 */
public class ModuleOperationalRole extends BaseEntity {

	private static final long serialVersionUID = 4407033056384926732L;

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @description: 查询该模块是否拥有的角色
	 */
	public interface GetModuleRoleIsChoose {
	}

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月26日
	 * @description:  查询机构在该模块下拥有和未拥有的权限列表
	 */
	public interface ModuleOperationalRoleList {
	}

	private Integer id;
	// @Length(min = 0, max = 32, message = "{module.operational.role.name.length}",
	// groups = { Insert.class})
	// @NotBlank(message = "{module.operational.role.name.notblank.valid}", groups =
	// { Insert.class})
	private String name;
	@NotNull(message = "{module.operational.role.system.module.id.notnull.valid}", groups = { Insert.class,
			GetModuleRoleIsChoose.class, ModuleOperationalRoleList.class })
	private Integer systemModuleId;
	@NotNull(message = "{module.operational.role.system.operational.role.id.notnull.valid}", groups = { Insert.class })
	private Integer operationalRoleId;

	private String code;

	private Integer organizationId;
	private String roleName;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSystemModuleId() {
		return systemModuleId;
	}

	public void setSystemModuleId(Integer systemModuleId) {
		this.systemModuleId = systemModuleId;
	}

	public Integer getOperationalRoleId() {
		return operationalRoleId;
	}

	public void setOperationalRoleId(Integer operationalRoleId) {
		this.operationalRoleId = operationalRoleId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class, GetModuleRoleIsChoose.class,
			ModuleOperationalRoleList.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class, GetModuleRoleIsChoose.class,
			ModuleOperationalRoleList.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}

}
