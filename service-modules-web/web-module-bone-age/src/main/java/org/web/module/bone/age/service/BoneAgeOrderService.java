package org.web.module.bone.age.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.tools.calculate.CalculateUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.BoneAgeOrderMapper;
import org.web.module.bone.age.dao.StandardHeightPercentileMapper;
import org.web.module.bone.age.domain.BoneAgeOrder;
import org.web.module.bone.age.domain.BoneAgeStandardPercentile;
import org.web.module.bone.age.domain.StandardHeightPercentile;
import org.web.module.bone.age.global.BaseGlobal;
import org.web.module.bone.age.global.GlobalEnum.Algorithm;
import org.web.module.bone.age.global.GlobalEnum.Percentile;
import org.web.module.bone.age.global.GlobalEnum.ScoreTableType;

@Service
public class BoneAgeOrderService {
	@Resource
	private BoneAgeOrderMapper boneAgeOrderMapper;
	
	@Resource
	private StandardHeightPercentileMapper standardHeightPercentileMapper;

	public int insert(BoneAgeOrder eAgeOrder) {
		return boneAgeOrderMapper.insert(eAgeOrder);
	}

	public int update(BoneAgeOrder eAgeOrder) {
		return boneAgeOrderMapper.update(eAgeOrder);
	}

	public Map<String, Object> getOne(BoneAgeOrder eAgeOrder) {
		return boneAgeOrderMapper.getOne(eAgeOrder);
	}

