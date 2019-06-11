package org.web.module.bone.age.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.RemoteBoneAgeOrder;

public interface RemoteBoneAgeOrderMapper extends IBaseMapper<RemoteBoneAgeOrder> {
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月6日
	 * @param remoteBoneAgeOrder
	 * @return
	 * @description: 接收人的远程阅片列表状态为未完成的
	 */
	public List<Map<String, Object>> getIncompleteRemoteBoneAgeOrder(RemoteBoneAgeOrder remoteBoneAgeOrder);
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月6日
	 * @param remoteBoneAgeOrder
	 * @return
	 * @description: 指定医生阅片使用次数  状态为已完成 
	 */
	public Map<String, Object> getRemoteBoneAgeOrderTimes(RemoteBoneAgeOrder remoteBoneAgeOrder);
	
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月6日
	 * @param remoteBoneAgeOrder
	 * @return
	 * @description: 指定医生阅片列表（统计）
	 */
	public List<Map<String, Object>> getRemoteBoneAgeOrderList(RemoteBoneAgeOrder remoteBoneAgeOrder);
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月15日
	 * @param remoteBoneAgeOrder
	 * @return
	 * @description: 指定医生阅片医生列表 
	 */
	public List<Map<String, Object>> getDoctors(RemoteBoneAgeOrder remoteBoneAgeOrder);
	
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月18日
	 * @param remoteBoneAgeOrder
	 * @return
	 * @description: 阅片历史
	 */
	public List<Map<String, Object>>  getListByReceiveOrganizationUserId(RemoteBoneAgeOrder remoteBoneAgeOrder);
   
}