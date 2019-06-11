package org.wechat.module.user.service.itv;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.itv.DoctorOrganizationDepartmentDutyMapper;
import org.wechat.module.user.domain.itv.DoctorOrganizationDepartmentDuty;

@Service
public class DoctorOrganizationDepartmentDutyService {

	@Resource
	private DoctorOrganizationDepartmentDutyMapper mapper;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param doctorOrganizationDepartmentDuty
	 * @return
	 * @description: 机构的医生列表
	 */
	public List<Map<String, Object>> getOrganizationMemberList(
			DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty) {
		return mapper.getOrganizationMemberList(doctorOrganizationDepartmentDuty);
	}

}
