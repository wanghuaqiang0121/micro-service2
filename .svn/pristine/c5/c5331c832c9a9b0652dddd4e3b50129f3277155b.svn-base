package org.web.module.organization.user.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.organization.user.domain.DoctorOrganizationDepartmentDuty;

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
	
	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param doctorOrganizationDepartmentDuty
	 * @return
	 * @date 2018年11月14日
	 * @version 1.0
	 * @description 不在该机构下的成员
	 */
	public  List<Map<String, Object>> getOrganizationNotMember(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty);
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年11月14日
	 * @param doctorOrganizationDepartmentDuty
	 * @return
	 * @description: 查询在该机构下的用户并且不在某个团队
	 */
	public  List<Map<String, Object>> getOrganizationUserNotTeamMember(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty);

}
