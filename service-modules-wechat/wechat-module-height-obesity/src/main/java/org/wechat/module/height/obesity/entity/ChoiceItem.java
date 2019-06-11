package org.wechat.module.height.obesity.entity;

import org.service.core.entity.BaseEntity;

public class ChoiceItem extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月11日
	 * @description: TODO
	 */
	private static final long serialVersionUID = -4128863159808835256L;

	private Integer id;

    private Integer choiceQuestionId;

    private String item;

    private Integer sort;

    private String createDateTime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChoiceQuestionId() {
        return choiceQuestionId;
    }

    public void setChoiceQuestionId(Integer choiceQuestionId) {
        this.choiceQuestionId = choiceQuestionId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}