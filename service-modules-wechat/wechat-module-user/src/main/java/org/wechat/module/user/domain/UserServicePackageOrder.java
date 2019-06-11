package  org.wechat.module.user.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: UserServicePackageOrder
 */
public class UserServicePackageOrder extends BaseEntity {
 
	private static final long serialVersionUID = -6779521274292415712L;

/**
   * 
   * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
   *
   * @author: ChenYan
   * @date: 2018年10月16日
   * @description:  取消订单
   */
    public interface refuseOrder {

	}

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @description:  确认订单
	 */
    public interface sureOrder {

	}

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @description: 线上新增订单
	 */
    public interface onlineOrder {

	}

	private Integer id;
	@NotNull(message = "{user.id.not.null.valid}", groups = { onlineOrder.class, SelectAll.class})
    private Integer userId;
	@NotNull(message = "{organization.doctor.team.service.package.id.notnull.valid}", groups = { 
			onlineOrder.class})
    private Integer teamOrganizationServicePackageId;
    
    private Integer organizationServicePackageId;

    private Integer doctorTeamId;

    private String orderNo;

    private BigDecimal price;

    private String evidenceSn;

    private String evidenceFile;

    private Integer type;

    private Integer status;

    private Integer[] statuss;
    
    private Date createDate;

    private Date expireDate;

    private Date completeDate;

    private String remark;
    
    private String merchantNumber;
    private String appId;
    private String appSecret;
    
    private String payKey;
    
    private String wechatId;
    
    private String wechatKey;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getWechatKey() {
		return wechatKey;
	}

	public void setWechatKey(String wechatKey) {
		this.wechatKey = wechatKey;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getPayKey() {
		return payKey;
	}

	public void setPayKey(String payKey) {
		this.payKey = payKey;
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

    public Integer getTeamOrganizationServicePackageId() {
		return teamOrganizationServicePackageId;
	}

	public void setTeamOrganizationServicePackageId(Integer teamOrganizationServicePackageId) {
		this.teamOrganizationServicePackageId = teamOrganizationServicePackageId;
	}

	public Integer getOrganizationServicePackageId() {
        return organizationServicePackageId;
    }

    public void setOrganizationServicePackageId(Integer organizationServicePackageId) {
        this.organizationServicePackageId = organizationServicePackageId;
    }

    public Integer getDoctorTeamId() {
        return doctorTeamId;
    }

    public void setDoctorTeamId(Integer doctorTeamId) {
        this.doctorTeamId = doctorTeamId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getEvidenceSn() {
        return evidenceSn;
    }

    public void setEvidenceSn(String evidenceSn) {
        this.evidenceSn = evidenceSn;
    }

    public String getEvidenceFile() {
        return evidenceFile;
    }

    public void setEvidenceFile(String evidenceFile) {
        this.evidenceFile = evidenceFile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer[] getStatuss() {
		return statuss;
	}

	public void setStatuss(Integer[] statuss) {
		this.statuss = statuss;
	}

	public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
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