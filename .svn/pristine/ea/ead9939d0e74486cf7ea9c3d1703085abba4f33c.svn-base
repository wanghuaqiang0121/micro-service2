package org.web.module.bone.age.service;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.ReadFilmRechargeRecordMapper;
import org.web.module.bone.age.domain.ReadFilmRechargeRecord;

@Service
public class ReadFilmRechargeRecordService {
	@Resource
	private ReadFilmRechargeRecordMapper mapper;

	public List<Map<String, Object>> getList(ReadFilmRechargeRecord readFilmRechargeRecord) {
		return mapper.getList(readFilmRechargeRecord);
	}

	public Map<String, Object> getOne(ReadFilmRechargeRecord readFilmRechargeRecord) {
		return mapper.getOne(readFilmRechargeRecord);
	}

	public int update(ReadFilmRechargeRecord readFilmRechargeRecord) {
		return mapper.update(readFilmRechargeRecord);
	}

	public int insert(ReadFilmRechargeRecord readFilmRechargeRecord) {
		return mapper.insert(readFilmRechargeRecord);
	}

	public Map<String, Object> getRepeat(ReadFilmRechargeRecord readFilmRechargeRecord) {
		return mapper.getRepeat(readFilmRechargeRecord);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月12日
	 * @param readFilmRechargeRecord
	 * @return
	 * @description: 机构列表
	 */
	public List<Map<String, Object>> getOrganizationList(ReadFilmRechargeRecord readFilmRechargeRecord){
		return mapper.getOrganizationList(readFilmRechargeRecord);
	}
	/**
	 * @author: ChenYan
	 * @date: 2019年3月12日
	 * @param readFilmRechargeRecord
	 * @return
	 * @description: 机构剩余余额
	 */
	public List<Map<String, Object>>  getLastRechargeRecord(ReadFilmRechargeRecord readFilmRechargeRecord){
		return mapper.getLastRechargeRecord(readFilmRechargeRecord);
	}

	public Map<String, Object> getNewOne(ReadFilmRechargeRecord readFilmRechargeRecord) {
		return mapper.getNewOne(readFilmRechargeRecord);
	}
	public int lockMoney(ReadFilmRechargeRecord readFilmRechargeRecord) {
		return mapper.lockMoney(readFilmRechargeRecord);
	}

	public int deductionFee(ReadFilmRechargeRecord readFilmRechargeRecord) {
		return mapper.deductionFee(readFilmRechargeRecord);
	}
}
