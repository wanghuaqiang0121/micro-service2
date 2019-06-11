package org.wechat.module.user.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.user.domain.UserOrganizationTeam;
import org.wechat.module.user.service.UserOrganizationTeamService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author <font color="red"><b>Wang.Hua.Qiang</b></font>
 * @Date 2018年10月17日
 * @Version 
 * @Description 用户团队关系关联表
 */
@RestController
public class UserOrganizationTeamController {
	@Resource
	private UserOrganizationTeamService userOrganizationTeamService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月31日
	 * @param userOrganizationTeam
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 用户团队关系列表
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@RequestMapping(value = { "/user/organization/team/relations" }, method = RequestMethod.GET)
	public JsonApi getUserOrganizationTeamRelations(@Validated(BaseEntity.SelectAll.class) UserOrganizationTeam userOrganizationTeam, BindingResult result) {
		Page<?> page = PageHelper.startPage(userOrganizationTeam.getPage(), userOrganizationTeam.getPageSize());
		List<Map<String, Object>> usersTeamRelationsList = userOrganizationTeamService.getList(userOrganizationTeam);
		if (usersTeamRelationsList != null && !usersTeamRelationsList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), usersTeamRelationsList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
