package org.web.module.height.obesity.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.ChildrenFamily;
import org.web.module.height.obesity.entity.ChildrenMaternity;
import org.web.module.height.obesity.entity.Diagnosis;
import org.web.module.height.obesity.entity.User;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.message.Prompt;
import org.web.module.height.obesity.service.ChildrenFamilyService;
import org.web.module.height.obesity.service.ChildrenMaternityService;
import org.web.module.height.obesity.service.DiagnosisService;
import org.web.module.height.obesity.service.UserService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: WangHuaQiang
 * @date: 2018年12月18日
 * @description: 儿童家族信息
 */
@RestController
public class ChildrenFamilyController {
	@Resource
	private ChildrenFamilyService childrenFamilyService;
	@Resource
	private UserService userService;
	@Resource
	private ChildrenMaternityService childrenMaternityervice;
	@Resource
	private DiagnosisService diagnosisService;
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月18日
	 * @param childrenfamily
	 * @param resul
	 * @return
	 * @description: 添加儿童家族信息
	 */
	@RequiresAuthentication(ignore = false,value = { "web-module-height-obesity:children-family:insert" },level = Level.OPERATION)
	@PostMapping(value = { "/children/family/insert" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) ChildrenFamily childrenfamily,
			BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 判断儿童家族信息是否存在 */
		Map<String, Object> childrenFamilyMap = childrenFamilyService.getOne(childrenfamily);
		if (childrenFamilyMap != null) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		/* 判断用户是否存在 */
		User user = new User();
		user.setId(childrenfamily.getUserId());
		Map<String, Object> userMap = userService.getOne(user);
		if (userMap == null) {
			return new JsonApi(ApiCodeEnum.FAIL,Prompt.bundle("user.is.not.exists"));
		}
		/*  如果修改父亲身高或母亲身高  计算遗传身高   */
		if ((childrenfamily.getFatherHeight() != null || childrenfamily.getMotherHeight() != null) && userMap != null && userMap.get("sex") != null) {
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setSex((Integer)userMap.get("sex"));
			diagnosis.setMotherHeight(new BigDecimal(childrenfamily.getMotherHeight()).doubleValue());
			diagnosis.setFatherHeight(new BigDecimal(childrenfamily.getFatherHeight()).doubleValue());
			ChildrenMaternity childrenMaternity = new ChildrenMaternity();
			childrenMaternity.setUserId(childrenfamily.getUserId());
			childrenMaternity.setGeneticHeight(new BigDecimal(diagnosisService.getGeneticHeight(diagnosis)).floatValue());
			Map<String, Object>	map=childrenMaternityervice.getOne(childrenMaternity);
			if (map!=null) {
				if (childrenMaternityervice.update(childrenMaternity) < 1) {
					throw new RuntimeException();
				}
			}
		}
		childrenfamily.setCreateDateTime(new Date());
		if (childrenFamilyService.insert(childrenfamily) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月18日
	 * @param userId
	 * @param childrenfamily
	 * @param resul
	 * @return
	 * @description: 修改儿童家族信息
	 */
	@RequiresAuthentication(ignore = false,value = { "web-module-height-obesity:children-family:update" },level = Level.OPERATION)
	@PutMapping(value = { "/children/family/{userId}" })
	public JsonApi update(@PathVariable("userId") Integer userId,@RequestBody @Validated({ BaseEntity.Update.class }) ChildrenFamily childrenfamily,
			BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		childrenfamily.setUserId(userId);
		/* 判断儿童家族信息是否存在 */
		Map<String, Object> childrenFamilyMap = childrenFamilyService.getOne(childrenfamily);
		if (childrenFamilyMap == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 判断用户是否存在 */
		User user = new User();
		user.setId(userId);
		Map<String, Object> userMap = userService.getOne(user);
		if (userMap == null) {
			return new JsonApi(ApiCodeEnum.FAIL,Prompt.bundle("user.is.not.exists"));
		}
		if ((childrenfamily.getFatherHeight() != null || childrenfamily.getMotherHeight() != null) && userMap !=null && userMap.get("sex") != null) {
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setSex((Integer)userMap.get("sex"));
			diagnosis.setMotherHeight(new BigDecimal(childrenfamily.getMotherHeight()).doubleValue());
			diagnosis.setFatherHeight(new BigDecimal(childrenfamily.getFatherHeight()).doubleValue());
			ChildrenMaternity childrenMaternity = new ChildrenMaternity();
			childrenMaternity.setUserId(childrenfamily.getUserId());
			childrenMaternity.setGeneticHeight(new BigDecimal(diagnosisService.getGeneticHeight(diagnosis)).floatValue());
			Map<String, Object>	map=childrenMaternityervice.getOne(childrenMaternity);
			if (map!=null) {
				if (childrenMaternityervice.update(childrenMaternity) < 1) {
					throw new RuntimeException();
				}
				
			}
		}
		childrenfamily.setId((Integer)childrenFamilyMap.get("id"));
		if (childrenFamilyService.update(childrenfamily) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月18日
	 * @param userId
	 * @param childrenfamily
	 * @param resul
	 * @return
	 * @description: 儿童家族信息详情
	 */
	@RequiresAuthentication(ignore = false,value = { "web-module-height-obesity:children-family:detail" },level = Level.OPERATION)
	@GetMapping(value = { "/children/family/{userId}" })
	public JsonApi detail(@PathVariable("userId") Integer userId,@Validated({ BaseEntity.SelectOne.class }) ChildrenFamily childrenfamily,
			BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		childrenfamily.setUserId(userId);
		/* 判断儿童家族信息是否存在 */
		Map<String, Object> childrenFamilyMap = childrenFamilyService.getOne(childrenfamily);
		if (childrenFamilyMap != null) {
			return new JsonApi(ApiCodeEnum.OK,childrenFamilyMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
