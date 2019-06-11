package org.web.module.bone.age.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.OrganizationChoosedReadFilmDoctor;

public interface OrganizationChoosedReadFilmDoctorMapper extends IBaseMapper<OrganizationChoosedReadFilmDoctor> {
	public List<Map<String, Object>> getReadFilmDoctorPriceList(OrganizationChoosedReadFilmDoctor organizationChoosedReadFilmDoctor);
}