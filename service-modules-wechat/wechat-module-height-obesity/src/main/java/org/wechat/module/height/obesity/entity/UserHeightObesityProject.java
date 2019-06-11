package org.wechat.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

public class UserHeightObesityProject extends BaseEntity {

    private static final long serialVersionUID = -5192775650282694029L;

    private Integer id;

    @NotNull(message = "{children.user.id.notblank.valid}", groups = { Insert.class })
    private Integer userId;

    @NotNull(message = "{children.user.childrenmeasureid.notblank.valid}", groups = { Insert.class })
    private Integer childrenMeasureId;

    private Integer organizationUserId;

    private String sleepContent;

    private String nutritionMethodContent;
    private String nutritionDetails;
    private String nutritionRemark;

    private Integer eerId;

    private String emotionContent;

    private String motionName;

    private String motionRemark;

    private String motionPicture;

    private String mattersNeedingAttention;

    private Date createDateTime;

    public Integer getId() {
        return id;
    }

    /**
     * @return the nutritionDetails
     */
    public String getNutritionDetails() {
        return nutritionDetails;
    }

    /**
     * @param nutritionDetails the nutritionDetails to set
     */
    public void setNutritionDetails(String nutritionDetails) {
        this.nutritionDetails = nutritionDetails;
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

    public Integer getChildrenMeasureId() {
        return childrenMeasureId;
    }

    public void setChildrenMeasureId(Integer childrenMeasureId) {
        this.childrenMeasureId = childrenMeasureId;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public String getSleepContent() {
        return sleepContent;
    }

    public void setSleepContent(String sleepContent) {
        this.sleepContent = sleepContent;
    }

    public String getNutritionMethodContent() {
        return nutritionMethodContent;
    }

    public void setNutritionMethodContent(String nutritionMethodContent) {
        this.nutritionMethodContent = nutritionMethodContent;
    }

    public String getNutritionRemark() {
        return nutritionRemark;
    }

    public void setNutritionRemark(String nutritionRemark) {
        this.nutritionRemark = nutritionRemark;
    }

    public Integer getEerId() {
        return eerId;
    }

    public void setEerId(Integer eerId) {
        this.eerId = eerId;
    }

    public String getEmotionContent() {
        return emotionContent;
    }

    public void setEmotionContent(String emotionContent) {
        this.emotionContent = emotionContent;
    }

    public String getMotionName() {
        return motionName;
    }

    public void setMotionName(String motionName) {
        this.motionName = motionName;
    }

    public String getMotionRemark() {
        return motionRemark;
    }

    public void setMotionRemark(String motionRemark) {
        this.motionRemark = motionRemark;
    }

    public String getMotionPicture() {
        return motionPicture;
    }

    public void setMotionPicture(String motionPicture) {
        this.motionPicture = motionPicture;
    }

    public String getMattersNeedingAttention() {
        return mattersNeedingAttention;
    }

    public void setMattersNeedingAttention(String mattersNeedingAttention) {
        this.mattersNeedingAttention = mattersNeedingAttention;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}