package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.SecondarySexCharactersConfigMapper;
import org.web.module.height.obesity.entity.SecondarySexCharactersConfig;

@Service
public class SecondarySexCharactersConfigService {

	@Resource
	private SecondarySexCharactersConfigMapper mapper;

	public List<Map<String, Object>> getList(SecondarySexCharactersConfig secondarySexCharactersConfig) {
		return mapper.getList(secondarySexCharactersConfig);
	}
	
}
