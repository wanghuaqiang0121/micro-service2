package org.web.module.organization.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.user.domain.DoctorDoctorSkill;
import org.web.module.organization.user.domain.DoctorInfo;
import org.web.module.organization.user.domain.DoctorSkill;
import org.web.module.organization.user.message.Prompt;
import org.web.module.organization.user.sevice.DoctorDoctorSkillService;
import org.web.module.organization.user.sevice.DoctorInfoService;
import org.web.module.organization.user.sevice.DoctorSkillService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年10月11日
 * @description: 医生信息
 */
@RestController
public class DoctorInfoController {

	@Resource
	private DoctorInfoService doctorInfoService;
	@Resource
	private DoctorSkillService doctorSkillService;
	@Resource
	private DoctorDoctorSkillService doctorDoctorSkillService;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param doctorInfo
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 获取医生信息
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:doctor-info:get-detail" }, level = Level.OPERATION)
	@GetMapping(value = { "/doctor/info/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) DoctorInfo doctorInfo, BindingResult result) {
		/* 设置主键 */
		doctorInfo.setOrganizationUserId(id);
		Map<String, Object> doctorInfoMap = doctorInfoService.getOne(doctorInfo);
		if (doctorInfoMap != null && !doctorInfoMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, doctorInfoMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);

	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月19日
	 * @param doctorInfo
	 * @param result
	 * @return
	 * @description: 新增医生信息
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:doctor-info:insert" }, level = Level.OPERATION)
	@PostMapping(value = { "/doctor/info" })
	@Transactional(propagation = Propagation.REQUIRED)
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody DoctorInfo doctorInfo, BindingResult result) {
		doctorInfo.setCreateDate(new Date());
		/* 判断执行证号是否重复 */
		Map<String, Object> doctorInfoMap = doctorInfoService.getRepeat(doctorInfo);
		if (doctorInfoMap != null && !doctorInfoMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("doctorInfo.certification.is.exists"));
		}
		/* 医生擅长领域 */
		List<DoctorSkill> doctorSkillList = doctorInfo.getDoctorSkills();
		/* 新增医生信息 */
		if (doctorInfoService.insert(doctorInfo) > 0) {
			/* 新增医生级别类别 */
			Map<String, Object> doctorSkillResultMap = null;
			/* 新增医生擅长领域 */
			if (doctorSkillList != null && !doctorSkillList.isEmpty()) {
				for (DoctorSkill doctorSkill : doctorSkillList) {
					/* 医生擅长领域是否存在 */
					doctorSkillResultMap = doctorSkillService.getOne(doctorSkill);
					/* 不存在 新增 */
					if (doctorSkillResultMap == null || doctorSkillResultMap.isEmpty()) {
						doctorSkill.setCreateDate(new Date());
						/* 新增医生擅长领域 */
						if (doctorSkillService.insert(doctorSkill) > 0) {
							/* 新增医生擅长领域关联 */
							DoctorDoctorSkill doctorDoctorSkill = new DoctorDoctorSkill();
							doctorDoctorSkill.setDoctorSkillId(doctorSkill.getId());
							doctorDoctorSkill.setDoctorInfoId(doctorInfo.getId());
							if (doctorDoctorSkillService.insert(doctorDoctorSkill) <= 0) {
								throw new RuntimeException();
							}
						} else {
							throw new RuntimeException();
						}
					} else {
						/* 医生擅长领域存在 */
						/* 新增医生擅长领域关联 */
						DoctorDoctorSkill doctorDoctorSkill = new DoctorDoctorSkill();
						doctorDoctorSkill.setDoctorSkillId(Integer.parseInt(doctorSkillResultMap.get("id").toString()));
						doctorDoctorSkill.setDoctorInfoId(doctorInfo.getId());
						if (doctorDoctorSkillService.insert(doctorDoctorSkill) <= 0) {
							throw new RuntimeException();
						}
					}
				}
			}
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param doctorInfo
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改医生信息
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:doctor-info:update" }, level = Level.OPERATION)
	@PutMapping(value = { "/doctor/info/{id}" })
	@Transactional
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody DoctorInfo doctorInfo, BindingResult result) {
		doctorInfo.setId(id);
		if (null != doctorInfo.getCertification()) {
			/* 判断执行证号是否重复 */
			Map<String, Object> doctorInfoMap = doctorInfoService.getRepeat(doctorInfo);
			if (doctorInfoMap != null && !doctorInfoMap.isEmpty()) {
				Integer doctorId = (Integer) doctorInfoMap.get("id");
				if (id.intValue() != doctorId.intValue()) {
					return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("doctorInfo.certification.is.exists"));
				}
			}
		}
		/* 医生擅长领域 */
		List<DoctorSkill> doctorSkillList = doctorInfo.getDoctorSkills();
		/* 修改数据 */
		if (doctorInfoService.update(doctorInfo) > 0) {
			/* 删除医生擅长领域关联 */
			DoctorDoctorSkill doctorDoctorSkillDelete = new DoctorDoctorSkill();
			doctorDoctorSkillDelete.setDoctorInfoId(doctorInfo.getId());
			doctorDoctorSkillService.deleteByDoctorInfoId(doctorDoctorSkillDelete);
			Map<String, Object> doctorSkillResultMap = null;
			/* 新增医生擅长领域 */
			if (doctorSkillList != null && !doctorSkillList.isEmpty()) {
				for (DoctorSkill doctorSkill : doctorSkillList) {
					/* 医生擅长领域是否存在 */
					doctorSkillResultMap = doctorSkillService.getOne(doctorSkill);
					/* 不存在 则新增 */
					if (doctorSkillResultMap == null || doctorSkillResultMap.isEmpty()) {
						/* 新增医生擅长领域 */
						doctorSkill.setCreateDate(new Date());
						if (doctorSkillService.insert(doctorSkill) > 0) {
							/* 新增医生擅长领域关联 */
							DoctorDoctorSkill doctorDoctorSkill = new DoctorDoctorSkill();
							doctorDoctorSkill.setDoctorSkillId(doctorSkill.getId());
							doctorDoctorSkill.setDoctorInfoId(doctorInfo.getId());
							if (doctorDoctorSkillService.insert(doctorDoctorSkill) <= 0) {
								throw new RuntimeException();
							}
						} else {
							throw new RuntimeException();
						}
					} else {/* 医生擅长领域存在 */
						/* 新增医生擅长领域关联 */
						DoctorDoctorSkill doctorDoctorSkill = new DoctorDoctorSkill();
						doctorDoctorSkill.setDoctorSkillId(Integer.parseInt(doctorSkillResultMap.get("id").toString()));
						doctorDoctorSkill.setDoctorInfoId(doctorInfo.getId());
						if (doctorDoctorSkillService.insert(doctorDoctorSkill) <= 0) {
							throw new RuntimeException();
						}
					}
				}
				return new JsonApi(ApiCodeEnum.OK);
			}
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年11月8日
	 * @param doctorInfo
	 * @param result
	 * @return
	 * @description:  验证执行证号重复
	 *  // TODO 待添加到新项目
	 */
	@RequiresAuthentication(value = { "organization:configure:doctorInfo:validCertificationRepeat" },level=Level.OPERATION)
	@GetMapping(value = { "/doctor/info/valid/certification" })
	public JsonApi validCertificationRepeat(
			@Validated({ DoctorInfo.ValidCertificationRepeat.class }) DoctorInfo  doctorInfo,BindingResult result){
		/*判断执行证号是否重复*/
		Map<String, Object> doctorInfoMap = doctorInfoService.getRepeat(doctorInfo);
		Map<String, Object> resultMap = new HashMap<>(); 
		if (doctorInfoMap!=null && !doctorInfoMap.isEmpty()) {
			resultMap.put("isRepeat",true);
			return new JsonApi(ApiCodeEnum.OK,resultMap).setMsg(Prompt.bundle("doctorInfo.certification.is.exists"));
		}
		resultMap.put("isRepeat",false);
		return new JsonApi(ApiCodeEnum.OK,resultMap);
	}
}
