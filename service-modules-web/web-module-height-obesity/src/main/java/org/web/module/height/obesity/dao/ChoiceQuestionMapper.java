package org.web.module.height.obesity.dao;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.height.obesity.entity.ChoiceQuestion;

public interface ChoiceQuestionMapper extends IBaseMapper<ChoiceQuestion> {
    int deleteByPrimaryKey(Integer id);

    int insert(ChoiceQuestion record);

    ChoiceQuestion selectByPrimaryKey(Integer id);

    List<ChoiceQuestion> selectAll();

    int updateByPrimaryKey(ChoiceQuestion record);

	List<Map<String, Object>> getSelectedList(ChoiceQuestion choiceQuestion);
}