package org.web.module.bone.age.dao;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.HeightForecastFactorTable;

public interface HeightForecastFactorTableMapper extends IBaseMapper<HeightForecastFactorTable> {

	HeightForecastFactorTable getDetali(HeightForecastFactorTable heightForecastFactorTable);

	boolean ageIsScope(HeightForecastFactorTable heightForecastFactorTable);
	
}