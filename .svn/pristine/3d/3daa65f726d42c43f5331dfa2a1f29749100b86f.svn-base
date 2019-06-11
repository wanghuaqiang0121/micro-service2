package org.web.module.height.obesity.tools;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ZhangGuangZhi
 * @date: 2018年12月19日
 * @description: 身高肥胖系统公共类
 */
public class HeightObesityCalculation {
	
	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月19日
	 * @param monthAge 用户月龄
	 * @return
	 * @description: 身高曲线图
	 */
	public static Map<String, Integer> getMonthAgeRange(Integer monthAge) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		if (monthAge > 216) {
			monthAge = 216;
		}
		if (monthAge > 24) {
			map.put("startMonthAge", monthAge - 24 + 1);
			map.put("endMonthAge", monthAge);
		} else {
			map.put("startMonthAge", 0);
			map.put("endMonthAge", 24);
		}
		return map;
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月19日
	 * @param monthAge 用户月龄
	 * @return
	 * @description: 根据月龄返回用户图片x轴
	 */
	public static List<Map<String, Integer>> getLinexByMonth(Integer startMonthAge, Integer endMonthAge) {
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		for (int i = startMonthAge; i <= endMonthAge; i++) {
			Map<String, Integer> map = new HashMap<>();
			map.put("monthAge", i);
			list.add(map);
		}
		return list;
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月24日
	 * @param age 用户年龄
	 * @return
	 * @description: 根据年龄返回称呼
	 */
	public static String getName(double age) {
		String name = "";
		if (age > 0 && age < 1) {
			name = "小宝宝";
		} else if (age >= 1 && age < 7) {
			name = "小朋友";
		} else if (age >= 7 && age <= 18) {
			name = "同学";
		}
		return name;
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月24日
	 * @param monthAge 用户月龄
	 * @return
	 * @description: 根据月龄返回年龄
	 */
	public static double getAge(int monthAge) {
		double value = (double) monthAge / 12.00;
		BigDecimal b = new BigDecimal(value);
		return b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月1日
	 * @param age
	 * @return
	 * @description: 根据年龄算月龄
	 */
	public static int getMonthAgeByAge(double age) {
		double value = (double) age * 12.00;
		BigDecimal b = new BigDecimal(value);
		return b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月24日
	 * @param monthAge 用户月龄
	 * @return
	 * @description: 计算两个日期之差 年月日
	 */
	public static String remainDateToString(String startDateStr, String endDateStr) {
		Calendar calS = Calendar.getInstance();
		java.util.Date startDate = null;
		java.util.Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		try {
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		calS.setTime(startDate);
		int startY = calS.get(Calendar.YEAR);
		int startM = calS.get(Calendar.MONTH);
		int startD = calS.get(Calendar.DATE);
		int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

		calS.setTime(endDate);
		int endY = calS.get(Calendar.YEAR);
		int endM = calS.get(Calendar.MONTH);
		// 处理2011-01-10到2011-01-10，认为服务为一天
		int endD = calS.get(Calendar.DATE) + 1;
		int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

		StringBuilder sBuilder = new StringBuilder();
		if (endDate.compareTo(startDate) < 0) {
			return sBuilder.append("过期").toString();
		}
		int lday = endD - startD;
		if (lday < 0) {
			endM = endM - 1;
			lday = startDayOfMonth + lday;
		}
		// 处理天数问题，如：2011-01-01 到 2013-12-31 2年11个月31天 实际上就是3年
		if (lday == endDayOfMonth) {
			endM = endM + 1;
			lday = 0;
		}
		int mos = (endY - startY) * 12 + (endM - startM);
		int lyear = mos / 12;
		int lmonth = mos % 12;
		if (lyear > 0) {
			sBuilder.append(lyear + "年");
		}
		if (lmonth > 0) {
			sBuilder.append(lmonth + "个月");
		}
		if (lday > 0) {
			sBuilder.append(lday + "天");
		}
		return sBuilder.toString();
	}

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月24日
	 * @param day 天数
	 * @return
	 * @description: 当前时间往后推X天
	 */
	public static Date getDayAfter(int day) {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		// 当前时间往后推X天
		calendar.add(Calendar.DAY_OF_MONTH, day);
		Date updateDate = calendar.getTime();
		return updateDate;
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年3月22日
	 * @param date
	 * @param day
	 * @return
	 * @description: 根据传入时间往后推X天
	 */
	public static Date getDayAfter(Date date,int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 当前时间往后推X天
		calendar.add(Calendar.DAY_OF_MONTH, day);
		Date updateDate = calendar.getTime();
		return updateDate;
	}
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param var1
	* @param var2
	* @return 两个数相乘
	* @date 2018年5月30日
	* @version 1.0
	* @description 乘法
	*/
	public static double mul(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.multiply(b2).doubleValue();
    }
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param v1
	* @param v2
	* @param scale 精度，到小数点后几位
	* @return 两个数相除
	* @date 2018年5月30日
	* @version 1.0
	* @description 除法
	*/
	public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param v
	* @param scale 精确位数
	* @return 四舍五入后的数字
	* @date 2018年5月30日
	* @version 1.0
	* @description 四舍五入
	*/
	public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
