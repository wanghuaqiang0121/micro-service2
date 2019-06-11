package org.wechat.module.height.obesity.dao;

import java.util.List;
import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.UserRecipesProject;

public interface UserRecipesProjectMapper extends IBaseMapper<UserRecipesProject> {
    int deleteByChildrenMeasureId(UserRecipesProject record);

    int insert(UserRecipesProject record);

    int insertBatch(List<UserRecipesProject> record);

    List<UserRecipesProject> selectAll();

    int updateByPrimaryKey(UserRecipesProject record);
}