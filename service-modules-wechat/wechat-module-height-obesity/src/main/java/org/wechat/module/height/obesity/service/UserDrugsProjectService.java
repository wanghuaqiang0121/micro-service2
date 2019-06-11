package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.UserDrugsProjectMapper;
import org.wechat.module.height.obesity.entity.UserDrugsProject;

@Service
public class UserDrugsProjectService {
    @Resource
    private UserDrugsProjectMapper mapper;


    public List<Map<String, Object>> getList(UserDrugsProject record) {
        return mapper.getList(record);
    }

}