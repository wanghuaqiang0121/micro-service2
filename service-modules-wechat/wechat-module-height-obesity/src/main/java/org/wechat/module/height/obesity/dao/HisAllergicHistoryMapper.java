package org.wechat.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.HisAllergicHistory;

public interface HisAllergicHistoryMapper extends IBaseMapper<HisAllergicHistory> {
	
	public int batchInsert(List<HisAllergicHistory> hisAllergicHistory);
}