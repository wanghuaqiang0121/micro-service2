package org.web.module.base.service.data.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.web.module.base.dao.data.base.ServiceMapper;
import org.web.module.base.domain.data.base.Service;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月16日
 * @description: ServiceService
 */
@org.springframework.stereotype.Service
public class ServiceService {

	@Resource
	private ServiceMapper serviceMapper;

	public List<Map<String, Object>> getList(Service service) {
		return serviceMapper.getList(service);
	}

	public Map<String, Object> getOne(Service service) {
		return serviceMapper.getOne(service);
	}

	public int update(Service service) {
		return serviceMapper.update(service);
	}

	public int insert(Service service) {
		return serviceMapper.insert(service);
	}

	public Map<String, Object> getRepeat(Service service) {
		return serviceMapper.getRepeat(service);
	}

}
