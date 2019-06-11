package org.web.module.team.domain;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 机构团队角色关联表
 */
public class OrganizationOrganizationTeamRole extends BaseEntity {
  
	private static final long serialVersionUID = 6105819839988998777L;

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @description: 查询机构用户团队操作列表
	 */
    public interface TeamOperations {

	}

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @description: 查询机构用户团队菜单列表
	 */
    public interface TeamMenus {

	}

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @description: 查询机构用户团队权限列表
	 */
    public interface TeamPermissions {

	}

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @description:  查询机构用户团队角色列表
	 */
    public interface TeamRoles {

	}

	private Integer id;

    private Integer organizationUserId;

    private Integer organizationTeamId;

    private Integer organizationTeamRoleId;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public Integer getOrganizationTeamId() {
        return organizationTeamId;
    }

    public void setOrganizationTeamId(Integer organizationTeamId) {
        this.organizationTeamId = organizationTeamId;
    }

    public Integer getOrganizationTeamRoleId() {
        return organizationTeamRoleId;
    }

    public void setOrganizationTeamRoleId(Integer organizationTeamRoleId) {
        this.organizationTeamRoleId = organizationTeamRoleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Override
   	@NotNull(message = "{page.empty}", groups = { SelectAll.class,TeamRoles.class ,TeamPermissions.class,TeamMenus.class,TeamOperations.class})
   	public Integer getPage() {
   		return super.getPage();
   	}

   	@Override
   	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,TeamRoles.class,TeamPermissions.class,TeamMenus.class,TeamOperations.class})
   	public Integer getPageSize() {
   		return super.getPageSize();
   	}
}