package org.web.module.base.service.data.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.data.base.ServicePackageTypeMapper;
import org.web.module.base.domain.data.base.ServicePackageType;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月16日
 * @description: ServicePackageTypeService
 */
@Service
public class ServicePackageTypeService {

	@Resource
	private ServicePackageTypeMapper servicePackageTypeMapper;

	public int insert(ServicePackageType servicePackageType) {
		return servicePackageTypeMapper.insert(servicePackageType);
	}

	public int update(ServicePackageType servicePackageType) {
		return servicePackageTypeMapper.update(servicePackageType);
	}

	public Map<String, Object> getOne(ServicePackageType servicePackageType) {
		return servicePackageTypeMapper.getOne(servicePackageType);
	}

	public Map<String, Object> getRepeat(ServicePackageType servicePackageType) {
		return servicePackageTypeMapper.getRepeat(servicePackageType);
	}

	public List<Map<String, Object>> getList(ServicePackageType servicePackageType) {
		return servicePackageTypeMapper.getList(servicePackageType);
	}

}
