package org.wechat.module.height.obesity.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.UserMeasurementRecordMapper;
import org.wechat.module.height.obesity.entity.UserMeasurementRecord;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: Administrator
 * @date: 2018/12/17
 * @description:
 */
@Service
public class UserMeasurementService {
    @Resource
    UserMeasurementRecordMapper userMeasurementRecordMapper;
    public int insert(UserMeasurementRecord userMeasurementRecord){
        return userMeasurementRecordMapper.insert(userMeasurementRecord);
    }
    public Map<String,Object> getHistoryList(UserMeasurementRecord userMeasurementRecord) {
        return userMeasurementRecordMapper.getHistoryList(userMeasurementRecord);
    }
    public Map<String,Object> getOne(UserMeasurementRecord userMeasurementRecord) {
        return userMeasurementRecordMapper.getOne(userMeasurementRecord);
    }
}
