package org.web.module.organization.user.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.service.core.entity.BaseEntity;
import org.web.module.organization.user.domain.OrganizationUser.InsertOrganizationUser;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月10日
 * @description: 机构用户证件夹
 */
public class OrganizationUserCertificate extends BaseEntity {
   
	private static final long serialVersionUID = 5025215199500874110L;

	private Integer id;

    private Integer organizationUserId;
    
    @NotBlank(message = "{organization.user.certificateType.notblank.valid}", groups = { InsertOrganizationUser.class })
    private String certificateType;
    
    @NotBlank(message = "{organization.user.certificateNumber.notblank.valid}", groups = { InsertOrganizationUser.class })
    private String certificateNumber;

    private String positive;

    private String opposite;

    private String images;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getOpposite() {
        return opposite;
    }

    public void setOpposite(String opposite) {
        this.opposite = opposite;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
    
}