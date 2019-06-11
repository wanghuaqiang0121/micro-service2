package org.wechat.module.height.obesity.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2019 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2019年1月30日
 * @description: 过敏
 */
public class HisAllergicHistory extends BaseEntity {
	private static final long serialVersionUID = 8416350047582910923L;

	private Integer id;

    private Integer userId;

    private String dicCode;

    private String dicValue;

    private String remark;

    private Integer createBy;

    private Date createDate;

    private Integer modifiedBy;

    private Byte status;

    private Date modifiedDate;
    
	private List<HisAllergicHistory> hisAllergicHistorys;

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

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    
    public List<HisAllergicHistory> getHisAllergicHistorys() {
		return hisAllergicHistorys;
	}

	public void setHisAllergicHistorys(List<HisAllergicHistory> hisAllergicHistorys) {
		this.hisAllergicHistorys = hisAllergicHistorys;
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