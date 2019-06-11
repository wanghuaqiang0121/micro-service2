package org.wechat.module.service.service.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.user.UsersTypeMapper;
import org.wechat.module.service.domain.user.UserType;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年11月28日
 * @description: 用户类型
 */
@Service
public class UserTypeService {

    @Resource
    private UsersTypeMapper usersTypeMapper;

    
    public Map<String,Object> getRepeat(UserType userType){
        return usersTypeMapper.getRepeat(userType);
    }

    public int insert(UserType userType) {
        return usersTypeMapper.insert(userType);
    }

    public int update(UserType userType){
        return usersTypeMapper.update(userType);
    }

    public Map<String,Object> getOne(UserType userType){
        return usersTypeMapper.getOne(userType);
    }

	public List<Map<String, Object>> getList(UserType userType) {
		return usersTypeMapper.getList(userType);
	}

	public int delete(UserType userType) {
		return usersTypeMapper.delete(userType);
	}
}
