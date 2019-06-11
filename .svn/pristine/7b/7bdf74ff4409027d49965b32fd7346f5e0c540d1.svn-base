package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.UserHeightObesityProjectMapper;
import org.web.module.height.obesity.entity.UserHeightObesityProject;

@Service
public class UserHeightObesityProjectService {

    @Resource
    private UserHeightObesityProjectMapper userHeightObesityProjectMapper;

    public int insert(UserHeightObesityProject record) {
        return userHeightObesityProjectMapper.insert(record);
    }

    List<Map<String, Object>> getList(UserHeightObesityProject record) {
        return userHeightObesityProjectMapper.getList(record);
    }

    public Map<String, Object> getOne(UserHeightObesityProject record) {
        return userHeightObesityProjectMapper.getOne(record);
    }

    public int deleteByChildrenMeasureId(UserHeightObesityProject record) {
        return userHeightObesityProjectMapper.deleteByChildrenMeasureId(record);
    }
}