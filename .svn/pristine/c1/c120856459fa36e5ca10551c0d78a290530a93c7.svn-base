package org.web.module.organization.user.sevice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.user.dao.DoctorInfoMapper;
import org.web.module.organization.user.domain.DoctorInfo;

@Service
public class DoctorInfoService {
	@Resource
	private DoctorInfoMapper doctorInfoMapper;
	
	public	int insert(DoctorInfo doctorInfo) {
		return doctorInfoMapper.insert(doctorInfo);

	}

	public	int update(DoctorInfo doctorInfo) {
		return doctorInfoMapper.update(doctorInfo);
	}

	public	int delete(DoctorInfo doctorInfo) {
		return doctorInfoMapper.delete(doctorInfo);
	}

	public	Map<String, Object> getRepeat(DoctorInfo doctorInfo) {
		return doctorInfoMapper.getRepeat(doctorInfo);
	}

	public	Map<String, Object> getOne(DoctorInfo doctorInfo) {
		return doctorInfoMapper.getOne(doctorInfo);
	}

	public	List<Map<String, Object>> getList(DoctorInfo doctorInfo){
		return doctorInfoMapper.getList(doctorInfo);
	}
}
