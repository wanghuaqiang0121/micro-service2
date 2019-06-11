package org.wechat.module.height.obesity.service;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.UserMapper;
import org.wechat.module.height.obesity.entity.User;
import org.wechat.module.height.obesity.message.Prompt;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: XiePeng
 * @date: 2019年1月17日
 * @description: UserService
 */
@Service
public class UserService {
	@Resource
	private UserMapper mapper;

	public List<Map<String, Object>> getList(User user) {
		return mapper.getList(user);
	}
	public Map<String,Object> getOne(User user){
		return mapper.getOne(user);
	}
	public Map<String,Object> getNewOne(User user){
		return mapper.getNewOne(user);
	}

	public Map<String,Object> getChildInfo(User user) {
		return mapper.getChildInfo(user);
	}

	public Map<String,Object> getChildProtectionNo(User user) {
		return mapper.getChildProtectionNo(user);
	}

	public Map<String,Object> getServerPackage(User user) {
		return mapper.getServerPackage(user);
	}

	public List<Map<String, Object>> getHighrisk(User user) {
		return mapper.getHighrisk(user);
	}

	public List<Map<String,Object>> getAllergy(User user) {
		return mapper.getAllergy(user);
	}

	public List<Map<String, Object>> getWHO(User user) {
		return mapper.getWHO(user);
	}

    public Map<String,Object> getGrowth(User user) {
		return mapper.getGrowth(user);
    }

	public List<Map<String,Object>> getTemplateMap(User user) {
		return mapper.getTemplate(user);
	}

	public Map<String,Object> getNewRecord(User user) {
		return mapper.getNewRecord(user);
	}

	public Map<String,Object> getChildHeightGrowth(User user) {
		/*返回对象*/
		Map<String,Object> resultMap = new HashMap<>();
		/* 查询最新记录月齡、性别 */
		Map<String,Object> userMap=getOne(user);
		Map<String, Object> recordMap = getNewRecord(user);
		if (userMap == null|| recordMap == null) {
			return null;
		}
		user.setMonthAge(Integer.parseInt(recordMap.get("monthAge").toString()));
		user.setSex(Integer.parseInt(userMap.get("sex").toString()));
		List<Map<String,Object>> median=mapper.getMedian(user);
		List<Map<String,Object>> subTwoStandard=mapper.getSubTwoStandard(user);
		List<Map<String,Object>> plusTwoStandard=mapper.getPlusTwoStandard(user);
		List<Integer> lineX=new ArrayList<>();
		List<Map<String,Object>> childData=mapper.getChildData(user);
		for(int i=0;i<=37;i++){
			lineX.add(i);
		}
		resultMap.put("median",median);
		resultMap.put("subTwoStandard",subTwoStandard);
		resultMap.put("plusTwoStandard",plusTwoStandard);
		resultMap.put("lineX",lineX);
		resultMap.put("childData",childData);
		return resultMap;
	}
}
