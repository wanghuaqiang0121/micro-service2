package org.web.module.height.obesity.entity;

import org.service.core.entity.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class News extends BaseEntity {
    /**
     * @type: {@link long}
     * @author: ZhangGuangZhi
     * @date: 2018年12月13日
     * @description: TODO
     */
    private static final long serialVersionUID = 3839000842121253803L;
    private Integer id;
    @NotBlank(message = "{news.config.title.notblank.valid}", groups = { Insert.class })
    private String title;

    private String subheading;

    private String briefIntroduction;

    private String picture;
    @NotNull(message = "{news.type.notnull.valid}", groups = {Insert.class })
    private Integer type;
    @NotNull(message = "{news.config.isHost.notnull.valid}", groups = { Insert.class })
    private Byte isHost;
    private Integer hotSort;

    private Date createDateTime;
    @NotBlank(message = "{news.config.content.notblank.valid}", groups = { Insert.class })
    private String content;

    private String source;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Byte getIsHost() {
        return isHost;
    }

    public void setIsHost(Byte isHost) {
        this.isHost = isHost;
    }

    public Integer getHotSort() {
        return hotSort;
    }

    public void setHotSort(Integer hotSort) {
        this.hotSort = hotSort;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Override
    @NotNull(message = "{page.empty}", groups = { SelectAll.class})
    public Integer getPage() {
        return super.getPage();
    }

    @Override
    @NotNull(message = "{pageSize.empty}", groups = { SelectAll.class})
    public Integer getPageSize() {
        return super.getPageSize();
    }

    public String getSource() { return source; }

    public void setSource(String source) { this.source = source; }
}