package  org.wechat.module.user.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.service.core.entity.BaseEntity;
import org.wechat.module.user.domain.User.register;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 用户微信
 */
public class UserWechat extends BaseEntity {
	
	private static final long serialVersionUID = 3375273371243365822L;
	private Integer id;
	private Integer userId;
	@NotBlank(message = "{user.wechat.appid.not.blank}", groups = { User.BindWechat.class, User.WechatLogin.class,register.class })
	private String appid;
	@NotBlank(message = "{user.wechat.openid.not.blank}", groups = { User.BindWechat.class, User.WechatLogin.class,register.class })
	private String openid;
	private Date loginDate;
	private Date createDate;

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

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
