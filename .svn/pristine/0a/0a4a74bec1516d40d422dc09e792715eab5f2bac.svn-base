package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.UserFoodCompositionRecordChildMapper;
import org.wechat.module.height.obesity.entity.UserFoodCompositionRecordChild;

@Service
public class UserFoodCompositionRecordChildService {
	
	@Resource
	private UserFoodCompositionRecordChildMapper  userFoodCompositionRecordChildMapper;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月18日
	 * @param userFoodCompositionRecordChild
	 * @return
	 * @description: 批量新增用户吃食含量记录
	 */
	public	int batchInsert(List<UserFoodCompositionRecordChild> userFoodCompositionRecordChild) {
		return userFoodCompositionRecordChildMapper.batchInsert(userFoodCompositionRecordChild);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月18日
	 * @param userFoodCompositionRecordChild
	 * @return
	 * @description: 饮食分析列表
	 */
	public List<Map<String, Object>> getList(UserFoodCompositionRecordChild userFoodCompositionRecordChild){
		return userFoodCompositionRecordChildMapper.getList(userFoodCompositionRecordChild);
	}

}
