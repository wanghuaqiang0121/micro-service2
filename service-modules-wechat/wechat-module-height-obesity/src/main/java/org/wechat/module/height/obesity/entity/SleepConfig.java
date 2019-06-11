package org.wechat.module.height.obesity.entity;

import org.service.core.entity.BaseEntity;

public class SleepConfig extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月11日
	 * @description: TODO
	 */
	private static final long serialVersionUID = 2118742307396693583L;

	private Integer id;

    private Integer sex;

    private Integer monthAgeStart;

    private Integer monthAgeEnd;

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
}