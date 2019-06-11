package org.web.module.organization.user.sevice;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.user.dao.DoctorSkillMapper;
import org.web.module.organization.user.domain.DoctorSkill;

@Service
public class DoctorSkillService {

	@Resource
	private DoctorSkillMapper mapper;

	public Map<String, Object> getOne(DoctorSkill doctorSkill) {
		return mapper.getOne(doctorSkill);
	}

	public int insert(DoctorSkill doctorSkill) {
		return mapper.insert(doctorSkill);
	}
	
}
