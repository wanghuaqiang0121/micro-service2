package org.wechat.module.service.service.referral;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wechat.module.service.dao.referral.ReferralMapper;
import org.wechat.module.service.domain.referral.Referral;

@Service
public class ReferralService {

	@Resource
	private ReferralMapper referralMapper;

	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param referral
	 * @return
	 * @date 2018年8月15日
	 * @version 1.0
	 * @description 查询分诊到我机构的转诊单
	 */
	public List<Map<String, Object>> getReferralByOrganizationId(Referral referral) {
		return referralMapper.getReferralByOrganizationId(referral);
	}

	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param referral
	 * @return
	 * @date 2018年8月15日
	 * @version 1.0
	 * @description 查询分诊到我机构科室的转诊单
	 */
	public List<Map<String, Object>> getReferralByDepartment(Referral referral) {
		return referralMapper.getReferralByDepartment(referral);
	}

	public Map<String, Object> getOne(Referral referral) {
		return referralMapper.getOne(referral);
	}

	public int update(Referral referralNew) {
		return referralMapper.update(referralNew);
	}
}
