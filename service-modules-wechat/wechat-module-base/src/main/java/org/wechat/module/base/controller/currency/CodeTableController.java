package org.wechat.module.base.controller.currency;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.base.domain.currency.CodeTable;
import org.wechat.module.base.service.currency.CodeTableService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月31日
 * @description: 码表
 */
@RestController
public class CodeTableController {

	@Resource
	private CodeTableService codeTableService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月18日
	 * @param id
	 * @param codeTable
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 码表详情
	 */
	@RequiresAuthentication(authc = true, value = {})
	@GetMapping(value = { "/code/table/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id,
			@Validated(BaseEntity.SelectOne.class) CodeTable codeTable, BindingResult result) {
		codeTable.setId(id);
		Map<String, Object> codeTableMap = codeTableService.getOne(codeTable);
		if (codeTableMap!=null && !codeTableMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, codeTableMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月18日
	 * @param codeTable
	 * @param result
	 * @return  {@link JsonApi}
	 * @description:  查询码表列表
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/code/tables" })
	public JsonApi getList(@Validated(BaseEntity.SelectAll.class) CodeTable codeTable,
			BindingResult result) {
		Page<?> page = PageHelper.startPage(codeTable.getPage(), codeTable.getPageSize());
		List<Map<String, Object>> codeTableList = codeTableService.getList(codeTable);
		if (codeTableList != null && !codeTableList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), codeTableList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
