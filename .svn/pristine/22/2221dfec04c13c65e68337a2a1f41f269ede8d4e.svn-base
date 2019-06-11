package org.web.module.base.domain.permission.team;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 权限操作
 */
public class OrganizationTeamPermissionOperation extends BaseEntity {
	
	private static final long serialVersionUID = -8013597335992514351L;

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @description: 团队权限（拥有未拥有）的操作 验证
	 */
	public interface HaveAndNotHaveOperation{};
	
    private Integer id;
    @NotNull(message="{organizationTeamPermissionOperation.organizationTeamPermissionId.is.not.null}",groups={Insert.class})
    private Integer organizationTeamPermissionId;
    @NotNull(message="{organizationTeamPermissionOperation.organizationTeamOperationId.is.not.null}",groups={Insert.class})
    private Integer organizationTeamOperationId;
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

    public Integer getOrganizationTeamPermissionId() {
        return organizationTeamPermissionId;
    }

    public void setOrganizationTeamPermissionId(Integer organizationTeamPermissionId) {
        this.organizationTeamPermissionId = organizationTeamPermissionId;
    }

    public Integer getOrganizationTeamOperationId() {
        return organizationTeamOperationId;
    }

    public void setOrganizationTeamOperationId(Integer organizationTeamOperationId) {
        this.organizationTeamOperationId = organizationTeamOperationId;
    }
    @Override
   	@NotNull(message = "{page.empty}", groups = { SelectAll.class,HaveAndNotHaveOperation.class})
   	public Integer getPage() {
   		return super.getPage();
   	}

   	@Override
   	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,HaveAndNotHaveOperation.class })
   	public Integer getPageSize() {
   		return super.getPageSize();
   	}
}