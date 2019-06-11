package  org.wechat.module.user.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;
/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 用户服务
 */
public class UserService extends BaseEntity {
    
	private static final long serialVersionUID = -8434116205166775656L;

	private Integer id;

    @NotNull(message = "{user.service.userId.is.not.null}",groups={SelectAll.class})
    private Integer userId;

    private Integer userServicePackageOrderId;

    private Integer serviceTypeId;
    
    private String serviceTypeCode;
    
    private String businessCode;

    private Integer times;

    private Integer lockTimes;

    private Integer useTimes;

    private Date createTime;

    private Date updateTime;

    
    public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getServiceTypeCode() {
		return serviceTypeCode;
	}

	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}

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

    public Integer getUserServicePackageOrderId() {
        return userServicePackageOrderId;
    }

    public void setUserServicePackageOrderId(Integer userServicePackageOrderId) {
        this.userServicePackageOrderId = userServicePackageOrderId;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getLockTimes() {
        return lockTimes;
    }

    public void setLockTimes(Integer lockTimes) {
        this.lockTimes = lockTimes;
    }

    public Integer getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(Integer useTimes) {
        this.useTimes = useTimes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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