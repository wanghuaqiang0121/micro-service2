package org.web.module.base.service.permission.organization;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.organization.OperationalPermissionOperationMapper;
import org.web.module.base.domain.permission.organization.OperationalPermissionOperation;

@Service
public class OperationalPermissionOperationService {
	@Resource
	private  OperationalPermissionOperationMapper mapper;
	
	public Map<String, Object> getRepeat(OperationalPermissionOperation operationalPermissionOperation){
		return mapper.getRepeat(operationalPermissionOperation);
	}
	
	public int insert(OperationalPermissionOperation operationalPermissionOperation) {
		return mapper.insert(operationalPermissionOperation);
	}

	public int delete(OperationalPermissionOperation operationalPermissionOperation) {
		return mapper.delete(operationalPermissionOperation);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermissionOperation
	 * @return
	 * @description: 查询该权限拥有和未拥有的操作
	 */
	public List<Map<String, Object>> getPermissionOperationIsChoose(OperationalPermissionOperation operationalPermissionOperation) {
		return mapper.getPermissionOperationIsChoose(operationalPermissionOperation);
	}
}