	public List<Map<String, Object>> getBoneAgeOrderList(BoneAgeOrder eAgeOrder) {
		return boneAgeOrderMapper.getList(eAgeOrder);
	}

	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月14日
	 * @param standardPercentile
	 * @return
	 * @description: 获取TW2类型的标准图
	 */
	@Cacheable(value=BaseGlobal.BONE_AGE, key="'standard_percentile_' + #standardPercentile.type + #standardPercentile.sex")
	public Map<String, Object> getBoneAgeStandardPercentile(BoneAgeStandardPercentile standardPercentile){
		Map<String, Object> map = new HashMap<String, Object>();
		//查询3百分位
		standardPercentile.setPercentile(Percentile.THREE_PERCENTILE.getValue());
		map.put("threePercentile", boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile));
		//查询10百分位
		standardPercentile.setPercentile(Percentile.TEN_PERCENTILE.getValue());
		map.put("tenPercentile", boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile));
		//查询25百分位
		standardPercentile.setPercentile(Percentile.TWENTY_FIVE_PERCENTILE.getValue());
		map.put("twentyFivePercentile", boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile));
		//查询50百分位
		standardPercentile.setPercentile(Percentile.FIFTY_PERCENTILE.getValue());
		map.put("fiftyPercentile", boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile));
		//查询70百分位
		standardPercentile.setPercentile(Percentile.SEVENTY_FIVE_PERCENTILE.getValue());
		map.put("seventyFivePercentile", boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile));
		//查询90百分位
		standardPercentile.setPercentile(Percentile.NINETY_PERCENTILE.getValue());
		map.put("ninetyPercentile", boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile));
		//查询97百分位
		standardPercentile.setPercentile(Percentile.NINETY_SEVEN_PERCENTILE.getValue());
		map.put("ninetySevenPercentile", boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile));
		return map;
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月14日
	 * @param standardPercentile
	 * @return
	 * @description: 获取TW3类型的标准图
	 */
	@Cacheable(value=BaseGlobal.TW3_STANDARD_PERCENTILE, key="'standard_percentile_' + #standardPercentile.type + #standardPercentile.sex")
	public Map<String, Object> getTw3StandardPercentiles(BoneAgeStandardPercentile standardPercentile){
		Map<String, Object> map = new HashMap<String, Object>();
		/* 获取TW3类型的标准图 */
		standardPercentile.setPercentile(Percentile.STANDARD.getValue());
		map.put("standard", boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile));
		return map;
	}
	
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月14日
	 * @param boneAgeOrder
	 * @return
	 * @description: 获取用户骨龄列表
	 */
	public List<Map<String, Object>> getBoneAgeUserScore(BoneAgeOrder boneAgeOrder){
		//根据用户ID和时间查询工单列表
		List<Map<String, Object>> orderList = boneAgeOrderMapper.getBoneAgeUserScoreList(boneAgeOrder);
		if(orderList != null && orderList.size()>0){
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(int i=0; i<orderList.size(); i++){
				Map<String, Object> map = new HashMap<String, Object>();
				boneAgeOrder.setId(Integer.valueOf(orderList.get(i).get("id").toString()));
				boneAgeOrder.setSex(Integer.valueOf(orderList.get(0).get("sex").toString()));
				Map<String, Object> totalPointsMap = null;
				Integer algorithm = orderList.get(i).get("summaryAlgorithm") == null 
						? Integer.parseInt(orderList.get(i).get("algorithm").toString().trim())
						: Integer.parseInt(orderList.get(i).get("summaryAlgorithm").toString().trim());
				//获取用户骨龄总分
				if(Algorithm.TW2CHN20TW2T.getValue().equals(algorithm)){
					boneAgeOrder.setIsTW2T(true);
					boneAgeOrder.setScoreTableType(ScoreTableType.T.getValue());
					totalPointsMap = boneAgeOrderMapper.getBoneTotalPoints(boneAgeOrder);
				}else if(Algorithm.TW2CHN13TW2R.getValue().equals(algorithm)){
					boneAgeOrder.setScoreTableType(ScoreTableType.R.getValue());
					totalPointsMap = boneAgeOrderMapper.getBoneTotalPoints(boneAgeOrder);
				}
				else if(Algorithm.TW3CHN13TW3R.getValue().equals(algorithm)){
					boneAgeOrder.setScoreTableType(ScoreTableType.R.getValue());
					totalPointsMap = boneAgeOrderMapper.getTw3rBoneTotalPoints(boneAgeOrder);
				}
				else if(Algorithm.TW3CHN7TW3C.getValue().equals(algorithm)){
					boneAgeOrder.setScoreTableType(ScoreTableType.C.getValue());
					totalPointsMap = boneAgeOrderMapper.getTw3cBoneTotalPoints(boneAgeOrder);
				}
				//设置分数
				map.put("score", CalculateUtil.boneAgeGraphical(Double.valueOf(totalPointsMap.get("totalPoints").toString())));
				//计算年龄，工单创建时间-用户生日=月龄，月龄/12=保留一位小数
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Calendar c1 = Calendar.getInstance();
					Calendar c2 = Calendar.getInstance();
					c1.setTime(sdf.parse(orderList.get(i).get("birthday").toString()));
					c2.setTime(sdf.parse(orderList.get(i).get("createDate").toString()));
					int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
					BigDecimal monthAge = new BigDecimal(year*12 + c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH));
					map.put("age", monthAge.divide(new BigDecimal(12), 1, BigDecimal.ROUND_DOWN));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				list.add(map);
			}
			// 按age从小到大排序 (便于判断用户发育情况：判断发育情况只取最后一条数据)
			Collections.sort(list,new Comparator<Map<String, Object>>(){
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					BigDecimal age1 = (BigDecimal) o1.get("age");//age1是从你list里面拿出来的第一个 
					BigDecimal age2 = (BigDecimal) o2.get("age") ; //age2是从你list里面拿出来的第二个
	                return age1.compareTo(age2);
	            }
	        });
			return list;
		}
		return null;
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月4日
	 * @param boneAgeOrder
	 * @return
	 * @description: tw2计算骨龄
	 */
	public Map<String, Object> getTW2BoneTestResult(BoneAgeOrder boneAgeOrder){
		Map<String, Object> orderMap = boneAgeOrderMapper.getOne(boneAgeOrder);
		if(orderMap!=null && orderMap.size()>0){
			//骨龄
			double boneAge = 0;
			//计算用户当前的骨得分
			//获取用户骨龄总分
			boneAgeOrder.setSex(Integer.valueOf(orderMap.get("sex").toString()));
			Map<String, Object> totalPointsMap = boneAgeOrderMapper.getBoneTotalPoints(boneAgeOrder);
			//计算骨龄总分
			Integer score = Integer.valueOf(totalPointsMap.get("totalPoints").toString());
			//查询R骨龄标准百分位数表是否有准确的骨龄数据
			BoneAgeStandardPercentile standardPercentile = new BoneAgeStandardPercentile();
			standardPercentile.setBoneAgeFraction(score);
			standardPercentile.setSex(Integer.valueOf(orderMap.get("sex").toString()));
			standardPercentile.setPercentile(Percentile.FIFTY_PERCENTILE.getValue());
			standardPercentile.setType(boneAgeOrder.getScoreTableType());
			List<Map<String, Object>> standardList = boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile);
			if(standardList != null && standardList.size() == 1){
				boneAge = Double.valueOf(standardList.get(0).get("boneAge").toString());
			}else{
				standardPercentile.setBoneAgeFraction(null);
				standardPercentile.setAdjacentValues(score);
				standardList = boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile);
				if(standardList != null && standardList.size() == 2){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("Atop", standardList.get(0).get("boneAgeFraction"));
					map.put("Adown", standardList.get(1).get("boneAgeFraction"));
					map.put("Btop", standardList.get(0).get("boneAge"));
					map.put("Bdown", standardList.get(1).get("boneAge"));
					map.put("D", score);
					boneAge = CalculateUtil.boneAge(map);
				}
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("boneAge", boneAge);
			resultMap.put("boneScore", score >= 1000 ? "成年" : score);
			return resultMap;
		}
		return null;
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月14日
	 * @param boneAgeOrder
	 * @return
	 * @description: tw3r计算骨龄
	 */
	public Map<String, Object> getTW3rBoneTestResult(BoneAgeOrder boneAgeOrder){
		Map<String, Object> orderMap = boneAgeOrderMapper.getOne(boneAgeOrder);
		if(orderMap!=null && orderMap.size()>0){
			//骨龄
			double boneAge = 0;
			//计算用户当前的骨得分
			//获取用户骨龄总分
			boneAgeOrder.setSex(Integer.valueOf(orderMap.get("sex").toString()));
			Map<String, Object> totalPointsMap = boneAgeOrderMapper.getTw3rBoneTotalPoints(boneAgeOrder);
			//计算骨龄总分
			Integer score = Integer.valueOf(totalPointsMap.get("totalPoints").toString());
			//查询R骨龄标准百分位数表是否有准确的骨龄数据
			BoneAgeStandardPercentile standardPercentile = new BoneAgeStandardPercentile();
			standardPercentile.setBoneAgeFraction(score);
			standardPercentile.setSex(Integer.valueOf(orderMap.get("sex").toString()));
			standardPercentile.setPercentile(Percentile.STANDARD.getValue());
			standardPercentile.setType(ScoreTableType.TW3R.getValue());
			List<Map<String, Object>> standardList = boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile);
			if(standardList != null && standardList.size() == 1){
				boneAge = Double.valueOf(standardList.get(0).get("boneAge").toString());
			}else{
				standardPercentile.setBoneAgeFraction(null);
				standardPercentile.setAdjacentValues(score);
				standardList = boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile);
				if(standardList != null && standardList.size() == 2){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("Atop", standardList.get(0).get("boneAgeFraction"));
					map.put("Adown", standardList.get(1).get("boneAgeFraction"));
					map.put("Btop", standardList.get(0).get("boneAge"));
					map.put("Bdown", standardList.get(1).get("boneAge"));
					map.put("D", score);
					boneAge = CalculateUtil.boneAge(map);
				}
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("boneAge", boneAge);
			resultMap.put("boneScore", score >= 1000 ? "成年" : score);
			return resultMap;
		}
		return null;
	}
	
	public int updateBoneAgeOrder(BoneAgeOrder boneAgeOrder){
		return boneAgeOrderMapper.update(boneAgeOrder);
	}

	public Map<String, Object> getLastOrder(BoneAgeOrder boneAgeOrder) {
		return boneAgeOrderMapper.getLastOrder(boneAgeOrder);
	}

	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月30日
	 * @param standardPercentile
	 * @return
	 * @description: 查询25百分位和75百分位标准值
	 */
	public Map<String, Object> getTwentyFiveAndSeventyFiveStandardPercentile(
			BoneAgeStandardPercentile standardPercentile) {
		Map<String, Object> map = new HashMap<String, Object>();
		//查询25百分位
		standardPercentile.setPercentile(Percentile.TWENTY_FIVE_PERCENTILE.getValue());
		map.put("twentyFivePercentile", boneAgeOrderMapper.getStandardPercentileList(standardPercentile));
		//查询75百分位
		standardPercentile.setPercentile(Percentile.SEVENTY_FIVE_PERCENTILE.getValue());
		map.put("seventyFivePercentile", boneAgeOrderMapper.getStandardPercentileList(standardPercentile));
		return map;
	}

	public Map<String, Object> getTw3StandardPercentile(BoneAgeStandardPercentile standardPercentile) {
		Map<String, Object> map = new HashMap<String, Object>();
		/* 获取TW3类型的标准图 */
		standardPercentile.setPercentile(Percentile.STANDARD.getValue());
		map.put("standard", boneAgeOrderMapper.getTw3StandardPercentile(standardPercentile));
		return map;
	}

	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月20日
	 * @param boneAgeOrder
	 * @return
	 * @description: tw3c计算骨龄
	 */
	public Map<String, Object> getTW3CBoneTestResult(BoneAgeOrder boneAgeOrder) {
		Map<String, Object> orderMap = boneAgeOrderMapper.getOne(boneAgeOrder);
		if(orderMap!=null && orderMap.size()>0){
			//骨龄
			double boneAge = 0;
			//计算用户当前的骨得分
			//获取用户骨龄总分
			boneAgeOrder.setSex(Integer.valueOf(orderMap.get("sex").toString()));
			Map<String, Object> totalPointsMap = boneAgeOrderMapper.getTw3cBoneTotalPoints(boneAgeOrder);
			//计算骨龄总分
			Integer score = Integer.valueOf(totalPointsMap.get("totalPoints").toString());
			//查询R骨龄标准百分位数表是否有准确的骨龄数据
			BoneAgeStandardPercentile standardPercentile = new BoneAgeStandardPercentile();
			standardPercentile.setBoneAgeFraction(score);
			standardPercentile.setSex(Integer.valueOf(orderMap.get("sex").toString()));
			standardPercentile.setPercentile(Percentile.STANDARD.getValue());
			standardPercentile.setType(ScoreTableType.TW3C.getValue());
			List<Map<String, Object>> standardList = boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile);
			if(standardList != null && standardList.size() == 1){
				boneAge = Double.valueOf(standardList.get(0).get("boneAge").toString());
			}else{
				standardPercentile.setBoneAgeFraction(null);
				standardPercentile.setAdjacentValues(score);
				standardList = boneAgeOrderMapper.getBoneAgeStandardPercentileList(standardPercentile);
				if(standardList != null && standardList.size() == 2){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("Atop", standardList.get(0).get("boneAgeFraction"));
					map.put("Adown", standardList.get(1).get("boneAgeFraction"));
					map.put("Btop", standardList.get(0).get("boneAge"));
					map.put("Bdown", standardList.get(1).get("boneAge"));
					map.put("D", score);
					boneAge = CalculateUtil.boneAge(map);
				}
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("boneAge", boneAge);
			resultMap.put("boneScore", score >= 1000 ? "成年" : score);
			return resultMap;
		}
		return null;
	
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月13日
	 * @param boneAgeOrder
	 * @return
	 * @description: 同步测量信息
	 */
	public Map<String, Object> getSynchronizationMeasure(BoneAgeOrder boneAgeOrder){
		return boneAgeOrderMapper.getSynchronizationMeasure(boneAgeOrder);
	}
	
	public Map<String, Object> getSynchronizationBone(BoneAgeOrder boneAgeOrder){
		return boneAgeOrderMapper.getSynchronizationBone(boneAgeOrder);
	}
	
	public  Map<String, Integer> getMonthAgeRange(Integer monthAge) {

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
	public Map<String, Object> getHeightDiagramChart(BoneAgeOrder boneAgeOrder) {

		/* 返回对象 */
		Map<String, Object> map = new HashMap<String, Object>();

		StandardHeightPercentile standardHeightPercentile = new StandardHeightPercentile();
		standardHeightPercentile.setSex(boneAgeOrder.getSex());
		standardHeightPercentile.setStartMonthAge(boneAgeOrder.getStartMonthAge());
		standardHeightPercentile.setEndMonthAge(boneAgeOrder.getEndMonthAge());

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

	/*	List<Map<String, Object>> boneAgeOrderList = boneAgeOrderMapper.getList(boneAgeOrder);
		List<Map<String, Object>> lineUser = new ArrayList<>();

		for (Map<String, Object> boneAgeOrderMap : boneAgeOrderList) {

			Map<String, Object> children = new HashMap<>();

			 查询用户数据所处百分位 
			standardHeightPercentile.setMonthAge(boneAgeOrder.getMonthAge());
			standardHeightPercentile
					.setStandardValue(new BigDecimal(boneAgeOrderMap.get("height").toString()));
			Map<String, Object> resultMap = standardHeightPercentileMapper.getPercentile(standardHeightPercentile);

			children.put("monthAge", boneAgeOrderMap.get("monthAge"));
			children.put("currentHeight", boneAgeOrderMap.get("height"));
			if (resultMap != null && resultMap.containsKey("percentile")) {

				if (((Integer) resultMap.get("percentile")).intValue() == BaseGlobal.MAX_HEIGHT_PERCENTILE
						&& ((BigDecimal) resultMap.get("standardValue"))
								.doubleValue() < (new Double(boneAgeOrderMap.get("height").toString())
										.doubleValue())) {
					 查询用户值是否高于最高值 
					children.put("operator", BaseGlobal.GREATER_THAN);
				} else if (((Integer) resultMap.get("percentile")).intValue() == BaseGlobal.MIN_HEIGHT_PERCENTILE
						&& ((BigDecimal) resultMap.get("standardValue"))
								.doubleValue() > (new Double(boneAgeOrderMap.get("height").toString())
										.doubleValue())) {
					 查询用户值是否低于最低值 
					children.put("operator", BaseGlobal.LESS_THAN);
				} else {
					children.put("operator", BaseGlobal.EQUAL);
				}
				children.put("percentile", resultMap.get("percentile"));
			}

			lineUser.add(children);
		}
		map.put("lineUser", lineUser);*/
		return map;

	}
	
	public Map<String, Object> getOneByTeam(BoneAgeOrder boneAgeOrder){
		return boneAgeOrderMapper.getOneByTeam(boneAgeOrder);
	}
	
	
	public List<Map<String, Object>>  getRemList(BoneAgeOrder boneAgeOrder){
		return boneAgeOrderMapper.getRemList(boneAgeOrder);
	}
	
}
