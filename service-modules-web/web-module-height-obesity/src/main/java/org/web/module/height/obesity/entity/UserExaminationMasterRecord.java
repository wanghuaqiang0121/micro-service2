package org.web.module.height.obesity.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月14日
 * @description: 检查检验主表
 */
public class UserExaminationMasterRecord extends BaseEntity  {

	private static final long serialVersionUID = 6957217737011217143L;

	private Integer id;

    private Integer type;

    private Integer userId;

    @NotNull(message = "{children.measure.id.is.not.null}", groups = {Insert.class})
    private Integer childrenMeasureId;
    
    private String examinationCode;
    
    private String examinationName;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date examinationDate;
    
    private Integer organizationUserId;

    private Date createDateTime;
    
    private String codeType;
    
    private List<UserExaminationRecord> userExaminationRecords;
    
	public List<UserExaminationRecord> getUserExaminationRecords() {
		return userExaminationRecords;
	}

	public void setUserExaminationRecords(List<UserExaminationRecord> userExaminationRecords) {
		this.userExaminationRecords = userExaminationRecords;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getChildrenMeasureId() {
		return childrenMeasureId;
	}

	public void setChildrenMeasureId(Integer childrenMeasureId) {
		this.childrenMeasureId = childrenMeasureId;
	}

	public String getExaminationCode() {
		return examinationCode;
	}

	public void setExaminationCode(String examinationCode) {
		this.examinationCode = examinationCode;
	}

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public Date getExaminationDate() {
		return examinationDate;
	}

	public void setExaminationDate(Date examinationDate) {
		this.examinationDate = examinationDate;
	}

	public Integer getOrganizationUserId() {
		return organizationUserId;
	}

	public void setOrganizationUserId(Integer organizationUserId) {
		this.organizationUserId = organizationUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	@Override
   	@NotNull(message = "{page.empty}", groups = { SelectAll.class})
   	public Integer getPage() {
   		return super.getPage();
   	}

   	@Override
   	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class })
   	public Integer getPageSize() {
   		return super.getPageSize();
   	} 
}
