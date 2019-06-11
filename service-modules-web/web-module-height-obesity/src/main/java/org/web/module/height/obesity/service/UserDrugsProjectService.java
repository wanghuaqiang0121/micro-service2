package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserDrugsProjectMapper;
import org.web.module.height.obesity.entity.UserDrugsProject;

@Service
public class UserDrugsProjectService {
    @Resource
    private UserDrugsProjectMapper mapper;

    public int insertBatch(List<UserDrugsProject> record) {
        return mapper.insertBatch(record);
    }

    public List<Map<String, Object>> getList(UserDrugsProject record) {
        return mapper.getList(record);
    }

    public int deleteByChildrenMeasureId(UserDrugsProject record) {
        return mapper.deleteByChildrenMeasureId(record);
    }
}