package org.web.module.base.service.permission.organization;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.permission.organization.OperationalOperationMapper;
import org.web.module.base.domain.permission.organization.OperationalOperation;

@Service
public class OperationalOperationService {
	@Resource
	private OperationalOperationMapper operationalOperationMapper;
	
	public	int insert(OperationalOperation operationalOperation) {
		return operationalOperationMapper.insert(operationalOperation);

	}

	public	int update(OperationalOperation operationalOperation) {
		return operationalOperationMapper.update(operationalOperation);
	}

	public	int delete(OperationalOperation operationalOperation) {
		return operationalOperationMapper.delete(operationalOperation);
	}

	public	Map<String, Object> getRepeat(OperationalOperation operationalOperation) {
		return operationalOperationMapper.getRepeat(operationalOperation);
	}

	public	Map<String, Object> getOne(OperationalOperation operationalOperation) {
		return operationalOperationMapper.getOne(operationalOperation);
	}

	public	List<Map<String, Object>> getList(OperationalOperation operationalOperation){
		return operationalOperationMapper.getList(operationalOperation);
	}

}
