package org.web.module.organization.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.MedicalOrganizationInfoMapper;
import org.web.module.organization.domain.MedicalOrganizationInfo;

@Service
public class MedicalOrganizationInfoService {
	
	@Resource
	private MedicalOrganizationInfoMapper medicalOrganizationInfoMapper;
	
	public int insert(MedicalOrganizationInfo medicalOrganizationInfo){
		return medicalOrganizationInfoMapper.insert(medicalOrganizationInfo);
	}
	
	public int update(MedicalOrganizationInfo medicalOrganizationInfo){
		return medicalOrganizationInfoMapper.update(medicalOrganizationInfo);
	}
	
	public Map<String, Object> getDetail(MedicalOrganizationInfo medicalOrganizationInfo){
		return medicalOrganizationInfoMapper.getOne(medicalOrganizationInfo);
	}
	
	public Map<String, Object> getRepeat(MedicalOrganizationInfo medicalOrganizationInfo){
		return medicalOrganizationInfoMapper.getRepeat(medicalOrganizationInfo);
	}

}
