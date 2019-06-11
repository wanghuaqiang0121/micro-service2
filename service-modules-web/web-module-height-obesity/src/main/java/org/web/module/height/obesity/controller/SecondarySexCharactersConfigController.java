package org.web.module.height.obesity.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity.SelectAll;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.SecondarySexCharactersConfig;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.service.SecondarySexCharactersConfigService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: WangHuaQiang
 * @date: 2018年12月26日
 * @description: 第二性征配置信息
 */
@RestController
public class SecondarySexCharactersConfigController {

	@Resource
	private SecondarySexCharactersConfigService secondarySexCharactersConfigService;
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月26日
	 * @param secondarySexCharactersConfig
	 * @param result
	 * @return
	 * @description: 第二性征配置列表
	 */
	@RequiresAuthentication(ignore = false, value = { "web-module-height-obesity:sexCharacters-config:get-list" },level=Level.OPERATION)
	@GetMapping("/secondary/sexCharacters/configs")
	public JsonApi getList(@Validated({ SelectAll.class }) SecondarySexCharactersConfig secondarySexCharactersConfig, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		Page<?> page = PageHelper.startPage(secondarySexCharactersConfig.getPage(), secondarySexCharactersConfig.getPageSize());
		List<Map<String, Object>> secondarySexCharactersConfigList = secondarySexCharactersConfigService.getList(secondarySexCharactersConfig);
		if (secondarySexCharactersConfigList != null && !secondarySexCharactersConfigList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), secondarySexCharactersConfigList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}	
}
