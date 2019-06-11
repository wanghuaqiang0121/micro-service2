package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.NutritionProjectMapper;
import org.web.module.height.obesity.entity.NutritionProject;

@Service
public class NutritionProjectService {

    @Resource
    private NutritionProjectMapper nutritionProjectMapper;

    public List<Map<String, Object>> getList(NutritionProject record) {
        return nutritionProjectMapper.getList(record);
    }
}