package org.wechat.module.service.service.bespeak;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.bespeak.TeamAppointmentOrderMapper;
import org.wechat.module.service.domain.bespeak.TeamAppointmentOrder;

@Service
public class TeamAppointmentOrderService {
	@Resource
	private TeamAppointmentOrderMapper teamAppointmentOrderMapper;
	
	public int insert(TeamAppointmentOrder teamAppointmentOrder) {
		return teamAppointmentOrderMapper.insert(teamAppointmentOrder);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月18日
	 * @param teamAppointmentOrder
	 * @return
	 * @description: 取消订单
	 */
	public int cancelOrder(TeamAppointmentOrder teamAppointmentOrder) {
		return teamAppointmentOrderMapper.cancelOrder(teamAppointmentOrder);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月18日
	 * @param teamAppointmentOrder
	 * @return
	 * @description: 查询预约号
	 */
	public Map<String, Object> getAppointmentNo(TeamAppointmentOrder teamAppointmentOrder) {
		return teamAppointmentOrderMapper.getAppointmentNo(teamAppointmentOrder);
	}
	

	public Map<String, Object> getOne(TeamAppointmentOrder teamAppointmentOrder) {
		return teamAppointmentOrderMapper.getOne(teamAppointmentOrder);
	}

	public List<Map<String, Object>> getList(TeamAppointmentOrder teamAppointmentOrder) {
		return teamAppointmentOrderMapper.getList(teamAppointmentOrder);
	}

}
