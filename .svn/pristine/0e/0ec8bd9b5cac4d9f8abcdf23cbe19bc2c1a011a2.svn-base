package org.service.task.delay.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.service.core.dao.IBaseMapper;
import org.service.task.delay.entity.service.ServiceTimeout;

@Mapper
public interface ServiceTimeoutMapper extends IBaseMapper<ServiceTimeout> {

	Map<String, Object> getInquiry(ServiceTimeout serviceTimeout);

	Map<String, Object> getAppointment(ServiceTimeout serviceTimeout);

	int updateAppointment(ServiceTimeout serviceTimeout);

	int deductService(Map<String, Object> appointmentMap);

	int closeInquiry(ServiceTimeout serviceTimeout);
}