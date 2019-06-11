package  org.wechat.module.user.dao;

import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.user.domain.User;

public interface UserMapper extends IBaseMapper<User> {
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 查询用户微信是否重复
	 */
	Map<String, Object> getWechatRepeat(User user);
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 绑定微信
	 */
	int bindWechat(User user);
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 查询用户通过微信
	 */
	Map<String, Object> getUserByWechat(User user);
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 修改用户微信
	 */
	int updateUserWechat(User user);
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 查询用户通过电话
	 */
	Map<String, Object> getUserByPhone(User user);
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @return
	 * @description: 查询登陆信息
	 */
	Map<String, Object> getLoginMsg(User user);
}
