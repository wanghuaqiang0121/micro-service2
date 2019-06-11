package org.web.module.base.controller.permission.organization;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.permission.organization.OperationalRolePermission;
import org.web.module.base.service.permission.organization.OperationalRolePermissionService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author <font color="red"><b>Chen.Yan</b></font>
 * @Date 2018年8月15日
 * @Version
 * @Description 机构角色权限关联表
 */
@RestController
public class OperationalRolePermissionRelationController {
	@Resource
	private OperationalRolePermissionService operationalRolePermissionService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalRolePermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增机构角色权限关联表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/operational/role/permission" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) OperationalRolePermission operationalRolePermission, BindingResult result) {
		Map<String, Object> operationalRolePermissionMap = operationalRolePermissionService.getRepeat(operationalRolePermission);
		/* 检查数据是否存在 */
		if (operationalRolePermissionMap != null && !operationalRolePermissionMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		/* 新增 */
		if (operationalRolePermissionService.insert(operationalRolePermission) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalRoleId
	 * @param operationalPermissionId
	 * @param operationalRolePermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除机构角色权限关联表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/operational/role/{operationalRoleId}/permission/{operationalPermissionId}" })
	public JsonApi delete(@PathVariable("operationalRoleId") Integer operationalRoleId, @PathVariable("operationalPermissionId") Integer operationalPermissionId,
			@Validated({ BaseEntity.Delete.class }) OperationalRolePermission operationalRolePermission, BindingResult result) {
		operationalRolePermission.setOperationalRoleId(operationalRoleId);
		operationalRolePermission.setOperationalPermissionId(operationalPermissionId);
		/* 删除 */
		if (operationalRolePermissionService.delete(operationalRolePermission) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalRolePermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询该角色拥有和未拥有的权限
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/role/permissions" })
	public JsonApi getPermissionOperationIsChoose(@Validated({ OperationalRolePermission.GetPermissionOperationIsChoose.class }) OperationalRolePermission operationalRolePermission,
			BindingResult result) {
		Page<?> page = PageHelper.startPage(operationalRolePermission.getPage(), operationalRolePermission.getPageSize());
		List<Map<String, Object>> operationalRolePermissionList = operationalRolePermissionService.getPermissionOperationIsChoose(operationalRolePermission);
		if (operationalRolePermissionList != null && !operationalRolePermissionList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), operationalRolePermissionList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
