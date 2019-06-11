package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserRecipesProjectMapper;
import org.web.module.height.obesity.entity.UserRecipesProject;

@Service
public class UserRecipesProjectService {
    @Resource
    private UserRecipesProjectMapper mapper;

    public int insertBatch(List<UserRecipesProject> record) {
        return mapper.insertBatch(record);
    }

    public Map<String, Object> getOne(UserRecipesProject record) {
        return mapper.getOne(record);
    }

    public int deleteByChildrenMeasureId(UserRecipesProject record) {
        return mapper.deleteByChildrenMeasureId(record);
    }
}