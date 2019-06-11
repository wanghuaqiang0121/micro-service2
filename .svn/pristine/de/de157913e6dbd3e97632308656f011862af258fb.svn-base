package org.wechat.module.service.service.inquire;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.inquire.InquiryReplyMapper;
import org.wechat.module.service.domain.inquire.InquiryReply;

@Service
public class InquiryReplyService {
	
	@Resource
	private InquiryReplyMapper inquiryReplyMapper;

	public int insert(InquiryReply inquiryReply) {
		return inquiryReplyMapper.insert(inquiryReply);
	}

	public Map<String, Object> getInquiriesNum(InquiryReply inquiryReply) {
		return inquiryReplyMapper.getInquiriesNum(inquiryReply);
	}
	
}
