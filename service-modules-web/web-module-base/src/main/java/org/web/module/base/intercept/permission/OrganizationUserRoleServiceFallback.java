package org.web.module.base.intercept.permission;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.springframework.stereotype.Component;

@Component
public class OrganizationUserRoleServiceFallback implements IOrganizationUserRoleService {

	@Override
	public JsonApi getOrganizationUserRoleRolesList(Integer page, Integer pageSize, String token,
			Integer organizationId, Integer moduleId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getOrganizationUserRolepermissionsList(Integer page, Integer pageSize, String token,
			Integer organizationId, Integer moduleId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getOrganizationUserRoleOperationsList(Integer page, Integer pageSize, String token,
			Integer organizationId, Integer moduleId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi getOrganizationUserRoleMenusList(Integer page, Integer pageSize, String token,
			Integer organizationId, Integer moduleId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

}
