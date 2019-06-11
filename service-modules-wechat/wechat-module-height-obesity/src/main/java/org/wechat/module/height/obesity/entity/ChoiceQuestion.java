package org.wechat.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

public class ChoiceQuestion extends BaseEntity {
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public interface Selected {

    }
    public interface Completed {

    }

    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description: TODO
     */
    private static final long serialVersionUID = -4315496842826154862L;

    private Integer id;

    private Integer monthAgeStart;

    private Integer monthAgeEnd;
    private Integer monthAge;

    private String title;

    private String code;

    private String name;

    private Integer type;

    private Integer sort;
    private Integer childrenMeasureId;
    private Integer userId;

    private Date createDateTime;
    private Date startTime;
    private Date endTime;


    public Integer getMonthAge() {
        return monthAge;
    }

    public void setMonthAge(Integer monthAge) {
        this.monthAge = monthAge;
    }

    public Integer getChildrenMeasureId() {
        return childrenMeasureId;
    }

    public void setChildrenMeasureId(Integer childrenMeasureId) {
        this.childrenMeasureId = childrenMeasureId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonthAgeStart() {
        return monthAgeStart;
    }

    public void setMonthAgeStart(Integer monthAgeStart) {
        this.monthAgeStart = monthAgeStart;
    }

    public Integer getMonthAgeEnd() {
        return monthAgeEnd;
    }

    public void setMonthAgeEnd(Integer monthAgeEnd) {
        this.monthAgeEnd = monthAgeEnd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
    @Override
    @NotNull(message = "{page.empty}", groups = { SelectAll.class ,Selected.class})
    public Integer getPage() {
        return super.getPage();
    }

    @Override
    @NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,Selected.class })
    public Integer getPageSize() {
        return super.getPageSize();
    }
}