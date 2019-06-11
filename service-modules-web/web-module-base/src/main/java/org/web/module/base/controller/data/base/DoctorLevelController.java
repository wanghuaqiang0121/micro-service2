package org.web.module.base.controller.data.base;

import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.data.base.DoctorLevel;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.data.base.DoctorLevelService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 医生职称（医生级别）
 */
@RestController
public class DoctorLevelController {

	@Resource
	private DoctorLevelService doctorLevelService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param doctorLevel
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增医生职称（医生级别）
	 */
	@RequiresAuthentication(value = { "web-module-base:doctor-level:insert" }, level = Level.OPERATION)
	@PostMapping(value = { "/doctor/level" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) DoctorLevel doctorLevel, BindingResult result) {
		/* 判断是否重复 */
		Map<String, Object> doctorLevelMap = doctorLevelService.getRepeat(doctorLevel);
		if (doctorLevelMap != null && !doctorLevelMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("doctorLevel.name.is.conflict"));
		}
		/* 设置创建时间 */
		doctorLevel.setCreateDate(new Date());
		if (doctorLevelService.insert(doctorLevel) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月15日
	 * @param id
	 * @param doctorLevel
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改医生级别
	 */
	@RequiresAuthentication(value = { "web-module-base:doctor-level:update" }, level = Level.OPERATION)
	@PutMapping(value = { "/doctor/level/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @RequestBody @Validated({ BaseEntity.Update.class }) DoctorLevel doctorLevel, BindingResult result) {
		/* 判断数据是否存在 */
		doctorLevel.setId(id);
		Map<String, Object> doctorLevelMap = doctorLevelService.getOne(doctorLevel);
		if (doctorLevelMap == null || doctorLevelMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 判断数据是否重复 */
		Map<String, Object> doctorLevelRepeatMap = doctorLevelService.getRepeat(doctorLevel);
		if (doctorLevelRepeatMap != null && !doctorLevelRepeatMap.isEmpty()) {
			Integer doctorId = (Integer) doctorLevelRepeatMap.get("id");
			if (doctorId.intValue() != id.intValue()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("doctorLevel.name.is.conflict"));
			}
		}
		if (doctorLevelService.update(doctorLevel) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月15日
	 * @param doctorLevel
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询医生级别列表
	 */
	@RequiresAuthentication(value = { "web-module-base:doctor-level:get-list" }, level = Level.OPERATION)
	@GetMapping(value = { "/doctor/levels" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) DoctorLevel doctorLevel, BindingResult result) {
		Page<?> page = PageHelper.startPage(doctorLevel.getPage(), doctorLevel.getPageSize());
		List<Map<String, Object>> doctorLevelList = doctorLevelService.getList(doctorLevel);
		if (doctorLevelList != null && !doctorLevelList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), doctorLevelList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
