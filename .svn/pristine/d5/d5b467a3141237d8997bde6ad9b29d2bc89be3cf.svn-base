package org.web.module.organization.dao.user;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.domain.user.DoctorOrganizationDepartmentDuty;

public interface DoctorOrganizationDepartmentDutyMapper extends IBaseMapper<DoctorOrganizationDepartmentDuty> {
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param doctorOrganizationDepartmentDuty
	 * @return
	 * @description: 机构的成员列表
	 */
	public	List<Map<String, Object>> getOrganizationMember(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty);
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param doctorOrganizationDepartmentDuty
	 * @return
	 * @description: 团队没有的机构成员列表
	 */
	public List<Map<String, Object>> getOrganizationMemberIsNullList(
			DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty);
	

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param doctorOrganizationDepartmentDuty
	 * @return
	 * @description: 查询详情
	 */
	public Map<String, Object> getDoctorOrganizationDepartmentDutyOne(
			DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty);
	
   public  List<Map<String, Object>> getOrganizationNotMember(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty);
	
	
	public  List<Map<String, Object>> getOrganizationUserNotTeamMember(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty);
	
   public int updateAuthorizeAptitude(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty);

}
