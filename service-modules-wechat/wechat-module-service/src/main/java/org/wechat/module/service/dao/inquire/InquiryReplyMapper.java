package org.wechat.module.service.dao.inquire;

import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.service.domain.inquire.InquiryReply;

public interface InquiryReplyMapper extends IBaseMapper<InquiryReply>{
   
	Map<String, Object> getInquiriesNum(InquiryReply inquiryReply);
}