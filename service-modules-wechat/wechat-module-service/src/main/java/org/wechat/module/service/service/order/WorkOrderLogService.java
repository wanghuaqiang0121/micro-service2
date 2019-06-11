package org.wechat.module.service.service.order;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.order.WorkOrderLogMapper;
import org.wechat.module.service.domain.order.WorkOrderLog;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年9月30日
 * @description: WorkOrderLogService
 */
@Service
public class WorkOrderLogService {

	@Resource
	private WorkOrderLogMapper workOrderLogMapper;

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param workOrderLog
	* @return 
	* @date 2018年3月28日
	* @version 1.0
	* @description 添加日志
	*/
	public int insert(WorkOrderLog workOrderLog) {
		return workOrderLogMapper.insert(workOrderLog);
	}
	
}
