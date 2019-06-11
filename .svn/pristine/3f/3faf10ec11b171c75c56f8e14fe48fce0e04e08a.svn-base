package org.service.task.delay.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.task.delay.dao.SmsMapper;
import org.service.task.delay.entity.sms.Recipient;
import org.service.task.delay.entity.sms.Sms;
import org.service.tools.date.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
	@Resource
	private SmsMapper mapper;

	public int insert(Sms sms) {
		Date now = new Date();
		for (Recipient recipient : sms.getRecipients()) {
			recipient.setStatus(1);
			recipient.setContent("【" + sms.getSign() + "】" + sms.getContent());
			recipient.setCreateDate(now);
			recipient.setSendDate(DateUtils.dateAfter(now, sms.getTime(), Calendar.SECOND));
		}
		return mapper.batchInsert(sms.getRecipients());
	}

	public int lock(Sms sms) {
		List<Recipient> recipients = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (Recipient recipient : sms.getRecipients()) {
			if (recipient.getOrganizationId() != null) {
				int orgId = recipient.getOrganizationId();
				if (map.containsKey(orgId)) {
					map.put(orgId, map.get(orgId) + recipient.getConsumeTimes());
				} else {
					map.put(orgId, recipient.getConsumeTimes());
				}
			}
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Recipient r = new Recipient();
			r.setOrganizationId(entry.getKey());
			r.setTotal(entry.getValue());
			recipients.add(r);
		}
		if (recipients.size() > 0) {
			return mapper.lock(recipients);
		} else {
			return 0;
		}
	}

	public int update(Sms sms) {
		return mapper.update(sms);
	}

	public int restore(Sms sms) {
		List<Recipient> recipients = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (Recipient recipient : sms.getRecipients()) {
			if (recipient.getOrganizationId() != null) {
				int orgId = recipient.getOrganizationId();
				if (map.containsKey(orgId)) {
					map.put(orgId, map.get(orgId) + recipient.getConsumeTimes());
				} else {
					map.put(orgId, recipient.getConsumeTimes());
				}
			}
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Recipient r = new Recipient();
			r.setOrganizationId(entry.getKey());
			r.setTotal(entry.getValue());
			recipients.add(r);
		}
		if (recipients.size() > 0) {
			return mapper.restore(recipients);
		} else {
			return 0;
		}
	}
}