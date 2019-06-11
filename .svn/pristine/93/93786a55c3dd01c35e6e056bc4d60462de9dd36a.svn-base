package org.web.module.base.domain.permission.team;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 权限菜单
 */
public class OrganizationTeamPermissionMenu extends BaseEntity {
	
	
	private static final long serialVersionUID = 8168825325393949909L;

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @description:  团队权限（拥有未拥有）的菜单 验证
	 */
	public interface HaveAndNotHaveMenu{};
	
    private Integer id;
    
    @NotNull(message="{organizationTeamPermissionMenu.organizationTeamPermissionId.is.not.null}",groups={Insert.class})
    private Integer organizationTeamPermissionId;
    
    @NotNull(message="{organizationTeamPermissionMenu.organizationTeamMenuId.is.not.null}",groups={Insert.class})
    private Integer organizationTeamMenuId;
    
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

    public Integer getOrganizationTeamMenuId() {
        return organizationTeamMenuId;
    }

    public void setOrganizationTeamMenuId(Integer organizationTeamMenuId) {
        this.organizationTeamMenuId = organizationTeamMenuId;
    }
    @Override
   	@NotNull(message = "{page.empty}", groups = { SelectAll.class,HaveAndNotHaveMenu.class})
   	public Integer getPage() {
   		return super.getPage();
   	}

   	@Override
   	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,HaveAndNotHaveMenu.class })
   	public Integer getPageSize() {
   		return super.getPageSize();
   	}
}