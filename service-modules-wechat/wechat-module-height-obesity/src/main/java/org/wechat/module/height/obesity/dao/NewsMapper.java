package org.wechat.module.height.obesity.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.News;
public interface NewsMapper extends IBaseMapper<News> {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    News selectByPrimaryKey(Integer id);

    List<News> selectAll();

    int updateByPrimaryKey(News record);

    List<Map<String,Object>> getNewsIndexList(News news);
}