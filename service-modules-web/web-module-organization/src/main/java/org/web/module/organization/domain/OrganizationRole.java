package org.web.module.organization.domain;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 机构角色
 */
public class OrganizationRole extends BaseEntity {
   
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @description: 批量新增
	 */
    public interface batchInsert {

	}

	private Integer id;

    @NotNull(message = "{organization.id.not.null.valid}", groups = { Insert.class, SelectAll.class,batchInsert.class})
    private Integer organizationId;

    @NotNull(message = "{organization.module.id.not.null.valid}", groups = { Insert.class, SelectAll.class,batchInsert.class})
    private Integer systemModuleId;
    
    @NotNull(message = "{organization.operational.role.id.not.null.valid}", groups = { Insert.class})
    private Integer operationalRoleId;
    
    //@NotEmpty(message = "{organization.operational.role.id.not.null.valid}", groups = { batchInsert.class})
    private List<Integer> operationalRoles;
    
    
    public List<Integer> getOperationalRoles() {
		return operationalRoles;
	}

	public void setOperationalRoles(List<Integer> operationalRoles) {
		this.operationalRoles = operationalRoles;
	}

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

	public Integer getOperationalRoleId() {
		return operationalRoleId;
	}

	public void setOperationalRoleId(Integer operationalRoleId) {
		this.operationalRoleId = operationalRoleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}