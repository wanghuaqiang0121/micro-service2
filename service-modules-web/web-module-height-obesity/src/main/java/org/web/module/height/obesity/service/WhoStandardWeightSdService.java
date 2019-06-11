package org.web.module.height.obesity.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.WhoStandardWeightSdMapper;
import org.web.module.height.obesity.entity.WhoStandardWeightSd;

@Service
public class WhoStandardWeightSdService {
    @Resource
    private WhoStandardWeightSdMapper whoStandardWeightSdMapper;

    public Map<String, Object> getStandValue(WhoStandardWeightSd record) {
        return whoStandardWeightSdMapper.getStandValue(record);
    }
}