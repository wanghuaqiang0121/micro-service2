package org.service.task.delay.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.service.task.delay.dao.ServiceTimeoutMapper;
import org.service.task.delay.entity.service.ServiceTimeout;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月7日
 * @description: 超时
 */
@Service
public class ServiceTimeoutService {
	@Resource
	private ServiceTimeoutMapper serviceTimeoutMapper;

	public Map<String, Object> getAppointment(ServiceTimeout serviceTimeout) {
		return serviceTimeoutMapper.getAppointment(serviceTimeout);
	}

	public Map<String, Object> getInquiry(ServiceTimeout serviceTimeout) {
		return serviceTimeoutMapper.getInquiry(serviceTimeout);
	}

	public int updateAppointment(ServiceTimeout serviceTimeout) {
		return serviceTimeoutMapper.updateAppointment(serviceTimeout);
	}

	public int deductService(Map<String, Object> appointmentMap) {
		return serviceTimeoutMapper.deductService(appointmentMap);
	}

	public int closeInquiry(ServiceTimeout serviceTimeout) {
		return serviceTimeoutMapper.closeInquiry(serviceTimeout);
	}
}