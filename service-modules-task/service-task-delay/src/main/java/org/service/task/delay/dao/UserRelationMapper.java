package org.service.task.delay.dao;

import org.apache.ibatis.annotations.Mapper;
import org.service.core.dao.IBaseMapper;
import org.service.task.delay.entity.user.UserRelation;

@Mapper
public interface UserRelationMapper extends IBaseMapper<UserRelation> {}