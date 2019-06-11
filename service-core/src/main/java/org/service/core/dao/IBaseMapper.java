package org.service.core.dao;

import java.util.List;
import java.util.Map;

import org.service.core.entity.BaseEntity;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @param <T>
 * @description: 顶层Mapper
 */
public abstract interface IBaseMapper<T extends BaseEntity> {

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param entity
	 * @return {@link Integer}
	 * @description: 新增
	 */
	abstract int insert(T entity);

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param entity
	 * @return {@link Integer}
	 * @description: 删除
	 */
	abstract int delete(T entity);

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param entity
	 * @return {@link Integer}
	 * @description: 修改
	 */
	abstract int update(T entity);

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param entity
	 * @return {@link Map}
	 * @description: 查询单条
	 */
	abstract Map<String, Object> getOne(T entity);

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param entity
	 * @return {@link List}
	 * @description: 查询多条
	 */
	abstract List<Map<String, Object>> getList(T entity);

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param entity
	 * @return {@link Map}
	 * @description: 检查重复
	 */
	abstract Map<String, Object> getRepeat(T entity);
}