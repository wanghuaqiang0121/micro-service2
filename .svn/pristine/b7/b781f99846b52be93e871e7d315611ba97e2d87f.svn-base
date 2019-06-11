package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.SleepConfigMapper;
import org.web.module.height.obesity.entity.SleepConfig;

@Service
public class SleepConfigService {

	@Resource
	private SleepConfigMapper sleepConfigMapper;

	public int insert(SleepConfig sleepConfig) {
		return sleepConfigMapper.insert(sleepConfig);
	}

	public int update(SleepConfig sleepConfig) {
		return sleepConfigMapper.update(sleepConfig);
	}

	public int delete(SleepConfig sleepConfig) {
		return sleepConfigMapper.delete(sleepConfig);
	}

	public Map<String, Object> getOne(SleepConfig sleepConfig) {
		return sleepConfigMapper.getOne(sleepConfig);
	}

	public List<Map<String, Object>> getList(SleepConfig sleepConfig) {
		return sleepConfigMapper.getList(sleepConfig);
	}

	public List<Map<String, Object>> getSleepList(SleepConfig sleepConfig) {
		return sleepConfigMapper.getSleepList(sleepConfig);
	}
}
