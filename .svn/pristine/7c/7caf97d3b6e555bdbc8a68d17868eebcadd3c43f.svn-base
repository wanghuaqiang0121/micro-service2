package org.wechat.module.height.obesity.controller.index;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.height.obesity.entity.News;
import org.wechat.module.height.obesity.service.NewsService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: XiePeng
 * @date: 2018年12月13日
 * @description: 新闻
 */
@RestController
public class NewsController {
    @Resource
    private NewsService newsService;
    /**
     * @author: XiePeng
     * @date: 2018年12月13日
     * @param type
     * @return {@link JsonApi}
     * @description:  新闻首页
     */
    @GetMapping(value = { "/news/index/{type}" })
    public JsonApi getNewsIndexList(@PathVariable("type")Integer type,@Validated({ BaseEntity.SelectAll.class }) News news,BindingResult result){
            news.setType(type);news.setIsHost(1);
            Page<?> page = PageHelper.startPage(news.getPage(),news.getPageSize());
            List<Map<String, Object> > newsList = newsService.getNewsIndexList(news);
            if(newsList!=null && !newsList.isEmpty()){
                return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),newsList));
            }
            return new JsonApi(ApiCodeEnum.NOT_FOUND);
    }
    /**
     * @author: XiePeng
     * @date: 2018年12月13日
     * @param id
     * @return {@link JsonApi}
     * @description:  新闻详情
     */
    @GetMapping(value = { "/news/detail/{id}" })
    public JsonApi getNewsDetails(@PathVariable("id")Integer id, News news,BindingResult result){
        news.setId(id);
        Map<String, Object> newsMap = newsService.getOne(news);
        if (newsMap!=null && !newsMap.isEmpty()) {
            return new JsonApi(ApiCodeEnum.OK,newsMap);
        }
        return new JsonApi(ApiCodeEnum.NOT_FOUND);
    }

    /**
     * @author: XiePeng
     * @date: 2018年12月13日
     * @param news
     * @return
     * @description:  新闻列表
     */
    @GetMapping(value = { "/news" })
    public JsonApi getNewsList(@Validated({ BaseEntity.SelectAll.class })News news, BindingResult result){
        Page<?> page = PageHelper.startPage(news.getPage(),news.getPageSize());
        List<Map<String, Object> > newsList = newsService.getList(news);
        if (newsList != null && !newsList.isEmpty()) {
            return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),newsList));
        }
        return new JsonApi(ApiCodeEnum.NOT_FOUND);
    }
}
