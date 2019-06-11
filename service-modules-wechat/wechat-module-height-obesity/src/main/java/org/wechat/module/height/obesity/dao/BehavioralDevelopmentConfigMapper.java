package org.wechat.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.BehavioralDevelopmentConfig;

public interface BehavioralDevelopmentConfigMapper extends IBaseMapper<BehavioralDevelopmentConfig> {
    int deleteByPrimaryKey(Integer id);

    int insert(BehavioralDevelopmentConfig record);

    BehavioralDevelopmentConfig selectByPrimaryKey(Integer id);

    List<BehavioralDevelopmentConfig> selectAll();

    int updateByPrimaryKey(BehavioralDevelopmentConfig record);
}