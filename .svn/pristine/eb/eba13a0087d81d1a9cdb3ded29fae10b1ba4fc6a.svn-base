package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.StandardHeightPercentileMapper;
import org.web.module.height.obesity.entity.StandardHeightPercentile;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ZhangGuangZhi
 * @date: 2018年12月18日
 * @description: 身高标准百分位 中国94标准
 */
@Service
public class StandardHeightPercentileService {

	@Resource
	private StandardHeightPercentileMapper standardHeightPercentileMapper;

	Map<String, Object> getOne(StandardHeightPercentile entity) {
		return standardHeightPercentileMapper.getOne(entity);
	}

	List<Map<String, Object>> getList(StandardHeightPercentile entity) {
		return standardHeightPercentileMapper.getList(entity);
	}

	Map<String, Object> getPercentile(StandardHeightPercentile entity) {
		return standardHeightPercentileMapper.getPercentile(entity);
	}

}
