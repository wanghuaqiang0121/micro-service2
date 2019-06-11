package org.service.task.delay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.service.core.dao.IBaseMapper;
import org.service.task.delay.entity.sms.Recipient;
import org.service.task.delay.entity.sms.Sms;

@Mapper
public interface SmsMapper extends IBaseMapper<Sms> {
	int batchInsert(List<Recipient> recipients);

	int lock(List<Recipient> recipients);

	int restore(List<Recipient> recipients);
}