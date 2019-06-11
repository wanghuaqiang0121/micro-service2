package org.wechat.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

public class ChildrenMeasure extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description: TODO
     */
    private static final long serialVersionUID = 5304125102861034645L;

    private Integer id;

    private String number;

    private Integer userId;

    private Integer monthAge;

    private Float currentHeight;

    private Float currentWeight;

    private Float idealHeight;

    private Float headCircumference;

    private Float chestCircumference;

    private Float abdomenCircumference;

    private Float upperBodyLength;

    private Float lowerBodyLength;

    private Float bodyFat;

    private Integer diastolicPressure;

    private Integer systolicPressure;

    private Integer heightPercentile;

    private Integer weightSd;

    private Float bmi;

    private Date createDateTime;
    private Date endCreateDateTime;

    private Integer status;

    private Integer startMonthAge;

    private Integer endMonthAge;

    public Integer getStartMonthAge() {
        return startMonthAge;
    }

    public void setStartMonthAge(Integer startMonthAge) {
        this.startMonthAge = startMonthAge;
    }

    public Integer getEndMonthAge() {
        return endMonthAge;
    }

    public void setEndMonthAge(Integer endMonthAge) {
        this.endMonthAge = endMonthAge;
    }

    public Date getEndCreateDateTime() {
        return endCreateDateTime;
    }

    public void setEndCreateDateTime(Date endCreateDateTime) {
        this.endCreateDateTime = endCreateDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMonthAge() {
        return monthAge;
    }

    public void setMonthAge(Integer monthAge) {
        this.monthAge = monthAge;
    }

    public Float getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(Float currentHeight) {
        this.currentHeight = currentHeight;
    }

    public Float getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Float currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Float getIdealHeight() {
        return idealHeight;
    }

    public void setIdealHeight(Float idealHeight) {
        this.idealHeight = idealHeight;
    }

    public Float getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(Float headCircumference) {
        this.headCircumference = headCircumference;
    }

    public Float getChestCircumference() {
        return chestCircumference;
    }

    public void setChestCircumference(Float chestCircumference) {
        this.chestCircumference = chestCircumference;
    }

    public Float getAbdomenCircumference() {
        return abdomenCircumference;
    }

    public void setAbdomenCircumference(Float abdomenCircumference) {
        this.abdomenCircumference = abdomenCircumference;
    }

    public Float getUpperBodyLength() {
        return upperBodyLength;
    }

    public void setUpperBodyLength(Float upperBodyLength) {
        this.upperBodyLength = upperBodyLength;
    }

    public Float getLowerBodyLength() {
        return lowerBodyLength;
    }

    public void setLowerBodyLength(Float lowerBodyLength) {
        this.lowerBodyLength = lowerBodyLength;
    }

    public Float getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(Float bodyFat) {
        this.bodyFat = bodyFat;
    }

    public Integer getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(Integer diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public Integer getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(Integer systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public Integer getHeightPercentile() {
        return heightPercentile;
    }

    public void setHeightPercentile(Integer heightPercentile) {
        this.heightPercentile = heightPercentile;
    }

    public Integer getWeightSd() {
        return weightSd;
    }

    public void setWeightSd(Integer weightSd) {
        this.weightSd = weightSd;
    }

    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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