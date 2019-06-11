package org.wechat.module.service.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.service.domain.user.UserType;
import org.wechat.module.service.message.Prompt;
import org.wechat.module.service.service.user.UserTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年11月28日
 * @description: 人群类型
 */
@RestController
public class UserTypeController {

	@Resource
	private UserTypeService userTypeService;

	/**
	 * @author: ChenYan
	 * @date: 2018年11月28日
	 * @param userType
	 * @param result
	 * @return
	 * @description: 人群类型列表
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@GetMapping(value = { "/user/types" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class })  UserType userType,BindingResult result) {
		Page<?> page = PageHelper.startPage(userType.getPage(), userType.getPageSize());
		List<Map<String, Object>> userTypeList = userTypeService.getList(userType);
		if (userTypeList != null && !userTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), userTypeList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年11月28日
	 * @param userType
	 * @param result
	 * @return
	 * @description: 新增人群类型
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@PostMapping(value = { "/user/type" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody UserType userType,BindingResult result) {
		/*查询数据是否存在*/
		Map<String, Object> userTypeRepeatMap = userTypeService.getRepeat(userType);
		if (userTypeRepeatMap!=null && !userTypeRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.type.name.is.repeat"));
		}
		/*新增人群类型*/
		userType.setCreateDate(new Date());
		if (userTypeService.insert(userType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年11月28日
	 * @param id
	 * @param userType
	 * @param result
	 * @return
	 * @description: 修改人群类型
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@PutMapping(value = { "/user/type/{id}" })
	public JsonApi update(@PathVariable("id")Integer id,@Validated({ BaseEntity.Update.class }) @RequestBody UserType userType,	BindingResult result) {
		userType.setId(id);
		/*查询数据是否存在*/
		Map<String, Object> userTypeOneMap = userTypeService.getOne(userType);
		if (userTypeOneMap==null || userTypeOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/*查询数据是否重复*/
		Map<String, Object> userTypeRepeatMap = userTypeService.getRepeat(userType);
		if (userTypeRepeatMap!=null && !userTypeRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.type.name.is.repeat"));
		}
		if (userTypeService.update(userType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年11月28日
	 * @param id
	 * @param userType
	 * @param result
	 * @return
	 * @description: 是否启用禁用
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@PutMapping(value = { "/user/type/is/used/{id}" })
	public JsonApi updateIsUsed(@PathVariable("id")Integer id,
			@Validated({ BaseEntity.Update.class }) @RequestBody UserType userType,BindingResult result) {
		userType.setId(id);
		/*查询数据是否存在*/
		Map<String, Object> userTypeOneMap = userTypeService.getOne(userType);
		if (userTypeOneMap==null || userTypeOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		UserType userTypeNew = new UserType();
		userTypeNew.setId(id);
		userTypeNew.setIsUsed(userType.getIsUsed());
		if (userTypeService.update(userTypeNew) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年11月28日
	 * @param id
	 * @param userType
	 * @param result
	 * @return
	 * @description: 人群类型详情
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@GetMapping(value = { "/user/type/{id}" })
	public JsonApi getDetail(@PathVariable("id")Integer id,
			@Validated({ BaseEntity.SelectOne.class })  UserType userType, BindingResult result) {
		userType.setId(id);
		/*查询数据是否存在*/
		Map<String, Object> userTypeOneMap = userTypeService.getOne(userType);
		if (userTypeOneMap!=null && !userTypeOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,userTypeOneMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年11月28日
	 * @param id
	 * @param userType
	 * @param result
	 * @return
	 * @description:  删除人群类型
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@DeleteMapping(value = { "/user/type/{id}" })
	public JsonApi delete(@PathVariable("id")Integer id,
			@Validated({ BaseEntity.Delete.class })  UserType userType, BindingResult result) {
	 	userType.setId(id);
		if (userTypeService.delete(userType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
