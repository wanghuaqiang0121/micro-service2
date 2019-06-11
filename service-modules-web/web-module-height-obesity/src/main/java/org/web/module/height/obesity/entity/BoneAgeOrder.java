package org.web.module.height.obesity.entity;

import java.util.Date;
import org.service.core.entity.BaseEntity;

public class BoneAgeOrder extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月27日
     * @description:
     */
    private static final long serialVersionUID = -8436037519041451112L;

    private Integer id;

    private Integer userId;

    private Integer organizationTeamId;

    private Integer organizationUserId;

    private String orderNo;

    private Integer status;

    private Integer sex;

    private Float height;

    private Float weight;

    private Integer checkPoint;

    private String xRay;

    private String xRayNo;

    private Integer algorithm;

    private Integer summaryAlgorithm;

    private String radiusLevel;

    private String ulnaLevel;

    private String metacarpal1Level;

    private String metacarpal3Level;

    private String metacarpal5Level;

    private String nearPhalanges1Level;

    private String nearPhalanges3Level;

    private String nearPhalanges5Level;

    private String inPhalanges3Level;

    private String inPhalanges5Level;

    private String farPhalanges1Level;

    private String farPhalanges3Level;

    private String farPhalanges5Level;

    private String headBoneLevel;

    private String hamateLevel;

    private String pyramidalBoneLevel;

    private String moonBoneLevel;

    private String scaphoidLevel;

    private String mostOfTheHornsLevel;

    private String smallPolyhornsLevel;

    private String menarcheType;

    private Float menarcheAge;

    private Float boneAge;

    private Float boneScore;

    private Float forecastHeight;

    private Float doctorForecastHeight;

    private Float minForecastHeight;

    private Float doctorMinForecastHeight;

    private Float maxForecastHeight;

    private Float doctorMaxForecastHeight;

    private Boolean isDisplayForecastHeight;

    private String explain;

    private String advise;

    private Date generatingReportDate;

    private Date createDate;

    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Integer getOrganizationTeamId() {
        return organizationTeamId;
    }

    public void setOrganizationTeamId(Integer organizationTeamId) {
        this.organizationTeamId = organizationTeamId;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public Integer getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(Integer checkPoint) {
        this.checkPoint = checkPoint;
    }

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

    public Integer getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Integer algorithm) {
        this.algorithm = algorithm;
    }

    public Integer getSummaryAlgorithm() {
        return summaryAlgorithm;
    }

    public void setSummaryAlgorithm(Integer summaryAlgorithm) {
        this.summaryAlgorithm = summaryAlgorithm;
    }

    public String getRadiusLevel() {
        return radiusLevel;
    }

    public void setRadiusLevel(String radiusLevel) {
        this.radiusLevel = radiusLevel;
    }

    public String getUlnaLevel() {
        return ulnaLevel;
    }

    public void setUlnaLevel(String ulnaLevel) {
        this.ulnaLevel = ulnaLevel;
    }

    public String getMetacarpal1Level() {
        return metacarpal1Level;
    }

    public void setMetacarpal1Level(String metacarpal1Level) {
        this.metacarpal1Level = metacarpal1Level;
    }

    public String getMetacarpal3Level() {
        return metacarpal3Level;
    }

    public void setMetacarpal3Level(String metacarpal3Level) {
        this.metacarpal3Level = metacarpal3Level;
    }

    public String getMetacarpal5Level() {
        return metacarpal5Level;
    }

    public void setMetacarpal5Level(String metacarpal5Level) {
        this.metacarpal5Level = metacarpal5Level;
    }

    public String getNearPhalanges1Level() {
        return nearPhalanges1Level;
    }

    public void setNearPhalanges1Level(String nearPhalanges1Level) {
        this.nearPhalanges1Level = nearPhalanges1Level;
    }

    public String getNearPhalanges3Level() {
        return nearPhalanges3Level;
    }

    public void setNearPhalanges3Level(String nearPhalanges3Level) {
        this.nearPhalanges3Level = nearPhalanges3Level;
    }

    public String getNearPhalanges5Level() {
        return nearPhalanges5Level;
    }

    public void setNearPhalanges5Level(String nearPhalanges5Level) {
        this.nearPhalanges5Level = nearPhalanges5Level;
    }

    public String getInPhalanges3Level() {
        return inPhalanges3Level;
    }

    public void setInPhalanges3Level(String inPhalanges3Level) {
        this.inPhalanges3Level = inPhalanges3Level;
    }

    public String getInPhalanges5Level() {
        return inPhalanges5Level;
    }

    public void setInPhalanges5Level(String inPhalanges5Level) {
        this.inPhalanges5Level = inPhalanges5Level;
    }

    public String getFarPhalanges1Level() {
        return farPhalanges1Level;
    }

    public void setFarPhalanges1Level(String farPhalanges1Level) {
        this.farPhalanges1Level = farPhalanges1Level;
    }

    public String getFarPhalanges3Level() {
        return farPhalanges3Level;
    }

    public void setFarPhalanges3Level(String farPhalanges3Level) {
        this.farPhalanges3Level = farPhalanges3Level;
    }

    public String getFarPhalanges5Level() {
        return farPhalanges5Level;
    }

    public void setFarPhalanges5Level(String farPhalanges5Level) {
        this.farPhalanges5Level = farPhalanges5Level;
    }

    public String getHeadBoneLevel() {
        return headBoneLevel;
    }

    public void setHeadBoneLevel(String headBoneLevel) {
        this.headBoneLevel = headBoneLevel;
    }

    public String getHamateLevel() {
        return hamateLevel;
    }

    public void setHamateLevel(String hamateLevel) {
        this.hamateLevel = hamateLevel;
    }

    public String getPyramidalBoneLevel() {
        return pyramidalBoneLevel;
    }

    public void setPyramidalBoneLevel(String pyramidalBoneLevel) {
        this.pyramidalBoneLevel = pyramidalBoneLevel;
    }

    public String getMoonBoneLevel() {
        return moonBoneLevel;
    }

    public void setMoonBoneLevel(String moonBoneLevel) {
        this.moonBoneLevel = moonBoneLevel;
    }

    public String getScaphoidLevel() {
        return scaphoidLevel;
    }

    public void setScaphoidLevel(String scaphoidLevel) {
        this.scaphoidLevel = scaphoidLevel;
    }

    public String getMostOfTheHornsLevel() {
        return mostOfTheHornsLevel;
    }

    public void setMostOfTheHornsLevel(String mostOfTheHornsLevel) {
        this.mostOfTheHornsLevel = mostOfTheHornsLevel;
    }

    public String getSmallPolyhornsLevel() {
        return smallPolyhornsLevel;
    }

    public void setSmallPolyhornsLevel(String smallPolyhornsLevel) {
        this.smallPolyhornsLevel = smallPolyhornsLevel;
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

    public Float getBoneAge() {
        return boneAge;
    }

    public void setBoneAge(Float boneAge) {
        this.boneAge = boneAge;
    }

    public Float getBoneScore() {
        return boneScore;
    }

    public void setBoneScore(Float boneScore) {
        this.boneScore = boneScore;
    }

    public Float getForecastHeight() {
        return forecastHeight;
    }

    public void setForecastHeight(Float forecastHeight) {
        this.forecastHeight = forecastHeight;
    }

    public Float getDoctorForecastHeight() {
        return doctorForecastHeight;
    }

    public void setDoctorForecastHeight(Float doctorForecastHeight) {
        this.doctorForecastHeight = doctorForecastHeight;
    }

    public Float getMinForecastHeight() {
        return minForecastHeight;
    }

    public void setMinForecastHeight(Float minForecastHeight) {
        this.minForecastHeight = minForecastHeight;
    }

    public Float getDoctorMinForecastHeight() {
        return doctorMinForecastHeight;
    }

    public void setDoctorMinForecastHeight(Float doctorMinForecastHeight) {
        this.doctorMinForecastHeight = doctorMinForecastHeight;
    }

    public Float getMaxForecastHeight() {
        return maxForecastHeight;
    }

    public void setMaxForecastHeight(Float maxForecastHeight) {
        this.maxForecastHeight = maxForecastHeight;
    }

    public Float getDoctorMaxForecastHeight() {
        return doctorMaxForecastHeight;
    }

    public void setDoctorMaxForecastHeight(Float doctorMaxForecastHeight) {
        this.doctorMaxForecastHeight = doctorMaxForecastHeight;
    }

    public Boolean getIsDisplayForecastHeight() {
        return isDisplayForecastHeight;
    }

    public void setIsDisplayForecastHeight(Boolean isDisplayForecastHeight) {
        this.isDisplayForecastHeight = isDisplayForecastHeight;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public Date getGeneratingReportDate() {
        return generatingReportDate;
    }

    public void setGeneratingReportDate(Date generatingReportDate) {
        this.generatingReportDate = generatingReportDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}