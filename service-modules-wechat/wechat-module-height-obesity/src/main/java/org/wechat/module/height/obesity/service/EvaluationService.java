package org.wechat.module.height.obesity.service;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.StandardHeightPercentileMapper;
import org.wechat.module.height.obesity.entity.StandardHeightPercentile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: Administrator
 * @date: 2019/1/21
 * @description:
 */
@Service
public class EvaluationService {
    @Resource
    private StandardHeightPercentileMapper standardHeightPercentileMapper;
    public Map<String, Object> getPercentile(StandardHeightPercentile standardHeightPercentile){
        return standardHeightPercentileMapper.getPercentile(standardHeightPercentile);
    }
    public Map<String, Object> getOne(StandardHeightPercentile standardHeightPercentile){
        return standardHeightPercentileMapper.getOne(standardHeightPercentile);
    }
}
