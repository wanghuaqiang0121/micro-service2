package org.web.module.base.service.data.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.data.base.DoctorLevelMapper;
import org.web.module.base.domain.data.base.DoctorLevel;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月16日
 * @description: DoctorLevelService
 */
@Service
public class DoctorLevelService {

	@Resource
	private DoctorLevelMapper doctorLevelMapper;

	public List<Map<String, Object>> getList(DoctorLevel doctorLevel) {
		return doctorLevelMapper.getList(doctorLevel);
	}

	public Map<String, Object> getRepeat(DoctorLevel doctorLevel) {
		return doctorLevelMapper.getRepeat(doctorLevel);
	}

	public int insert(DoctorLevel doctorLevel) {
		return doctorLevelMapper.insert(doctorLevel);
	}

	public Map<String, Object> getOne(DoctorLevel doctorLevel) {
		return doctorLevelMapper.getOne(doctorLevel);
	}

	public int update(DoctorLevel doctorLevel) {
		return doctorLevelMapper.update(doctorLevel);
	}
}
