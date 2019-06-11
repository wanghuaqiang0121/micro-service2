package org.web.module.height.obesity.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2019 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2019年1月16日
 * @description: 影响身高的疾病
 */
public class InfluenceHeightDisease  extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotNull(message = "{user.id.notnull.valid}", groups = { Insert.class })
	private Integer userId;
	private String code;
	private String name;
	private Date createDateTime;
	private List<InfluenceHeightDisease> influenceHeightDiseases;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	
	public List<InfluenceHeightDisease> getInfluenceHeightDiseases() {
		return influenceHeightDiseases;
	}
	public void setInfluenceHeightDiseases(List<InfluenceHeightDisease> influenceHeightDiseases) {
		this.influenceHeightDiseases = influenceHeightDiseases;
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
