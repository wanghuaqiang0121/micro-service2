package org.web.module.bone.age.service.feign;

import org.service.core.api.JsonApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.web.module.bone.age.global.BaseGlobal;

@FeignClient(value = "${module.organization.user}", fallback = OrganizationUserRoleServiceFallback.class)
public interface IOrganizationUserRoleService {

	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月3日
	 * @param authenticationToken
	 * @return
	 * @description: 根据token获取机构用户id
	 */
	@RequestMapping(value = { "/organization/user/info/by/token" }, method = RequestMethod.GET)
	JsonApi getSession(@RequestParam("cacheName") String cacheName, @RequestParam("key") String key,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token);
	
	@RequestMapping(value = { "/organizationTeam/organization/team/roles" }, method = RequestMethod.GET)
	JsonApi getOrganizationOrganizationTeamRoles(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId);

	@RequestMapping(value = { "/organizationTeam/organization/team/permissions" }, method = RequestMethod.GET)
	JsonApi getOrganizationOrganizationTeamPermissions(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId);

	@RequestMapping(value = { "/organizationTeam/organization/team/operations" }, method = RequestMethod.GET)
	JsonApi getOrganizationOrganizationTeamOperations(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId);

	@RequestMapping(value = { "/organizationTeam/organization/team/menus" }, method = RequestMethod.GET)
	JsonApi getOrganizationOrganizationTeamMenus(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId);
}
