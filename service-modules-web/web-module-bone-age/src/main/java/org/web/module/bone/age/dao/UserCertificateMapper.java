package org.web.module.bone.age.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.UserCertificate;

public interface UserCertificateMapper extends IBaseMapper<UserCertificate> {

	int batchInsert(List<UserCertificate> userCertificates);
	
	public int deleteByUserId(UserCertificate userCertificate);
}