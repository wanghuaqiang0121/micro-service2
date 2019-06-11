package org.web.module.bone.age.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.HeightForecastFactorTableMapper;
import org.web.module.bone.age.domain.HeightForecastFactorTable;

@Service
public class HeightForecastFactorTableService {

	@Autowired
	private HeightForecastFactorTableMapper heightForecastFactorTableMapper;

	public boolean ageIsScope(HeightForecastFactorTable heightForecastFactorTable) {
		return heightForecastFactorTableMapper.ageIsScope(heightForecastFactorTable);
	}

	public HeightForecastFactorTable getDetali(HeightForecastFactorTable heightForecastFactorTable) {
		return heightForecastFactorTableMapper.getDetali(heightForecastFactorTable);
	}
	
}
