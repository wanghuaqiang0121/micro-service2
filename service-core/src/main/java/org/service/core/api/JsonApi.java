package org.service.core.api;

import java.beans.Transient;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 数据返回格式
 */
public class JsonApi {
	/**
	 * @type: {@link Integer}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 状态码
	 */
	private Integer code;
	/**
	 * @type: {@link Object}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 数据
	 */
	private Object data;
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 消息
	 */
	private String msg;

	public JsonApi() {
	}

	public JsonApi(ApiCodeEnum code) {
		this.code = code.getValue();
		this.msg = code.getMessage();
	}

	public JsonApi(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public JsonApi(ApiCodeEnum code, Object data) {
		this.data = data;
		this.code = code.getValue();
		this.msg = code.getMessage();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data == null ? "" : data;
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(Class<T> type) {
		return data == null ? null : (T) data;
	}

	@Transient
	public List<Map<String, Object>> getRows() {
		MultiLine multiLine = getData(MultiLine.class);
		return multiLine == null ? null : multiLine.getRows();
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public JsonApi setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	/**
	 * @Title: compare
	 * @param code
	 * @return {@link Boolean}
	 * @Author: LiuGangQiang
	 * @Date: 2018年8月31日 下午2:00:34
	 * @Return: boolean
	 * @Description: 状态码比较
	 */
	public boolean compare(ApiCodeEnum code) {
		return getCode() == code.getValue();
	}
}
