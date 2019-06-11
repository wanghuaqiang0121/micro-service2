package org.web.module.organization.controller.doctor;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.user.DoctorInfo;
import org.web.module.organization.domain.user.DoctorOrganizationDepartmentDuty;
import org.web.module.organization.domain.user.OrganizationUser;
import org.web.module.organization.global.BaseGlobal;
import org.web.module.organization.global.BaseGlobalEnum;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.user.DoctorInfoService;
import org.web.module.organization.service.user.DoctorOrganizationDepartmentDutyService;
import org.web.module.organization.service.user.OrganizationUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年10月11日
 * @description: 医生机构部门关联表
 */
@RestController
public class DoctorOrganizationDepartmentRelationController {
	@Resource
	private DoctorOrganizationDepartmentDutyService doctorOrganizationDepartmentDutyService;
	@Resource
	private OrganizationUserService organizationUserService;
	@Resource
	private DoctorInfoService doctorInfoService;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 新增关联
	 */
	@RequiresAuthentication(value = { "web-module-organization:doctor-organization-department-duty:insert" }, level = Level.OPERATION)
	@PostMapping(value = { "/doctor/organization/department/duty" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		/*设置默认值*/
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		doctorOrganizationDepartmentDuty.setIsManager(false);
		doctorOrganizationDepartmentDuty.setIsLocal(true);
		/*判断数据是否重复*/
		Map<String, Object> DoctorOrganizationDepartmentDutyMap = doctorOrganizationDepartmentDutyService.getRepeat(doctorOrganizationDepartmentDuty);
		if (DoctorOrganizationDepartmentDutyMap != null && !DoctorOrganizationDepartmentDutyMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.user.organization.is.exists"));
		}
		if (doctorOrganizationDepartmentDutyService.insert(doctorOrganizationDepartmentDuty) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年12月4日
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @param organizationId
	 * @return
	 * @description: 新增机构管理员是添加
	 */
	@RequiresAuthentication(value = { "web-module-organization:doctor-organization-department-duty:insert-manager" },level = Level.OPERATION)
	@PostMapping(value = { "/doctor/organization/department/duty/insert/manager" })
	public JsonApi insertManager(@RequestBody @Validated({ BaseEntity.Insert.class }) DoctorOrganizationDepartmentDuty  doctorOrganizationDepartmentDuty,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId){
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		doctorOrganizationDepartmentDuty.setIsManager(true);
		doctorOrganizationDepartmentDuty.setIsLocal(true);
		doctorOrganizationDepartmentDuty.setStatus(BaseGlobalEnum.Organization.ENABLE.getValue());
		Map<String, Object>  resultMap=doctorOrganizationDepartmentDutyService.getRepeat(doctorOrganizationDepartmentDuty);
		if (resultMap!=null && !resultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.user.organization.is.exists"));
		}
		if (doctorOrganizationDepartmentDutyService.insert(doctorOrganizationDepartmentDuty)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}	
	
	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationUserId
	 * @param organizationIdd
	 * @param departmentId
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 删除关联
	 */
	@RequiresAuthentication(value = { "web-module-organization:doctor-organization-department-duty:delete" }, level = Level.OPERATION)
	@DeleteMapping(value = { "/doctor/{organizationUserId}/organization/{organizationId}/department/{departmentId}/duty" })
	public JsonApi delete(@PathVariable("organizationUserId") Integer organizationUserId, @PathVariable("organizationId") Integer organizationIdd, @PathVariable("departmentId") Integer departmentId,
			@Validated({ BaseEntity.Delete.class }) DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		/* 设置数据 */
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		doctorOrganizationDepartmentDuty.setOrganizationUserId(organizationUserId);
		doctorOrganizationDepartmentDuty.setDepartmentId(departmentId);
		if (doctorOrganizationDepartmentDutyService.delete(doctorOrganizationDepartmentDuty) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 关联列表
	 */
	@RequiresAuthentication(value = { "web-module-organization:doctor-organization-department-duty:get-list" }, level = Level.OPERATION)
	@GetMapping(value = { "/doctor/organization/department/dutys" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(doctorOrganizationDepartmentDuty.getPage(), doctorOrganizationDepartmentDuty.getPageSize());
		List<Map<String, Object>> doctorOrganizationDepartmentDutyList = doctorOrganizationDepartmentDutyService.getList(doctorOrganizationDepartmentDuty);
		if (doctorOrganizationDepartmentDutyList != null && !doctorOrganizationDepartmentDutyList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), doctorOrganizationDepartmentDutyList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 获取机构成员列表
	 */
	@RequiresAuthentication(value = { "web-module-organization:doctor-organization-department-duty:get-member-list" }, level = Level.OPERATION)
	@GetMapping(value = { "/doctor/organization/department/duty/members" })
	public JsonApi getMemberList(@Validated({ BaseEntity.SelectAll.class }) DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(doctorOrganizationDepartmentDuty.getPage(), doctorOrganizationDepartmentDuty.getPageSize());
		List<Map<String, Object>> doctorOrganizationDepartmentDutyList = doctorOrganizationDepartmentDutyService.getOrganizationMemberList(doctorOrganizationDepartmentDuty);
		if (doctorOrganizationDepartmentDutyList != null && !doctorOrganizationDepartmentDutyList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), doctorOrganizationDepartmentDutyList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationTeamId
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 查询团队没有的成员列表
	 */
	@RequiresAuthentication(value = { "web-module-organization:doctor-organization-department-duty:get-member-null-list" }, level = Level.OPERATION)
	@GetMapping(value = { "/doctor/organization/department/duty/{organizationTeamId}/members" })
	public JsonApi getMemberIsNullList(@PathVariable("organizationTeamId") Integer organizationTeamId,
			@Validated({ BaseEntity.SelectAll.class }) DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		doctorOrganizationDepartmentDuty.setOrganizationTeamId(organizationTeamId);
		Page<?> page = PageHelper.startPage(doctorOrganizationDepartmentDuty.getPage(), doctorOrganizationDepartmentDuty.getPageSize());
		List<Map<String, Object>> doctorOrganizationDepartmentDutyList = doctorOrganizationDepartmentDutyService.getOrganizationMemberIsNullList(doctorOrganizationDepartmentDuty);
		if (doctorOrganizationDepartmentDutyList != null && !doctorOrganizationDepartmentDutyList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), doctorOrganizationDepartmentDutyList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改关联
	 */
	@RequiresAuthentication(value = { "web-module-organization:doctor-organization-department-duty:update" }, level = Level.OPERATION)
	@PutMapping(value = { "/doctor/organization/department/duty/{id}" })
	@Transactional
	public JsonApi update(@PathVariable("id") Integer id, @RequestBody @Validated({ BaseEntity.Update.class }) DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty,
			BindingResult result) {
		doctorOrganizationDepartmentDuty.setId(id);
		/* 查看数据是否存在 */
		Map<String, Object> doctorOrganizationDepartmentDutyMap = doctorOrganizationDepartmentDutyService.getDoctorOrganizationDepartmentDutyOne(doctorOrganizationDepartmentDuty);
		if (doctorOrganizationDepartmentDutyMap == null || doctorOrganizationDepartmentDutyMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("doctor.organization.department.duty.not.found"));
		}
		/* 设置机构用户id */
		Integer organizationUserId = (Integer) doctorOrganizationDepartmentDutyMap.get("organizationUserId");
		OrganizationUser organizationUser = doctorOrganizationDepartmentDuty.getOrganizationUser();
		if (null == organizationUser) {
			organizationUser = new OrganizationUser();
		}
		/* 不能修改身份证和账号 */
		organizationUser.setAccount(null);
		organizationUser.setId(organizationUserId);
		Map<String, Object> organizationUserMap = organizationUserService.getOne(organizationUser);
		if (organizationUserMap == null || organizationUserMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("organization.user.one.not.found"));
		}
		Integer doctorInfoId = (Integer) organizationUserMap.get("doctorInfoId");
		/* 判断是否需要修改执行证号 */
		DoctorInfo doctorInfo = doctorOrganizationDepartmentDuty.getDoctorInfo();
		if (null == doctorInfo) {
			doctorInfo = new DoctorInfo();
		}
		doctorInfo.setId(doctorInfoId);
		String certification = doctorInfo.getCertification();
		if (null != certification && !"".equals(certification)) {
			Map<String, Object> doctorInfoMap = doctorInfoService.getRepeat(doctorInfo);
			if (doctorInfoMap != null && !doctorInfoMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("doctor.info.is.conflict"));
			}
		}
		/* 修改数据 */
		if (doctorOrganizationDepartmentDutyService.update(doctorOrganizationDepartmentDuty) > 0) {
			if (organizationUserService.update(organizationUser) > 0) {
				if (doctorInfoService.update(doctorInfo) > 0) {
					return new JsonApi(ApiCodeEnum.OK);
				}
				throw new RuntimeException();
			}
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年11月15日
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @param organizationId
	 * @return{@link JsonApi}
	 * @description: 查询在该机构下的用户并且不在某个团队
	 */
	@RequiresAuthentication(value = { "web-module-organization:doctor-organization-department-duty:get-organization-member-not-in-this-team" },level = Level.OPERATION)
	@GetMapping(value = { "/doctor/organization/department/duty/not/team/members" })
	public JsonApi getOrganizationUserNotTeamMember(@Validated({ DoctorOrganizationDepartmentDuty.GetOrganizationUserNotTeamMember.class }) DoctorOrganizationDepartmentDuty  doctorOrganizationDepartmentDuty,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId){
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(doctorOrganizationDepartmentDuty.getPage(), doctorOrganizationDepartmentDuty.getPageSize());
		List<Map<String, Object>>  doctorOrganizationDepartmentDutyList=doctorOrganizationDepartmentDutyService.getOrganizationUserNotTeamMember(doctorOrganizationDepartmentDuty);
		if (doctorOrganizationDepartmentDutyList!=null && !doctorOrganizationDepartmentDutyList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), doctorOrganizationDepartmentDutyList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年11月15日
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @param organizationId
	 * @return{@link JsonApi}
	 * @description: 添加外部机构成员
	 */
	@RequiresAuthentication(value = { "web-module-organization:doctor-organization-department-duty:insert-external" },level = Level.OPERATION)
	@PostMapping(value = { "/doctor/organization/department/duty/external" })
	public JsonApi insertExternal(@RequestBody @Validated({ BaseEntity.Insert.class }) DoctorOrganizationDepartmentDuty  doctorOrganizationDepartmentDuty,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId
			){
		/*设置默认值*/
		doctorOrganizationDepartmentDuty.setOrganizationUser(doctorOrganizationDepartmentDuty.getOrganizationUser());
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		doctorOrganizationDepartmentDuty.setIsManager(false);
		doctorOrganizationDepartmentDuty.setIsLocal(false);
		doctorOrganizationDepartmentDuty.setStatus(BaseGlobalEnum.OrganizationUser.ENABLE.getValue());
		/*判读数据是否存在*/
		Map<String, Object>  DoctorOrganizationDepartmentDutyMap=doctorOrganizationDepartmentDutyService.getRepeat(doctorOrganizationDepartmentDuty);
		if (DoctorOrganizationDepartmentDutyMap!=null && !DoctorOrganizationDepartmentDutyMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.user.organization.is.exists"));
		}
		if (doctorOrganizationDepartmentDutyService.insert(doctorOrganizationDepartmentDuty)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年11月15日
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @param organizationId
	 * @return{@link JsonApi}
	 * @description: 不在该机构下的成员列表
	 */
	@RequiresAuthentication(value = { "web-module-organization:doctor-organization-department-duty:get-organization-not-member" },level = Level.OPERATION)
	@GetMapping(value = { "/doctor/organization/department/duty/not/members" })
	public JsonApi getOrganizationNotMemberList(@Validated({ BaseEntity.SelectAll.class }) DoctorOrganizationDepartmentDuty  doctorOrganizationDepartmentDuty,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId){
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(doctorOrganizationDepartmentDuty.getPage(), doctorOrganizationDepartmentDuty.getPageSize());
		List<Map<String, Object>>  doctorOrganizationDepartmentDutyList=doctorOrganizationDepartmentDutyService.getOrganizationNotMember(doctorOrganizationDepartmentDuty);
		if (doctorOrganizationDepartmentDutyList!=null && !doctorOrganizationDepartmentDutyList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), doctorOrganizationDepartmentDutyList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月5日
	 * @param organizationUserId
	 * @param doctorOrganizationDepartmentDuty
	 * @param result
	 * @return
	 * @description: 查询管理员关联信息
	 */
	@RequiresAuthentication(ignore = true, value = { "web-module-organization:doctor-organization-department-duty:get-manager-detail" },level = Level.OPERATION)
	@GetMapping(value = { "/doctor/organization/department/duty/{organizationUserId}"})
	public JsonApi getManagerDetail(@PathVariable("organizationUserId") Integer organizationUserId, @Validated({ BaseEntity.SelectOne.class }) DoctorOrganizationDepartmentDuty  doctorOrganizationDepartmentDuty,BindingResult result){
		doctorOrganizationDepartmentDuty.setOrganizationUserId(organizationUserId);
		Map<String, Object>  resultMap = doctorOrganizationDepartmentDutyService.getOne(doctorOrganizationDepartmentDuty);
		if (resultMap!=null && !resultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,resultMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}	
	
	
}
