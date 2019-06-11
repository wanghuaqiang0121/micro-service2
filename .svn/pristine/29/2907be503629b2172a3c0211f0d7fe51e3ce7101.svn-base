package org.wechat.module.base.service.currency;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.base.dao.currency.CodeTableMapper;
import org.wechat.module.base.domain.currency.CodeTable;

@Service
public class CodeTableService {

	@Resource
	private CodeTableMapper codeTableMapper;

	public Map<String, Object> getOne( CodeTable codeTable) {
		return codeTableMapper.getOne(codeTable);
	}

	public List<Map<String, Object>> getList(CodeTable codeTable) {
		return codeTableMapper.getList(codeTable);
	}
}
