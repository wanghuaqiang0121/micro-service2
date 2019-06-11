package org.wechat.module.height.obesity.global;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 全局通用静态变量
 */
public class BaseGlobal {

	public static final String TOKEN_FLAG = "token";
	public static final String ORGANIZATION_ID = "organizationId";
	public static final String MODULE_ID = "moduleId";
	public static final String CACHE_ORGANIZATION_USER = "";
	public static final Object PERMISSION_CODE = "code";
	public static final String CACHE_USER = "cache_user";
	/* 18岁月龄 */
	public static final int MAX_MONTH_AGE = 216;
	/* 最大身高百分位 */
	public static final int MAX_HEIGHT_PERCENTILE = 97;
	/* 最小身高百分位 */
	public static final int MIN_HEIGHT_PERCENTILE = 3;
	/* 最大SD */
	public static final int MAX_SD = 3;
	/* 最小SD */
	public static final int MIN_SD = -3;
	/* 大于 */
	public static final String GREATER_THAN = ">";
	/* 小于 */
	public static final String LESS_THAN = "<";
	/* 等于 */
	public static final String EQUAL = "=";
	/*码表儿保证*/
	public static final int CHILD_PROTECTION_NO = 98;
	/*码表计免证*/
	public static final int PLAN_IMMUNITY_NO = 97;
	/*码表证件类型*/
	public static final String TYPE = "CV02.01.101";
	/*基公卫类型code*/
	public static final String JGW = "jgw";
}
