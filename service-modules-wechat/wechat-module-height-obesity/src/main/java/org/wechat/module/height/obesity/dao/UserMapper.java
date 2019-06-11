package org.wechat.module.height.obesity.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.User;

public interface UserMapper extends IBaseMapper<User> {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
    Map<String, Object> getNewOne(User user);

    Map<String,Object> getChildInfo(User user);

    Map<String,Object> getChildProtectionNo(User user);

    Map<String,Object> getServerPackage(User user);

    List<Map<String, Object>> getHighrisk(User user);

    List<Map<String, Object>> getAllergy(User user);

    List<Map<String, Object>> getWHO(User user);

    Map<String,Object> getGrowth(User user);

    List<Map<String,Object>> getTemplate(User user);

    Map<String,Object> getNewRecord(User user);

    List<Map<String,Object>> getMedian(User user);

    List<Map<String,Object>> getSubTwoStandard(User user);

    List<Map<String,Object>> getPlusTwoStandard(User user);

    List<Map<String,Object>> getChildData(User user);
}