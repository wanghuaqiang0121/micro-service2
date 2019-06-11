package org.web.module.bone.age.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.OrganizationChoosedReadFilmDoctorMapper;
import org.web.module.bone.age.domain.OrganizationChoosedReadFilmDoctor;

@Service
public class OrganizationChoosedReadFilmDoctorService {
	@Resource
	private OrganizationChoosedReadFilmDoctorMapper  organizationChoosedReadFilmDoctorMapper;
	
	public int insert(OrganizationChoosedReadFilmDoctor organizationChoosedReadFilmDoctor) {
		return organizationChoosedReadFilmDoctorMapper.insert(organizationChoosedReadFilmDoctor);
	}
	public Map<String, Object> getOne(OrganizationChoosedReadFilmDoctor organizationChoosedReadFilmDoctor) {
		return organizationChoosedReadFilmDoctorMapper.getOne(organizationChoosedReadFilmDoctor);
	}
	
	public Map<String, Object> getRepeat(OrganizationChoosedReadFilmDoctor organizationChoosedReadFilmDoctor){
		return organizationChoosedReadFilmDoctorMapper.getRepeat(organizationChoosedReadFilmDoctor);
	}
	public List<Map<String, Object>> getList(OrganizationChoosedReadFilmDoctor organizationChoosedReadFilmDoctor){
		return organizationChoosedReadFilmDoctorMapper.getList(organizationChoosedReadFilmDoctor);
	}
	
	public List<Map<String, Object>> getReadFilmDoctorPriceList(OrganizationChoosedReadFilmDoctor organizationChoosedReadFilmDoctor){
		return organizationChoosedReadFilmDoctorMapper.getReadFilmDoctorPriceList(organizationChoosedReadFilmDoctor);
	}
	public int update(OrganizationChoosedReadFilmDoctor organizationChoosedReadFilmDoctor) {
		return organizationChoosedReadFilmDoctorMapper.update(organizationChoosedReadFilmDoctor);
	}

}
