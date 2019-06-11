package org.web.module.bone.age.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.ReadFilmDoctorPrice;

public interface ReadFilmDoctorPriceMapper extends IBaseMapper<ReadFilmDoctorPrice>{
	List<Map<String, Object>> getDoctorList(ReadFilmDoctorPrice readFilmDoctorPrice);
}
