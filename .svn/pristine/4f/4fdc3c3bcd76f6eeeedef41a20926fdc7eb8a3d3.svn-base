package org.web.module.base.service.data.currency;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.data.currency.CodeTableMapper;
import org.web.module.base.domain.data.currency.CodeTable;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月16日
 * @description: CodeTableService
 */
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
