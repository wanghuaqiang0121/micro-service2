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
 * @description: 用户签约
 */
public class UserSign extends BaseEntity {
   
	private static final long serialVersionUID = 5861918406831131867L;

	private Integer id;

    private Integer userId;

    private Integer organizationTeamId;

    private Integer organizationUserId;

    private String signNo;

    private Integer intentionServicePackageId;

    private Integer userServicePackageOrderId;

    private Integer launchOrganizationUserId;

    private Integer status;

    private String signature;

    private String explain;

    private Integer launchType;

    private String protocol;

    private String closeReasonCode;

    private String closeReason;

    private String notice;

    private Date updateDate;

    private Date createDate;

    private String remark;

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

    public Integer getOrganizationTeamId() {
        return organizationTeamId;
    }

    public void setOrganizationTeamId(Integer organizationTeamId) {
        this.organizationTeamId = organizationTeamId;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public String getSignNo() {
        return signNo;
    }

    public void setSignNo(String signNo) {
        this.signNo = signNo;
    }

    public Integer getIntentionServicePackageId() {
        return intentionServicePackageId;
    }

    public void setIntentionServicePackageId(Integer intentionServicePackageId) {
        this.intentionServicePackageId = intentionServicePackageId;
    }

    public Integer getUserServicePackageOrderId() {
        return userServicePackageOrderId;
    }

    public void setUserServicePackageOrderId(Integer userServicePackageOrderId) {
        this.userServicePackageOrderId = userServicePackageOrderId;
    }

    public Integer getLaunchOrganizationUserId() {
        return launchOrganizationUserId;
    }

    public void setLaunchOrganizationUserId(Integer launchOrganizationUserId) {
        this.launchOrganizationUserId = launchOrganizationUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getLaunchType() {
        return launchType;
    }

    public void setLaunchType(Integer launchType) {
        this.launchType = launchType;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getCloseReasonCode() {
        return closeReasonCode;
    }

    public void setCloseReasonCode(String closeReasonCode) {
        this.closeReasonCode = closeReasonCode;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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