package org.web.module.base.controller.data.base;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.data.base.BusinessType;
import org.web.module.base.service.data.base.BusinessTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月15日
 * @description: BusinessTypeController
 */
@RestController
public class BusinessTypeController {

	@Resource
	private BusinessTypeService businessTypeService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月19日
	 * @param businessType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询业务类型列表
	 */
	@RequiresAuthentication(authc = true, level = Level.OPERATION)
	@GetMapping(value = { "/business/types" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) BusinessType businessType, BindingResult result) {
		Page<?> page = PageHelper.startPage(businessType.getPage(), businessType.getPageSize());
		List<Map<String, Object>> businessTypeList = businessTypeService.getList(businessType);
		if (businessTypeList != null && !businessTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), businessTypeList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
