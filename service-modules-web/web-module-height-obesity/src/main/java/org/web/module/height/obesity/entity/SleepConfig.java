package org.web.module.height.obesity.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月19日
 * @description: 睡眠配置
 */
public class SleepConfig extends BaseEntity {
  
	private static final long serialVersionUID = 2118742307396693583L;

	private Integer id;
	 @NotNull(message = "{sleep.config.sex.notnull.valid}", groups = { Insert.class })
    private Integer sex;
    @NotNull(message = "{sleep.config.month.age.start.notnull.valid}", groups = { Insert.class })
    private Integer monthAgeStart;
    @NotNull(message = "{sleep.config.month.age.end.notnull.valid}", groups = { Insert.class })
    private Integer monthAgeEnd;
    @NotBlank(message = "{sleep.config.remark.notblank.valid}", groups = { Insert.class })
    private String remark;

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

    public Integer getMonthAgeStart() {
        return monthAgeStart;
    }

    public void setMonthAgeStart(Integer monthAgeStart) {
        this.monthAgeStart = monthAgeStart;
    }

    public Integer getMonthAgeEnd() {
        return monthAgeEnd;
    }

    public void setMonthAgeEnd(Integer monthAgeEnd) {
        this.monthAgeEnd = monthAgeEnd;
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
    @NotNull(message = "{pageSize.empty}", groups = { SelectAll.class})
    public Integer getPageSize() {
        return super.getPageSize();
    }
}