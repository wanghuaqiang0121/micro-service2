package org.web.module.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationGradeMapper;
import org.web.module.organization.domain.OrganizationGrade;

@Service
public class OrganizationGradeService {

	@Resource
	private OrganizationGradeMapper organizationGradeMapper;

	public int insert(OrganizationGrade organizationGrade) {
		return organizationGradeMapper.insert(organizationGrade);
	}

	public int update(OrganizationGrade organizationGrade) {
		return organizationGradeMapper.update(organizationGrade);
	}

	public List<Map<String, Object>> getList(OrganizationGrade organizationGrade) {
		return organizationGradeMapper.getList(organizationGrade);

	}

	public Map<String, Object> getRepeat(OrganizationGrade organizationGrade) {
		return organizationGradeMapper.getRepeat(organizationGrade);
	}
	
	public Map<String, Object> getDetail(OrganizationGrade organizationGrade) {
		return organizationGradeMapper.getOne(organizationGrade);
	}

}
