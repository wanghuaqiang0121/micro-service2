package org.wechat.module.height.obesity.entity;

import java.math.BigDecimal;
import org.service.core.entity.BaseEntity;

public class WhoStandardWeightSd extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description: TODO
     */
    private static final long serialVersionUID = -1464788000939057465L;

    private Integer id;

    private Integer monthAge;

    private Integer sex;

    private Integer sd;

    private BigDecimal standardValue;

    private Integer startMonthAge;
    private Integer endMonthAge;

    public Integer getId() {
        return id;
    }

    /**
     * @return the endMonthAge
     */
    public Integer getEndMonthAge() {
        return endMonthAge;
    }

    /**
     * @param endMonthAge the endMonthAge to set
     */
    public void setEndMonthAge(Integer endMonthAge) {
        this.endMonthAge = endMonthAge;
    }

    /**
     * @return the startMonthAge
     */
    public Integer getStartMonthAge() {
        return startMonthAge;
    }

    /**
     * @param startMonthAge the startMonthAge to set
     */
    public void setStartMonthAge(Integer startMonthAge) {
        this.startMonthAge = startMonthAge;
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

    public void setSd(Integer sd) {
        this.sd = sd;
    }

    public BigDecimal getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(BigDecimal standardValue) {
        this.standardValue = standardValue;
    }
}