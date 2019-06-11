package org.web.module.bone.age.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.UserCertificateMapper;
import org.web.module.bone.age.domain.UserCertificate;

@Service
public class UserCertificateService {

	@Resource
	private UserCertificateMapper userCertificateMapper;

	public Map<String, Object> getRepeat(UserCertificate userCertificate) {
		return userCertificateMapper.getRepeat(userCertificate);
	}

	public int insert(UserCertificate userCertificate) {
		return userCertificateMapper.insert(userCertificate);
	}

	public int batchInsert(List<UserCertificate> userCertificates) {
		return userCertificateMapper.batchInsert(userCertificates);
	}
	
	public int deleteByUserId(UserCertificate userCertificate) {
		return userCertificateMapper.deleteByUserId(userCertificate);
	}
}
