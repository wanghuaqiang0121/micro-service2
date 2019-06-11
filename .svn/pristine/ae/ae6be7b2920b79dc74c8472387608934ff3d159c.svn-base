package org.web.module.bone.age.domain;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;


/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: WangHuaQiang
 * @date: 2018年11月29日
 * @description: R骨分值表
 */
public class BoneScoreTable extends BaseEntity {
    
	private static final long serialVersionUID = 2031655745962562588L;

	private Integer id;

    private Integer sex;

    private String boneType;

    private Integer boneScore;

    private String boneGrade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBoneType() {
        return boneType;
    }

    public void setBoneType(String boneType) {
        this.boneType = boneType;
    }

    public Integer getBoneScore() {
        return boneScore;
    }

    public void setBoneScore(Integer boneScore) {
        this.boneScore = boneScore;
    }

    public String getBoneGrade() {
        return boneGrade;
    }

    public void setBoneGrade(String boneGrade) {
        this.boneGrade = boneGrade;
    }
    
    @Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}
}