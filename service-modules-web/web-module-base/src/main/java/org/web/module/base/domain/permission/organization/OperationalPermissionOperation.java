package org.web.module.base.domain.permission.organization;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 权限操作关联表
 */
public class OperationalPermissionOperation extends BaseEntity {
	
	private static final long serialVersionUID = 1126880525104741473L;

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @description: 查询该权限是否拥有的操作
	 */
	public interface GetPermissionOperationIsChoose{};
	private Integer id;
	@NotNull(message = "{operational.permission.operation.operational.permission.id.notnull.valid}", groups = { Insert.class,GetPermissionOperationIsChoose.class})
	private Integer operationalPermissionId;
	@NotNull(message = "{operational.permission.operational.operational.operation.id.notnull.valid}", groups = { Insert.class})
	private Integer operationalOperationId;
	
	private String code;
	private String name;
	
	
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
	
	public Integer getOperationalPermissionId() {
		return operationalPermissionId;
	}
	public void setOperationalPermissionId(Integer operationalPermissionId) {
		this.operationalPermissionId = operationalPermissionId;
	}
		
	public Integer getOperationalOperationId() {
		return operationalOperationId;
	}
	public void setOperationalOperationId(Integer operationalOperationId) {
		this.operationalOperationId = operationalOperationId;
	}
	@Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class,GetPermissionOperationIsChoose.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,GetPermissionOperationIsChoose.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}
	
	

}
