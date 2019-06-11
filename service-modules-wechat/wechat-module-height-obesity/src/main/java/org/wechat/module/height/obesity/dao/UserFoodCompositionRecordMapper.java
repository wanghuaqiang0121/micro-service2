package org.wechat.module.height.obesity.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.FoodComposition;
import org.wechat.module.height.obesity.entity.UserFoodCompositionRecord;

public interface UserFoodCompositionRecordMapper extends IBaseMapper<UserFoodCompositionRecord> {
  
	/**
	 * @author: ChenYan
	 * @date: 2018年12月17日
	 * @param foodCompositions
	 * @return
	 * @description: 批量新增用户饮食记录 
	 */
	int batchInsert(List<FoodComposition>  foodCompositions);
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月17日
	 * @param userFoodCompositionRecord
	 * @return
	 * @description:  饮食历史记录(每天)
	 */
	public List<Map<String, Object>> getUserFoodCompositionRecordEveryDay(UserFoodCompositionRecord userFoodCompositionRecord);
}