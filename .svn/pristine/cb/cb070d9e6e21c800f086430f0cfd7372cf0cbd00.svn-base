package org.web.module.height.obesity.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.ChildrenMeasure;
import org.web.module.height.obesity.entity.UserExaminationMasterRecord;
import org.web.module.height.obesity.entity.UserExaminationRecord;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.global.GlobalEnum.UserExaminationMasterRecordType;
import org.web.module.height.obesity.message.Prompt;
import org.web.module.height.obesity.service.ChildrenMeasureService;
import org.web.module.height.obesity.service.UserExaminationMasterRecordService;
import org.web.module.height.obesity.service.UserExaminationRecordService;
import org.web.module.height.obesity.service.feign.IOrganizationUserRoleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: WangHuaQiang
 * @date: 2018年12月27日
 * @description: 用户检查检验记录
 */
@RestController
public class UserExaminationMasterRecordController {

	@Resource
	private UserExaminationMasterRecordService userExaminationMasterRecordService;
	@Resource
	private ChildrenMeasureService childrenMeasureService;
	@Resource
	private UserExaminationRecordService userExaminationRecordService;
	@Resource
	private IOrganizationUserRoleService organizationUserRoleService;
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月27日
	 * @param userExaminationMasterRecord
	 * @param result
	 * @return
	 * @description:  用户检查检验记录列表(患者上传|医生上传)
	 */
	@RequiresAuthentication(ignore = false, value = { "web-module-height-obesity:user-examination:get-list" },level=Level.OPERATION)
	@GetMapping(value = { "/user/examination/master/records" })
	public JsonApi getList(@Validated(BaseEntity.SelectAll.class) UserExaminationMasterRecord userExaminationMasterRecord,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		//userExaminationMasterRecord.setType(UserExaminationMasterRecordType.USER.getValue());
		Page<?> page = PageHelper.startPage(userExaminationMasterRecord.getPage(), userExaminationMasterRecord.getPageSize());
		List<Map<String, Object>> userExaminationMasterRecordList = userExaminationMasterRecordService.getList(userExaminationMasterRecord);
		if (userExaminationMasterRecordList != null && !userExaminationMasterRecordList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), userExaminationMasterRecordList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月27日
	 * @param userExaminationMasterRecord
	 * @param result
	 * @return
	 * @description: 新增用户检查检验记录
	 */
	@Transactional
	@RequiresAuthentication(ignore = false, value = { "web-module-height-obesity:user-examination:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/user/examination/master/record" })
	public JsonApi insert(@RequestBody @Validated(BaseEntity.Insert.class) UserExaminationMasterRecord userExaminationMasterRecord, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 查询当前测量信息对应的检查记录 */
		/*PageHelper.startPage(1,1000);
		List<Map<String, Object>> userExaminationMasterRecordList = userExaminationMasterRecordService.getList(userExaminationMasterRecord);
		if (userExaminationMasterRecordList != null && !userExaminationMasterRecordList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}*/
		List<UserExaminationRecord> userExaminationRecordList = userExaminationMasterRecord.getUserExaminationRecords();
		for (UserExaminationRecord userExaminationRecord : userExaminationRecordList) {
			
			 if (!userExaminationMasterRecord.getExaminationCode().equals("gmdjc")) {
				  if (userExaminationRecord.getExaminationCode()==null || userExaminationRecord.getExaminationCode().isEmpty()) {
					  return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("examination.code.is.notblank.valid"));
				}
				  if (userExaminationRecord.getExaminationName()==null || userExaminationRecord.getExaminationName().isEmpty()) {
					  return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("examination.name.is.notblank.valid"));
				}
					if (userExaminationRecord.getExaminationResult()==null || userExaminationRecord.getExaminationResult().isEmpty()) {
						  return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("examination.result.is.notblank.valid"));
					}
				   }else {
					 if(userExaminationRecordList==null || userExaminationRecordList.isEmpty()) {
						 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("examination.is.notblank.valid"));
					 }
				}
		}
		ChildrenMeasure childrenMeasure = new ChildrenMeasure();
		childrenMeasure.setId(userExaminationMasterRecord.getChildrenMeasureId());
		Map<String, Object> childrenMeasureMap = childrenMeasureService.getOne(childrenMeasure);
		if (childrenMeasureMap == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 新增需要的数据 */
		userExaminationMasterRecord.setUserId((Integer)childrenMeasureMap.get("userId"));
		userExaminationMasterRecord.setType(UserExaminationMasterRecordType.DOCTOR.getValue());
		/* 获取登录机构用户id */
		JsonApi jsonApi = organizationUserRoleService.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userExaminationMasterRecord.setOrganizationUserId(Integer.parseInt(jsonApi.getData().toString()));
		}
		userExaminationMasterRecord.setCreateDateTime(new Date());
		/* 新增数据 */
		if (userExaminationMasterRecordService.insert(userExaminationMasterRecord) > 0) {
			/* 新增子项 */
		
			if (userExaminationRecordList != null && !userExaminationRecordList.isEmpty()) {
				for (UserExaminationRecord userExaminationRecord : userExaminationRecordList) {
					userExaminationRecord.setUserId((Integer)childrenMeasureMap.get("userId"));
					userExaminationRecord.setUserExaminationMasterRecordId(userExaminationMasterRecord.getId());
					userExaminationRecord.setCreateDateTime(new Date());
				}
				int rows =userExaminationRecordService.beatchInsert(userExaminationRecordList);
				if (rows == userExaminationRecordList.size()) {
					return new JsonApi(ApiCodeEnum.OK);
				}else if (rows != userExaminationRecordList.size()) {
					throw new RuntimeException();
				}
				return new JsonApi(ApiCodeEnum.FAIL);
			}
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
}
