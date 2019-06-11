package org.web.module.bone.age.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.RemoteBoneAgeOrderMapper;
import org.web.module.bone.age.domain.RemoteBoneAgeOrder;


@Service
public class RemoteBoneAgeOrderService {
	@Resource
	private RemoteBoneAgeOrderMapper remoteBoneAgeOrderMapper; 
	
	public Map<String, Object> getOne(RemoteBoneAgeOrder remoteBoneAgeOrder) {
		return remoteBoneAgeOrderMapper.getOne(remoteBoneAgeOrder);
	}
	public Map<String, Object> getRepeat(RemoteBoneAgeOrder remoteBoneAgeOrder) {
		return remoteBoneAgeOrderMapper.getRepeat(remoteBoneAgeOrder);
	}
	
	public List<Map<String, Object>> getList(RemoteBoneAgeOrder remoteBoneAgeOrder) {
		return remoteBoneAgeOrderMapper.getList(remoteBoneAgeOrder);
	}
	
	public int insert(RemoteBoneAgeOrder remoteBoneAgeOrder) {
		return remoteBoneAgeOrderMapper.insert(remoteBoneAgeOrder);
	}

	public int update(RemoteBoneAgeOrder remoteBoneAgeOrder) {
		return remoteBoneAgeOrderMapper.update(remoteBoneAgeOrder);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月6日
	 * @param remoteBoneAgeOrder
	 * @return
	 * @description: 接收人的远程阅片列表状态为未完成的
	 */
	public List<Map<String, Object>> getIncompleteRemoteBoneAgeOrder(RemoteBoneAgeOrder remoteBoneAgeOrder){
		return remoteBoneAgeOrderMapper.getIncompleteRemoteBoneAgeOrder(remoteBoneAgeOrder);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月6日
	 * @param remoteBoneAgeOrder
	 * @return
	 * @description: 指定医生阅片使用次数  状态为已完成 
	 */
	public Map<String, Object> getRemoteBoneAgeOrderTimes(RemoteBoneAgeOrder remoteBoneAgeOrder){
		return remoteBoneAgeOrderMapper.getRemoteBoneAgeOrderTimes(remoteBoneAgeOrder);
	}

	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月6日
	 * @param remoteBoneAgeOrder
	 * @return
	 * @description: 指定医生阅片列表（统计）
	 */
	public List<Map<String, Object>> getRemoteBoneAgeOrderList(RemoteBoneAgeOrder remoteBoneAgeOrder){
		return remoteBoneAgeOrderMapper.getRemoteBoneAgeOrderList(remoteBoneAgeOrder);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月15日
	 * @param remoteBoneAgeOrder
	 * @return
	 * @description: 指定医生阅片医生列表 
	 */
	public List<Map<String, Object>> getDoctors(RemoteBoneAgeOrder remoteBoneAgeOrder){
		return remoteBoneAgeOrderMapper.getDoctors(remoteBoneAgeOrder);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月18日
	 * @param remoteBoneAgeOrder
	 * @return
	 * @description: 阅片历史
	 */
	public List<Map<String, Object>>  getListByReceiveOrganizationUserId(RemoteBoneAgeOrder remoteBoneAgeOrder){
		return remoteBoneAgeOrderMapper.getListByReceiveOrganizationUserId(remoteBoneAgeOrder);
	}
}
