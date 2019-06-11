package org.web.module.bone.age.dao;

import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.web.module.bone.age.domain.RemoteQuestion;

public interface RemoteQuestionMapper extends IBaseMapper<RemoteQuestion> {
   public Map<String, Object> getOrganizationUserName(RemoteQuestion remoteQuestion);
   
   
   public  Map<String, Object> getIsAnswer(RemoteQuestion remoteQuestion);
}