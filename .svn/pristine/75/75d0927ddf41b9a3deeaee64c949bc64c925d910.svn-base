package org.wechat.module.height.obesity.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.UserMeasurementRecord;;

public interface UserMeasurementRecordMapper extends IBaseMapper<UserMeasurementRecord> {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMeasurementRecord record);

    UserMeasurementRecord selectByPrimaryKey(Integer id);

    List<UserMeasurementRecord> selectAll();

    int updateByPrimaryKey(UserMeasurementRecord record);

    Map<String,Object> getHistoryList(UserMeasurementRecord userMeasurementRecord);
}