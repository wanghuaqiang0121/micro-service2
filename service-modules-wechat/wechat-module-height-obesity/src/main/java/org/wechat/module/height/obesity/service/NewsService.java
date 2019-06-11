package org.wechat.module.height.obesity.service;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.NewsMapper;
import org.wechat.module.height.obesity.entity.News;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {
    @Resource
    private NewsMapper newsMapper;

    /**
     *
     * @author: XiePeng
     * @date: 2018年12月13日
     * @param news
     * @return
     * @description: 新闻列表
     */
    public List<Map<String, Object>> getList(News news) {
        return newsMapper.getList(news);
    }

    /**
     * @author: XiePeng
     * @date: 2018年12月13日
     * @param news
     * @return
     * @description: 首页新闻列表
     */
    public List<Map<String,Object>> getNewsIndexList(News news) {
        return newsMapper.getNewsIndexList(news);
    }
    /**
     * @author: XiePeng
     * @date: 2018年12月13日
     * @param news
     * @return
     * @description: 新闻详情
     */
    public Map<String, Object> getOne(News news) {
        return newsMapper.getOne(news);
    }
}
