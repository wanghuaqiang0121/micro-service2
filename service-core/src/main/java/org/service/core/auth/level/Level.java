package org.service.core.auth.level;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 安全级别枚举类<br>
 *               目前提供三种级别<br>
 *               ROLE:角色级别<br>
 *               PERMISSION:权限级别<br>
 *               OPERATION:操作（方法）级别
 */
public enum Level {
	/**
	 * @type: {@link Level}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 角色
	 */
	ROLE,
	/**
	 * @type: {@link Level}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 权限
	 */
	PERMISSION,
	/**
	 * @type: {@link Level}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 操作
	 */
	OPERATION
}
