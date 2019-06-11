package org.wechat.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.UserFoodCompositionRecordChild;

public interface UserFoodCompositionRecordChildMapper extends IBaseMapper<UserFoodCompositionRecordChild> {
	int batchInsert(List<UserFoodCompositionRecordChild> userFoodCompositionRecordChild);
}
