package org.web.module.bone.age.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.service.tools.calculate.CalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.HeightPercentileConvertTableMapper;
import org.web.module.bone.age.domain.HeightPercentileConvertTable;
import org.web.module.bone.age.global.GlobalEnum;


@Service
public class HeightPercentileConvertTableService {

	@Autowired
	private HeightPercentileConvertTableMapper heightPercentileConvertTableMapper;

	/**
	 * @author <font color="green"><b>Gong.YiYang</b></font>
	 * @param actual
	 * @return
	 * @date 2018年6月4日
	 * @version 1.0
	 * @description 身高实际值求转换值
	 */
	public Double getActualToConvert(HeightPercentileConvertTable heightPercentileConvertTable) {
		Float actual = heightPercentileConvertTable.getActual();
		List<Map<String, Object>> heightPercentileConvertTableresult = heightPercentileConvertTableMapper
				.getActualToConvert(heightPercentileConvertTable);
		if (heightPercentileConvertTableresult != null && heightPercentileConvertTableresult.size() > 0) {
			Map<String, Object> heightPercentileConvertTableMax = heightPercentileConvertTableresult.get(0);
			Map<String, Object> heightPercentileConvertTableMin = heightPercentileConvertTableresult.get(1);
			Float actualMax = (Float) heightPercentileConvertTableMax.get("actual");
			Float actualMin = (Float) heightPercentileConvertTableMin.get("actual");
			// 实际身高超过最大值
			if (actual > actualMax) {
				Float convertMax = (Float) heightPercentileConvertTableMax.get("convert");
				Map<String, Object> heightPercentileConvertTableMaxMap = new HashMap<>();
				heightPercentileConvertTableMaxMap.put("convertBercentile", convertMax);
				heightPercentileConvertTableMaxMap.put("actualBercentile", actualMax);
				heightPercentileConvertTableMaxMap.put("actual", actual);
				double proportionConvertValue = CalculateUtil
						.proportionConvertValue(heightPercentileConvertTableMaxMap);
				return proportionConvertValue;
			}
			// 实际身高小于最小身高
			if (actual < actualMin) {
				Float convertMin = (Float) heightPercentileConvertTableMin.get("convert");
				Map<String, Object> heightPercentileConvertTableMaxMap = new HashMap<>();
				heightPercentileConvertTableMaxMap.put("convertBercentile", convertMin);
				heightPercentileConvertTableMaxMap.put("actualBercentile", actualMin);
				heightPercentileConvertTableMaxMap.put("actual", actual);
				double proportionConvertValue = CalculateUtil
						.proportionConvertValue(heightPercentileConvertTableMaxMap);
				return proportionConvertValue;
			}
			// 判断是否能直接查询到转换值
			heightPercentileConvertTable.setCondition(
					GlobalEnum.HeightPercentileConvertTable.BE_EQUAL_TO.getValue());
			List<Map<String, Object>> heightPercentileConvertTableEqualResult = heightPercentileConvertTableMapper
					.getActualToConvert(heightPercentileConvertTable);
			// 如果直接找到转换值，直接返回
			if (heightPercentileConvertTableEqualResult != null && heightPercentileConvertTableEqualResult.size() > 0) {
				Map<String, Object> heightPercentileConvertTableEqual = heightPercentileConvertTableEqualResult.get(0);
				Double convertEqual = Double.valueOf(heightPercentileConvertTableEqual.get("convert").toString());
				return convertEqual;
			}
			// 利用插入法计算身高转换
			heightPercentileConvertTable.setCondition(
					GlobalEnum.HeightPercentileConvertTable.BOTH_SIDES.getValue());
			List<Map<String, Object>> heightPercentileConvertTableSidesResult = heightPercentileConvertTableMapper
					.getActualToConvert(heightPercentileConvertTable);
			//利用插入法公式计算转换值
			if (heightPercentileConvertTableSidesResult != null && heightPercentileConvertTableSidesResult.size() >0) {
				Map<String, Object> heightPercentileConvertTableSidesMax = heightPercentileConvertTableSidesResult.get(0);
				Map<String, Object> heightPercentileConvertTableSidesMin = heightPercentileConvertTableSidesResult.get(1);
				Map<String, Object> heightPercentileConvertTableSides = new HashMap<>();
				// 转换值小convertSmall、转换值大convertBig、实际值小actualSmall、实际值大actualBig
				heightPercentileConvertTableSides.put("convertBig", heightPercentileConvertTableSidesMax.get("convert"));
				heightPercentileConvertTableSides.put("convertSmall", heightPercentileConvertTableSidesMin.get("convert"));
				heightPercentileConvertTableSides.put("actualBig", heightPercentileConvertTableSidesMax.get("actual"));
				heightPercentileConvertTableSides.put("actualSmall", heightPercentileConvertTableSidesMin.get("actual"));
				heightPercentileConvertTableSides.put("actual", heightPercentileConvertTable.getActual());
				double insertMethodheightConvertValue = CalculateUtil.insertMethodheightConvertValue(heightPercentileConvertTableSides);
				return insertMethodheightConvertValue;
			}
		}
		return (Double) null;
	}
	
