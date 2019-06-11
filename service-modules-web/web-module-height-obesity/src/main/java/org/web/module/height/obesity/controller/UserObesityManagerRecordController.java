package org.web.module.height.obesity.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.UserObesityManagerRecord;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.service.UserObesityManagerRecordService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController
public class UserObesityManagerRecordController {
	
	@Resource
	private UserObesityManagerRecordService userObesityManagerRecordService;
	
	
	/**
	 * @author: ChenYan
	 * @date: 2019年1月8日
	 * @param userObesityManagerRecord
	 * @param result
	 * @return
	 * @description: 查询专案管理列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-height-obesity:user-obesity-manager-record:get-list" })
	@GetMapping(value = { "/user/obesity/manager/records" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class })  UserObesityManagerRecord userObesityManagerRecord, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token
			, @RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) String organizationTeamId) {
		// 设置团队id
		userObesityManagerRecord.setOrganizationTeamId(Integer.parseInt(organizationTeamId));
		Page<?> page = PageHelper.startPage(userObesityManagerRecord.getPage(), userObesityManagerRecord.getPageSize());
		List<Map<String, Object>> userObesityManagerRecordList = userObesityManagerRecordService.getList(userObesityManagerRecord);
		if (userObesityManagerRecordList != null && !userObesityManagerRecordList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), userObesityManagerRecordList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2019年1月8日
	 * @param userId
	 * @param userObesityManagerRecord
	 * @param result
	 * @return
	 * @description: 通过用户id查询专案管理列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-height-obesity:user-obesity:by-user-id" })
	@GetMapping(value = { "/user/obesity/manager/record/{userId}" })
	public JsonApi getListByUserId(@PathVariable("userId") Integer userId, @Validated({ BaseEntity.SelectAll.class })  UserObesityManagerRecord userObesityManagerRecord, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		userObesityManagerRecord.setUserId(userId);
		Page<?> page = PageHelper.startPage(userObesityManagerRecord.getPage(), userObesityManagerRecord.getPageSize());
		List<Map<String, Object>> userObesityManagerRecordList = userObesityManagerRecordService.getListByUserId(userObesityManagerRecord);
		if (userObesityManagerRecordList != null && !userObesityManagerRecordList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), userObesityManagerRecordList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

}
