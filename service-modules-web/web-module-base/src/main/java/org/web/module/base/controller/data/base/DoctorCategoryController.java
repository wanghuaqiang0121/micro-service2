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
import org.web.module.base.domain.data.base.DoctorCategory;
import org.web.module.base.service.data.base.DoctorCategoryService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月15日
 * @description: DoctorCategoryController
 */
@RestController
public class DoctorCategoryController {

	@Resource
	private DoctorCategoryService doctorCategoryService;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月15日
	 * @param doctorCategory
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 获取列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-base:doctor-category:get-list" }, level = Level.OPERATION)
	@GetMapping(value = { "/doctor/categorys" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) DoctorCategory doctorCategory, BindingResult result) {
		Page<?> page = PageHelper.startPage(doctorCategory.getPage(), doctorCategory.getPageSize());
		List<Map<String, Object>> doctorCategoryList = doctorCategoryService.getList(doctorCategory);
		if (doctorCategoryList != null && !doctorCategoryList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), doctorCategoryList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
