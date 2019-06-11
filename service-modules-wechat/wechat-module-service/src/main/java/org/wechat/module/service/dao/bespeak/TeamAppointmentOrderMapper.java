package org.wechat.module.service.dao.bespeak;

import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.service.domain.bespeak.TeamAppointmentOrder;

public interface TeamAppointmentOrderMapper extends IBaseMapper<TeamAppointmentOrder> {
 

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月18日
	 * @param teamAppointmentOrder
	 * @return
	 * @description: 取消订单
	 */
	int cancelOrder(TeamAppointmentOrder teamAppointmentOrder);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月18日
	 * @param teamAppointmentOrder
	 * @return
	 * @description: 查询预约号
	 */
	Map<String, Object> getAppointmentNo(TeamAppointmentOrder teamAppointmentOrder);
}