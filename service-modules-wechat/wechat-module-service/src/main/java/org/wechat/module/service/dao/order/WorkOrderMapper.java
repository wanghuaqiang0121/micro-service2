package org.wechat.module.service.dao.order;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.service.domain.order.WorkOrder;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年9月30日
 * @description: WorkOrderMapper
 */
public interface WorkOrderMapper extends IBaseMapper<WorkOrder> {
  
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param workOrder
	* @return 
	* @date 2018年3月27日
	* @version 1.0
	* @description 查询每天预约的人数
	*/
	List<Map<String, Object>> getReservationNumber(WorkOrder workOrder);

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param workOrder2
	* @return 
	* @date 2018年3月28日
	* @version 1.0
	* @description 查询用户是否存在未完成的相同服务的工单
	*/
	List<Map<String, Object>> getNotFinishWorkOrdersByUserId(WorkOrder workOrder2);

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param workOrder
	* @return 
	* @date 2018年3月28日
	* @version 1.0
	* @description 用户工单列表
	*/
	List<Map<String, Object>> getListByUserId(WorkOrder workOrder);
	
	/**
	 * @author <font color="green"><b>Zhang.Xiang.Zheng</b></font>
	 * @param workOrder
	 * @return
	 * @date 2018年5月4日
	 * @version 1.0
	 * @description 查询工单明细
	 */
	Map<String, Object> getWorkOrderDetail(WorkOrder workOrder);
}