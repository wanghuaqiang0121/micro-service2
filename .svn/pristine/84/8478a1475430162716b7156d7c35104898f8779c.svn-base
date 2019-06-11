package org.wechat.module.user.dao;

import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.user.domain.UserCertificate;

public interface UserCertificateMapper extends IBaseMapper<UserCertificate> {
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userCertificate
	 * @return
	 * @description:  判断已有用户是否已经存在该类型证件
	 */
	Map<String, Object> getRepeatByType(UserCertificate userCertificate);
}