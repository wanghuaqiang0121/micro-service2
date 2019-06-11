package org.web.module.height.obesity.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.height.obesity.dao.ChoiceQuestionMapper;
import org.web.module.height.obesity.entity.ChoiceQuestion;

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
	
}
