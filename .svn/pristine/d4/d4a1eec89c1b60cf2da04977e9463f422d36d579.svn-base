package org.wechat.module.height.obesity.controller.examine;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.height.obesity.entity.ChildrenMeasure;
import org.wechat.module.height.obesity.entity.UserExaminationMasterRecord;
import org.wechat.module.height.obesity.entity.UserExaminationRecord;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.global.GlobalEnum;
import org.wechat.module.height.obesity.message.Prompt;
import org.wechat.module.height.obesity.service.ChildrenMeasureService;
import org.wechat.module.height.obesity.service.UserExaminationMasterRecordService;
import org.wechat.module.height.obesity.service.UserExaminationRecordService;
import org.wechat.module.height.obesity.service.feign.IUserService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月13日
 * @description: 用户检查检验记录表
 */
@RestController
public class UserExaminationRecordController {
	
	@Resource
	private UserExaminationRecordService userExaminationRecordService;
	
	@Resource
	private UserExaminationMasterRecordService userExaminationMasterRecordService ;
	
	@Resource
	private ChildrenMeasureService childrenMeasureService;
	@Resource
	private IUserService useruService;
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月13日
	 * @param userExaminationRecord
	 * @param result
	 * @param token
	 * @return
	 * @description: 新增检查项目
	 */
	@RequiresAuthentication(authc = true)
	@PostMapping(value = { "/user/examination/record" })
	@Transactional
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody UserExaminationRecord userExaminationRecord,BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*验证必传项*/
		List<UserExaminationRecord> userExaminationRecords =userExaminationRecord.getUserExaminationRecords();
	   for (UserExaminationRecord userExamination : userExaminationRecords) {
		   
		   if (!userExaminationRecord.getCode().equals("gmdjc")) {
			   
		  if (userExamination.getExaminationCode()==null || userExamination.getExaminationCode().isEmpty()) {
			  return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("examination.code.is.notblank.valid"));
		}
		  if (userExamination.getExaminationName()==null || userExamination.getExaminationName().isEmpty()) {
			  return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("examination.name.is.notblank.valid"));
		}
		   }else {
			
			 if(userExaminationRecords==null || userExaminationRecords.isEmpty()) {
				 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("examination.is.notblank.valid"));
			 }
		}
	}
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		/*通过用户ID查询儿童测量信息表ID*/
		ChildrenMeasure childrenMeasure=new ChildrenMeasure();
		childrenMeasure.setUserId(userId);
		Integer childrenMeasureId=null;
		Map<String, Object> childrenMeasureMap=childrenMeasureService.getOne(childrenMeasure);
		if (childrenMeasureMap!=null ) {
			childrenMeasureId=Integer.parseInt(childrenMeasureMap.get("id").toString());
		}
		/*设置用户检查检验记录主表值*/
		UserExaminationMasterRecord  userExaminationMasterRecord=new UserExaminationMasterRecord();
		userExaminationMasterRecord.setChildrenMeasureId(childrenMeasureId);
		userExaminationMasterRecord.setUserId(userId);
		userExaminationMasterRecord.setType(GlobalEnum.ExaminationRecordType.USER.getValue());
		userExaminationMasterRecord.setExaminationCode(userExaminationRecord.getCode());
		userExaminationMasterRecord.setExaminationName(userExaminationRecord.getName());
	    userExaminationMasterRecord.setExaminationDate(userExaminationRecord.getExaminationDate());
	    userExaminationMasterRecord.setCreateDateTime(new Date());
	    if (userExaminationRecord.getOrganizationUserId()!=null) {
	    	userExaminationMasterRecord.setOrganizationUserId(userExaminationRecord.getOrganizationUserId());
		}
	 /*新增主表值*/
		if (userExaminationMasterRecordService.insert(userExaminationMasterRecord)<0) {
			return new JsonApi(ApiCodeEnum.FAIL);
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("id", userExaminationMasterRecord.getId());
		
	   /*获取检查检验项目列表*/
		 for (UserExaminationRecord userExaminationRecordParm : userExaminationRecords) {
				/*设置值*/
			 userExaminationRecordParm.setUserExaminationMasterRecordId(userExaminationMasterRecord.getId());
			 userExaminationRecordParm.setCreateDateTime(new Date());
			 userExaminationRecordParm.setUserId(userId);
			 userExaminationRecordParm.setIsView((byte)0);
			 userExaminationRecordParm.setPicture(userExaminationRecord.getPicture());
			 userExaminationRecordParm.setExaminationDate(userExaminationRecord.getExaminationDate());
		}
		 /*批量新增*/
		if (userExaminationRecordService.batchInsert(userExaminationRecords)==userExaminationRecords.size()) {
			return new JsonApi(ApiCodeEnum.OK,resultMap);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	
}
