package org.service.redis.token;

import java.io.Serializable;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 登录Session
 */
public class RedisSession implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * @type: {@link long}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 序列化ID
	 */
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 唯一区别串
	 */
	private String identify;
	/**
	 * @type: {@link Object}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 用户信息
	 */
	private Object data;

	public RedisSession() {
	}

	public RedisSession(String identify, Object data) {
		this.identify = identify;
		this.data = data;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @param type
	 * @return {@link T}
	 * @date 2018年8月16日
	 * @version 1.0
	 * @description 获取缓存主体数据对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> type) {
		return data == null ? null : (T) data;
	}
}
