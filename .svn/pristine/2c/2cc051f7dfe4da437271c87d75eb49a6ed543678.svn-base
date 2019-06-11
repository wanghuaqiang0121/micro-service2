package org.web.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserSecondarySexCharactersRecord extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description:
     */
    private static final long serialVersionUID = -5747499115897580271L;

    private Integer id;

    @NotNull(message = "{children.measure.user.id.not.null.valid}", groups = { SelectAll.class })
    private Integer userId;

    @NotNull(message = "{children.measure.id.is.not.null}", groups = { SelectOne.class, Insert.class })
    private Integer childrenMeasureId;
    @NotNull(message = "{secondary.characters.config.id.is.not.null}", groups = { Insert.class , Update.class})
    private Integer secondarySexCharactersConfigId;

    private Integer organizationUserId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{examination.date.is.not.null}", groups = { Insert.class })
    private Date examinationDate;
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

    public Integer getChildrenMeasureId() {
        return childrenMeasureId;
    }

    public void setChildrenMeasureId(Integer childrenMeasureId) {
        this.childrenMeasureId = childrenMeasureId;
    }

    public Integer getSecondarySexCharactersConfigId() {
        return secondarySexCharactersConfigId;
    }

    public void setSecondarySexCharactersConfigId(Integer secondarySexCharactersConfigId) {
        this.secondarySexCharactersConfigId = secondarySexCharactersConfigId;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public Date getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
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