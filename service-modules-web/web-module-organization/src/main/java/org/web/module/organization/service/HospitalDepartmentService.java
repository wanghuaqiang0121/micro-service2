package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.HospitalDepartmentMapper;
import org.web.module.organization.domain.HospitalDepartment;

@Service
public class HospitalDepartmentService {

	@Resource
	private HospitalDepartmentMapper hospitalDepartmentMapper;

	public int insert(HospitalDepartment hospitalDepartment) {
		return hospitalDepartmentMapper.insert(hospitalDepartment);
	}

	public Map<String, Object> getOne(HospitalDepartment hospitalDepartment) {
		return hospitalDepartmentMapper.getOne(hospitalDepartment);
	}

	public int update(HospitalDepartment hospitalDepartment) {
		return hospitalDepartmentMapper.update(hospitalDepartment);
	}

	public int delete(HospitalDepartment hospitalDepartment) {
		return hospitalDepartmentMapper.delete(hospitalDepartment);
	}

	public List<Map<String, Object>> getList(HospitalDepartment hospitalDepartment) {
		return hospitalDepartmentMapper.getList(hospitalDepartment);
	}

	public Map<String, Object> getRepeat(HospitalDepartment hospitalDepartment) {
		return hospitalDepartmentMapper.getRepeat(hospitalDepartment);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param hospitalDepartment
	 * @return
	 * @description: 通过机构ID查询医疗机构科室信息
	 */
	public Map<String, Object> getMedicalOrganizationInfo(HospitalDepartment hospitalDepartment) {
		return hospitalDepartmentMapper.getMedicalOrganizationInfo(hospitalDepartment);
	}

}
