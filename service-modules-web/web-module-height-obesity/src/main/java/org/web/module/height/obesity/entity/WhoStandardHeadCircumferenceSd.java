package org.web.module.height.obesity.entity;

import java.math.BigDecimal;
import org.service.core.entity.BaseEntity;

public class WhoStandardHeadCircumferenceSd extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月11日
	 * @description: TODO
	 */
	private static final long serialVersionUID = -4149223183315367134L;

	private Integer id;

    private Integer monthAge;

    private Integer sex;

    private Integer sd;

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