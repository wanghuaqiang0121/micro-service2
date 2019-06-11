package org.wechat.module.user.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.user.dao.UserCertificateMapper;
import org.wechat.module.user.domain.UserCertificate;

@Service
public class UserCertificateService {

	@Resource
	private UserCertificateMapper userCertificateMapper;

	public int insert(UserCertificate userCertificate) {
		return userCertificateMapper.insert(userCertificate);
	}

	public Map<String, Object> getRepeat(UserCertificate userCertificate) {
		return userCertificateMapper.getRepeat(userCertificate);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userCertificate
	 * @return
	 * @description: 判断已有用户是否已经存在该类型证件
	 */
	public Map<String, Object> getRepeatByType(UserCertificate userCertificate) {
		return userCertificateMapper.getRepeatByType(userCertificate);
	}
}
