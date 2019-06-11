package org.web.module.height.obesity.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月19日
 * @description: 药品信息配置表
 */
public class DrugsConfig extends BaseEntity {
    
	private static final long serialVersionUID = 8365735771121644274L;

	private Integer id;
    @NotBlank(message = "{drugs.config.code.type.notblank.valid}", groups = { Insert.class })
    private String codeType;
	@NotBlank(message = "{drugs.config.code.value.notblank.valid}", groups = { Insert.class })
    private String codeValue;
	@NotBlank(message = "{drugs.config.name.notblank.valid}", groups = { Insert.class })
    private String name;
	@NotBlank(message = "{drugs.config.initials.notblank.valid}", groups = { Insert.class })
    private String initials;
	@NotBlank(message = "{drugs.config.acronym.notblank.valid}", groups = { Insert.class })
	private String	acronym;

    private String unit;
    @NotBlank(message = "{drugs.config.instructions.notblank.valid}", groups = { Insert.class })
    private String instructions;

    private String remark;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
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