package org.web.module.bone.age.service;

import java.util.HashMap;
import java.util.Map;

import org.service.tools.calculate.CalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.bone.age.calculate.FactorMethod;
import org.web.module.bone.age.dao.BoneAgeOrderMapper;
import org.web.module.bone.age.domain.BoneAgeOrder;
import org.web.module.bone.age.domain.Factor;
import org.web.module.bone.age.global.GlobalEnum.Menarche;
import org.web.module.bone.age.global.GlobalEnum.Sex;

/**
 * @author <font color="red"><b>Wang.HuaQiang</b></font>
 * @Date 2018年6月1日
 * @Version 
 * @Description 预测身高
 */
@Service
public class HeightForecastService {
	
	@Autowired
	private BoneAgeOrderMapper boneAgeOrderMapper;
	
	/** 
	* @author <font color="green"><b>Wang.HuaQiang</b></font>
	* @param m
	* @return 
	* @date 2018年6月1日
	* @version 1.0
	* @description 因素法预测身高
	*/
	public Map<String, Object> getHeightForecast(Factor factor){
		BoneAgeOrder boneAgeOrder = new BoneAgeOrder();
		boneAgeOrder.setUserId(factor.getUserId());
		//boneAgeOrder.setIdCard(factor.getIdCard());
		boneAgeOrder.setStartCreateDate(CalculateUtil.getYearsAgo(1.12));
		boneAgeOrder.setEndCreateDate(CalculateUtil.getYearsAgo(0.88));
		// 查询用户0.88-1.12年前的工单最新的一条(根据用户身份证查询)
		Map<String, Object> userBoneAgeOrderMap = boneAgeOrderMapper.getBoneAgeOrdersByYearsAgo(boneAgeOrder);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 有一年前的身高记录
		if (userBoneAgeOrderMap != null) {
			if(null != userBoneAgeOrderMap.get("forecastHeight")) {

				// 判断性别
				if (factor.getSex() == Sex.SEX_MAN.getValue()) {// 性别为男
					// DeltaH为4因素法需要的身高差
					factor.setOrderHeight(Double.parseDouble(userBoneAgeOrderMap.get("height").toString())); 
					factor.setDeltaH(CalculateUtil.sub(factor.getHeight(), factor.getOrderHeight()));
					// 调用4因素法
					return FactorMethod.fourFactorMethod(factor);
				}else if (factor.getSex() == Sex.SEX_WOMAN.getValue()) {// 性别为女
					// 判断是否有0.88-1.12年前的骨龄数据
					if (null != userBoneAgeOrderMap.get("boneAge")) {// 有1.12年前的骨龄数据
						// 判断初潮情况
						if (factor.getMenarcheType() == Menarche.MENARCHE_TYPE_TEO.getValue()) {// 初潮已有
							// 调用4因素法
							// DeltaH为4因素法需要的身高差
							factor.setDeltaH(CalculateUtil.sub(factor.getHeight(), Double.parseDouble(userBoneAgeOrderMap.get("height").toString())));
							return FactorMethod.fourFactorMethod(factor);
						}else if (factor.getMenarcheType() == Menarche.MENARCHE_TYPE_ONE.getValue()) {// 初潮尚无
							// 调用5因素法
							// △H、△R分别为上1年（0.88-1.12年，或1年前后6周内）的身高增加数、R骨龄增加数
							factor.setDeltaH(CalculateUtil.sub(factor.getHeight(), Double.parseDouble(userBoneAgeOrderMap.get("height").toString())));
							factor.setDeltaR(CalculateUtil.sub(factor.getBoneAge(), Double.parseDouble(userBoneAgeOrderMap.get("boneAge").toString())));
							return FactorMethod.fiveFactorMethod(factor);
						}else {// 初潮不明
							// 调用3因素法
							return FactorMethod.threeFactorMethod(factor);
						}
					} else {// 没有1.12年前的骨龄数据
						// 判断初潮情况
						if (factor.getMenarcheType() == Menarche.MENARCHE_TYPE_THREE.getValue()) {// 初潮不明
							// 调用3因素法
							return FactorMethod.threeFactorMethod(factor);
						}else {// 初潮已有  初潮尚无
							// 调用4因素法
							// DeltaH为4因素法需要的身高差
							factor.setDeltaH(CalculateUtil.sub(factor.getHeight(), Double.parseDouble(userBoneAgeOrderMap.get("height").toString())));
							return FactorMethod.fourFactorMethod(factor);
						}
					}
				}
			
			}
		}else if (null == userBoneAgeOrderMap || (null != userBoneAgeOrderMap && (null == userBoneAgeOrderMap.get("forecastHeight")))) {// 没有一年前的身高记录
			// 调用3因素法
			return FactorMethod.threeFactorMethod(factor);
		}
		// 提示无法计算预测身高
		resultMap.put("height", 0);
		resultMap.put("heightMax",0);
		resultMap.put("heightMin", 0);
		resultMap.put("message", "无法计算预测身高");
		return resultMap;
	}
}
