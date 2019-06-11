package org.web.module.height.obesity.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.height.obesity.entity.News;
import org.web.module.height.obesity.global.BaseGlobal;
import org.web.module.height.obesity.service.NewsService;

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
     * @param news
     * @return
     * @description:  新闻列表
     */
    @GetMapping(value = { "/news" })
    public JsonApi getNewsList(
            @Validated({ BaseEntity.SelectAll.class })News news, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
        Page<?> page = PageHelper.startPage(news.getPage(),news.getPageSize());
        List<Map<String, Object> > newsList = newsService.getList(news);
        if (newsList != null && !newsList.isEmpty()) {
            return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),newsList));
        }
        return new JsonApi(ApiCodeEnum.NOT_FOUND);
    }
    /**
     * @author: XiePeng
     * @date: 2018年12月14日
     * @param news
     * @return
     * @description:  新增资讯配置
     */
    @PostMapping(value = { "/news/config" })
    public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) News news, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
        news.setCreateDateTime(new Date());
        if(newsService.insert(news)>0){
            return new JsonApi(ApiCodeEnum.OK);
        }
        return new JsonApi(ApiCodeEnum.FAIL);
    }
    /**
     * @author: XiePeng
     * @date: 2018年12月14日
     * @param news
     * @return
     * @description:  编辑资讯配置（修改）
     */
    @PutMapping(value = { "/news/config/{id}" })
    public JsonApi update(@PathVariable("id") Integer id, @RequestBody @Validated({ BaseEntity.Update.class }) News news, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
        news.setId(id);
        if(newsService.update(news)>0){
            return new JsonApi(ApiCodeEnum.OK);
        }
        return new JsonApi(ApiCodeEnum.FAIL);
    }
    /**
     * @author: XiePeng
     * @date: 2018年12月14日
     * @param news
     * @return
     * @description: 删除资讯配置
     */
    @DeleteMapping(value = { "/news/config/delete/{id}" })
    public JsonApi delete(@PathVariable("id") Integer id, News news){
        news.setId(id);
        if(newsService.delete(news)>0){
            return new JsonApi(ApiCodeEnum.OK);
        }
        return new JsonApi(ApiCodeEnum.FAIL);
    }
}
