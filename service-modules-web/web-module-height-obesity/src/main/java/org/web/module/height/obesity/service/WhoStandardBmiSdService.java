package org.web.module.height.obesity.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.WhoStandardBmiSdMapper;
import org.web.module.height.obesity.entity.WhoStandardBmiSd;

@Service
public class WhoStandardBmiSdService {
    @Resource
    private WhoStandardBmiSdMapper whoStandardBmiSdMapper;

    public Map<String, Object> getBmiStandValue(WhoStandardBmiSd record) {
        return whoStandardBmiSdMapper.getBmiStandValue(record);
    }
}