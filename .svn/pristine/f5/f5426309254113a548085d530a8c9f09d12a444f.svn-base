package org.wechat.module.user.controller.itv;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.user.domain.itv.DoctorOrganizationDepartmentDuty;
import org.wechat.module.user.service.itv.DoctorOrganizationDepartmentDutyService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 医生机构部门关联
 */
@RestController
public class DoctorOrganizationDepartmentDutyController {
	
	@Resource
	private DoctorOrganizationDepartmentDutyService doctorOrganizationDepartmentDutyService;

	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @return
	 * @description: 机构的医生列表
	 */
	@RequiresAuthentication(ignore = true)
	@GetMapping(value = { "/doctor/organization/department/duty/members" })
	public JsonApi getOrganizationMemberList(
			@Validated({ BaseEntity.SelectAll.class }) DoctorOrganizationDepartmentDuty  doctorOrganizationDepartmentDuty,BindingResult result){
		doctorOrganizationDepartmentDuty.setOrganizationId(doctorOrganizationDepartmentDuty.getOrganizationId());
		Page<?> page = PageHelper.startPage(doctorOrganizationDepartmentDuty.getPage(), doctorOrganizationDepartmentDuty.getPageSize());
		/*机构的医生列表*/ 
		List<Map<String, Object>>  doctorOrganizationDepartmentDutyList=doctorOrganizationDepartmentDutyService.getOrganizationMemberList(doctorOrganizationDepartmentDuty);
		if (doctorOrganizationDepartmentDutyList!=null && doctorOrganizationDepartmentDutyList.size()>0) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), doctorOrganizationDepartmentDutyList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	
	
}
