package org.web.module.bone.age.domain;

import java.util.Date;

import org.service.core.entity.BaseEntity;

public class BaseMedicalImagingData extends BaseEntity {
	private static final long serialVersionUID = -5517708496588217583L;
	
	private Integer id;

    private Integer userId;

    private Integer currentAge;

    private Float height;

    private Float weight;

    private Integer sex;

    private String menarcheType;

    private Float menarcheAge;

    private String leftWristImg;

    private String leftWristNumber;

    private Date leftWristDate;

    private String pelvisImg;

    private String pelvisNumber;

    private Date pelvisDate;

    private String bilateralSternoclavicularImg;

    private String bilateralSternoclavicularNumber;

    private Date bilateralSternoclavicularDate;

    private String ctTomography;

    private String ctTomographyNumber;

    private Date ctTomographyDate;

    private String advisoryImg;

    private Integer advisoryStatus;

    private String advisoryReason;

    private String entrusImg;

    private Integer entrusImgStatus;

    private Integer judicialexpertiseStatus;

    private Integer medicalImagingStatus;

    private String medicalImagingReason;

    private Integer boneAgeStatus;

    private Integer organizationUserId;

    private Integer organizationId;

    private Integer organizationTeamId;

    private Integer judicialBoneAgeOrganizationId;

    private Integer judicialBoneAgeOrganizationTeamId;

    private String supplementaryMaterialsImg;

    private Integer supplementaryMaterialsStatus;

    private Date createTime;

    private String leftKneeJointImg;

    private String leftKneeJointImgNumber;

    private Date leftKneeJointDate;

    private String leftKneeJointLateralImg;

    private String leftKneeJointLateralNumber;
    
    private String xRay;
    private String xRayNo;

    private Date leftKneeJointLateralDate;

    private Integer updateOrganizationTeamId;

    private Integer updateOrganizationUserId;
    
    private Integer checkPoint;
    private Integer algorithm;

    
    public String getxRay() {
		return xRay;
	}

	public void setxRay(String xRay) {
		this.xRay = xRay;
	}

	public String getxRayNo() {
		return xRayNo;
	}

	public void setxRayNo(String xRayNo) {
		this.xRayNo = xRayNo;
	}

	public Integer getCheckPoint() {
		return checkPoint;
	}

	public void setCheckPoint(Integer checkPoint) {
		this.checkPoint = checkPoint;
	}

