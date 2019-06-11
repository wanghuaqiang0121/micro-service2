package org.wechat.module.service.service.bespeak;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.bespeak.TeamAppointmentConfigureMapper;
import org.wechat.module.service.domain.bespeak.TeamAppointmentConfigure;

@Service
public class TeamAppointmentConfigureService {

	@Resource
	private TeamAppointmentConfigureMapper teamAppointmentConfigureMapper;

	public List<Map<String, Object>> getList(TeamAppointmentConfigure teamAppointmentConfigure) {
		return teamAppointmentConfigureMapper.getList(teamAppointmentConfigure);
	}

	public Map<String, Object> getOne(TeamAppointmentConfigure teamAppointmentConfigure) {
		return teamAppointmentConfigureMapper.getOne(teamAppointmentConfigure);
	}

	public List<Map<String, Object>> getAppointmentDetail(TeamAppointmentConfigure teamAppointmentConfigure) {
		return teamAppointmentConfigureMapper.getAppointmentDetail(teamAppointmentConfigure);
	}
	
}
