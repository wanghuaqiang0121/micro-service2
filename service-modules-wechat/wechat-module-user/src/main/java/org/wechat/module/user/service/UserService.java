package org.wechat.module.user.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.UserMapper;
import org.wechat.module.user.domain.User;

@Service
public class UserService {
	@Resource
	private UserMapper userMapper;

	public int insert(User user) {
		return userMapper.insert(user);
	}

	public int update(User user) {
		return userMapper.update(user);
	}

	public Map<String, Object> getOne(User user) {
		return userMapper.getOne(user);
	}

	public Map<String, Object> getRepeat(User user) {
		return userMapper.getRepeat(user);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 查询用户微信是否重复
	 */
	public Map<String, Object> getWechatRepeat(User user) {
		return userMapper.getWechatRepeat(user);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 绑定微信
	 */
	public int bindWechat(User user) {
		return userMapper.bindWechat(user);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 根据微信查询用户
	 */
	public Map<String, Object> getUserByWechat(User user) {
		return userMapper.getUserByWechat(user);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 修改用户微信
	 */
	public int updateUserWechat(User user) {
		return userMapper.updateUserWechat(user);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 根据电话号码查询用户
	 */
	public Map<String, Object> getUserByPhone(User user) {
		return userMapper.getUserByPhone(user);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 查询登录信息
	 */
	public Map<String, Object> getLoginMsg(User user) {
		return userMapper.getLoginMsg(user);
	}
}
