package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.PensionOrganizationInfoMapper;
import org.web.module.organization.domain.PensionOrganizationInfo;

@Service
public class PensionOrganizationInfoService {

	@Resource
	private PensionOrganizationInfoMapper pensionOrganizationInfoMapper;

	public List<Map<String, Object>> getList(PensionOrganizationInfo pensionOrganizationInfo) {
		return pensionOrganizationInfoMapper.getList(pensionOrganizationInfo);
	}

	public int insert(PensionOrganizationInfo pensionOrganizationInfo) {
		return pensionOrganizationInfoMapper.insert(pensionOrganizationInfo);
	}

	public int update(PensionOrganizationInfo pensionOrganizationInfo) {
		return pensionOrganizationInfoMapper.update(pensionOrganizationInfo);
	}

	public Map<String, Object> getOne(PensionOrganizationInfo pensionOrganizationInfo) {
		return pensionOrganizationInfoMapper.getOne(pensionOrganizationInfo);
	}
}
