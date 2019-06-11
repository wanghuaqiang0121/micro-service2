package org.web.module.organization.user.sevice;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.user.dao.DoctorDoctorSkillMapper;
import org.web.module.organization.user.domain.DoctorDoctorSkill;

@Service
public class DoctorDoctorSkillService {

	@Resource
	private DoctorDoctorSkillMapper mapper;

	public int insert(DoctorDoctorSkill doctorDoctorSkill) {
		return mapper.insert(doctorDoctorSkill);
	}

	public int deleteByDoctorInfoId(DoctorDoctorSkill doctorDoctorSkillDelete) {
		return mapper.deleteByDoctorInfoId(doctorDoctorSkillDelete);
	}
	
}
