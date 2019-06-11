package org.wechat.module.service.dao.bespeak;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.service.domain.bespeak.TeamAppointmentConfigure;

public interface TeamAppointmentConfigureMapper extends IBaseMapper<TeamAppointmentConfigure> {
  

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月18日
	 * @param teamAppointmentConfigure
	 * @return
	 * @description: 是否可预约详情
	 */
	List<Map<String, Object>> getAppointmentDetail(TeamAppointmentConfigure teamAppointmentConfigure);
}