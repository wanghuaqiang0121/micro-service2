package org.wechat.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

public class UserMeasurementRecord extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月17日
	 * @description: TODO
	 */
	private static final long serialVersionUID = 2547362554134577662L;

    private Date start;
	private Date end;
	private Integer dayOrMonthType;
	private Integer id;
    @NotNull(message = "{measurement.userId.notnull.valid}", groups = { SelectAll.class})
    private Integer userId;
    @NotNull(message = "{measurement.type.notnull.valid}", groups = { Insert.class})
    private Integer type;
    @NotNull(message = "{measurement.value.notnull.valid}", groups = { Insert.class})
    private Float measurementValue;

    private String evaluation;

    private Byte isHistory;
    //@JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss", timezone = "GMT+8")
    @NotNull(message = "{measurement.date.notnull.valid}", groups = { Insert.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date measurementDate;

    private Date createDateTime;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(Float measurementValue) {
        this.measurementValue = measurementValue;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Byte getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(Byte isHistory) {
        this.isHistory = isHistory;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
    @Override
    @NotNull(message = "{page.empty}", groups = { SelectAll.class})
    public Integer getPage() {
        return super.getPage();
    }

    @Override
    @NotNull(message = "{pageSize.empty}", groups = { SelectAll.class})
    public Integer getPageSize() {
        return super.getPageSize();
    }

    public Integer getDayOrMonthType() {
        return dayOrMonthType;
    }

    public void setDayOrMonthType(Integer dayOrMonthType) {
        this.dayOrMonthType = dayOrMonthType;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

}