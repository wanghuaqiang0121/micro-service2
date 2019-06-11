package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.ExaminationCodeTableMapper;
import org.web.module.height.obesity.entity.ExaminationCodeTable;

@Service
public class ExaminationCodeTableService {
	
	@Resource
	private ExaminationCodeTableMapper  examinationCodeTableMapper;

	  public int insert(ExaminationCodeTable examinationCodeTable){
	        return examinationCodeTableMapper.insert(examinationCodeTable);
	    }
	    public int update(ExaminationCodeTable examinationCodeTable){
	        return examinationCodeTableMapper.update(examinationCodeTable);
	    }
	    public int delete(ExaminationCodeTable examinationCodeTable){
	        return examinationCodeTableMapper.delete(examinationCodeTable);
	    }
	    public Map<String, Object> getOne(ExaminationCodeTable examinationCodeTable) {
	        return examinationCodeTableMapper.getOne(examinationCodeTable);
	    }
	    public Map<String, Object> getRepeat(ExaminationCodeTable examinationCodeTable) {
	        return examinationCodeTableMapper.getRepeat(examinationCodeTable);
	    }
	    public List<Map<String, Object>> getList(ExaminationCodeTable examinationCodeTable) {
	        return examinationCodeTableMapper.getList(examinationCodeTable);
	    }
}
