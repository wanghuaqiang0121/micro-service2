package org.web.module.height.obesity.controller;

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
import org.web.module.height.obesity.entity.ChildrenMaternity;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.service.ChildrenMaternityService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: WangHuaQiang
 * @date: 2018年12月17日
 * @description: 儿童出生信息
 */
@RestController
public class ChildrenMaternityController {
	@Resource
	private ChildrenMaternityService childrenMaternityService;
	
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月17日
	 * @param childrenMaternity
	 * @param resul
	 * @return
	 * @description: 添加儿童出生信息
	 */
	@RequiresAuthentication(ignore = false,value = { "web-module-height-obesity:children-maternity:insert" },level = Level.OPERATION)
	@PostMapping(value = { "/children/maternity/insert" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) ChildrenMaternity childrenMaternity,
			BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 判断儿童出生信息是否存在 */
		Map<String, Object> childrenMaternityMap = childrenMaternityService.getOne(childrenMaternity);
		if (childrenMaternityMap != null) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		childrenMaternity.setCreateDateTime(new Date());
		/*判断是否是高危儿*/
		if (childrenMaternity.IsHighRiskChildren()) {
			childrenMaternity.setIsHighRiskChildren(true);
		}else {
			childrenMaternity.setIsHighRiskChildren(false);
		}
		if (childrenMaternityService.insert(childrenMaternity) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月18日
	 * @param userId
	 * @param childrenMaternity
	 * @param resul
	 * @return
	 * @description: 修改儿童出生信息
	 */
	@RequiresAuthentication(ignore = false,value = { "web-module-height-obesity:children-maternity:update" },level = Level.OPERATION)
	@PutMapping(value = { "/children/maternity/{userId}" })
	public JsonApi update(@PathVariable("userId") Integer userId,@RequestBody @Validated({ BaseEntity.Update.class }) ChildrenMaternity childrenMaternity,
			BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		childrenMaternity.setUserId(userId);
		/* 判断儿童出生信息是否存在 */
		Map<String, Object> childrenMaternityMap = childrenMaternityService.getOne(childrenMaternity);
		if (childrenMaternityMap == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		childrenMaternity.setLastUpdateTime(new Date());
		/*判断是否是高危儿*/
		if (childrenMaternity.IsHighRiskChildren()) {
			childrenMaternity.setIsHighRiskChildren(true);
		}else {
			childrenMaternity.setIsHighRiskChildren(false);
		}
		if (childrenMaternityService.update(childrenMaternity) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月18日
	 * @param userId
	 * @param childrenMaternity
	 * @param resul
	 * @return
	 * @description: 儿童出生信息详情
	 */
	@RequiresAuthentication(ignore = false,value = { "web-module-height-obesity:children-maternity:detail" },level = Level.OPERATION)
	@GetMapping(value = { "/children/maternity/{userId}" })
	public JsonApi detail(@PathVariable("userId") Integer userId,@Validated({ BaseEntity.SelectOne.class }) ChildrenMaternity childrenMaternity,
			BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		childrenMaternity.setUserId(userId);
		/* 判断儿童出生信息是否存在 */
		Map<String, Object> childrenMaternityMap = childrenMaternityService.getOne(childrenMaternity);
		if (childrenMaternityMap != null) {
			return new JsonApi(ApiCodeEnum.OK,childrenMaternityMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	
}
