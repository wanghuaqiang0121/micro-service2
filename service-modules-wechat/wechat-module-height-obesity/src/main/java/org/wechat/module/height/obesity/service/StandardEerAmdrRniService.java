package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.StandardEerAmdrRniMapper;
import org.wechat.module.height.obesity.entity.StandardEerAmdrRni;

@Service
public class StandardEerAmdrRniService {
    @Resource
    private StandardEerAmdrRniMapper standardEerAmdrRniMapper;

    public List<Map<String, Object>> getList(StandardEerAmdrRni record) {
        return standardEerAmdrRniMapper.getList(record);
    }

    public Map<String, Object> getOne(StandardEerAmdrRni record) {
        return standardEerAmdrRniMapper.getOne(record);
    }
}