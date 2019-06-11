package org.web.module.bone.age.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.BaseMedicalImagingDataMapper;
import org.web.module.bone.age.domain.BaseMedicalImagingData;

@Service
public class BaseMedicalImagingDataService {
	@Autowired
	private BaseMedicalImagingDataMapper mapper;

	public Map<String, Object> getOne(BaseMedicalImagingData baseMedicalImagingData) {
		return mapper.getOne(baseMedicalImagingData);
	}

	
}
