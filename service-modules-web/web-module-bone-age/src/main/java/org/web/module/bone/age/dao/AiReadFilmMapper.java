package org.web.module.bone.age.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.AiReadFilm;

public interface AiReadFilmMapper extends IBaseMapper<AiReadFilm> {
	/**
	 * @author: ChenYan
	 * @date: 2019年3月6日
	 * @param aiReadFilm
	 * @return
	 * @description: 人工智能使用次数
	 */
	public Map<String, Object> getAiReadFilmTimes(AiReadFilm aiReadFilm);
	/**
	 * @author: ChenYan
	 * @date: 2019年3月15日
	 * @param aiReadFilm
	 * @return
	 * @description: 人工智能阅片医生列表
	 */
	public List<Map<String, Object>>  getDoctors(AiReadFilm aiReadFilm);

}