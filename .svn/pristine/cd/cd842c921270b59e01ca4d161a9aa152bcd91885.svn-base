package org.wechat.module.height.obesity.controller.food;

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
import org.wechat.module.height.obesity.entity.FoodComposition;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.service.FoodCompositionService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月14日
 * @description: 食物成份表
 */
@RestController
public class FoodCompositionController {
	@Resource
	private FoodCompositionService  foodCompositionService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月14日
	 * @param foodComposition
	 * @param result
	 * @return
	 * @description: 食物成份列表
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/food/compositions" })
	public JsonApi getList(@Validated(BaseEntity.SelectAll.class) FoodComposition foodComposition,BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		Page<?> page = PageHelper.startPage(foodComposition.getPage(), foodComposition.getPageSize());
		List<Map<String, Object>> foodCompositionList = foodCompositionService.getList(foodComposition);
		if (foodCompositionList != null && !foodCompositionList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), foodCompositionList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}


}
