package org.web.module.height.obesity.controller.configuration;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.DrugsConfig;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.service.DrugsConfigService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月19日
 * @description: 药品信息配置表
 */
@RestController
public class DrugsConfigController {
	
	@Resource
	private DrugsConfigService  drugsConfigService;

	/**
	 * @author: ChenYan
	 * @date: 2018年12月19日
	 * @param nutritionConfig
	 * @param result
	 * @return
	 * @description: 新增药品信息配置
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-height-obesity:drugs-config:insert" })
	@PostMapping(value = { "/drugs/config" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody DrugsConfig drugsConfig, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		if (drugsConfigService.insert(drugsConfig) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月19日
	 * @param id
	 * @param nutritionConfig
	 * @param result
	 * @return
	 * @description: 修改药品信息配置
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-height-obesity:drugs-config:update" })
	@PutMapping(value = { "/drugs/config/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody DrugsConfig drugsConfig, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		drugsConfig.setId(id);
		/* 检查数据是否存在 */
		Map<String, Object> drugsConfigMap = drugsConfigService.getOne(drugsConfig);
		if (drugsConfigMap == null ) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		if (drugsConfigService.update(drugsConfig) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月19日
	 * @param id
	 * @param nutritionConfig
	 * @param result
	 * @return
	 * @description: 查询药品信息配置详情
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-height-obesity:drugs-config:get-detail" })
	@GetMapping(value = { "/drugs/config/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) DrugsConfig drugsConfig, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		drugsConfig.setId(id);
		Map<String, Object> drugsConfigMap = drugsConfigService.getOne(drugsConfig);
		if (drugsConfigMap != null ) {
			return new JsonApi(ApiCodeEnum.OK, drugsConfigMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年12月19日
	 * @param id
	 * @param nutritionConfig
	 * @param result
	 * @return
	 * @description: 删除药品信息配置
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-height-obesity:drugs-config:delete" })
	@DeleteMapping(value = { "/drugs/config/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id, @Validated({ BaseEntity.Delete.class }) DrugsConfig drugsConfig, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		drugsConfig.setId(id);
		/* 检查数据是否存在 */
		Map<String, Object> drugsConfigMap = drugsConfigService.getOne(drugsConfig);
		if (drugsConfigMap == null || drugsConfigMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		if (drugsConfigService.delete(drugsConfig) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月19日
	 * @param drugsConfig
	 * @param result
	 * @return
	 * @description: 查询药品信息配置列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-height-obesity:drugs-config:get-list" })
	@GetMapping(value = { "/drugs/configs" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class })  DrugsConfig drugsConfig, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		Page<?> page = PageHelper.startPage(drugsConfig.getPage(), drugsConfig.getPageSize());
		List<Map<String, Object>> drugsConfigList = drugsConfigService.getList(drugsConfig);
		if (drugsConfigList != null && !drugsConfigList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), drugsConfigList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
