package org.web.module.bone.age.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.ReadFilmRechargeRecord;

public interface ReadFilmRechargeRecordMapper extends IBaseMapper<ReadFilmRechargeRecord> {

	
	List<Map<String, Object>> getOrganizationList(ReadFilmRechargeRecord readFilmRechargeRecord);
	
	List<Map<String, Object>>  getLastRechargeRecord(ReadFilmRechargeRecord readFilmRechargeRecord);

	Map<String, Object> getNewOne(ReadFilmRechargeRecord readFilmRechargeRecord);

	int lockMoney(ReadFilmRechargeRecord readFilmRechargeRecord);

	int deductionFee(ReadFilmRechargeRecord readFilmRechargeRecord);
}
