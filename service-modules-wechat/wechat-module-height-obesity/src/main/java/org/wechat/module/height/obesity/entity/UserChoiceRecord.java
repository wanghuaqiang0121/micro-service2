package org.wechat.module.height.obesity.entity;

import java.util.Date;
import org.service.core.entity.BaseEntity;

import javax.validation.constraints.NotNull;

public class UserChoiceRecord extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月11日
	 * @description: TODO
	 */
	private static final long serialVersionUID = -388964485317143615L;

	private Integer id;

    private Integer userId;
    private Integer childrenMeasureId;
    @NotNull(message = "{choiceQuestion.id.notnull.valid}", groups = { Insert.class})
    private Integer choiceQuestionId;
    @NotNull(message = "{choiceItem.id.notnull.valid}", groups = { Insert.class})
    private Integer choiceItemId;

    private Integer organizationUserId;

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
}