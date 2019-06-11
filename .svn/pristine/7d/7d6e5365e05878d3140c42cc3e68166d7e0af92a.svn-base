package org.wechat.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.ExaminationCodeTableMapper;
import org.wechat.module.height.obesity.entity.ExaminationCodeTable;

@Service
public class ExaminationCodeTableService {
	@Resource
	private ExaminationCodeTableMapper examinationCodeTableMapper ;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月18日
	 * @param examinationCodeTable
	 * @return
	 * @description:  查询检查检验列表
	 */
	public List<Map<String, Object>> getList(ExaminationCodeTable examinationCodeTable) {
		return examinationCodeTableMapper.getList(examinationCodeTable);
	}
	

}
