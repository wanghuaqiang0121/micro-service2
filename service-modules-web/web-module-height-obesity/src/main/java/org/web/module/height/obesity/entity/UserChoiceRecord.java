package org.web.module.height.obesity.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

public class UserChoiceRecord extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description:
     */
    private static final long serialVersionUID = -388964485317143615L;

    private Integer id;

    // @NotNull(message = "{children.measure.user.id.not.null.valid}", groups = {
    // Insert.class })
    private Integer userId;

    // @NotNull(message = "{children.measure.id.is.not.null}", groups = {
    // Insert.class })
    private Integer childrenMeasureId;

    // @NotNull(message = "{question.id.not.null.valid}", groups = { Insert.class })
    private Integer choiceQuestionId;

    // @NotNull(message = "{choice.item.id.not.null.valid}", groups = { Insert.class
    // })
    private Integer choiceItemId;

    private Integer organizationUserId;

    private Integer code;

    private Date createDateTime;

    @NotNull(message = "{userChoiceRecords.is.not.null}", groups = { Insert.class, Update.class })
    List<UserChoiceRecord> userChoiceRecords;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<UserChoiceRecord> getUserChoiceRecords() {
        return userChoiceRecords;
    }

    public void setUserChoiceRecords(List<UserChoiceRecord> userChoiceRecords) {
        this.userChoiceRecords = userChoiceRecords;
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

    public Integer getChildrenMeasureId() {
        return childrenMeasureId;
    }

    public void setChildrenMeasureId(Integer childrenMeasureId) {
        this.childrenMeasureId = childrenMeasureId;
    }

    public Integer getChoiceQuestionId() {
        return choiceQuestionId;
    }

    public void setChoiceQuestionId(Integer choiceQuestionId) {
        this.choiceQuestionId = choiceQuestionId;
    }

    public Integer getChoiceItemId() {
        return choiceItemId;
    }

    public void setChoiceItemId(Integer choiceItemId) {
        this.choiceItemId = choiceItemId;
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