package org.web.module.organization.service.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.user.DoctorOrganizationDepartmentDutyMapper;
import org.web.module.organization.domain.user.DoctorOrganizationDepartmentDuty;

@Service
public class DoctorOrganizationDepartmentDutyService {
	
	@Resource
	private DoctorOrganizationDepartmentDutyMapper mapper;
	
	
	public int insert(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty){
		return mapper.insert(doctorOrganizationDepartmentDuty);
	}
	
	public int update(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty){
		return mapper.update(doctorOrganizationDepartmentDuty);
	}
	
	public Map<String, Object> getOne(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty) {
		return mapper.getOne(doctorOrganizationDepartmentDuty);
	}
	
	
	public int delete(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty){
		return mapper.delete(doctorOrganizationDepartmentDuty);
	}
	

	public	Map<String, Object> getRepeat(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty) {
		return mapper.getRepeat(doctorOrganizationDepartmentDuty);
	}
	
	
	public	List<Map<String, Object>> getList(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty){
		return mapper.getList(doctorOrganizationDepartmentDuty);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param doctorOrganizationDepartmentDuty
	 * @return
	 * @description: 机构的成员列表
	 */
	public	List<Map<String, Object>> getOrganizationMemberList(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty){
		return mapper.getOrganizationMember(doctorOrganizationDepartmentDuty);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param doctorOrganizationDepartmentDuty
	 * @return
	 * @description: 团队没有的机构成员列表
	 */
	public List<Map<String, Object>> getOrganizationMemberIsNullList(
			DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty) {
		return mapper.getOrganizationMemberIsNullList(doctorOrganizationDepartmentDuty);
	}



	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param doctorOrganizationDepartmentDuty
	 * @return
	 * @description: 查询详情
	 */
	public Map<String, Object> getDoctorOrganizationDepartmentDutyOne(
			DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty) {
		return mapper.getDoctorOrganizationDepartmentDutyOne(doctorOrganizationDepartmentDuty);
	}
	
	 public  List<Map<String, Object>> getOrganizationNotMember(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty){
		 return mapper.getOrganizationNotMember(doctorOrganizationDepartmentDuty);
	 }
		
		
		public  List<Map<String, Object>> getOrganizationUserNotTeamMember(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty){
			 return mapper.getOrganizationUserNotTeamMember(doctorOrganizationDepartmentDuty);
		}
		  public int updateAuthorizeAptitude(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty) {
			  return mapper.updateAuthorizeAptitude(doctorOrganizationDepartmentDuty);
		  }
}
