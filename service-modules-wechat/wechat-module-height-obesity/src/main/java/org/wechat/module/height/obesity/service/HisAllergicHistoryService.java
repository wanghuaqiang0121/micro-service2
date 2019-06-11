package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.HisAllergicHistoryMapper;
import org.wechat.module.height.obesity.entity.HisAllergicHistory;

@Service
public class HisAllergicHistoryService {

	@Resource
	private HisAllergicHistoryMapper hisAllergicHistoryMapper;
	
	public int batchInsert(List<HisAllergicHistory> hisAllergicHistory) {
		return hisAllergicHistoryMapper.batchInsert(hisAllergicHistory);
	}
	
	public List<Map<String, Object>> getList(HisAllergicHistory hisAllergicHistory) {
		return hisAllergicHistoryMapper.getList(hisAllergicHistory);
	}
	
	  public int delete(HisAllergicHistory hisAllergicHistory){
	        return hisAllergicHistoryMapper.delete(hisAllergicHistory);
	    }
}
