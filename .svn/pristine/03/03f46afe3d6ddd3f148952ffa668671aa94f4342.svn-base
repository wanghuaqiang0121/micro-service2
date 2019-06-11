package org.web.module.height.obesity.entity;

import java.util.Date;

import org.service.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserExaminationRecord extends BaseEntity {

    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description: 用户检查检验
     */
    private static final long serialVersionUID = 640769480731957874L;

    private Integer id;

    private Integer type;

    private Integer userId;

    private Integer childrenMeasureId;

    private String examinationCode;

    private String examinationName;

    private String examinationType;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
 	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date examinationDate;

    private String picture;

    private String unit;

    private String examinationResult;

    private String diagnosticOpinion;

    private Byte isView;

    private Integer organizationUserId;

    private Date createDateTime;

    private Integer examinationCodeTableId;
    private Integer userExaminationMasterRecordId;

    public Integer getUserExaminationMasterRecordId() {
        return userExaminationMasterRecordId;
    }

    public void setUserExaminationMasterRecordId(Integer userExaminationMasterRecordId) {
        this.userExaminationMasterRecordId = userExaminationMasterRecordId;
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

    public String getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(String examinationType) {
        this.examinationType = examinationType;
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

    public Integer getExaminationCodeTableId() {
        return examinationCodeTableId;
    }

    public void setExaminationCodeTableId(Integer baseExaminationCodeTableId) {
        this.examinationCodeTableId = baseExaminationCodeTableId;
    }
}