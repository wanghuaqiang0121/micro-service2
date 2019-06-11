package org.wechat.module.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.UserServicePackageOrderMapper;
import org.wechat.module.user.domain.UserServicePackageOrder;

@Service
public class UserServicePackageOrderService {

	@Resource
	private UserServicePackageOrderMapper userServicePackageOrderMapper;

	public List<Map<String, Object>> getList(UserServicePackageOrder userServicePackageOrder) {
		return userServicePackageOrderMapper.getList(userServicePackageOrder);
	}

	public int insert(UserServicePackageOrder userServicePackageOrder) {
		return userServicePackageOrderMapper.insert(userServicePackageOrder);
	}

	public Map<String, Object> getOne(UserServicePackageOrder userServicePackageOrder) {
		return userServicePackageOrderMapper.getOne(userServicePackageOrder);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userServicePackageOrder
	 * @return
	 * @description: 确认订单
	 */
	public int updateSureUserServicePackageOrder(UserServicePackageOrder userServicePackageOrder) {
		return userServicePackageOrderMapper.updateSureUserServicePackageOrder(userServicePackageOrder);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userServicePackageOrder
	 * @return
	 * @description: 取消订单
	 */
	public int updateCancelUserServicePackageOrder(UserServicePackageOrder userServicePackageOrder) {
		return userServicePackageOrderMapper.updateCancelUserServicePackageOrder(userServicePackageOrder);
	}


}
