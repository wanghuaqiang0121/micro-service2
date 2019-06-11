package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.ChildrenMeasureMapper;
import org.wechat.module.height.obesity.entity.ChildrenMeasure;

@Service
public class ChildrenMeasureService {

	@Resource
	private ChildrenMeasureMapper childrenMeasureMapper;

	public Map<String, Object> getOne(ChildrenMeasure childrenMeasure) {
		return childrenMeasureMapper.getOne(childrenMeasure);
	}

	public Map<String, Object> getDetail(ChildrenMeasure childrenMeasure) {
		return childrenMeasureMapper.getDetail(childrenMeasure);
	}

	public List<Map<String, Object>> getList(ChildrenMeasure childrenMeasure) {
		return childrenMeasureMapper.getList(childrenMeasure);
	}

	public Map<String, Object> getNewOne(ChildrenMeasure childrenMeasure) {
		return childrenMeasureMapper.getNewOne(childrenMeasure);
	}
}
