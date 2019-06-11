package org.wechat.module.user.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.user.domain.UserService;

public interface UserServiceMapper extends IBaseMapper<UserService> {
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userServices
	 * @return
	 * @description:  批量新增用户服务
	 */
public 	int insertByList(List<UserService> userServices);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userService
	 * @return
	 * @description: 用户预约服务列表
	 */
public	List<Map<String, Object>> getAppointmentServiceList(UserService userService);
}