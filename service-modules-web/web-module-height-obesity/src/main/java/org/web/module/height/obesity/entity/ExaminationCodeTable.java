package org.web.module.height.obesity.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月14日
 * @description: 检查检验码表
 */
public class ExaminationCodeTable extends BaseEntity {
   
	private static final long serialVersionUID = -1613946843102655007L;

	private Integer id;
	@NotBlank(message = "{examination.code.table.type.notnull.valid}", groups = { Insert.class })
    private String type;
    @NotBlank(message = "{examination.code.table.code.notblank.valid}", groups = { Insert.class })
    private String code;
    @NotBlank(message = "{examination.code.table.name.notblank.valid}", groups = { Insert.class })
    private String name;
    @NotBlank(message = "{examination.code.table.pcode.notblank.valid}", groups = { Insert.class })
    private String pcode;

    private String unit;

    private String initial;

    private String acronym;
    
    private String  referenceStart;
    
    private String  referenceEnd;
    
    private String  remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

	


	public String getReferenceStart() {
		return referenceStart;
	}

	public void setReferenceStart(String referenceStart) {
		this.referenceStart = referenceStart;
	}

	public String getReferenceEnd() {
		return referenceEnd;
	}

	public void setReferenceEnd(String referenceEnd) {
		this.referenceEnd = referenceEnd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
   	@NotNull(message = "{page.empty}", groups = { SelectAll.class})
   	public Integer getPage() {
   		return super.getPage();
   	}

   	@Override
   	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class })
   	public Integer getPageSize() {
   		return super.getPageSize();
   	}
}