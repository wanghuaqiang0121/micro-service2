package org.web.module.height.obesity.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.entity.Diagnosis;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ZhangGuangZhi
 * @date: 2018年12月21日
 * @description: EarlyWarningService 预警
 */
@Service
public class EarlyWarningService {

	/**
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月24日
	 * @param
	 * @return
	 * @description: 身高评价
	 */
	public Map<String, Object> getWarning(Diagnosis diagnosis) {
		Map<String, Object> map = new HashMap<>();

		/* 预测身高与遗传身高相关10cm以上 */
		
		/* 3个月身高增长为0 预警：季度身高增长为0，存在生长迟缓的风险。 */

		/* 婴儿期年生长速度<<23cm,1-2岁年生长速度<<9cm,2-3岁年生长速度<7cm,3-4岁<6cm，4-青春期<5cm，预警： */

		/* 4岁以上，年生长速度>8cm以上，预警：生长速度偏快，须至少3个月一次复诊身高门诊，评估小朋友的身高增长情况，警惕早熟可能。 */

		return map;
	}
}
