package org.service.core.api;

import java.util.List;
import java.util.Map;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 多行数据封装
 */
public class MultiLine {

	/**
	 * @type: {@link Long}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 总记录数
	 */
	private Long total;

	/**
	 * @type: {@link List}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 列表数据
	 */
	private List<Map<String, Object>> rows;

	public MultiLine(long total, List<Map<String, Object>> rows) {
		this.total = total;
		this.rows = rows;
	}

	public MultiLine() {
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}
}
