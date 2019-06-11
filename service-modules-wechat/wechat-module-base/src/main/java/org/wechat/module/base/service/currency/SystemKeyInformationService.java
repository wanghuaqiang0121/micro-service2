package org.wechat.module.base.service.currency;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.base.dao.currency.SystemKeyInformationMapper;
import org.wechat.module.base.domain.currency.SystemKeyInformation;

@Service
public class SystemKeyInformationService {

	@Resource
	private SystemKeyInformationMapper systemKeyInformationMapper;

	public Map<String, Object> getOne(SystemKeyInformation systemKeyInformation) {
		return systemKeyInformationMapper.getOne(systemKeyInformation);
	}
}
