package org.web.module.organization.dao;

import java.util.Map;

import org.web.module.organization.domain.HospitalDepartment;
import org.service.core.dao.IBaseMapper;

public interface HospitalDepartmentMapper extends IBaseMapper<HospitalDepartment> {

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param hospitalDepartment
	 * @return
	 * @description: 通过机构ID查询医疗机构科室信息
	 */
	Map<String, Object> getMedicalOrganizationInfo(HospitalDepartment hospitalDepartment);
}