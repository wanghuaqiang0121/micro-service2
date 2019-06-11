package org.web.module.base.service.data.currency;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.base.dao.data.currency.SystemKeyInformationMapper;
import org.web.module.base.domain.data.currency.SystemKeyInformation;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月16日
 * @description: SystemKeyInformationService
 */
@Service
public class SystemKeyInformationService {

	@Resource
	private SystemKeyInformationMapper systemKeyInformationMapper;

	public Map<String, Object> getOne(SystemKeyInformation systemKeyInformation) {
		return systemKeyInformationMapper.getOne(systemKeyInformation);
	}
}
