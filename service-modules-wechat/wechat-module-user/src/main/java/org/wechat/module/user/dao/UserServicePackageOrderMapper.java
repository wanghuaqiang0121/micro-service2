package org.wechat.module.user.dao;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.user.domain.UserServicePackageOrder;

public interface UserServicePackageOrderMapper extends IBaseMapper<UserServicePackageOrder> {
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param baseUserServicePackageOrder
	 * @return
	 * @description: 确认完成订单
	 */
	int updateSureUserServicePackageOrder(UserServicePackageOrder baseUserServicePackageOrder);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userServicePackageOrder
	 * @return
	 * @description: 取消订单
	 */
	int updateCancelUserServicePackageOrder(UserServicePackageOrder userServicePackageOrder);
}