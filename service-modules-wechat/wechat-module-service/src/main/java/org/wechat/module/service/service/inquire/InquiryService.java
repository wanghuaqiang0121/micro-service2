package org.wechat.module.service.service.inquire;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.inquire.InquiryMapper;
import org.wechat.module.service.domain.inquire.Inquiry;

@Service
public class InquiryService {

	@Resource
	private InquiryMapper inquiryMapper;

	public int insert(Inquiry inquiry) {
		return inquiryMapper.insert(inquiry);
	}

	public List<Map<String, Object>> getList(Inquiry inquiry) {
		return inquiryMapper.getList(inquiry);
	}

	public int update(Inquiry inquiry) {
		return inquiryMapper.update(inquiry);
	}

	public Map<String, Object> getOne(Inquiry inquiry) {
		return inquiryMapper.getOne(inquiry);
	}
	
}
