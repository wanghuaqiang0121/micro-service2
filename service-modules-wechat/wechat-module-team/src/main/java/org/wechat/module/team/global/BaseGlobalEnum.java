package org.wechat.module.team.global;

public interface BaseGlobalEnum {
	/**
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: LiuGangQiang
	 * @date: 2018年11月15日
	 * @description: 服务包类型枚举
	 */
	public enum Type{
		
		/**
		 * @Fields <font color="blue">JGW</font>
		 * @description 基本公共卫生服务包
		 */
		JGW("JGW");
		
		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		private Type(String value) {
			this.value = value;
		}
	}
}
