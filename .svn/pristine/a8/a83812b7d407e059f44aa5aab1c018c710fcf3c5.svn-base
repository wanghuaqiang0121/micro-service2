package org.web.module.organization.domain;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 机构模块
 */
public class OrganizationModule extends BaseEntity{
	
	private static final long serialVersionUID = 8865860372924565500L;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 查询机构关联和未关联的模块列表
 */
	public interface getOrganizationModuleIsChoose{};
	
	private Integer id;
	@NotNull(message = "{organization.module.organization.id.not.null.valid}", groups = { Insert.class,getOrganizationModuleIsChoose.class})
	private Integer organizationId;
	@NotNull(message = "{organization.module.system.module.id.not.null.valid}", groups = { Insert.class})
	private Integer systemModuleId;
	
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getSystemModuleId() {
		return systemModuleId;
	}
	public void setSystemModuleId(Integer systemModuleId) {
		this.systemModuleId = systemModuleId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	@Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class,getOrganizationModuleIsChoose.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,getOrganizationModuleIsChoose.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}

}
