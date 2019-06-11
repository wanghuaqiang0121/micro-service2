package org.wechat.module.height.obesity.entity;

import java.math.BigDecimal;
import org.service.core.entity.BaseEntity;

import javax.validation.constraints.NotNull;

public class StandardHeightPercentile extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月11日
     * @description: TODO
     */
    public interface GetHeightEvaluation {

    }

    private static final long serialVersionUID = -65986698253938171L;

    private Integer id;

    private Integer monthAge;
    private Integer sex;

    private Integer percentile;
    @NotNull(message = "{heightValue.is.null}", groups = { GetHeightEvaluation.class })
    private BigDecimal standardValue;
    private Integer startMonthAge;

    private Integer endMonthAge;

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

    public Integer getPercentile() {
        return percentile;
    }

    public void setPercentile(Integer percentile) {
        this.percentile = percentile;
    }

    public BigDecimal getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(BigDecimal standardValue) {
        this.standardValue = standardValue;
    }
}