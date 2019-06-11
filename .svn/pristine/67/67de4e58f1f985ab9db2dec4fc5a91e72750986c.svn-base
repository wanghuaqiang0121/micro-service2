package org.wechat.module.service.domain.inquire;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月18日
 * @description: 查询
 */
public class Inquiry extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Integer id;

    @NotNull(message = "{user.id.not.null.valid}", groups = { Insert.class})
    private Integer userId;
    
    @NotNull(message = "{organization.team.id.not.null.valid}", groups = { Insert.class})
    private Integer organizationTeamId;

    private Integer organizationUserId;

    @NotBlank(message = "{content.not.null.valid}", groups = { Insert.class})
    private String content;

    private Integer status;
    
    private InquiryReply inquiryReply;
    
    @NotNull(message = "{satisfaction.not.null.valid}", groups = { Update.class})
    @Min(value = 0,message = "{satisfaction.greater.than.zero.valid}", groups = { Update.class})
    @Max(value = 5,message = "{satisfaction.less.than.five.valid}", groups = { Update.class})
    private Float satisfaction;

    private String evaluate;

    private Date acceptDate;

    private Date createDate;

  

	public InquiryReply getInquiryReply() {
		return inquiryReply;
	}

	public void setInquiryReply(InquiryReply inquiryReply) {
		this.inquiryReply = inquiryReply;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Float satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
}