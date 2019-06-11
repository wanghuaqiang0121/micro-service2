package org.service.task.delay.entity.wechat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月17日
 * @description: 微信消息模板
 */
public class WechatTemplate extends BaseEntity {

	private static final long serialVersionUID = -3625393922277237208L;

	@NotBlank(message = "{wechatapi.type.not.blank}", groups = { Insert.class })
	private String type;
	private Integer recordId;
	@NotBlank(message = "{wechatapi.appid.not.blank}", groups = { Insert.class })
	private String appId;
	@NotBlank(message = "{wechatapi.openid.not.blank}", groups = { Insert.class })
	private String openId;
	@NotBlank(message = "{wechatapi.user.name.not.blank}", groups = { Insert.class })
	private String userName;
	@NotBlank(message = "{wechatapi.content.not.blank}", groups = { Insert.class })
	private String content;
	@NotBlank(message = "{wechatapi.notifier.not.blank}", groups = { Insert.class })
	private String notifier;
	private String remark;
	@NotNull(message = "{wechat.time.not.null}", groups = { Insert.class })
	@Min(message = "{wechat.time.min}", value = 0, groups = { Insert.class })
	private Integer time;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNotifier() {
		return notifier;
	}

	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTime() {
		if (time > 0) {
			return time * 1000;
		}
		return 1;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}
