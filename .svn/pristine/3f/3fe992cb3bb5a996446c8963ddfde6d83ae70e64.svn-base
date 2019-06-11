package org.web.module.organization.service.service.packages;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.service.packages.ServiceTypeMapper;
import org.web.module.organization.domain.service.packages.ServiceType;

@Service
public class ServiceTypeService {

	@Resource
	private ServiceTypeMapper serviceTypeMapper;

	public Map<String, Object> getOne(ServiceType serviceType) {
		return serviceTypeMapper.getOne(serviceType);
	}
}
