package org.web.module.height.obesity.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.UserExaminationRecord;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.service.UserExaminationRecordService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: WangHuaQiang
 * @date: 2018年12月28日
 * @description: 用户检查检验记录(子项)
 */
@RestController
public class UserExaminationRecordController {

	@Resource
	private UserExaminationRecordService  userExaminationRecordService;
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月28日
	 * @param id
	 * @param userExaminationRecord
	 * @param result
	 * @return
	 * @description: 修改用户检查检验记录(子项)
	 */
	@RequiresAuthentication(ignore = false, value = { "web-module-height-obesity:user-examination-record:update" },level=Level.OPERATION)
	@PutMapping(value = { "/user/examination/record/{id}" })
	public JsonApi update(@PathVariable("id") Integer id,@RequestBody @Validated(BaseEntity.Update.class) UserExaminationRecord userExaminationRecord, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 判断数据是否存在 */
		userExaminationRecord.setId(id);
		Map<String, Object> userExaminationRecordMap= userExaminationRecordService.getOne(userExaminationRecord);
		if (userExaminationRecordMap == null ) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		if (userExaminationRecordService.update(userExaminationRecord) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
		
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月28日
	 * @param id
	 * @param userExaminationRecord
	 * @param result
	 * @return
	 * @description: 用户检查检验记录(子项)详情
	 */
	@RequiresAuthentication(ignore = false, value = { "web-module-height-obesity:user-examination-record:detail" },level=Level.OPERATION)
	@GetMapping(value = { "/user/examination/record/{id}" })
	public JsonApi detail(@PathVariable("id") Integer id,UserExaminationRecord userExaminationRecord, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 判断数据是否存在 */
		userExaminationRecord.setId(id);
		Map<String, Object> userExaminationRecordMap= userExaminationRecordService.getOne(userExaminationRecord);
		if (userExaminationRecordMap != null ) {
			return new JsonApi(ApiCodeEnum.OK,userExaminationRecordMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
