package org.wechat.module.height.obesity.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.ChildrenMaternityMapper;
import org.wechat.module.height.obesity.entity.ChildrenMaternity;

@Service
public class ChildrenMaternityService {
	
	@Resource
	private ChildrenMaternityMapper childrenMaternityMapper;
	
	public int insert(ChildrenMaternity childrenMaternity) {
		return childrenMaternityMapper.insert(childrenMaternity);
	}
    
	/**
	 * @author: ChenYan
	 * @date: 2018年12月20日
	 * @param childrenMaternity
	 * @return
	 * @description: 通过用户ID查询儿童出生信息详情
	 */
   public Map<String, Object> getChildrenMaternityByUserId(ChildrenMaternity childrenMaternity){
	   return childrenMaternityMapper.getChildrenMaternityByUserId(childrenMaternity);
   }
   
   public int update(ChildrenMaternity childrenMaternity) {
	   return childrenMaternityMapper.update(childrenMaternity);
   }

	public Map<String, Object> getOne(ChildrenMaternity childrenMaternity) {
		return childrenMaternityMapper.getOne(childrenMaternity);
	}
}
