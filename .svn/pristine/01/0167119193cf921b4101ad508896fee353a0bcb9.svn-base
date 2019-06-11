package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.FoodCompositionRecordMapper;
import org.wechat.module.height.obesity.entity.FoodCompositionRecord;

@Service
public class FoodCompositionRecordService {
	
	@Resource
	private FoodCompositionRecordMapper foodCompositionRecordMapper;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月18日
	 * @param foodCompositionRecord
	 * @return
	 * @description: 食物具体成分
	 */
	public List<Map<String, Object>> getList(FoodCompositionRecord foodCompositionRecord) {
		return foodCompositionRecordMapper.getList(foodCompositionRecord);
	}

}
