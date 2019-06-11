package org.wechat.module.service.dao.referral;

import java.util.List;
import java.util.Map;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.service.domain.referral.Referral;

public interface ReferralMapper extends IBaseMapper<Referral> {

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param referral
	 * @return
	 * @description: 查询分诊到我机构的转诊单
	 */
	List<Map<String, Object>> getReferralByOrganizationId(Referral referral);

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param referral
	 * @return
	 * @description: 查询分诊到我机构科室的转诊单
	 */
	List<Map<String, Object>> getReferralByDepartment(Referral referral);
}