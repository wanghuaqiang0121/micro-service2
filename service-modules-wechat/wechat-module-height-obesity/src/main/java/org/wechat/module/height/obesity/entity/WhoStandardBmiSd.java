package org.wechat.module.height.obesity.entity;

import java.math.BigDecimal;
import org.service.core.entity.BaseEntity;

public class WhoStandardBmiSd extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description:
     */
    private static final long serialVersionUID = 8176752744864890283L;

    private Integer id;

    private Integer monthAge;

    private Integer sex;

    private Integer sd;

    private BigDecimal standardValue;

    private Integer startMonthAge;

    public Integer getStartMonthAge() {
        return startMonthAge;
    }

    public void setStartMonthAge(Integer startMonthAge) {
        this.startMonthAge = startMonthAge;
    }

    public Integer getEndMonthAge() {
        return endMonthAge;
    }

    public void setEndMonthAge(Integer endMonthAge) {
        this.endMonthAge = endMonthAge;
    }

    private Integer endMonthAge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonthAge() {
        return monthAge;
    }

    public void setMonthAge(Integer monthAge) {
        this.monthAge = monthAge;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSd() {
        return sd;
    }

    public void setSd(Integer percentile) {
        this.sd = percentile;
    }

    public BigDecimal getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(BigDecimal standardValue) {
        this.standardValue = standardValue;
    }
}