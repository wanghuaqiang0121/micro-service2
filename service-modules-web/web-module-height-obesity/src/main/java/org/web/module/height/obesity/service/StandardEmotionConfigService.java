package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.StandardEmotionConfigMapper;
import org.web.module.height.obesity.entity.StandardEmotionConfig;

@Service
public class StandardEmotionConfigService {

    @Resource
    StandardEmotionConfigMapper mapper;

    public List<Map<String, Object>> getList(StandardEmotionConfig record) {
        return mapper.getList(record);
    }
}