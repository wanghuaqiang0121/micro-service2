package org.wechat.module.height.obesity.entity;

import java.math.BigDecimal;
import org.service.core.entity.BaseEntity;

public class WhoStandardHeightWeightSdTemp extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月11日
	 * @description: TODO
	 */
	private static final long serialVersionUID = 2380487209710309108L;

	private Integer id;

    private Integer type;

    private Integer sex;

    private BigDecimal height;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
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