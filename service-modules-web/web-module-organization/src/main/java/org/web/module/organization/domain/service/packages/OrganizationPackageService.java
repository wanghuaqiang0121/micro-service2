package org.web.module.organization.domain.service.packages;

import java.math.BigDecimal;
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
 * @description: 机构服务包和服务关联表
 */
public class OrganizationPackageService extends BaseEntity {
	
	
	
	private static final long serialVersionUID = 8563640459284134824L;

	/**
	 * @author <font color="red"><b>Wang.HuaQiang</b></font>
	 * @Date 2018年3月20日
	 * @Version OrganizationPackageService
	 * @Description 批量添加验证类
	 */
	public interface BatchInsert{};
	
    private Integer id;
    
    @NotNull(message = "{organization.package.service.organization.service.package.id.is.not.null}" , groups = {Insert.class,SelectAll.class })
    private Integer organizationServicePackageId;

    @NotNull(message = "{organization.package.service.service.type.id.is.not.null}" , groups = {Insert.class })
    private Integer serviceTypeId;

    private BigDecimal price;

    @NotNull(message = "{organization.package.service.service.times.id.is.not.null}" , groups = {Insert.class })
    private Integer times;
    
    @NotEmpty(message = "{organization.package.service.organization.package.services.is.not.null}" , groups = {BatchInsert.class })
    private List<OrganizationPackageService> organizationPackageServices;
    
    private Integer organizationId;
    
    public List<OrganizationPackageService> getOrganizationPackageServices() {
		return organizationPackageServices;
	}

	public void setOrganizationPackageServices(List<OrganizationPackageService> organizationPackageServices) {
		this.organizationPackageServices = organizationPackageServices;
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

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
    
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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