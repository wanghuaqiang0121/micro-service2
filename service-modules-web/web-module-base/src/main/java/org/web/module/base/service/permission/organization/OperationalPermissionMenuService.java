package org.web.module.base.service.permission.organization;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.organization.OperationalPermissionMenuMapper;
import org.web.module.base.domain.permission.organization.OperationalPermissionMenu;

@Service
public class OperationalPermissionMenuService {
	@Resource
	private OperationalPermissionMenuMapper mapper;
	
	public Map<String, Object> getRepeat(OperationalPermissionMenu operationalPermissionMenu){
		return mapper.getRepeat(operationalPermissionMenu);
	}

	public int insert(OperationalPermissionMenu operationalPermissionMenu) {
		return mapper.insert(operationalPermissionMenu);
	}

	public int delete(OperationalPermissionMenu operationalPermissionMenu) {
		return mapper.delete(operationalPermissionMenu);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermissionMenu
	 * @return
	 * @description:  查询该权限拥有和未拥有的菜单
	 */
	public List<Map<String, Object>> getPermissionMenuIsChoose(OperationalPermissionMenu operationalPermissionMenu) {
		return mapper.getPermissionMenuIsChoose(operationalPermissionMenu);
	}
}
