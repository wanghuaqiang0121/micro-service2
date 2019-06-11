package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.UserFoodCompositionRecordMapper;
import org.wechat.module.height.obesity.entity.UserFoodCompositionRecord;

@Service 
public class UserFoodCompositionRecordService {
	
	@Resource
	private UserFoodCompositionRecordMapper userFoodCompositionRecordMapper;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月17日
	 * @param foodCompositions
	 * @return
	 * @description: 新增用户饮食记录 
	 */
	public int insert(UserFoodCompositionRecord userFoodCompositionRecord) {
		return userFoodCompositionRecordMapper.insert(userFoodCompositionRecord);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月18日
	 * @param userFoodCompositionRecord
	 * @return
	 * @description: 饮食记录列表
	 */
	public List<Map<String, Object>> getList(UserFoodCompositionRecord userFoodCompositionRecord){
		return userFoodCompositionRecordMapper.getList(userFoodCompositionRecord);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年12月17日
	 * @param userFoodCompositionRecord
	 * @return
	 * @description:  饮食历史记录(每天)
	 */
	public List<Map<String, Object>> getUserFoodCompositionRecordEveryDay(UserFoodCompositionRecord userFoodCompositionRecord){
		return userFoodCompositionRecordMapper.getUserFoodCompositionRecordEveryDay(userFoodCompositionRecord);
	}
}
