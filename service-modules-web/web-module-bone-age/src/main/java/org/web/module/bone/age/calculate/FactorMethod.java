package org.web.module.bone.age.calculate;

import java.util.HashMap;
import java.util.Map;

import org.service.tools.calculate.CalculateUtil;
import org.web.module.bone.age.domain.Factor;
import org.web.module.bone.age.domain.HeightForecastFactorTable;
import org.web.module.bone.age.domain.HeightPercentileConvertTable;
import org.web.module.bone.age.global.GlobalEnum;
import org.web.module.bone.age.global.GlobalEnum.Menarche;
import org.web.module.bone.age.global.GlobalEnum.Sex;
import org.web.module.bone.age.service.HeightForecastFactorTableService;
import org.web.module.bone.age.service.HeightPercentileConvertTableService;
import org.web.module.bone.age.tools.SpringContextUtils;

/**
 * @author <font color="red"><b>Wang.HuaQiang</b></font>
 * @Date 2018年6月1日
 * @Version 
 * @Description 因素法
 */

public class FactorMethod {
	
	
	private static HeightPercentileConvertTableService heightPercentileConvertTableService = 
			SpringContextUtils.getBean(HeightPercentileConvertTableService.class);
	
	private static HeightForecastFactorTableService heightForecastFactorTableService = 
			SpringContextUtils.getBean(HeightForecastFactorTableService.class);
	
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param m
	* @return 
	* @date 2018年6月1日
	* @version 1.0
	* @description 三因素法预测身高
	*/
	public static Map<String, Object> threeFactorMethod(Factor factor){
		HeightForecastFactorTable heightForecastFactorTable = new HeightForecastFactorTable();
		heightForecastFactorTable.setSex(factor.getSex() == Sex.SEX_MAN.getValue() ? Sex.SEX_MAN.getValue() : Sex.SEX_WOMAN.getValue());
		// 3因素法的年龄是整数：需要把年龄转成整数
		heightForecastFactorTable.setAge((float)CalculateUtil.round(factor.getAge(),0));
		heightForecastFactorTable.setType(GlobalEnum.Factor.HEIGHT_FORECAST_FACTOR_TYPE_THREE.getValue());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 判断性别
		if (factor.getSex() == Sex.SEX_MAN.getValue()) {// 性别为男
			//年龄是否在男孩三因素范围内（在：范围类设置最外层初始参数，不在：提示）
			if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)) {// 不在
				// 提示无法计算预测身高
				resultMap.put("height", 0);
				resultMap.put("heightMax",0);
				resultMap.put("heightMin", 0);
				resultMap.put("message", "无法计算预测身高");
			}
		}else if (factor.getSex() == Sex.SEX_WOMAN.getValue()) {// 性别为女
			// 判断初潮情况
			if (factor.getMenarcheType() == Menarche.MENARCHE_TYPE_TEO.getValue()) {// 初潮已有
				heightForecastFactorTable.setMenarcheType(Menarche.MENARCHE_TYPE_TEO.getValue());
				// 年龄是否在女孩三因素范围内
				if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)){// 不在
					//判断R骨骨龄是否在女孩三因素范围内
					//R骨骨龄代替年龄
					heightForecastFactorTable.setAge((float)CalculateUtil.round(factor.getBoneAge(),0));
					if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)){// 不在
						// 提示无法计算预测身高
						resultMap.put("height", 0);
						resultMap.put("heightMax",0);
						resultMap.put("heightMin", 0);
						resultMap.put("message", "无法计算预测身高");
					}
				}
			}else if (factor.getMenarcheType() == Menarche.MENARCHE_TYPE_ONE.getValue()) {// 初潮尚无
				heightForecastFactorTable.setMenarcheType(Menarche.MENARCHE_TYPE_ONE.getValue());
				// 判断年龄是否在三因素初潮尚无范围
				if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)) {// 不在
					// 提示无法计算预测身高
					resultMap.put("height", 0);
					resultMap.put("heightMax",0);
					resultMap.put("heightMin", 0);
					resultMap.put("message", "无法计算预测身高");
				}
			}else if (factor.getMenarcheType() == Menarche.MENARCHE_TYPE_THREE.getValue()) {// 初潮不明
				heightForecastFactorTable.setMenarcheType(Menarche.MENARCHE_TYPE_THREE.getValue());
				// 判断年龄是否在三因素初潮不明范围
				if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)) {// 不在
					// 提示无法计算预测身高
					resultMap.put("height", 0);
					resultMap.put("heightMax",0);
					resultMap.put("heightMin", 0);
					resultMap.put("message", "无法计算预测身高");
				}
			}
		}
		// 查询身高预测3因素法数据
		heightForecastFactorTable = heightForecastFactorTableService.getDetali(heightForecastFactorTable);
		if (null == heightForecastFactorTable) {
			resultMap.put("height", 0);
			resultMap.put("heightMax",0);
			resultMap.put("heightMin", 0);
			resultMap.put("message", "无法计算预测身高");
			return resultMap;
		}
		// y`是成年身高预测值的转换值，下面用y表示。H’是当前身高的转换值，下面用H表示，G是当前年龄（岁），R是当前R骨龄（岁），M是月经初潮年龄（岁）
        // a:身高系数、b:年龄系数、c:R骨龄系数、d:初潮年龄系数、K:相应系数或常数
		double G=CalculateUtil.round(factor.getAge(),2);
		double R=factor.getBoneAge();
		double a=CalculateUtil.round(heightForecastFactorTable.getHeightCoefficient(),2);
		double b=CalculateUtil.round(heightForecastFactorTable.getAgeCoefficient(),2);
		double c=CalculateUtil.round(heightForecastFactorTable.getrBoneCoefficient(),2);
		
		double K=heightForecastFactorTable.getConstant();
		// 标准差
		double standardDeviation=CalculateUtil.round(heightForecastFactorTable.getStandardDeviation(),2);
		// 实际值转换为转换值
		HeightPercentileConvertTable heightPercentileConvertTable = new HeightPercentileConvertTable();
		heightPercentileConvertTable.setAge(G >= 18 ? 18 : (int)CalculateUtil.round(G,0));
		heightPercentileConvertTable.setSex(factor.getSex());
		if (heightPercentileConvertTable.getSex().equals(Sex.SEX_WOMAN.getValue())) {
			heightPercentileConvertTable.setAge(G > 15 ? 18 : (int)CalculateUtil.round(G,0));
		}
		heightPercentileConvertTable.setActual(Float.parseFloat(factor.getHeight().toString()));
		// 求转换值
		double H=heightPercentileConvertTableService.getActualToConvert(heightPercentileConvertTable);
		if (H == 0) {
			resultMap.put("height", 0);
			resultMap.put("heightMax",0);
			resultMap.put("heightMin", 0);
			resultMap.put("message", "无法计算预测身高");
		}
		// 计算成年身高预测值的转换值
		if (factor.getSex()== Sex.SEX_WOMAN.getValue() && factor.getMenarcheAge() > 0) {
			double d=CalculateUtil.round(heightForecastFactorTable.getMenarcheCoefficient(),2);
			double M=factor.getMenarcheAge();
			// 初潮己有的女孩: y`=aH’+bG+cR+dM+K
			double heightConvert = CalculateUtil.round(
					CalculateUtil.add(
						CalculateUtil.add(
							CalculateUtil.add(
								CalculateUtil.add(CalculateUtil.mul(a, H), CalculateUtil.mul(b, G)),
								CalculateUtil.mul(c, R)),
							CalculateUtil.mul(d, M)),
						K),
					2);
			// 95%可能范围的转换值y`±2*标准差
			// y`+2*标准差
			double heightMaxConvert = CalculateUtil.round(CalculateUtil.add(heightConvert,CalculateUtil.mul(2, standardDeviation)),2);
			// y`-2*标准差
			double heightMinConvert = CalculateUtil.round(CalculateUtil.sub(heightConvert,CalculateUtil.mul(2, standardDeviation)),2);
			HeightPercentileConvertTable heightPercentileConvertTableConvert = new HeightPercentileConvertTable();
			heightPercentileConvertTableConvert.setAge(G >= 18 ? 18 : (int)CalculateUtil.round(G,0));
			heightPercentileConvertTableConvert.setSex(factor.getSex());
			heightPercentileConvertTableConvert.setConvert((float)heightConvert);
			Double height = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
			heightPercentileConvertTableConvert.setConvert((float)heightMaxConvert);
			Double heightMax = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
			heightPercentileConvertTableConvert.setConvert((float)heightMinConvert);
			Double heightMin = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
			if (null == height || null == heightMax || null == heightMin) {
				resultMap.put("height", 0);
				resultMap.put("heightMax",0 );
				resultMap.put("heightMin", 0);
				resultMap.put("message", "无法计算预测身高");
				return resultMap;
			}
			resultMap.put("height", height);
			resultMap.put("heightMax", heightMax);
			resultMap.put("heightMin", heightMin);
			resultMap.put("message", null);
			return resultMap;
		}else {
			// 其他:y`=aH’+bG+cR+K
			double heightConvert = CalculateUtil.round(
					CalculateUtil.add(
						CalculateUtil.add(
							CalculateUtil.add(CalculateUtil.mul(a, H), CalculateUtil.mul(b, G)),
							CalculateUtil.mul(c, R)),
						K),
					2);
			// 95%可能范围的转换值y`±2*标准差
			// y`+2*标准差
			double heightMaxConvert = CalculateUtil.round(CalculateUtil.add(heightConvert,CalculateUtil.mul(2, standardDeviation)),2);
			// y`-2*标准差
			double heightMinConvert = CalculateUtil.round(CalculateUtil.sub(heightConvert,CalculateUtil.mul(2, standardDeviation)),2);
			// 转换值转换为实际值
			HeightPercentileConvertTable heightPercentileConvertTableConvert = new HeightPercentileConvertTable();
			heightPercentileConvertTableConvert.setAge(G >= 18 ? 18 : (int)CalculateUtil.round(G,0));
			heightPercentileConvertTableConvert.setSex(factor.getSex());
			heightPercentileConvertTableConvert.setConvert((float)heightConvert);
			Double height = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
			heightPercentileConvertTableConvert.setConvert((float)heightMaxConvert);
			Double heightMax = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
			heightPercentileConvertTableConvert.setConvert((float)heightMinConvert);
			Double heightMin = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
			if (null == height || null == heightMax || null == heightMin) {
				resultMap.put("height", 0);
				resultMap.put("heightMax",0 );
				resultMap.put("heightMin", 0);
				resultMap.put("message", "无法计算预测身高");
				return resultMap;
			}
			resultMap.put("height", height);
			resultMap.put("heightMax", heightMax);
			resultMap.put("heightMin", heightMin);
			resultMap.put("message", null);
			return resultMap;
		}
	}
	
	/** 
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param m
	 * @return 
	 * @date 2018年6月1日
	 * @version 1.0
	 * @description 四因素法预测身高
	 */
	public static Map<String, Object> fourFactorMethod(Factor factor){
		// 查询4因素法的参数
		HeightForecastFactorTable heightForecastFactorTable = new HeightForecastFactorTable();
		heightForecastFactorTable.setSex(factor.getSex() == Sex.SEX_MAN.getValue() ? Sex.SEX_MAN.getValue() : Sex.SEX_WOMAN.getValue());
		heightForecastFactorTable.setAge(factor.getAge());
		heightForecastFactorTable.setType(GlobalEnum.Factor.HEIGHT_FORECAST_FACTOR_TYPE_FOUR.getValue());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 判断性别
		if (factor.getSex() == Sex.SEX_MAN.getValue()) {// 性别为男
			//年龄是否在男孩4因素范围内（在：范围类设置最外层初始参数，不在：降级调用因素法）
			if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)) {// 不在
				// 调用3因素
				return FactorMethod.threeFactorMethod(factor);
			}
		}else if (factor.getSex() == Sex.SEX_WOMAN.getValue()) {// 性别为女
			// 判断初潮情况
			if (factor.getMenarcheType() == Menarche.MENARCHE_TYPE_TEO.getValue()) {// 初潮已有=初潮后
				heightForecastFactorTable.setMenarcheType(Menarche.MENARCHE_TYPE_FIVE.getValue());
				// 年龄是否在女孩4因素范围内
				if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)){// 不在
					//判断R骨骨龄是否在女孩4因素范围内
					//R骨骨龄代替年龄
					heightForecastFactorTable.setAge((float)CalculateUtil.round(factor.getBoneAge(),0));
					if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)){// 不在
						// 调用3因素
						return FactorMethod.threeFactorMethod(factor);
					}
				}
			}else if (factor.getMenarcheType() == Menarche.MENARCHE_TYPE_ONE.getValue() 
					|| factor.getMenarcheType() == Menarche.MENARCHE_TYPE_THREE.getValue()) {// 初潮尚无或不明=初潮前
				heightForecastFactorTable.setMenarcheType(Menarche.MENARCHE_TYPE_FOUR.getValue());
				// 判断年龄是否在4因素初潮尚无范围
				if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)) {// 不在
					// 调用3因素
					return FactorMethod.threeFactorMethod(factor);
				}
			}
		}
		// 查询因素法需要的参数
		heightForecastFactorTable = heightForecastFactorTableService.getDetali(heightForecastFactorTable);
		if (null == heightForecastFactorTable) {
			resultMap.put("height", 0);
			resultMap.put("heightMax",0 );
			resultMap.put("heightMin", 0);
			resultMap.put("message", "无法计算预测身高");
			return resultMap;
		}
		// y为成年身高预测值，
		// H:当前的身高、G:年龄（岁）、R骨龄（岁），
		// △H、△R分别为上1年（0.88-1.12年，或1年前后6周内）的身高增加数、R骨龄增加数；
		// a、b、c、d、e为有关因素的系数，K为常数，
		double H=factor.getHeight();
		double G=CalculateUtil.round(factor.getAge(),2);
		double R=factor.getBoneAge();
		double a=CalculateUtil.round(heightForecastFactorTable.getHeightCoefficient(),2);
		double b=CalculateUtil.round(heightForecastFactorTable.getAgeCoefficient(),2);
		double c=CalculateUtil.round(heightForecastFactorTable.getrBoneCoefficient(),2);
		double d=CalculateUtil.round(heightForecastFactorTable.getLastYearHeightAddCoefficient(),2);
		//double e=heightForecastFactorTable.getLastYearRBoneAddCoefficient();
		// △H:上1年（0.88-1.12年，或1年前后6周内）的身高增加数 从工单中查询记录，再用现在实际的减去工单的
		double deltaH=factor.getDeltaH();
		double K=heightForecastFactorTable.getConstant();
		// 标准差
		double standardDeviation=CalculateUtil.round(heightForecastFactorTable.getStandardDeviation(),2);
		//4因素和5因素法的H变量使用的是真实身高而非转换值，因此无需做转换，直接计算即可
		// 4因素:y =aH+bG+cR+d△H+K
		double heightConvert = CalculateUtil.round(
				CalculateUtil.add(
					CalculateUtil.add(
						CalculateUtil.add(
							CalculateUtil.add(CalculateUtil.mul(a, H), CalculateUtil.mul(b, G)),
							CalculateUtil.mul(c, R)),
						CalculateUtil.mul(d, deltaH)),
					K),
				2);
		// 95%可能范围的转换值y`±2*标准差
		// y`+2*标准差
		double heightMaxConvert = CalculateUtil.round(CalculateUtil.add(heightConvert,CalculateUtil.mul(2, standardDeviation)),2);
		// y`-2*标准差
		double heightMinConvert = CalculateUtil.round(CalculateUtil.sub(heightConvert,CalculateUtil.mul(2, standardDeviation)),2);
		// 转换值转换为实际值
		HeightPercentileConvertTable heightPercentileConvertTableConvert = new HeightPercentileConvertTable();
		heightPercentileConvertTableConvert.setAge(G >= 18 ? 18 : (int)CalculateUtil.round(G,0));
		heightPercentileConvertTableConvert.setSex(factor.getSex());
		heightPercentileConvertTableConvert.setConvert((float)heightConvert);
		Double height = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
		heightPercentileConvertTableConvert.setConvert((float)heightMaxConvert);
		Double heightMax = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
		heightPercentileConvertTableConvert.setConvert((float)heightMinConvert);
		Double heightMin = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
		if (null == height || null == heightMax || null == heightMin) {
			resultMap.put("height", 0);
			resultMap.put("heightMax",0 );
			resultMap.put("heightMin", 0);
			resultMap.put("message", "无法计算预测身高");
			return resultMap;
		}
		resultMap.put("height", height);
		resultMap.put("heightMax", heightMax);
		resultMap.put("heightMin", heightMin);
		resultMap.put("message", null);
		return resultMap;
	}
	
	/** 
	 * @author <font color="green"><b>Wang.HuaQiang</b></font>
	 * @param m
	 * @return 
	 * @date 2018年6月1日
	 * @version 1.0
	 * @description 五因素法预测身高
	 */
	public static Map<String, Object> fiveFactorMethod(Factor factor){
		// 查询5因素法的参数
		HeightForecastFactorTable heightForecastFactorTable = new HeightForecastFactorTable();
		heightForecastFactorTable.setSex(factor.getSex() == Sex.SEX_MAN.getValue() ? Sex.SEX_MAN.getValue() : Sex.SEX_WOMAN.getValue());
		heightForecastFactorTable.setAge(factor.getAge());
		heightForecastFactorTable.setType(GlobalEnum.Factor.HEIGHT_FORECAST_FACTOR_TYPE_FIVE.getValue());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 判断性别
		if (factor.getSex() == Sex.SEX_MAN.getValue()) {// 性别为男
			//年龄是否在男孩5因素范围内（在：范围内设置最外层初始参数，不在：降级调用因素法）
			if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)) {// 不在
				// 调用4因素
				return FactorMethod.fourFactorMethod(factor);
			}
		}else if (factor.getSex() == Sex.SEX_WOMAN.getValue()) {// 性别为女
			// 判断初潮情况
			if (factor.getMenarcheType() == Menarche.MENARCHE_TYPE_TEO.getValue()) {// 初潮已有=初潮后
				heightForecastFactorTable.setMenarcheType(Menarche.MENARCHE_TYPE_FIVE.getValue());
				// 年龄是否在女孩5因素范围内
				if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)){// 不在
					//判断R骨骨龄是否在女孩5因素范围内
					//R骨骨龄代替年龄
					heightForecastFactorTable.setAge((float)CalculateUtil.round(factor.getBoneAge(),0));
					if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)){// 不在
						// 调用4因素
						return FactorMethod.fourFactorMethod(factor);
					}
				}
			}else if (factor.getMenarcheType() == Menarche.MENARCHE_TYPE_ONE.getValue() 
					|| factor.getMenarcheType() == Menarche.MENARCHE_TYPE_THREE.getValue()) {// 初潮尚无或不明=初潮前
				heightForecastFactorTable.setMenarcheType(Menarche.MENARCHE_TYPE_FOUR.getValue());
				// 判断年龄是否在5因素初潮尚无范围
				if (!heightForecastFactorTableService.ageIsScope(heightForecastFactorTable)) {// 不在
					// 调用4因素
					return FactorMethod.fourFactorMethod(factor);
				}
			}
		}
				
		// 查询因素法需要的参数
		heightForecastFactorTable = heightForecastFactorTableService.getDetali(heightForecastFactorTable);
		if (null == heightForecastFactorTable) {
			resultMap.put("height", 0);
			resultMap.put("heightMax",0 );
			resultMap.put("heightMin", 0);
			resultMap.put("message", "无法计算预测身高");
			return resultMap;
		}		
		// y为成年身高预测值，
		// H:当前的身高、G:年龄（岁）、R骨龄（岁），
		// △H、△R分别为上1年（0.88-1.12年，或1年前后6周内）的身高增加数、R骨龄增加数；
		// a、b、c、d、e为有关因素的系数，K为常数，
		double H=factor.getHeight();
		double G=CalculateUtil.round(factor.getAge(),2);
		double R=factor.getBoneAge();
		double a=CalculateUtil.round(heightForecastFactorTable.getHeightCoefficient(),2);
		double b=CalculateUtil.round(heightForecastFactorTable.getAgeCoefficient(),2);
		double c=CalculateUtil.round(heightForecastFactorTable.getrBoneCoefficient(),2);
		double d=CalculateUtil.round(heightForecastFactorTable.getLastYearHeightAddCoefficient(),2);
		double e=CalculateUtil.round(heightForecastFactorTable.getLastYearRBoneAddCoefficient(),2);
		// △H:上1年（0.88-1.12年，或1年前后6周内）的身高增加数 从工单中查询记录，再用现在实际的减去工单的
		double deltaH=factor.getDeltaH();
		double deltaR=factor.getDeltaR();
		double K=heightForecastFactorTable.getConstant();
		// 标准差
		double standardDeviation=CalculateUtil.round(heightForecastFactorTable.getStandardDeviation(),2);
		//4因素和5因素法的H变量使用的是真实身高而非转换值，因此无需做转换，直接计算即可
		// 4因素:y =aH+bG+cR+d△H+K
		// 5因素:y =aH+bG+cR+d△H+e△R+K
		double heightConvert = CalculateUtil.round(
				CalculateUtil.add(
					CalculateUtil.add(
						CalculateUtil.add(
							CalculateUtil.add(
									CalculateUtil.add(CalculateUtil.mul(a, H), CalculateUtil.mul(b, G)),
									CalculateUtil.mul(c, R)),
							CalculateUtil.mul(d, deltaH)),
					    CalculateUtil.mul(e, deltaR)),
					K),
				2);
		// 95%可能范围的转换值y`±2*标准差
		// y`+2*标准差
		double heightMaxConvert = CalculateUtil.round(CalculateUtil.add(heightConvert,CalculateUtil.mul(2, standardDeviation)),2);
		// y`-2*标准差
		double heightMinConvert = CalculateUtil.round(CalculateUtil.sub(heightConvert,CalculateUtil.mul(2, standardDeviation)),2);
		// 转换值转换为实际值
		HeightPercentileConvertTable heightPercentileConvertTableConvert = new HeightPercentileConvertTable();
		heightPercentileConvertTableConvert.setAge(G >= 18 ? 18 : (int)CalculateUtil.round(G,0));
		heightPercentileConvertTableConvert.setSex(factor.getSex());
		heightPercentileConvertTableConvert.setConvert((float)heightConvert);
		Double height = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
		heightPercentileConvertTableConvert.setConvert((float)heightMaxConvert);
		Double heightMax = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
		heightPercentileConvertTableConvert.setConvert((float)heightMinConvert);
		Double heightMin = heightPercentileConvertTableService.getConvertToActual(heightPercentileConvertTableConvert);
		if (null == height || null == heightMax || null == heightMin) {
			resultMap.put("height", 0);
			resultMap.put("heightMax",0 );
			resultMap.put("heightMin", 0);
			resultMap.put("message", "无法计算预测身高");
			return resultMap;
		}
		resultMap.put("height", height);
		resultMap.put("heightMax", heightMax);
		resultMap.put("heightMin", heightMin);
		resultMap.put("message", null);
		return resultMap;
	}
	
}
