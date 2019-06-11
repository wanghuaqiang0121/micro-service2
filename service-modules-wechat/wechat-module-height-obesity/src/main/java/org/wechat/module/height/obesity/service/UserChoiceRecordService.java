package org.wechat.module.height.obesity.service;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.UserChoiceRecordMapper;
import org.wechat.module.height.obesity.entity.UserChoiceRecord;

import javax.annotation.Resource;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: Administrator
 * @date: 2019/1/14
 * @description:
 */
@Service
public class UserChoiceRecordService {
    @Resource
    UserChoiceRecordMapper userChoiceRecordMapper;
    public int insert(UserChoiceRecord userChoiceRecord){
        return userChoiceRecordMapper.insert(userChoiceRecord);
    }
}
