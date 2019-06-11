package org.web.module.organization.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 机构等级
 */
public class OrganizationGrade extends BaseEntity {
  
	private static final long serialVersionUID = -1308422196682642407L;
	private Integer id;
    @NotBlank(message = "{organization.grade.name.not.blank.valid}", groups = {Insert.class})
    private String name;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}