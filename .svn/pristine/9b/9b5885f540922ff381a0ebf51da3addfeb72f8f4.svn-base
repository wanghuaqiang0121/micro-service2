package org.web.module.base.service.data.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.data.base.ServiceTypeMapper;
import org.web.module.base.domain.data.base.ServiceType;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月16日
 * @description: ServiceTypeService
 */
@Service
public class ServiceTypeService {

	@Resource
	private ServiceTypeMapper serviceTypeMapper;

	public int insert(ServiceType serviceType) {
		return serviceTypeMapper.insert(serviceType);
	}

	public Map<String, Object> getOne(ServiceType serviceType) {
		return serviceTypeMapper.getOne(serviceType);
	}

	public int update(ServiceType serviceType) {
		return serviceTypeMapper.update(serviceType);
	}

	public Map<String, Object> getRepeat(ServiceType serviceType) {
		return serviceTypeMapper.getRepeat(serviceType);
	}

	public List<Map<String, Object>> getList(ServiceType serviceType) {
		return serviceTypeMapper.getList(serviceType);
	}

}