	public Integer getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Integer algorithm) {
		this.algorithm = algorithm;
	}

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

    public Integer getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(Integer currentAge) {
        this.currentAge = currentAge;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMenarcheType() {
        return menarcheType;
    }

    public void setMenarcheType(String menarcheType) {
        this.menarcheType = menarcheType;
    }

    public Float getMenarcheAge() {
        return menarcheAge;
    }

    public void setMenarcheAge(Float menarcheAge) {
        this.menarcheAge = menarcheAge;
    }

    public String getLeftWristImg() {
        return leftWristImg;
    }

    public void setLeftWristImg(String leftWristImg) {
        this.leftWristImg = leftWristImg;
    }

    public String getLeftWristNumber() {
        return leftWristNumber;
    }

    public void setLeftWristNumber(String leftWristNumber) {
        this.leftWristNumber = leftWristNumber;
    }

    public Date getLeftWristDate() {
        return leftWristDate;
    }

    public void setLeftWristDate(Date leftWristDate) {
        this.leftWristDate = leftWristDate;
    }

    public String getPelvisImg() {
        return pelvisImg;
    }

    public void setPelvisImg(String pelvisImg) {
        this.pelvisImg = pelvisImg;
    }

    public String getPelvisNumber() {
        return pelvisNumber;
    }

    public void setPelvisNumber(String pelvisNumber) {
        this.pelvisNumber = pelvisNumber;
    }

    public Date getPelvisDate() {
        return pelvisDate;
    }

    public void setPelvisDate(Date pelvisDate) {
        this.pelvisDate = pelvisDate;
    }

    public String getBilateralSternoclavicularImg() {
        return bilateralSternoclavicularImg;
    }

    public void setBilateralSternoclavicularImg(String bilateralSternoclavicularImg) {
        this.bilateralSternoclavicularImg = bilateralSternoclavicularImg;
    }

    public String getBilateralSternoclavicularNumber() {
        return bilateralSternoclavicularNumber;
    }

    public void setBilateralSternoclavicularNumber(String bilateralSternoclavicularNumber) {
        this.bilateralSternoclavicularNumber = bilateralSternoclavicularNumber;
    }

    public Date getBilateralSternoclavicularDate() {
        return bilateralSternoclavicularDate;
    }

    public void setBilateralSternoclavicularDate(Date bilateralSternoclavicularDate) {
        this.bilateralSternoclavicularDate = bilateralSternoclavicularDate;
    }

    public String getCtTomography() {
        return ctTomography;
    }

    public void setCtTomography(String ctTomography) {
        this.ctTomography = ctTomography;
    }

    public String getCtTomographyNumber() {
        return ctTomographyNumber;
    }

    public void setCtTomographyNumber(String ctTomographyNumber) {
        this.ctTomographyNumber = ctTomographyNumber;
    }

    public Date getCtTomographyDate() {
        return ctTomographyDate;
    }

    public void setCtTomographyDate(Date ctTomographyDate) {
        this.ctTomographyDate = ctTomographyDate;
    }

    public String getAdvisoryImg() {
        return advisoryImg;
    }

    public void setAdvisoryImg(String advisoryImg) {
        this.advisoryImg = advisoryImg;
    }

    public Integer getAdvisoryStatus() {
        return advisoryStatus;
    }

    public void setAdvisoryStatus(Integer advisoryStatus) {
        this.advisoryStatus = advisoryStatus;
    }

    public String getAdvisoryReason() {
        return advisoryReason;
    }

    public void setAdvisoryReason(String advisoryReason) {
        this.advisoryReason = advisoryReason;
    }

    public String getEntrusImg() {
        return entrusImg;
    }

    public void setEntrusImg(String entrusImg) {
        this.entrusImg = entrusImg;
    }

    public Integer getEntrusImgStatus() {
        return entrusImgStatus;
    }

    public void setEntrusImgStatus(Integer entrusImgStatus) {
        this.entrusImgStatus = entrusImgStatus;
    }

    public Integer getJudicialexpertiseStatus() {
        return judicialexpertiseStatus;
    }

    public void setJudicialexpertiseStatus(Integer judicialexpertiseStatus) {
        this.judicialexpertiseStatus = judicialexpertiseStatus;
    }

    public Integer getMedicalImagingStatus() {
        return medicalImagingStatus;
    }

    public void setMedicalImagingStatus(Integer medicalImagingStatus) {
        this.medicalImagingStatus = medicalImagingStatus;
    }

    public String getMedicalImagingReason() {
        return medicalImagingReason;
    }

    public void setMedicalImagingReason(String medicalImagingReason) {
        this.medicalImagingReason = medicalImagingReason;
    }

    public Integer getBoneAgeStatus() {
        return boneAgeStatus;
    }

    public void setBoneAgeStatus(Integer boneAgeStatus) {
        this.boneAgeStatus = boneAgeStatus;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getOrganizationTeamId() {
        return organizationTeamId;
    }

    public void setOrganizationTeamId(Integer organizationTeamId) {
        this.organizationTeamId = organizationTeamId;
    }

    public Integer getJudicialBoneAgeOrganizationId() {
        return judicialBoneAgeOrganizationId;
    }

    public void setJudicialBoneAgeOrganizationId(Integer judicialBoneAgeOrganizationId) {
        this.judicialBoneAgeOrganizationId = judicialBoneAgeOrganizationId;
    }

    public Integer getJudicialBoneAgeOrganizationTeamId() {
        return judicialBoneAgeOrganizationTeamId;
    }

    public void setJudicialBoneAgeOrganizationTeamId(Integer judicialBoneAgeOrganizationTeamId) {
        this.judicialBoneAgeOrganizationTeamId = judicialBoneAgeOrganizationTeamId;
    }

    public String getSupplementaryMaterialsImg() {
        return supplementaryMaterialsImg;
    }

    public void setSupplementaryMaterialsImg(String supplementaryMaterialsImg) {
        this.supplementaryMaterialsImg = supplementaryMaterialsImg;
    }

    public Integer getSupplementaryMaterialsStatus() {
        return supplementaryMaterialsStatus;
    }

    public void setSupplementaryMaterialsStatus(Integer supplementaryMaterialsStatus) {
        this.supplementaryMaterialsStatus = supplementaryMaterialsStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLeftKneeJointImg() {
        return leftKneeJointImg;
    }

    public void setLeftKneeJointImg(String leftKneeJointImg) {
        this.leftKneeJointImg = leftKneeJointImg;
    }

    public String getLeftKneeJointImgNumber() {
        return leftKneeJointImgNumber;
    }

    public void setLeftKneeJointImgNumber(String leftKneeJointImgNumber) {
        this.leftKneeJointImgNumber = leftKneeJointImgNumber;
    }

    public Date getLeftKneeJointDate() {
        return leftKneeJointDate;
    }

    public void setLeftKneeJointDate(Date leftKneeJointDate) {
        this.leftKneeJointDate = leftKneeJointDate;
    }

    public String getLeftKneeJointLateralImg() {
        return leftKneeJointLateralImg;
    }

    public void setLeftKneeJointLateralImg(String leftKneeJointLateralImg) {
        this.leftKneeJointLateralImg = leftKneeJointLateralImg;
    }

    public String getLeftKneeJointLateralNumber() {
        return leftKneeJointLateralNumber;
    }

    public void setLeftKneeJointLateralNumber(String leftKneeJointLateralNumber) {
        this.leftKneeJointLateralNumber = leftKneeJointLateralNumber;
    }

    public Date getLeftKneeJointLateralDate() {
        return leftKneeJointLateralDate;
    }

    public void setLeftKneeJointLateralDate(Date leftKneeJointLateralDate) {
        this.leftKneeJointLateralDate = leftKneeJointLateralDate;
    }

    public Integer getUpdateOrganizationTeamId() {
        return updateOrganizationTeamId;
    }

    public void setUpdateOrganizationTeamId(Integer updateOrganizationTeamId) {
        this.updateOrganizationTeamId = updateOrganizationTeamId;
    }

    public Integer getUpdateOrganizationUserId() {
        return updateOrganizationUserId;
    }

    public void setUpdateOrganizationUserId(Integer updateOrganizationUserId) {
        this.updateOrganizationUserId = updateOrganizationUserId;
    }
}