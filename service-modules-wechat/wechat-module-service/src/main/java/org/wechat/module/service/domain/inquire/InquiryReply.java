package org.wechat.module.service.domain.inquire;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月18日
 * @description: 询问答复
 */
public class InquiryReply extends BaseEntity {
  
	private static final long serialVersionUID = -6283470443333453578L;

	private Integer id;

    @NotNull(message = "{inquiry.id.not.null.valid}", groups = { Insert.class})
    private Integer inquiryId;

    private Integer type;
    
    @NotBlank(message = "{content.not.blank.valid}", groups = { Insert.class})
    private String content;

    private Date createDate;
    
    private Integer inquiriesNum;
    
    public Integer getInquiriesNum() {
		return inquiriesNum;
	}

	public void setInquiriesNum(Integer inquiriesNum) {
		this.inquiriesNum = inquiriesNum;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Integer inquiryId) {
        this.inquiryId = inquiryId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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