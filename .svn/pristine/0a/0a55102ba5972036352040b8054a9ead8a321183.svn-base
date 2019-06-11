package org.web.module.organization.domain.service.packages;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月10日
 * @description: 服务包使用人群关联表
 */
public class OrganizationPackageUserType extends BaseEntity {
	
	
	
	private static final long serialVersionUID = -6785474753575400518L;

	/**
	 * @author <font color="red"><b>Wang.HuaQiang</b></font>
	 * @Date 2018年3月20日
	 * @Version OrganizationPackageUserType
	 * @Description 批量添加校验
	 */
	public interface BatchInsert{};
	
    private Integer id;

    @NotNull(message = "{organization.package.user.type.organization.service.package.id.is.not.null}" , groups = {Insert.class })
    private Integer organizationServicePackageId;

    @NotNull(message = "{organization.package.user.type.user.type.id.is.not.null}" , groups = {Insert.class })
    private Integer userTypeId;
    
    @NotEmpty(message = "{organization.package.user.type.organization.package.user.types.is.not.null}" , groups = {BatchInsert.class })
    private List<OrganizationPackageUserType> organizationPackageUserTypes;
    
    public List<OrganizationPackageUserType> getOrganizationPackageUserTypes() {
		return organizationPackageUserTypes;
	}

	public void setOrganizationPackageUserTypes(List<OrganizationPackageUserType> organizationPackageUserTypes) {
		this.organizationPackageUserTypes = organizationPackageUserTypes;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganizationServicePackageId() {
        return organizationServicePackageId;
    }

    public void setOrganizationServicePackageId(Integer organizationServicePackageId) {
        this.organizationServicePackageId = organizationServicePackageId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }
}