package org.wechat.module.height.obesity.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月12日
 * @description: 用户检查检验记录表
 */
public class UserExaminationRecord extends BaseEntity {
	
    
	private static final long serialVersionUID = 640769480731957874L;

	private Integer id;

   
    private Integer userId;

    private Integer userExaminationMasterRecordId;
    
    private String examinationCode;
    
    private String examinationName;
	@NotNull(message = "{user.examination.record.date.notnull.valid}", groups = { 	Insert.class})
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date examinationDate;
    @NotBlank(message = "{user.examination.record.picture.notblank.valid}", groups = { 	Insert.class})
    private String picture;

    private String unit;

    private String examinationResult;

    private String diagnosticOpinion;

    private Byte isView;

    private Integer organizationUserId;

    private Date createDateTime;

   @NotNull(message = "{user.xamination.ecord.examination.code.notnull.valid}", groups = {Insert.class })
    private List<UserExaminationRecord> userExaminationRecords;

	@NotBlank(message = "{user.examination.record.name.notblank.valid}", groups = { 	Insert.class})
   private String name;
	@NotBlank(message = "{user.examination.record.code.notblank.valid}", groups = { 	Insert.class})
   private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

 

    public Integer getUserExaminationMasterRecordId() {
		return userExaminationMasterRecordId;
	}

	public void setUserExaminationMasterRecordId(Integer userExaminationMasterRecordId) {
		this.userExaminationMasterRecordId = userExaminationMasterRecordId;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getExaminationResult() {
        return examinationResult;
    }

    public void setExaminationResult(String examinationResult) {
        this.examinationResult = examinationResult;
    }

    public String getDiagnosticOpinion() {
        return diagnosticOpinion;
    }

    public void setDiagnosticOpinion(String diagnosticOpinion) {
        this.diagnosticOpinion = diagnosticOpinion;
    }

    public Byte getIsView() {
        return isView;
    }

    public void setIsView(Byte isView) {
        this.isView = isView;
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

	public List<UserExaminationRecord> getUserExaminationRecords() {
		return userExaminationRecords;
	}

	public void setUserExaminationRecords(List<UserExaminationRecord> userExaminationRecords) {
		this.userExaminationRecords = userExaminationRecords;
	}

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