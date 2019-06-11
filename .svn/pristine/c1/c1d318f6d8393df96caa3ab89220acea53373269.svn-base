package org.web.module.height.obesity.service;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.NewsMapper;
import org.web.module.height.obesity.entity.News;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {
    @Resource
    private NewsMapper newsMapper;
    public List<Map<String, Object>> getList(News news) {
        return newsMapper.getList(news);
    }
    public int insert(News news){
        return newsMapper.insert(news);
    }
    public int update(News news){
        return newsMapper.update(news);
    }
    public int delete(News news){
        return newsMapper.delete(news);
    }
}
