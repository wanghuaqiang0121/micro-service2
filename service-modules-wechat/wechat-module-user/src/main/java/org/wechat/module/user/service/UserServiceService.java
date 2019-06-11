package org.wechat.module.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.domain.UserService;
import org.wechat.module.user.dao.UserServiceMapper;

@Service
public class UserServiceService {

	@Resource
	private UserServiceMapper userServiceMapper;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userServices
	 * @return
	 * @description: 批量新增用户服务
	 */
	public int insertByList(List<UserService> userServices) {
		return userServiceMapper.insertByList(userServices);
	}

	public List<Map<String, Object>> getList(UserService userService) {
		return userServiceMapper.getList(userService);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userService
	 * @return
	 * @description: 用户预约服务列表
	 */
	public List<Map<String, Object>> getAppointmentServiceList(UserService userService) {
		return userServiceMapper.getAppointmentServiceList(userService);
	}
}
