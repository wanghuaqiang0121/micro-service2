package org.service.redis.terminal;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 终端枚举类
 */
public enum TerminalEnum {
	WEB("web"), Android("android"), IOS("ios"), BOX("box"), MOBILE("mobile"), WECHAT("wechat");
	private String flag;

	public String getFlag() {
		return flag;
	}

	TerminalEnum(String falg) {
		this.flag = falg;
	}
}