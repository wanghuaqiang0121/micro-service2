package org.wechat.module.height.obesity.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.ChildrenMeasureMapper;
import org.wechat.module.height.obesity.dao.StandardHeightPercentileMapper;
import org.wechat.module.height.obesity.dao.WhoStandardBmiSdMapper;
import org.wechat.module.height.obesity.dao.WhoStandardHeadCircumferenceSdMapper;
import org.wechat.module.height.obesity.dao.WhoStandardWeightSdMapper;
import org.wechat.module.height.obesity.entity.ChildrenMeasure;
import org.wechat.module.height.obesity.entity.Diagnosis;
import org.wechat.module.height.obesity.entity.StandardHeightPercentile;
import org.wechat.module.height.obesity.entity.WhoStandardBmiSd;
import org.wechat.module.height.obesity.entity.WhoStandardWeightSd;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.tools.HeightObesityCalculation;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ZhangGuangZhi
 * @date: 2018年12月18日
 * @description: DiagnosisService 辅助诊疗功能
 */
@Service
public class DiagnosisService {

	@Resource
	private StandardHeightPercentileMapper standardHeightPercentileMapper;
	@Resource
	private ChildrenMeasureMapper childrenMeasureMapper;
	@Resource
	private WhoStandardHeadCircumferenceSdMapper whoStandardHeadCircumferenceSdMapper;
	@Resource
	private WhoStandardWeightSdMapper whoStandardWeightSdMapper;
	@Resource
	private WhoStandardBmiSdMapper whoStandardBmiSdMapper;

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月18日
	 * @param
	 * @return
	 * @description: 身高直方图
	 */
	public Map<String, Object> getHeightChart(Diagnosis diagnosis) {

		/* 返回对象 */
		Map<String, Object> map = new HashMap<String, Object>();

		/* 计算对应成年身高 */
		map.put("adultHeight", getAdultHeight(diagnosis));

		/* 理想身高 */
		map.put("idealHeight", diagnosis.getIdealHeight());

		/* 计算遗传身高 */
		map.put("geneticHeight", getGeneticHeight(diagnosis));

		/* 骨龄对应成年身高 */
		Map<String, Object>  resturMap=childrenMeasureMapper.getForecastHeight(diagnosis);

		if (resturMap!=null) {
			map.put("boneAgeHeight", resturMap.get("forecastHeight")==null ? null :resturMap.get("forecastHeight"));
		}else {
			/* 骨龄对应成年身高 */
			map.put("boneAgeHeight", null);
		}

		return map;
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月18日
	 * @param userId        用户编号
	 * @param sex           性别
	 * @param startMonthAge
	 * @param endMonthAge
	 * @return
	 * @description: 身高曲线图
	 */
	public Map<String, Object> getHeightDiagramChart(Diagnosis diagnosis) {

		/* 返回对象 */
		Map<String, Object> map = new HashMap<String, Object>();

		StandardHeightPercentile standardHeightPercentile = new StandardHeightPercentile();
		standardHeightPercentile.setSex(diagnosis.getSex());
		standardHeightPercentile.setStartMonthAge(diagnosis.getStartMonthAge());
		standardHeightPercentile.setEndMonthAge(diagnosis.getEndMonthAge());

		/* 身高 97号标准线 */
		standardHeightPercentile.setPercentile(97);
		map.put("ninetySeven", standardHeightPercentileMapper.getList(standardHeightPercentile));

		/* 身高 90号标准线 */
		standardHeightPercentile.setPercentile(90);
		map.put("ninety", standardHeightPercentileMapper.getList(standardHeightPercentile));

		/* 身高 75号标准线 */
		standardHeightPercentile.setPercentile(75);
		map.put("seventyFive", standardHeightPercentileMapper.getList(standardHeightPercentile));

		/* 身高 50号标准线 */
		standardHeightPercentile.setPercentile(50);
		map.put("fifty", standardHeightPercentileMapper.getList(standardHeightPercentile));

		/* 身高 25号标准线 */
		standardHeightPercentile.setPercentile(25);
		map.put("twentyFive", standardHeightPercentileMapper.getList(standardHeightPercentile));

		/* 身高 10号标准线 */
		standardHeightPercentile.setPercentile(10);
		map.put("ten", standardHeightPercentileMapper.getList(standardHeightPercentile));

		/* 身高 3号标准线 */
		standardHeightPercentile.setPercentile(3);
		map.put("three", standardHeightPercentileMapper.getList(standardHeightPercentile));

		/* 身高 用户线 */
		ChildrenMeasure childrenMeasure = new ChildrenMeasure();
		childrenMeasure.setUserId(diagnosis.getUserId());
		childrenMeasure.setStartMonthAge(diagnosis.getStartMonthAge());
		childrenMeasure.setEndMonthAge(diagnosis.getEndMonthAge());

		List<Map<String, Object>> childrenMeasureList = childrenMeasureMapper.getListByMonthAge(childrenMeasure);
		List<Map<String, Object>> lineUser = new ArrayList<>();

		for (Map<String, Object> childrenMeasureMap : childrenMeasureList) {

			Map<String, Object> children = new HashMap<>();

			/* 查询用户数据所处百分位 */
			standardHeightPercentile.setMonthAge(diagnosis.getMonthAge());
			standardHeightPercentile
					.setStandardValue(new BigDecimal(childrenMeasureMap.get("currentHeight").toString()));
			Map<String, Object> resultMap = standardHeightPercentileMapper.getPercentile(standardHeightPercentile);

			children.put("monthAge", childrenMeasureMap.get("monthAge"));
			children.put("currentHeight", childrenMeasureMap.get("currentHeight"));
			if (resultMap != null && resultMap.containsKey("percentile")) {

				if (((Integer) resultMap.get("percentile")).intValue() == BaseGlobal.MAX_HEIGHT_PERCENTILE
						&& ((BigDecimal) resultMap.get("standardValue"))
								.doubleValue() < (new Double(childrenMeasureMap.get("currentHeight").toString())
										.doubleValue())) {
					/* 查询用户值是否高于最高值 */
					children.put("operator", BaseGlobal.GREATER_THAN);
				} else if (((Integer) resultMap.get("percentile")).intValue() == BaseGlobal.MIN_HEIGHT_PERCENTILE
						&& ((BigDecimal) resultMap.get("standardValue"))
								.doubleValue() > (new Double(childrenMeasureMap.get("currentHeight").toString())
										.doubleValue())) {
					/* 查询用户值是否低于最低值 */
					children.put("operator", BaseGlobal.LESS_THAN);
				} else {
					children.put("operator", BaseGlobal.EQUAL);
				}
				children.put("percentile", resultMap.get("percentile"));
			}

			lineUser.add(children);
		}

		map.put("lineUser", lineUser);
		return map;
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月18日
	 * @param
	 * @return
	 * @description: 体重曲线图
	 */
	public Map<String, Object> getWeightDiagramChart(Diagnosis diagnosis) {

		/* 返回对象 */
		Map<String, Object> map = new HashMap<String, Object>();

		/* 判断应该以体重标准或bmi标准 0-60体重标准 61-218bmi标准 */
		if (diagnosis.getEndMonthAge() >= 61) {
			Map<String, Integer> monthAgeMap = HeightObesityCalculation.getMonthAgeRange(60);
			diagnosis.setStartMonthAge(Integer.parseInt(monthAgeMap.get("startMonthAge").toString()));
			diagnosis.setEndMonthAge(Integer.parseInt(monthAgeMap.get("endMonthAge").toString()));
		}

		/* 体重标准线who */
		WhoStandardWeightSd whoStandardWeightSd = new WhoStandardWeightSd();
		whoStandardWeightSd.setSex(diagnosis.getSex());
		whoStandardWeightSd.setStartMonthAge(diagnosis.getStartMonthAge());
		whoStandardWeightSd.setEndMonthAge(diagnosis.getEndMonthAge());

		/*-3SD*/
		whoStandardWeightSd.setSd(-3);
		map.put("subThree", whoStandardWeightSdMapper.getList(whoStandardWeightSd));

		/*-2SD*/
		whoStandardWeightSd.setSd(-2);
		map.put("subTwo", whoStandardWeightSdMapper.getList(whoStandardWeightSd));

		/*-1SD*/
		whoStandardWeightSd.setSd(-1);
		map.put("subOne", whoStandardWeightSdMapper.getList(whoStandardWeightSd));

		/* 0SD */
		whoStandardWeightSd.setSd(0);
		map.put("median", whoStandardWeightSdMapper.getList(whoStandardWeightSd));

		/* 1SD */
		whoStandardWeightSd.setSd(1);
		map.put("plusOne", whoStandardWeightSdMapper.getList(whoStandardWeightSd));

		/* 2SD */
		whoStandardWeightSd.setSd(2);
		map.put("plusTwo", whoStandardWeightSdMapper.getList(whoStandardWeightSd));

		/* 3SD */
		whoStandardWeightSd.setSd(3);
		map.put("plusThree", whoStandardWeightSdMapper.getList(whoStandardWeightSd));

		/* 查询用户体重数据 */
		ChildrenMeasure childrenMeasure = new ChildrenMeasure();
		childrenMeasure.setUserId(diagnosis.getUserId());
		childrenMeasure.setStartMonthAge(diagnosis.getStartMonthAge());
		childrenMeasure.setEndMonthAge(diagnosis.getEndMonthAge());

		List<Map<String, Object>> list = childrenMeasureMapper.getListByMonthAge(childrenMeasure);
		for (Map<String, Object> userWeightMap : list) {

			WhoStandardWeightSd whoStandardWeightSdModel = new WhoStandardWeightSd();
			whoStandardWeightSdModel.setSex(diagnosis.getSex());
			whoStandardWeightSdModel.setMonthAge((Integer) userWeightMap.get("monthAge"));
			whoStandardWeightSdModel.setStandardValue(new BigDecimal(userWeightMap.get("currentWeight").toString()));

			Map<String, Object> whoStandardWeightSdMap = whoStandardWeightSdMapper
					.getWeightSd(whoStandardWeightSdModel);

			Integer sd = (Integer) whoStandardWeightSdMap.get("sd");
			userWeightMap.put("sd", sd);

			if (sd == -3 && ((BigDecimal) whoStandardWeightSdMap.get("standardValue"))
					.compareTo(new BigDecimal(diagnosis.getWeight())) == 1) {
				/* 判断是否小于最低 */
				userWeightMap.put("operator", BaseGlobal.LESS_THAN);
			} else if (sd == 3 && ((BigDecimal) whoStandardWeightSdMap.get("standardValue"))
					.compareTo(new BigDecimal(diagnosis.getWeight())) == -1) {
				userWeightMap.put("operator", BaseGlobal.GREATER_THAN);
			} else {
				userWeightMap.put("operator", BaseGlobal.EQUAL);
			}

		}
		map.put("lineUser", list);

		return map;
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月18日
	 * @param
	 * @return
	 * @description: BMI曲线图
	 */
	public Map<String, Object> getBMIDiagramChart(Diagnosis diagnosis) {
		/* 返回对象 */
		Map<String, Object> map = new HashMap<String, Object>();

		/* 查询标准线 */
		WhoStandardBmiSd whoStandardBmiSd = new WhoStandardBmiSd();
		whoStandardBmiSd.setSex(diagnosis.getSex());
		if (diagnosis.getMonthAge() > 60 && diagnosis.getMonthAge() <= 84) {
			whoStandardBmiSd.setStartMonthAge(61);
			whoStandardBmiSd.setEndMonthAge(84);
		} else {
			whoStandardBmiSd.setStartMonthAge(diagnosis.getStartMonthAge());
			whoStandardBmiSd.setEndMonthAge(diagnosis.getEndMonthAge());
		}

		/*-3SD*/
		whoStandardBmiSd.setSd(-3);
		map.put("subThree", whoStandardBmiSdMapper.getList(whoStandardBmiSd));

		/*-2SD*/
		whoStandardBmiSd.setSd(-2);
		map.put("subTwo", whoStandardBmiSdMapper.getList(whoStandardBmiSd));

		/*-1SD*/
		whoStandardBmiSd.setSd(-1);
		map.put("subOne", whoStandardBmiSdMapper.getList(whoStandardBmiSd));

		/* 0SD */
		whoStandardBmiSd.setSd(0);
		map.put("median", whoStandardBmiSdMapper.getList(whoStandardBmiSd));

		/* 1SD */
		whoStandardBmiSd.setSd(1);
		map.put("plusOne", whoStandardBmiSdMapper.getList(whoStandardBmiSd));

		/* 2SD */
		whoStandardBmiSd.setSd(2);
		map.put("plusTwo", whoStandardBmiSdMapper.getList(whoStandardBmiSd));

		/* 3SD */
		whoStandardBmiSd.setSd(3);
		map.put("plusThree", whoStandardBmiSdMapper.getList(whoStandardBmiSd));

		/* 查询用户体重数据 */
		ChildrenMeasure childrenMeasure = new ChildrenMeasure();
		childrenMeasure.setUserId(diagnosis.getUserId());

		if (diagnosis.getMonthAge() > 60 && diagnosis.getMonthAge() <= 84) {
			childrenMeasure.setStartMonthAge(61);
			childrenMeasure.setEndMonthAge(84);
		} else {
			childrenMeasure.setStartMonthAge(diagnosis.getStartMonthAge());
			childrenMeasure.setEndMonthAge(diagnosis.getEndMonthAge());
		}

		List<Map<String, Object>> list = childrenMeasureMapper.getListByMonthAge(childrenMeasure);
		for (Map<String, Object> userWeightMap : list) {

			WhoStandardBmiSd whoStandardBmiSdMap = new WhoStandardBmiSd();
			whoStandardBmiSdMap.setSex(diagnosis.getSex());
			whoStandardBmiSdMap.setMonthAge((Integer) userWeightMap.get("monthAge"));
			whoStandardBmiSdMap.setStandardValue(new BigDecimal(userWeightMap.get("currentWeight").toString()));

			Map<String, Object> whoStandardBmiSdMaps = whoStandardBmiSdMapper.getBmiSd(whoStandardBmiSdMap);
			if (whoStandardBmiSdMaps == null) {
				break;
			}
			Integer sd = (Integer) whoStandardBmiSdMaps.get("sd");
			userWeightMap.put("sd", sd);

			if (sd == -3 && ((BigDecimal) whoStandardBmiSdMaps.get("standardValue"))
					.compareTo(new BigDecimal(diagnosis.getWeight())) == 1) {
				/* 判断是否小于最低 */
				userWeightMap.put("operator", BaseGlobal.LESS_THAN);
			} else if (sd == 3 && ((BigDecimal) whoStandardBmiSdMaps.get("standardValue"))
					.compareTo(new BigDecimal(diagnosis.getWeight())) == -1) {
				userWeightMap.put("operator", BaseGlobal.GREATER_THAN);
			} else {
				userWeightMap.put("operator", BaseGlobal.EQUAL);
			}

		}
		map.put("lineUser", list);

		return map;
	}

	// /**
	// * @author: ZhangGuangZhi
	// * @date: 2018年12月18日
	// * @param
	// * @return
	// * @description: 头围曲线图
	// */
	// public Map<String, Object> getHeadCircumferenceDiagramChart(Diagnosis
	// diagnosis) {

	// /* 返回对象 */
	// Map<String, Object> map = new HashMap<String, Object>();

	// WhoStandardHeadCircumferenceSd whoStandardHeadCircumferenceSd = new
	// WhoStandardHeadCircumferenceSd();
	// whoStandardHeadCircumferenceSd.setSex(diagnosis.getSex());
	// whoStandardHeadCircumferenceSd.setStartMonthAge(diagnosis.getStartMonthAge());
	// whoStandardHeadCircumferenceSd.setEndMonthAge(diagnosis.getEndMonthAge());
	// if (diagnosis.getMonthAge() > 60) {
	// whoStandardHeadCircumferenceSd.setStartMonthAge(36);
	// whoStandardHeadCircumferenceSd.setEndMonthAge(60);
	// }

	// /* 查询-3SD标准线 */
	// whoStandardHeadCircumferenceSd.setSd(-3);
	// map.put("subThree",
	// whoStandardHeadCircumferenceSdMapper.getList(whoStandardHeadCircumferenceSd));

	// /* 查询-2SD标准线 */
	// whoStandardHeadCircumferenceSd.setSd(-2);
	// map.put("subTwo",
	// whoStandardHeadCircumferenceSdMapper.getList(whoStandardHeadCircumferenceSd));

	// /* 查询-1SD标准线 */
	// whoStandardHeadCircumferenceSd.setSd(-1);
	// map.put("subOne",
	// whoStandardHeadCircumferenceSdMapper.getList(whoStandardHeadCircumferenceSd));

	// /* 查询0SD标准线 */
	// whoStandardHeadCircumferenceSd.setSd(0);
	// map.put("median",
	// whoStandardHeadCircumferenceSdMapper.getList(whoStandardHeadCircumferenceSd));

	// /* 查询1SD标准线 */
	// whoStandardHeadCircumferenceSd.setSd(1);
	// map.put("plusOne",
	// whoStandardHeadCircumferenceSdMapper.getList(whoStandardHeadCircumferenceSd));

	// /* 查询2SD标准线 */
	// whoStandardHeadCircumferenceSd.setSd(2);
	// map.put("plusTwo",
	// whoStandardHeadCircumferenceSdMapper.getList(whoStandardHeadCircumferenceSd));

	// /* 查询3SD标准线 */
	// whoStandardHeadCircumferenceSd.setSd(3);
	// map.put("plusThree",
	// whoStandardHeadCircumferenceSdMapper.getList(whoStandardHeadCircumferenceSd));

	// /* 查询用户线 */
	// ChildrenMeasure childrenMeasure = new ChildrenMeasure();
	// childrenMeasure.setUserId(diagnosis.getUserId());
	// if (diagnosis.getMonthAge() > 60) {
	// childrenMeasure.setStartMonthAge(36);
	// childrenMeasure.setEndMonthAge(60);
	// } else {
	// childrenMeasure.setStartMonthAge(diagnosis.getStartMonthAge());
	// childrenMeasure.setEndMonthAge(diagnosis.getEndMonthAge());
	// }

	// List<Map<String, Object>> list =
	// childrenMeasureMapper.getListByMonthAge(childrenMeasure);
	// for (Map<String, Object> userWeightMap : list) {

	// WhoStandardHeadCircumferenceSd whoStandardHeadCircumferenceSdModel = new
	// WhoStandardHeadCircumferenceSd();
	// whoStandardHeadCircumferenceSdModel.setSex(diagnosis.getSex());
	// whoStandardHeadCircumferenceSdModel.setMonthAge((Integer)
	// userWeightMap.get("monthAge"));
	// whoStandardHeadCircumferenceSdModel
	// .setStandardValue(new
	// BigDecimal(userWeightMap.get("currentWeight").toString()));

	// Map<String, Object> whoStandardHeadCircumferenceSdMap =
	// whoStandardHeadCircumferenceSdMapper
	// .getHeadCircumferenceSd(whoStandardHeadCircumferenceSdModel);

	// Integer sd = (Integer) whoStandardHeadCircumferenceSdMap.get("sd");
	// userWeightMap.put("sd", sd);

	// if (sd == -3 && ((BigDecimal)
	// whoStandardHeadCircumferenceSdMap.get("standardValue"))
	// .compareTo(new BigDecimal(diagnosis.getWeight())) == 1) {
	// /* 判断是否小于最低 */
	// userWeightMap.put("operator", BaseGlobal.LESS_THAN);
	// } else if (sd == 3 && ((BigDecimal)
	// whoStandardHeadCircumferenceSdMap.get("standardValue"))
	// .compareTo(new BigDecimal(diagnosis.getWeight())) == -1) {
	// userWeightMap.put("operator", BaseGlobal.GREATER_THAN);
	// } else {
	// userWeightMap.put("operator", BaseGlobal.EQUAL);
	// }

	// }
	// map.put("lineUser", list);

	// return map;
	// }

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月18日
	 * @param sex      性别
	 * @param monthAge 月龄
	 * @param height   当前身高
	 * @return
	 * @description: 年龄对应成年身高
	 */
	public double getAdultHeight(Diagnosis diagnosis) {
		double adultHeight = 0;
		StandardHeightPercentile standardHeightPercentile = new StandardHeightPercentile();
		standardHeightPercentile.setSex(diagnosis.getSex());
		standardHeightPercentile.setMonthAge(diagnosis.getMonthAge());
		standardHeightPercentile.setStandardValue(new BigDecimal(diagnosis.getHeight()));

		/* 查询当前身高所在百分位 */
		Map<String, Object> standardHeightPercentileMap = standardHeightPercentileMapper
				.getPercentile(standardHeightPercentile);
		if (standardHeightPercentileMap != null && standardHeightPercentileMap.containsKey("percentile")) {

			/* 查询对应成年身高 */
			standardHeightPercentile.setMonthAge(BaseGlobal.MAX_MONTH_AGE);
			standardHeightPercentile
					.setPercentile(Integer.parseInt(standardHeightPercentileMap.get("percentile").toString()));
			Map<String, Object> standardHeightPercentileMapHeight = standardHeightPercentileMapper
					.getOne(standardHeightPercentile);
			if (standardHeightPercentileMapHeight != null
					&& standardHeightPercentileMapHeight.containsKey("standardValue")) {
				adultHeight = Double.parseDouble(standardHeightPercentileMapHeight.get("standardValue").toString());
			}
		}
		return adultHeight;
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月18日
	 * @param sex          性别
	 * @param fatherHeight 父亲身高
	 * @param motherHeight 母亲身高
	 * @return
	 * @description: 计算遗传身高
	 */
	public double getGeneticHeight(Diagnosis diagnosis) {

		double geneticHeight = 0;
		if (diagnosis.getSex() == 1) {
			/* 男 (父亲身高+母亲身高+12)/2 */
			geneticHeight = (diagnosis.getFatherHeight() + diagnosis.getMotherHeight() + 12) / 2;
		} else if (diagnosis.getSex() == 2) {
			/* 女 (父亲身高+母亲身高-12)/2 */
			geneticHeight = (diagnosis.getFatherHeight() + diagnosis.getMotherHeight() - 12) / 2;
		}
		return geneticHeight;
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月18日
	 * @return
	 * @description: 年龄别体重
	 */
	public double getAgeWeight(Diagnosis diagnosis) {
		// if (diagnosis.getMonthAge() > 60) {
		// /* bmi表 */
		// } else {
		// /* 体重标准表 */
		// WhoStandardWeightSd whoStandardWeightSdModel = new WhoStandardWeightSd();
		// whoStandardWeightSdModel.setSex(diagnosis.getSex());
		// whoStandardWeightSdModel.setMonthAge(diagnosis.getMonthAge());
		// whoStandardWeightSdModel.setStandardValue(new
		// BigDecimal(diagnosis.getWeight()));

		// Map<String, Object> whoStandardWeightSdMap = whoStandardWeightSdMapper
		// .getWeightSd(whoStandardWeightSdModel);

		// Integer sd = (Integer) whoStandardWeightSdMap.get("sd");
		// return sd;
		// }
		return 0;
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月21日
	 * @param height 身高
	 * @param weight 体重
	 * @return
	 * @description: 计算bmi
	 */
	public double getBMI(double height, double weight) {
		double f = weight / (Math.pow(height / 100, 2));
		BigDecimal bg = new BigDecimal(f);
		return bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
