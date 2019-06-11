package org.web.module.height.obesity.dao;

import java.util.List;
import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.User;

public interface UserMapper extends IBaseMapper<User> {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}