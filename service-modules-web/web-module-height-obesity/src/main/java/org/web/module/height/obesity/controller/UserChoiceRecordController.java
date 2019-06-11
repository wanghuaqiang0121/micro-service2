package org.web.module.height.obesity.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.ChildrenMeasure;
import org.web.module.height.obesity.entity.UserChoiceRecord;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.message.Prompt;
import org.web.module.height.obesity.service.ChildrenMeasureService;
import org.web.module.height.obesity.service.UserChoiceRecordService;
import org.web.module.height.obesity.service.feign.IOrganizationUserRoleService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: WangHuaQiang
 * @date: 2018年12月25日
 * @description: 用户选择项记录表
 */
@RestController
public class UserChoiceRecordController {

	@Resource
	private UserChoiceRecordService userChoiceRecordService;
	@Resource
	private IOrganizationUserRoleService organizationUserRoleService;
	@Resource
	private ChildrenMeasureService childrenMeasureService;
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月25日
	 * @param userChoiceRecord
	 * @param resul
	 * @param token
	 * @return
	 * @description: 添加用户选择项记录
	 */
	@Transactional
	@RequiresAuthentication(ignore = false,value = { "web-module-height-obesity:user-choice-record:insert" },level = Level.OPERATION)
	@PostMapping(value = { "/user/choice/record" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) UserChoiceRecord userChoiceRecord, BindingResult resul,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 判断参数 */
		List<UserChoiceRecord> userChoiceRecordList = userChoiceRecord.getUserChoiceRecords();
		if (userChoiceRecordList == null || userChoiceRecordList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.BAD_REQUEST);
		}
		/* 查询儿童测量信息是否存在 */
		ChildrenMeasure childrenMeasure = new ChildrenMeasure();
		childrenMeasure.setId(userChoiceRecordList.get(0).getChildrenMeasureId());
		Map<String, Object> childrenMeasureMap = childrenMeasureService.getOne(childrenMeasure);
		if (childrenMeasureMap == null) {
			return new JsonApi(ApiCodeEnum.FAIL,Prompt.bundle("children.measure.is.null"));
		}
		/* 判断当前儿童测量信息的选择项记录是否存在 */
		userChoiceRecord.setChildrenMeasureId(userChoiceRecordList.get(0).getChildrenMeasureId());
		List<Map<String, Object>> existsUserChoiceRecordList = userChoiceRecordService.getList(userChoiceRecord);
		if (existsUserChoiceRecordList != null && !existsUserChoiceRecordList .isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		/* 获取登录机构用户id */
		JsonApi jsonApi = organizationUserRoleService.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token,token);
		Integer organizationUserId = null;
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			organizationUserId = Integer.parseInt(jsonApi.getData().toString());
		}
		for (UserChoiceRecord userChoiceRecord2 : userChoiceRecordList) {
			if (userChoiceRecord2.getChildrenMeasureId() == null || userChoiceRecord2.getChoiceQuestionId() == null || userChoiceRecord2.getChoiceItemId() == null) {
				return new JsonApi(ApiCodeEnum.BAD_REQUEST);
			}
			userChoiceRecord2.setUserId(Integer.parseInt(childrenMeasureMap.get("userId").toString()));
			userChoiceRecord2.setOrganizationUserId(organizationUserId);
			userChoiceRecord2.setCreateDateTime(new Date());
		}
		int rows = userChoiceRecordService.beatchInsert(userChoiceRecordList);
		if (rows == userChoiceRecordList.size()) {
			return new JsonApi(ApiCodeEnum.OK);
		}else if (rows != userChoiceRecordList.size()) {
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月25日
	 * @param childrenMeasureId
	 * @param userChoiceRecord
	 * @param resul
	 * @param token
	 * @return
	 * @description: 修改用户选择项记录
	 */
	@Transactional
	@RequiresAuthentication(ignore = false,value = { "web-module-height-obesity:user-choice-record:update" },level = Level.OPERATION)
	@PutMapping(value = { "/user/choice/record/{childrenMeasureId}" })
	public JsonApi update(@PathVariable("childrenMeasureId") Integer childrenMeasureId,@RequestBody @Validated({ BaseEntity.Update.class }) UserChoiceRecord userChoiceRecord, BindingResult resul,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 判断参数 如果传入null : 根据childrenMeasureId删除用户选择项记录*/
		List<UserChoiceRecord> userChoiceRecordList = userChoiceRecord.getUserChoiceRecords();
		if (userChoiceRecordList == null || userChoiceRecordList.isEmpty()) {
			userChoiceRecord.setChildrenMeasureId(childrenMeasureId);
			if (userChoiceRecordService.delete(userChoiceRecord) >= 0) {
				return new JsonApi(ApiCodeEnum.OK); 
			}else {
				return new JsonApi(ApiCodeEnum.FAIL);
			}
		}else if(userChoiceRecordList.size() > 0){
			/* 查询儿童测量信息是否存在 */
			ChildrenMeasure childrenMeasure = new ChildrenMeasure();
			childrenMeasure.setId(userChoiceRecordList.get(0).getChildrenMeasureId());
			Map<String, Object> childrenMeasureMap = childrenMeasureService.getOne(childrenMeasure);
			if (childrenMeasureMap == null) {
				return new JsonApi(ApiCodeEnum.FAIL,Prompt.bundle("儿童测量信息未完善"));
			}
			/* 判断当前儿童测量信息的选择项记录是否存在 */
			userChoiceRecord.setChildrenMeasureId(userChoiceRecordList.get(0).getChildrenMeasureId());
			List<Map<String, Object>> existsUserChoiceRecordList = userChoiceRecordService.getList(userChoiceRecord);
			if (existsUserChoiceRecordList == null) {
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			}
			/* 获取登录机构用户id */
			JsonApi jsonApi = organizationUserRoleService.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token,token);
			Integer organizationUserId = null;
			if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
				organizationUserId = Integer.parseInt(jsonApi.getData().toString());
			}
			
			for (UserChoiceRecord userChoiceRecord2 : userChoiceRecordList) {
				if (userChoiceRecord2.getChildrenMeasureId() == null || userChoiceRecord2.getChoiceQuestionId() == null || userChoiceRecord2.getChoiceItemId() == null) {
					return new JsonApi(ApiCodeEnum.BAD_REQUEST);
				}
				userChoiceRecord2.setUserId(Integer.parseInt(childrenMeasureMap.get("userId").toString()));
				userChoiceRecord2.setOrganizationUserId(organizationUserId);
				userChoiceRecord2.setCreateDateTime(new Date());
			}
			if (userChoiceRecordService.delete(userChoiceRecord) < 0) {
				throw new RuntimeException();
			}
			int rows = userChoiceRecordService.beatchInsert(userChoiceRecordList);
			if (rows == userChoiceRecordList.size()) {
				return new JsonApi(ApiCodeEnum.OK);
			}else if (rows != userChoiceRecordList.size()) {
				throw new RuntimeException();
			}
			return new JsonApi(ApiCodeEnum.FAIL);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月25日
	 * @param childrenMeasureId
	 * @param userChoiceRecord
	 * @param resul
	 * @param token
	 * @return
	 * @description: 修改体格检查
	 */
	@Transactional
	@RequiresAuthentication(ignore = false,value = { "web-module-height-obesity:user-choice-record:update-physique" },level = Level.OPERATION)
	@PutMapping(value = { "/user/choice/record/physique/{childrenMeasureId}" })
	public JsonApi updatePhysique(@PathVariable("childrenMeasureId") Integer childrenMeasureId,@RequestBody @Validated({ BaseEntity.Update.class }) UserChoiceRecord userChoiceRecord, BindingResult resul,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 判断参数 */
		List<UserChoiceRecord> userChoiceRecordList = userChoiceRecord.getUserChoiceRecords();
		if (userChoiceRecordList == null || userChoiceRecordList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.BAD_REQUEST);
		}
		/* 查询儿童测量信息是否存在 */
		ChildrenMeasure childrenMeasure = new ChildrenMeasure();
		childrenMeasure.setId(userChoiceRecordList.get(0).getChildrenMeasureId());
		Map<String, Object> childrenMeasureMap = childrenMeasureService.getOne(childrenMeasure);
		if (childrenMeasureMap == null) {
			return new JsonApi(ApiCodeEnum.FAIL,Prompt.bundle("儿童测量信息未完善"));
		}
		/* 判断当前儿童测量信息的选择项记录是否存在 */
		userChoiceRecord.setChildrenMeasureId(userChoiceRecordList.get(0).getChildrenMeasureId());
		List<Map<String, Object>> existsUserChoiceRecordList = userChoiceRecordService.getList(userChoiceRecord);
		if (existsUserChoiceRecordList == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 获取登录机构用户id */
		JsonApi jsonApi = organizationUserRoleService.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token,token);
		Integer organizationUserId = null;
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			organizationUserId = Integer.parseInt(jsonApi.getData().toString());
		}
		for (UserChoiceRecord userChoiceRecord2 : userChoiceRecordList) {
			if (userChoiceRecord2.getChildrenMeasureId() == null || userChoiceRecord2.getChoiceQuestionId() == null || userChoiceRecord2.getChoiceItemId() == null) {
				return new JsonApi(ApiCodeEnum.BAD_REQUEST);
			}
			userChoiceRecord2.setUserId(Integer.parseInt(childrenMeasureMap.get("userId").toString()));
			userChoiceRecord2.setOrganizationUserId(organizationUserId);
			userChoiceRecord2.setCreateDateTime(new Date());
		}
		/* 删除体格检查 */
		userChoiceRecord.setCode(5);
		if (userChoiceRecordService.deleteCodeRqFive(userChoiceRecord) < 0) {
			throw new RuntimeException();
		}
		int rows = userChoiceRecordService.beatchInsert(userChoiceRecordList);
		if (rows == userChoiceRecordList.size()) {
			return new JsonApi(ApiCodeEnum.OK);
		}else if (rows != userChoiceRecordList.size()) {
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
}
