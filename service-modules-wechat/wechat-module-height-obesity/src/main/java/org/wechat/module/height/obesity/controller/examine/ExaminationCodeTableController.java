package org.wechat.module.height.obesity.controller.examine;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.height.obesity.entity.ExaminationCodeTable;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.service.ExaminationCodeTableService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月13日
 * @description: 检查检验码表
 */
@RestController
public class ExaminationCodeTableController {
	
	@Resource
	private ExaminationCodeTableService examinationCodeTableService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月12日
	 * @param examinationCodeTable
	 * @param result
	 * @return
	 * @description: 查询检查检验列表
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/examination/code/tables" })
	public JsonApi getList(@Validated(BaseEntity.SelectAll.class) ExaminationCodeTable examinationCodeTable,
			BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		Page<?> page = PageHelper.startPage(examinationCodeTable.getPage(), examinationCodeTable.getPageSize());
		List<Map<String, Object>> codeTableList = examinationCodeTableService.getList(examinationCodeTable);
		if (codeTableList != null && !codeTableList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), codeTableList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

}
