package org.web.module.base.service.data.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.data.base.DoctorCategoryMapper;
import org.web.module.base.domain.data.base.DoctorCategory;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月16日
 * @description: DoctorCategoryService
 */
@Service
public class DoctorCategoryService {

	@Resource
	private DoctorCategoryMapper doctorCategoryMapper;

	public List<Map<String, Object>> getList(DoctorCategory doctorCategory) {
		return doctorCategoryMapper.getList(doctorCategory);
	}
}
