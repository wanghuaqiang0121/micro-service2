package org.wechat.module.service.controller.referral;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.service.domain.referral.Referral;
import org.wechat.module.service.global.BaseGlobal;
import org.wechat.module.service.message.Prompt;
import org.wechat.module.service.service.referral.ReferralService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 转诊单
 */
@RestController
public class ReferralController {

	@Resource
	private ReferralService referralService;
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param referral
	 * @param result
	 * @param organizationId
	 * @return  {@link JsonApi}
	 * @description: 查询分诊到我机构的转诊单
	 */
	@RequiresAuthentication(authc = true , value = { })
	@GetMapping(value = { "/referral/organization" })
	public JsonApi getOwnOrganiztionReferrals(
			@Validated({ Referral.referralByOrganizationId.class }) Referral referral, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		referral.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(referral.getPage(), referral.getPageSize());
		List<Map<String, Object>> referralList = referralService.getReferralByOrganizationId(referral);
		if (referralList != null && !referralList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), referralList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param referral
	 * @param result
	 * @param organizationId
	 * @return  {@link JsonApi}
	 * @description:  查询分诊到我机构科室的转诊单
	 */
	@RequiresAuthentication(authc = true , value = { })
	@GetMapping(value = { "/referral/department" })
	public JsonApi getOwnDepartmentReferrals(
			@Validated({ Referral.referralByDepartment.class }) Referral referral, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		referral.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(referral.getPage(), referral.getPageSize());
		List<Map<String, Object>> referralList = referralService.getReferralByDepartment(referral);
		if (referralList != null && !referralList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), referralList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param referral
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 修改分诊转诊单
	 */
	@RequiresAuthentication(authc = true , value = { })
	@PutMapping(value = { "/referral/triage/{id}" })
	@Transactional
	public JsonApi update(@PathVariable("id")Integer id,
			@Validated({ Referral.Triage.class })@RequestBody Referral referral, BindingResult result) {
		referral.setId(id);
		/*查询数据是否存在*/
		Map<String, Object> referralOne = referralService.getOne(referral);
		if (referralOne!=null && !referralOne.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("referral.not.found"));
		}
		if(null != referralOne.get("organizationTeamId")) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("referral.is.triage"));
		}
		
		Referral referralNew = new Referral();
		/*修改数据*/
		referralNew.setId(id);
		referralNew.setDepartmentId(referral.getDepartmentId());
		referralNew.setOrganizationTeamId(referral.getOrganizationTeamId());
		referralNew.setOrganizationUserId(referral.getOrganizationUserId());
		if (referralService.update(referralNew) >0) {
				return new JsonApi(ApiCodeEnum.OK);
		}
		
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
