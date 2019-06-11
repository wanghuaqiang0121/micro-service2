package org.service.task.delay.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.service.task.delay.dao.UserRelationMapper;
import org.service.task.delay.entity.user.UserRelation;

@Service
public class UserRelationService {
	@Resource
	private UserRelationMapper mapper;

	public Map<String, Object> getOne(UserRelation relation) {
		return mapper.getOne(relation);
	}

	public int update(UserRelation relation) {
		return mapper.update(relation);
	}

	public int insert(UserRelation relation) {
		return mapper.insert(relation);
	}
}