package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.StandardSleepConfigMapper;
import org.web.module.height.obesity.entity.StandardSleepConfig;

@Service
public class StandardSleepConfigService {
    @Resource
    private StandardSleepConfigMapper standardSleepConfigMapper;

    public List<Map<String, Object>> getList(StandardSleepConfig record) {
        return standardSleepConfigMapper.getList(record);
    }
}