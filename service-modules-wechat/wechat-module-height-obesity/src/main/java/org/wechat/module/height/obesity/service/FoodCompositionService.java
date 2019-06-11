package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.FoodCompositionMapper;
import org.wechat.module.height.obesity.entity.FoodComposition;

@Service
public class FoodCompositionService {
	
	@Resource
	private FoodCompositionMapper  foodCompositionMapper; 
	
	public List<Map<String, Object>> getList(FoodComposition foodComposition){
		return foodCompositionMapper.getList(foodComposition);
	}

}