	/** 
	* @author <font color="green"><b>Gong.YiYang</b></font>
	* @param heightPercentileConvertTable
	* @return 
	* @date 2018年6月4日
	* @version 1.0
	* @description 身高转换值求实际值
	*/
	public Double getConvertToActual(HeightPercentileConvertTable heightPercentileConvertTable) {
		
		Float convert = heightPercentileConvertTable.getConvert();
		// 身高转换值求实际值：转换值是成年后的转换值所以下面的数据是查询成年的数据
		// 查询成年的数据
		HeightPercentileConvertTable heightPercentileConvertTableadult = new HeightPercentileConvertTable();
		heightPercentileConvertTableadult.setAge(18);
		heightPercentileConvertTableadult.setSex(heightPercentileConvertTable.getSex());
		List<Map<String, Object>> heightPercentileConvertTableResultMax = heightPercentileConvertTableMapper
				.getConvertToActual(heightPercentileConvertTableadult);
		Map<String, Object> heightPercentileConvertTableMaxAdult = heightPercentileConvertTableResultMax.get(0);
		Map<String, Object> heightPercentileConvertTableMinAdult = heightPercentileConvertTableResultMax.get(1);
		
		Float convertMax = (Float) heightPercentileConvertTableMaxAdult.get("convert");
		Float convertMin = (Float) heightPercentileConvertTableMinAdult.get("convert");
		// 实际身高超过最大值
		if (convert > convertMax) {
			Float actualMax = (Float) heightPercentileConvertTableMaxAdult.get("actual");
			Map<String, Object> heightPercentileConvertTableMaxMap = new HashMap<>();
			heightPercentileConvertTableMaxMap.put("convertBercentile", convertMax);
			heightPercentileConvertTableMaxMap.put("actualBercentile", actualMax);
			heightPercentileConvertTableMaxMap.put("convert", convert);
			 double proportionActualValue = CalculateUtil
					.proportionActualValue(heightPercentileConvertTableMaxMap);
			return proportionActualValue;
		}
		// 实际身高小于最小身高
		if (convert < convertMin) {
			Float actualMin = (Float) heightPercentileConvertTableMinAdult.get("actual");
			Map<String, Object> heightPercentileConvertTableMaxMap = new HashMap<>();
			heightPercentileConvertTableMaxMap.put("convertBercentile", convertMin);
			heightPercentileConvertTableMaxMap.put("actualBercentile", actualMin);
			heightPercentileConvertTableMaxMap.put("convert", convert);
			 double proportionActualValue = CalculateUtil
					.proportionActualValue(heightPercentileConvertTableMaxMap);
			return proportionActualValue;
		}
		heightPercentileConvertTable.setAge(18);
		// 判断是否能直接查询到实际值
		heightPercentileConvertTable.setCondition(
				GlobalEnum.HeightPercentileConvertTable.BE_EQUAL_TO.getValue());
		List<Map<String, Object>> heightPercentileConvertTableEqualResult = heightPercentileConvertTableMapper
				.getConvertToActual(heightPercentileConvertTable);
		// 如果直接找到转换值，直接返回
		if (heightPercentileConvertTableEqualResult != null && heightPercentileConvertTableEqualResult.size() > 0) {
			Map<String, Object> heightPercentileConvertTableEqual = heightPercentileConvertTableEqualResult.get(0);
			Double actualEqual = Double.valueOf(heightPercentileConvertTableEqual.get("actual").toString());
			return actualEqual;
		}
		// 利用插入法计算身高实际值
		heightPercentileConvertTable.setCondition(
				GlobalEnum.HeightPercentileConvertTable.BOTH_SIDES.getValue());
		List<Map<String, Object>> heightPercentileConvertTableSidesResult = heightPercentileConvertTableMapper
				.getConvertToActual(heightPercentileConvertTable);
		//利用插入法公式计算转换值
		if (heightPercentileConvertTableSidesResult != null && heightPercentileConvertTableSidesResult.size() >0) {
			Map<String, Object> heightPercentileConvertTableSidesMax = heightPercentileConvertTableSidesResult.get(0);
			Map<String, Object> heightPercentileConvertTableSidesMin = heightPercentileConvertTableSidesResult.get(1);
			Map<String, Object> heightPercentileConvertTableSides = new HashMap<>();
			// 转换值小convertSmall、转换值大convertBig、实际值小actualSmall、实际值大actualBig
			heightPercentileConvertTableSides.put("convertBig", heightPercentileConvertTableSidesMax.get("convert"));
			heightPercentileConvertTableSides.put("convertSmall", heightPercentileConvertTableSidesMin.get("convert"));
			heightPercentileConvertTableSides.put("actualBig", heightPercentileConvertTableSidesMax.get("actual"));
			heightPercentileConvertTableSides.put("actualSmall", heightPercentileConvertTableSidesMin.get("actual"));
			heightPercentileConvertTableSides.put("convert", heightPercentileConvertTable.getConvert());
			 double insertMethodheightActualValue = CalculateUtil.insertMethodheightActualValue(heightPercentileConvertTableSides);
			return insertMethodheightActualValue;
		}
		return (Double) null;
	}
}
