package org.wechat.module.height.obesity.entity;

import java.math.BigDecimal;
import org.service.core.entity.BaseEntity;

public class WhoStandardWeightSdTemp extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月11日
	 * @description: TODO
	 */
	private static final long serialVersionUID = -4901954286010605709L;

	private Integer id;

    private Integer monthAge;

    private Integer sex;

    private BigDecimal subThreeSd;

    private BigDecimal subTwoSd;

    private BigDecimal subOneSd;

    private BigDecimal medianSd;

    private BigDecimal plusOneSd;

    private BigDecimal plusTwoSd;

    private BigDecimal plusThreeSd;

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

    public BigDecimal getSubThreeSd() {
        return subThreeSd;
    }

    public void setSubThreeSd(BigDecimal subThreeSd) {
        this.subThreeSd = subThreeSd;
    }

    public BigDecimal getSubTwoSd() {
        return subTwoSd;
    }

    public void setSubTwoSd(BigDecimal subTwoSd) {
        this.subTwoSd = subTwoSd;
    }

    public BigDecimal getSubOneSd() {
        return subOneSd;
    }

    public void setSubOneSd(BigDecimal subOneSd) {
        this.subOneSd = subOneSd;
    }

    public BigDecimal getMedianSd() {
        return medianSd;
    }

    public void setMedianSd(BigDecimal medianSd) {
        this.medianSd = medianSd;
    }

    public BigDecimal getPlusOneSd() {
        return plusOneSd;
    }

    public void setPlusOneSd(BigDecimal plusOneSd) {
        this.plusOneSd = plusOneSd;
    }

    public BigDecimal getPlusTwoSd() {
        return plusTwoSd;
    }

    public void setPlusTwoSd(BigDecimal plusTwoSd) {
        this.plusTwoSd = plusTwoSd;
    }

    public BigDecimal getPlusThreeSd() {
        return plusThreeSd;
    }

    public void setPlusThreeSd(BigDecimal plusThreeSd) {
        this.plusThreeSd = plusThreeSd;
    }
}