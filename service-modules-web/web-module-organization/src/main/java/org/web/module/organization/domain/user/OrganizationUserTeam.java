package org.web.module.organization.domain.user;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月10日
 * @description: 机构团队用户关联表
 */
public class OrganizationUserTeam extends BaseEntity {
	
	private static final long serialVersionUID = -3961662421808498765L;

	/**
	 * @author <font color="red"><b>Wang.HuaQiang</b></font>
	 * @Date 2018年5月8日
	 * @Version DoctorDoctorTeam
	 * @Description 团队成员列表
	 */
	public interface TeamDoctors{};
	
    private Integer id;

    private String position;
    @NotNull(message = "{doctorDoctorTeam.organizationUserId.not.null}", groups = { Insert.class})
    private Integer organizationUserId;
    @NotNull(message = "{doctorDoctorTeam.organizationTeamId.not.null}", groups = { Insert.class})
    private Integer organizationTeamId;

    private String duty;
    //@NotNull(message = "{doctorDoctorTeam.isManager.not.null}", groups = { Insert.class})
    private Boolean isManager;

    private Date createDate;
    
    private OrganizationUser organizationUser;
    
    private String name;
    
    private String province;
    private String city;
    private String area;
    private String street;
    private String community;
    
    
    
    public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrganizationUser getOrganizationUser() {
		return organizationUser;
	}

	public void setOrganizationUser(OrganizationUser organizationUser) {
		this.organizationUser = organizationUser;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public Integer getOrganizationTeamId() {
        return organizationTeamId;
    }

    public void setOrganizationTeamId(Integer organizationTeamId) {
        this.organizationTeamId = organizationTeamId;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
	@Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}
}