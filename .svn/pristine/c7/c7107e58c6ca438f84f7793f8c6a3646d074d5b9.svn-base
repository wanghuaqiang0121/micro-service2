package org.web.module.organization.user.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.user.domain.OrganizationUserTeamRole;
import org.web.module.organization.user.global.BaseGlobal;
import org.web.module.organization.user.global.BaseGlobalEnum;
import org.web.module.organization.user.message.Prompt;
import org.web.module.organization.user.sevice.OrganizationUserTeamRoleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author <font color="red"><b>Chen.Yan</b></font>
 * @Date 2018年3月20日
 * @Version 
 * @Description 机构用户和团队角色关联表
 */
@RestController
public class OrganizationUserTeamRoleRelationController {
	
	@Resource
	private OrganizationUserTeamRoleService organizationOrganizationTeamService;
	
	@Resource
	private RedisCacheManager cacheManager;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationOrganizationTeam
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增机构用户所在团队和团队角色关联
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-team-role:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/user/team/role" })
	public JsonApi insert( @Validated({ BaseEntity.Insert.class })@RequestBody OrganizationUserTeamRole  organizationOrganizationTeam,BindingResult result
			){
		/*设置状态*/
		organizationOrganizationTeam.setStatus(BaseGlobalEnum.OrganizationTeam.ENABLE.getValue());
		/*判断是否重复*/
		Map<String, Object> organizationOrganizationTeamMap = organizationOrganizationTeamService.getRepeat(organizationOrganizationTeam);
		if (organizationOrganizationTeamMap!=null && !organizationOrganizationTeamMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.rganization.team.role.is.exists"));
		}
		if (organizationOrganizationTeamService.insert(organizationOrganizationTeam)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserTeamId
	 * @param organizationTeamRoleId
	 * @param organizationOrganizationTeam
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 删除机构用户所在团队和团队角色关联（删除用户在团队具体角色）
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-team-role:delete-role" },level=Level.OPERATION)
	@DeleteMapping(value = { "/organization/{organizationUserTeamId}/organization/team/{organizationTeamRoleId}" })
	public JsonApi deleteRole(
			@PathVariable("organizationUserTeamId") Integer organizationUserTeamId,
			@PathVariable("organizationTeamRoleId") Integer organizationTeamRoleId,
			@Validated({ BaseEntity.Delete.class }) OrganizationUserTeamRole  organizationOrganizationTeam,BindingResult result){
		organizationOrganizationTeam.setOrganizationUserTeamId(organizationUserTeamId);
		organizationOrganizationTeam.setOrganizationTeamRoleId(organizationTeamRoleId);
		if (organizationOrganizationTeamService.delete(organizationOrganizationTeam)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationOrganizationTeam
	 * @param result
	 * @return  {@link JsonApi}
	 * @description:  删除关联表
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-team-role:delete" },level=Level.OPERATION)
	@DeleteMapping(value = { "/organization/organization/team/{id}" })
	public JsonApi delete(
			@PathVariable("id") Integer id,		
			@Validated({ BaseEntity.Delete.class }) OrganizationUserTeamRole  organizationOrganizationTeam,BindingResult result){
		organizationOrganizationTeam.setId(id);
		if (organizationOrganizationTeamService.deleteRole(organizationOrganizationTeam)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationOrganizationTeam
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 查询机构用户在这个团队拥有和未拥有的权限
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user-team-role:get-organiztion-user-have-not-have-roles" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/user/team/roles" })
	public JsonApi getRoleIsChoose(@Validated({OrganizationUserTeamRole.GetOrganizationUserRoleIsChoose.class })  OrganizationUserTeamRole  organizationOrganizationTeam,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId){
		organizationOrganizationTeam.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(organizationOrganizationTeam.getPage(), organizationOrganizationTeam.getPageSize());
		List<Map<String, Object>>  organizationUserRoleList=organizationOrganizationTeamService.getOrganizationUserRoleIsChoose(organizationOrganizationTeam);
		if (organizationUserRoleList!=null && !organizationUserRoleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationUserRoleList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
}
