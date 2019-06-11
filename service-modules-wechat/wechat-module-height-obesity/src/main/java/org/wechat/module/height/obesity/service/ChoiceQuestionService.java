package org.wechat.module.height.obesity.service;

import org.springframework.stereotype.Service;
import org.wechat.module.height.obesity.dao.ChoiceQuestionMapper;
import org.wechat.module.height.obesity.entity.ChoiceQuestion;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ChoiceQuestionService {

	@Resource
	private ChoiceQuestionMapper choiceQuestionMapper;

	public List<Map<String, Object>> getList(ChoiceQuestion choiceQuestion) {
		return choiceQuestionMapper.getList(choiceQuestion);
	}

	public List<Map<String, Object>> getSelectedList(ChoiceQuestion choiceQuestion) {
		return choiceQuestionMapper.getSelectedList(choiceQuestion);
	}
	public int getQuestionCount(ChoiceQuestion choiceQuestion){
		return choiceQuestionMapper.getQuestionCount(choiceQuestion);
	}
	public int getRecordCount(ChoiceQuestion choiceQuestion){
		return choiceQuestionMapper.getRecordCount(choiceQuestion);
	}
	public List<Map<String,Object>> getRecord(ChoiceQuestion choiceQuestion){
		return choiceQuestionMapper.getRecord(choiceQuestion);
	}
	public int getRecordDays(ChoiceQuestion choiceQuestion){
		return choiceQuestionMapper.getRecordDays(choiceQuestion);
	}
}
