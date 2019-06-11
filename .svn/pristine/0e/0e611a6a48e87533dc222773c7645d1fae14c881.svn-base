package org.web.module.height.obesity.service.feign;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.springframework.stereotype.Component;

@Component
public class OrganizationUserRoleServiceFallback implements IOrganizationUserRoleService {

	@Override
	public JsonApi getRoles(Integer page, Integer pageSize, String token, Integer organizationId, Integer moduleId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getPermissions(Integer page, Integer pageSize, String token, Integer organizationId, Integer moduleId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getOperations(Integer page, Integer pageSize, String token, Integer organizationId, Integer moduleId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getMenus(Integer page, Integer pageSize, String token, Integer organizationId, Integer moduleId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getSession(String cacheName, String key, String token) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getOrganizationOrganizationTeamRoles(Integer page, Integer pageSize, String token,
			Integer organizationTeamId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getOrganizationOrganizationTeamPermissions(Integer page, Integer pageSize, String token,
			Integer organizationTeamId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getOrganizationOrganizationTeamOperations(Integer page, Integer pageSize, String token,
			Integer organizationTeamId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getOrganizationOrganizationTeamMenus(Integer page, Integer pageSize, String token,
			Integer organizationTeamId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}
}
