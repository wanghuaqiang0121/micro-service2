package org.web.module.height.obesity.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.ChildrenFamily;
import org.web.module.height.obesity.entity.ChildrenMeasure;
import org.web.module.height.obesity.entity.Diagnosis;
import org.web.module.height.obesity.entity.User;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.service.ChildrenFamilyService;
import org.web.module.height.obesity.service.ChildrenMeasureService;
import org.web.module.height.obesity.service.DiagnosisService;
import org.web.module.height.obesity.service.UserService;
import org.web.module.height.obesity.tools.HeightObesityCalculation;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ZhangGuangZhi
 * @date: 2018年12月18日
 * @description: 辅助诊疗信息
 */
@RestController
public class DiagnosisController {

	@Resource
	private DiagnosisService diagnosisService;
	@Resource
	private ChildrenMeasureService childrenMeasureService;
	@Resource
	private UserService userService;
	@Resource
	private ChildrenFamilyService childrenFamilyService;

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月18日
	 * @param userId            用户编号
	 * @param childrenMeasureId 测量记录编号
	 * @return
	 * @description: 身高直方图
	 */
	@RequiresAuthentication(ignore = false, value = {
			"web-module-height-obesity:diagnosis:chart:height" }, level = Level.OPERATION)
	@GetMapping(value = { "/diagnosis/chart/height" })
	public JsonApi getHeightChart(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis, BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {

		diagnosis = getUserDiagnosis(diagnosis);
		if (diagnosis == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		return new JsonApi(ApiCodeEnum.OK, diagnosisService.getHeightChart(diagnosis));
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月18日
	 * @param userId            用户编号
	 * @param childrenMeasureId 测量记录编号
	 * @return
	 * @description: 身高曲线图
	 */
	@RequiresAuthentication(ignore = false, value = {
			"web-module-height-obesity:diagnosis:chart:diagram:height" }, level = Level.OPERATION)
	@GetMapping(value = { "/diagnosis/chart/diagram/height" })
	public JsonApi getHeightDiagramChart(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis,
			BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {

		diagnosis = getUserDiagnosis(diagnosis);
		if (diagnosis == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		return new JsonApi(ApiCodeEnum.OK, diagnosisService.getHeightDiagramChart(diagnosis));
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月20日
	 * @param userId            用户编号
	 * @param childrenMeasureId 测量记录编号
	 * @return
	 * @description: 体重曲线图
	 */
	@RequiresAuthentication(ignore = false, value = {
			"web-module-height-obesity:diagnosis:chart:diagram:weight" }, level = Level.OPERATION)
	@GetMapping(value = { "/diagnosis/chart/diagram/weight" })
	public JsonApi getWeightDiagramChart(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis,
			BindingResult resul) {

		diagnosis = getUserDiagnosis(diagnosis);
		if (diagnosis == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}

		Map<String, Object> map = diagnosisService.getWeightDiagramChart(diagnosis);

		return new JsonApi(ApiCodeEnum.OK, map);
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月20日
	 * @param userId            用户编号
	 * @param childrenMeasureId 测量记录编号
	 * @return
	 * @description: 体重bmi曲线图
	 */
	@RequiresAuthentication(ignore = false, value = {
			"web-module-height-obesity:diagnosis:chart:diagram:bmi" }, level = Level.OPERATION)
	@GetMapping(value = { "/diagnosis/chart/diagram/bmi" })
	public JsonApi getBMIDiagramChart(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis,
			BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {

		diagnosis = getUserDiagnosis(diagnosis);
		if (diagnosis == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		return new JsonApi(ApiCodeEnum.OK, diagnosisService.getBMIDiagramChart(diagnosis));
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月18日
	 * @param userId            用户编号
	 * @param childrenMeasureId 测量记录编号
	 * @return
	 * @description:头围曲线图
	 */
	@RequiresAuthentication(ignore = false, value = {
			"web-module-height-obesity:diagnosis-diagram:headcircumference" }, level = Level.OPERATION)
	@GetMapping(value = { "/diagnosis/chart/diagram/head" })
	public JsonApi getHeadCircumferenceDiagramChart(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis,
			BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {

		diagnosis = getUserDiagnosis(diagnosis);
		if (diagnosis == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		return new JsonApi(ApiCodeEnum.OK, diagnosisService.getHeadCircumferenceDiagramChart(diagnosis));
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月19日
	 * @param userId            用户编号
	 * @param childrenMeasureId 测量记录编号
	 * @return
	 * @description:获取用户图表x轴
	 */
	@RequiresAuthentication(ignore = false, value = {
			"web-module-height-obesity:diagnosis:chart:diagram:x" }, level = Level.OPERATION)
	@GetMapping(value = { "/diagnosis/chart/diagram/linex" })
	public JsonApi getChartX(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis, BindingResult resul, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {

		diagnosis = getUserDiagnosis(diagnosis);
		if (diagnosis == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		return new JsonApi(ApiCodeEnum.OK,
				HeightObesityCalculation.getLinexByMonth(diagnosis.getStartMonthAge(), diagnosis.getEndMonthAge()));
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月19日
	 * @param userId            用户编号
	 * @param childrenMeasureId 测量记录编号
	 * @return
	 * @description:获取用户信息
	 */
	private Diagnosis getUserDiagnosis(Diagnosis diagnosis) {
		/* 查询用户信息 */
		User user = new User();
		user.setId(diagnosis.getUserId());
		Map<String, Object> userMap = userService.getOne(user);
		if (userMap == null || !userMap.containsKey("id")) {
			/* 用户信息不存在 */
			return null;
		}
		diagnosis.setSex(Integer.parseInt(userMap.get("sex").toString()));

		/* 查询家庭信息 */
		ChildrenFamily childrenFamily = new ChildrenFamily();
		childrenFamily.setUserId(diagnosis.getUserId());
		Map<String, Object> childrenFamilyMap = childrenFamilyService.getOne(childrenFamily);
		if (childrenFamilyMap != null && childrenFamilyMap.containsKey("id")) {
			diagnosis.setFatherHeight(new Double(childrenFamilyMap.get("fatherHeight").toString()));
			diagnosis.setMotherHeight(new Double(childrenFamilyMap.get("motherHeight").toString()));
		}

		ChildrenMeasure childrenMeasure = new ChildrenMeasure();
		Map<String, Object> childrenMeasureMap;

		if (diagnosis.getChildrenMeasureId() != null) {
			/* 查询记录 */
			childrenMeasure.setId(diagnosis.getChildrenMeasureId());
			childrenMeasureMap = childrenMeasureService.getOne(childrenMeasure);
		} else {
			/* 查询用户最新记录信息 */
			childrenMeasure.setUserId(diagnosis.getUserId());
			childrenMeasureMap = childrenMeasureService.getNewOne(childrenMeasure);
		}

		if (childrenMeasureMap == null || !childrenMeasureMap.containsKey("id")) {
			/* 用户测量信息不存在 */
			return null;
		}

		/* 查询相应的数据 测量信息 */
		diagnosis.setChildrenMeasureId((Integer) childrenMeasureMap.get("id"));
		diagnosis.setMonthAge(Integer.parseInt(childrenMeasureMap.get("monthAge").toString()));
		diagnosis.setHeight(new Double(childrenMeasureMap.get("currentHeight").toString()));
		diagnosis.setWeight(new Double(childrenMeasureMap.get("currentWeight").toString()));
		if (childrenMeasureMap.get("abdomenCircumference") != null) {
			diagnosis.setAbdomenCircumference(new Double(childrenMeasureMap.get("abdomenCircumference").toString()));
		}

		/* 根据月龄查询当前显示数据范围 */
		Map<String, Integer> monthAgeRange = HeightObesityCalculation.getMonthAgeRange(diagnosis.getMonthAge());
		diagnosis.setStartMonthAge(monthAgeRange.get("startMonthAge"));
		diagnosis.setEndMonthAge(monthAgeRange.get("endMonthAge"));
		if (childrenMeasureMap.get("idealHeight") != null) {
			diagnosis.setIdealHeight(new Double(childrenMeasureMap.get("idealHeight").toString()));
		}
		return diagnosis;
	}

}
