package org.web.module.organization.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 医疗机构扩展信息
 */
public class MedicalOrganizationInfo extends BaseEntity {
   
	private static final long serialVersionUID = -9195816430225156505L;
	private Integer id;
    @NotNull(message = "{medical.organization.info.organization.info.id.not.null.valid}", groups = { Insert.class})
    private Integer organizationId;
    @NotNull(message = "{medical.organization.info.grade.id.not.null.valid}", groups = { Insert.class})
    private Integer organizationGradeId;
    @NotNull(message = "{medical.organization.info.type.id.not.null.valid}", groups = { Insert.class})
    private Integer medicalOrganizationTypeId;

    private Integer administrativeOrganizationId;
    @NotNull(message = "{medical.organization.info.is.administrative.not.null.valid}", groups = { Insert.class})
    private Boolean isAdministrative;
    @NotNull(message = "{medical.organization.info.nature.not.null.valid}", groups = { Insert.class})
    private Integer nature;
    @NotBlank(message = "{medical.organization.info.contract.protocol.prefix.notblank.valid}", groups = { Insert.class})
    private String contractProtocolPrefix;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getOrganizationGradeId() {
        return organizationGradeId;
    }

    public void setOrganizationGradeId(Integer organizationGradeId) {
        this.organizationGradeId = organizationGradeId;
    }

    public Integer getMedicalOrganizationTypeId() {
        return medicalOrganizationTypeId;
    }

    public void setMedicalOrganizationTypeId(Integer medicalOrganizationTypeId) {
        this.medicalOrganizationTypeId = medicalOrganizationTypeId;
    }

    public Integer getAdministrativeOrganizationId() {
        return administrativeOrganizationId;
    }

    public void setAdministrativeOrganizationId(Integer administrativeOrganizationId) {
        this.administrativeOrganizationId = administrativeOrganizationId;
    }

    public Boolean getIsAdministrative() {
        return isAdministrative;
    }

    public void setIsAdministrative(Boolean isAdministrative) {
        this.isAdministrative = isAdministrative;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getContractProtocolPrefix() {
        return contractProtocolPrefix;
    }

    public void setContractProtocolPrefix(String contractProtocolPrefix) {
        this.contractProtocolPrefix = contractProtocolPrefix;
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