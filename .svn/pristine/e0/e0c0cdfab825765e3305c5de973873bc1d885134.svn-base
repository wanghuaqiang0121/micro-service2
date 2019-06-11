package org.wechat.module.height.obesity.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.ChildrenFamilyMapper;
import org.wechat.module.height.obesity.entity.ChildrenFamily;

@Service
public class ChildrenFamilyService {
	@Resource
	private ChildrenFamilyMapper childrenFamilyMapper;

	public int insert(ChildrenFamily childrenFamily) {
		return childrenFamilyMapper.insert(childrenFamily);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年12月20日
	 * @param childrenFamily
	 * @return
	 * @description: 通过用户ID查询儿童出生信息详情
	 */
	public Map<String, Object> getChildrenFamilyByUserId(ChildrenFamily childrenFamily) {
		return childrenFamilyMapper.getChildrenFamilyByUserId(childrenFamily);
	}

	public int update(ChildrenFamily childrenFamily) {
		return childrenFamilyMapper.update(childrenFamily);
	}

	public Map<String, Object> getOne(ChildrenFamily childrenfamily) {
		return childrenFamilyMapper.getOne(childrenfamily);
	}

}
