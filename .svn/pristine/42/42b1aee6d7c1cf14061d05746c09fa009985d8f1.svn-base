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
import org.wechat.module.height.obesity.entity.ChildrenMaternity;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.message.Prompt;
import org.wechat.module.height.obesity.service.ChildrenMaternityService;
import org.wechat.module.height.obesity.service.feign.IUserService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月21日
 * @description: 儿童出生信息表
 */
@RestController
public class ChildrenMaternityController {
	
	@Resource
	private ChildrenMaternityService  childrenMaternityService;
	@Resource
	private IUserService useruService;
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月20日
	 * @param childrenMaternity
	 * @param result
	 * @param token
	 * @return
	 * @description: 通过用户ID查询儿童出生信息详情
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/children/maternity" })
	public JsonApi getOne(@Validated({ BaseEntity.SelectOne.class }) ChildrenMaternity childrenMaternity,BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		childrenMaternity.setUserId(userId);
		/*通过用户id查询儿童出生信息*/
		Map<String, Object> childrenMaternityServiceMap = childrenMaternityService.getChildrenMaternityByUserId(childrenMaternity);
		if (childrenMaternityServiceMap!=null) {
			return new JsonApi(ApiCodeEnum.OK,childrenMaternityServiceMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月20日
	 * @param childrenMaternity
	 * @param result
	 * @param token
	 * @return
	 * @description: 新增儿童出生信息
	 */
	@RequiresAuthentication(authc = true)
	@PostMapping(value = { "/children/maternity" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody ChildrenMaternity childrenMaternity, BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		/*设置儿童信息默认值*/
		childrenMaternity.setUserId(userId);
		childrenMaternity.setCreateDateTime(new Date());
		childrenMaternity.setLastUpdateTime(new Date());
		/* 判断儿童出生信息是否存在 */
		Map<String, Object> childrenMaternityMap = childrenMaternityService.getOne(childrenMaternity);
		if (childrenMaternityMap != null) {
			childrenMaternity.setId((Integer)childrenMaternityMap.get("id"));
			if (childrenMaternityService.update(childrenMaternity)> 0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
		}
		/*判断是否是高危儿*/
		if (childrenMaternity.IsHighRiskChildren()) {
			childrenMaternity.setIsHighRiskChildren(true);
		}else {
			childrenMaternity.setIsHighRiskChildren(false);
		}
		if (childrenMaternityService.insert(childrenMaternity)> 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月21日
	 * @param childrenMaternity
	 * @param token
	 * @return
	 * @description: 修改儿童出生信息
	 */
	@RequiresAuthentication(authc = true)
	@PutMapping(value = { "/children/maternity" })
	public JsonApi update(@Validated({ BaseEntity.Update.class }) @RequestBody ChildrenMaternity childrenMaternity,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}
		childrenMaternity.setUserId(userId);
		/* 判断儿童出生信息是否存在 */
		Map<String, Object> childrenMaternityMap = childrenMaternityService.getChildrenMaternityByUserId(childrenMaternity);
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

}
