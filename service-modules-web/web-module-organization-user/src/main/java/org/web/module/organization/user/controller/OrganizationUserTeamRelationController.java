package org.web.module.organization.user.controller;

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
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.token.RedisSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.user.domain.OrganizationUserTeam;
import org.web.module.organization.user.domain.OrganizationUserTeamRole;
import org.web.module.organization.user.global.BaseGlobal;
import org.web.module.organization.user.message.Prompt;
import org.web.module.organization.user.sevice.OrganizationUserTeamRoleService;
import org.web.module.organization.user.sevice.OrganizationUserTeamService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年10月11日
 * @description: 机构用户团队关联
 */
@RestController
public class OrganizationUserTeamRelationController {

	@Resource
	private OrganizationUserTeamService organizationUserTeamService;
	@Resource
	private OrganizationUserTeamRoleService organizationUserTeamRoleService;
	@Resource
	private RedisCacheManager cacheManager;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationUserTeam
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增关联
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user-team:insert" }, level = Level.OPERATION)
	@PostMapping(value = { "/organization/user/team" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationUserTeam organizationUserTeam, BindingResult result) {
		/* 判断是否重复 */
		Map<String, Object> organizationUserTeamMap = organizationUserTeamService.getRepeat(organizationUserTeam);
		if (organizationUserTeamMap != null && !organizationUserTeamMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("doctor.doctor.team.is.exists"));
		}
		organizationUserTeam.setIsManager(false);
		organizationUserTeam.setCreateDate(new Date());
		if (organizationUserTeamService.insert(organizationUserTeam) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationUserTeam
	 * @param result
	 * @param organizationUserId
	 * @param organizationTeamId
	 * @return {@link JsonApi}
	 * @description: 移除成员(并删除成员所在团队的所有权限)
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user-team:delete" }, level = Level.OPERATION)
	@DeleteMapping(value = { "/organization/user/{organizationUserId}/team/{organizationTeamId}" })
	@Transactional
	public JsonApi delete(@Validated({ BaseEntity.Delete.class }) OrganizationUserTeam organizationUserTeam, BindingResult result, @PathVariable("organizationUserId") Integer organizationUserId,
			@PathVariable("organizationTeamId") Integer organizationTeamId) {
		organizationUserTeam.setOrganizationTeamId(organizationTeamId);
		organizationUserTeam.setOrganizationUserId(organizationUserId);
		/* 判断是否重复 */
		Map<String, Object> organizationUserTeamMap = organizationUserTeamService.getRepeat(organizationUserTeam);
		if (organizationUserTeamMap == null || organizationUserTeamMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 移除成员 */
		if (organizationUserTeamService.delete(organizationUserTeam) > 0) {
			/* 删除在团队的角色 */
			OrganizationUserTeamRole organizationOrganizationTeam = new OrganizationUserTeamRole();
			organizationOrganizationTeam.setOrganizationUserTeamId(Integer.parseInt(organizationUserTeamMap.get("id").toString()));
			/* 查询医生所在团队角色 */
			List<Map<String, Object>> organizationUserRoleList = organizationUserTeamRoleService.getOrganizationUserRoles(organizationOrganizationTeam);
			if (organizationUserRoleList != null && !organizationUserRoleList.isEmpty()) {
				if (organizationUserTeamRoleService.deleteByDoctorDoctorTeamId(organizationOrganizationTeam) > 0) {
					return new JsonApi(ApiCodeEnum.OK);
				}
				throw new RuntimeException();
			}
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationUserTeam
	 * @param result
	 * @param token
	 * @return {@link JsonApi}
	 * @description: 获取医生团队列表
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user-team:get-doctor-teams" }, level = Level.OPERATION)
	@GetMapping(value = { "/organization/user/teams" })
	public JsonApi getDoctorTeams(@Validated({ BaseEntity.SelectAll.class }) OrganizationUserTeam organizationUserTeam, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 获取机构用户id */
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		int organizationUserId = (Integer) session.get(Map.class).get("id");
		/* 设置机构用户id */
		organizationUserTeam.setOrganizationUserId(organizationUserId);
		Page<?> page = PageHelper.startPage(organizationUserTeam.getPage(), organizationUserTeam.getPageSize());
		/* 查询列表 */
		List<Map<String, Object>> organizationUserTeamList = organizationUserTeamService.getList(organizationUserTeam);
		if (organizationUserTeamList != null && !organizationUserTeamList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationUserTeamList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationUserTeam
	 * @param result
	 * @param organizationTeamId
	 * @return {@link JsonApi}
	 * @description: 团队成员列表
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user-team:get-team-doctors" }, level = Level.OPERATION)
	@GetMapping(value = { "/organization/user/team/{organizationTeamId}/teamDoctors" })
	public JsonApi getTeamDoctors(@Validated({ OrganizationUserTeam.TeamDoctors.class }) OrganizationUserTeam organizationUserTeam, BindingResult result,
			@PathVariable("organizationTeamId") Integer organizationTeamId) {
		/* 设置机构团队id */
		organizationUserTeam.setOrganizationTeamId(organizationTeamId);
		Page<?> page = PageHelper.startPage(organizationUserTeam.getPage(), organizationUserTeam.getPageSize());
		/* 查询列表 */
		List<Map<String, Object>> organizationUserTeamList = organizationUserTeamService.getTeamDoctors(organizationUserTeam);
		if (organizationUserTeamList != null && !organizationUserTeamList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationUserTeamList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationUserTeam
	 * @param result
	 * @param organizationTeamId
	 * @return {@link JsonApi}
	 * @description: 查询不在团队的机构成员列表
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user-team:get-team-out-users" }, level = Level.OPERATION)
	@RequestMapping(value = { "/doctor/doctor/team/{organizationTeamId}/members" }, method = RequestMethod.GET)
	public JsonApi getTeamOutUsers(@Validated({ OrganizationUserTeam.SelectAll.class }) OrganizationUserTeam organizationUserTeam, BindingResult result,
			@PathVariable("organizationTeamId") Integer organizationTeamId) {
		// 设置机构团队id
		organizationUserTeam.setOrganizationTeamId(organizationTeamId);
		Page<?> page = PageHelper.startPage(organizationUserTeam.getPage(), organizationUserTeam.getPageSize());
		// 查询列表
		List<Map<String, Object>> organizationUserTeamList = organizationUserTeamService.getOrganizationUserMembers(organizationUserTeam);
		if (organizationUserTeamList != null && !organizationUserTeamList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationUserTeamList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
