package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.OrganizationConsultingRoomMapper;
import org.web.module.height.obesity.entity.OrganizationConsultingRoom;

@Service
public class OrganizationConsultingRoomService {

	@Resource
	private OrganizationConsultingRoomMapper mapper;

	public Map<String, Object> getOne(OrganizationConsultingRoom organizationConsultingRoom) {
		return mapper.getOne(organizationConsultingRoom);
	}

	public int insert(OrganizationConsultingRoom organizationConsultingRoom) {
		return mapper.insert(organizationConsultingRoom);
	}

	public int update(OrganizationConsultingRoom organizationConsultingRoom) {
		return mapper.update(organizationConsultingRoom);
	}

	public List<Map<String, Object>> getList(OrganizationConsultingRoom organizationConsultingRoom) {
		return mapper.getList(organizationConsultingRoom);
	}
	
}
