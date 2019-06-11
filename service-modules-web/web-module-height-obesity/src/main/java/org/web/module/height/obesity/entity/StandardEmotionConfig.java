package org.web.module.height.obesity.entity;

import org.service.core.entity.BaseEntity;

public class StandardEmotionConfig extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2019年1月16日
	 * @description: TODO
	 */
	private static final long serialVersionUID = -8729579897699050708L;

	private Integer id;

    private Integer startMonthAge;

    private Integer endMonthAge;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartMonthAge() {
        return startMonthAge;
    }

    public void setStartMonthAge(Integer startMonthAge) {
        this.startMonthAge = startMonthAge;
    }

    public Integer getEndMonthAge() {
        return endMonthAge;
    }

    public void setEndMonthAge(Integer endMonthAge) {
        this.endMonthAge = endMonthAge;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}