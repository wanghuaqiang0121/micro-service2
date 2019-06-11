package org.web.module.base.dao.permission.organization;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.base.domain.permission.organization.OperationalPermissionMenu;

public interface OperationalPermissionMenuMapper extends IBaseMapper<OperationalPermissionMenu>{
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermissionMenu
	 * @return
	 * @description: 查询该权限拥有和未拥有的菜单
	 */
 public  List<Map<String, Object>> getPermissionMenuIsChoose(OperationalPermissionMenu operationalPermissionMenu);
}
