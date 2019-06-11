package org.web.module.organization.service.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.user.OrganizationUserCertificateMapper;
import org.web.module.organization.domain.user.OrganizationUserCertificate;

@Service
public class OrganizationUserCertificateService {

	@Resource
	private OrganizationUserCertificateMapper organizationUserCertificateMapper;

	public int batchInsert(List<OrganizationUserCertificate> organizationUserCertificates) {
		return organizationUserCertificateMapper.batchInsert(organizationUserCertificates);
	}

	public Map<String,Object> getRepeat(OrganizationUserCertificate organizationUserCertificate) {
		return organizationUserCertificateMapper.getRepeat(organizationUserCertificate);
	}
	
}
