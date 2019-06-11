package org.wechat.module.user.domain.itv;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;
/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description:  机构用户、机构、部门关联表
 */
public class DoctorOrganizationDepartmentDuty extends BaseEntity {
	
	
	private static final long serialVersionUID = 1555682086144892893L;
	private Integer id;
	private Integer departmentId;	
	@NotNull(message="{doctor.organization.department.duty.organizationId.is.not.null}",groups={SelectAll.class})
	private Integer organizationId;
	private Integer organizationUserId;
	private String post;
	private String remark;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getOrganizationUserId() {
		return organizationUserId;
	}

	public void setOrganizationUserId(Integer organizationUserId) {
		this.organizationUserId = organizationUserId;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
