package org.wechat.module.service.service.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.order.WorkOrderMapper;
import org.wechat.module.service.domain.order.WorkOrder;

@Service
public class WorkOrderService {
	
	@Resource
	private WorkOrderMapper workOrderMapper;

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param workOrder
	* @return 
	* @date 2018年3月27日
	* @version 1.0
	* @description 查询每天预约的人数
	*/
	public List<Map<String, Object>> getReservationNumber(WorkOrder workOrder) {
		return workOrderMapper.getReservationNumber(workOrder);
	}

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param workOrder2
	* @return 
	* @date 2018年3月27日
	* @version 1.0
	* @description 查询用户是否存在未完成的相同服务的工单
	*/
	public List<Map<String, Object>> getList(WorkOrder workOrder2) {
		return workOrderMapper.getList(workOrder2);
	}

	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param workOrder
	* @return 
	* @date 2018年3月28日
	* @version 1.0
	* @description 添加工单
	*/
	public int insert(WorkOrder workOrder) {
		return workOrderMapper.insert(workOrder);
	}

	public List<Map<String, Object>> getListByUserId(WorkOrder workOrder) {
		return workOrderMapper.getListByUserId(workOrder);
	}
	
	
	public Map<String, Object> getWorkOrderDetail(WorkOrder workOrder){
		return workOrderMapper.getWorkOrderDetail(workOrder);
	}
	
	public int updateUserWorkOrder(WorkOrder workOrder){
		return workOrderMapper.update(workOrder);
	}
}
