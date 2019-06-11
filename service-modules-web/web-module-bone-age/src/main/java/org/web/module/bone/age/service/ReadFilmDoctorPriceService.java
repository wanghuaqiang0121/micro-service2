package org.web.module.bone.age.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.ReadFilmDoctorPriceMapper;
import org.web.module.bone.age.domain.ReadFilmDoctorPrice;

@Service
public class ReadFilmDoctorPriceService {
	@Resource
	private ReadFilmDoctorPriceMapper mapper;

	public List<Map<String, Object>> getList(ReadFilmDoctorPrice readFilmDoctorPrice) {
		return mapper.getList(readFilmDoctorPrice);
	}

	public Map<String, Object> getOne(ReadFilmDoctorPrice readFilmDoctorPrice) {
		return mapper.getOne(readFilmDoctorPrice);
	}

	public int update(ReadFilmDoctorPrice readFilmDoctorPrice) {
		return mapper.update(readFilmDoctorPrice);
	}

	public int insert(ReadFilmDoctorPrice readFilmDoctorPrice) {
		return mapper.insert(readFilmDoctorPrice);
	}

	public Map<String, Object> getRepeat(ReadFilmDoctorPrice readFilmDoctorPrice) {
		return mapper.getRepeat(readFilmDoctorPrice);
	}

	public List<Map<String, Object>> getDoctorList(ReadFilmDoctorPrice readFilmDoctorPrice) {
		return mapper.getDoctorList(readFilmDoctorPrice);
	}

}
