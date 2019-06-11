package org.wechat.module.height.obesity.controller.archives;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.height.obesity.entity.ChildrenFamily;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.message.Prompt;
import org.wechat.module.height.obesity.service.ChildrenFamilyService;
import org.wechat.module.height.obesity.service.feign.IUserService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月21日
 * @description: 儿童家族
 */
@RestController
public class ChildrenFamilyController {
	
	@Resource
	private ChildrenFamilyService  childrenFamilyService;
	@Resource
	private IUserService useruService;
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月20日
	 * @param childrenFamily
	 * @param result
	 * @param token
	 * @return
	 * @description: 通过用户ID查询儿童家族信息详情
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/children/family" })
	public JsonApi getOne(@Validated({ BaseEntity.SelectOne.class }) ChildrenFamily childrenFamily,BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		childrenFamily.setUserId(userId);
		/*通过用户id查询儿童家族信息*/
		Map<String, Object> childrenFamilyServiceMap = childrenFamilyService.getChildrenFamilyByUserId(childrenFamily);
		if (childrenFamilyServiceMap!=null ) {
			return new JsonApi(ApiCodeEnum.OK,childrenFamilyServiceMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月20日
	 * @param childrenFamily
	 * @param result
	 * @param token
	 * @return
	 * @description: 新增儿童家族信息
	 */
	@RequiresAuthentication(authc = true)
	@PostMapping(value = { "/children/family" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody ChildrenFamily childrenFamily, BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		childrenFamily.setUserId(userId);
		/*通过用户id查询儿童家族信息*/
		Map<String, Object> childrenFamilyServiceMap = childrenFamilyService.getChildrenFamilyByUserId(childrenFamily);
		if (childrenFamilyServiceMap!=null) {
			//根据userId修改  childrenFamily.setId((Integer)childrenFamilyServiceMap.get("id"));
			if (childrenFamilyService.update(childrenFamily)> 0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
		}
		/*设置默认值*/
		childrenFamily.setCreateDateTime(new Date());
		childrenFamily.setLastUpdateTime(new Date());
		if (childrenFamilyService.insert(childrenFamily)> 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月21日
	 * @param childrenFamily
	 * @param result
	 * @param token
	 * @return
	 * @description: 修改儿童家族信息
	 */
	@RequiresAuthentication(authc = true)
	@PutMapping(value = { "/children/family" })
	public JsonApi update(@Validated({ BaseEntity.Update.class }) ChildrenFamily childrenFamily,BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		childrenFamily.setUserId(userId);
		/*通过用户id查询儿童家族信息*/
		Map<String, Object> childrenFamilyServiceMap = childrenFamilyService.getChildrenFamilyByUserId(childrenFamily);
		if (childrenFamilyServiceMap==null ) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		childrenFamily.setLastUpdateTime(new Date());
		if (childrenFamilyService.update(childrenFamily)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
	}
}
