package org.wechat.module.height.obesity.entity;

import java.math.BigDecimal;
import org.service.core.entity.BaseEntity;

public class WhoStandardBmiSdTemp extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description: TODO
     */
    private static final long serialVersionUID = 2949807398099555597L;

    private Integer id;

    private Integer monthAge;

    private Integer sex;

    private BigDecimal fiftyTh;

    private BigDecimal eightyFiveTh;

    private BigDecimal ninetyFiveTh;

    private BigDecimal ninetySevenTh;

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

    public BigDecimal getFiftyTh() {
        return fiftyTh;
    }

    public void setFiftyTh(BigDecimal fiftyTh) {
        this.fiftyTh = fiftyTh;
    }

    public BigDecimal getEightyFiveTh() {
        return eightyFiveTh;
    }

    public void setEightyFiveTh(BigDecimal eightyFiveTh) {
        this.eightyFiveTh = eightyFiveTh;
    }

    public BigDecimal getNinetyFiveTh() {
        return ninetyFiveTh;
    }

    public void setNinetyFiveTh(BigDecimal ninetyFiveTh) {
        this.ninetyFiveTh = ninetyFiveTh;
    }

    public BigDecimal getNinetySevenTh() {
        return ninetySevenTh;
    }

    public void setNinetySevenTh(BigDecimal ninetySevenTh) {
        this.ninetySevenTh = ninetySevenTh;
    }
}