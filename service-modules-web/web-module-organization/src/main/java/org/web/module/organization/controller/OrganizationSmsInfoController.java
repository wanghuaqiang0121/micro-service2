package org.web.module.organization.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.OrganizationSms;
import org.web.module.organization.domain.OrganizationSmsInfo;
import org.web.module.organization.global.BaseGlobalEnum;
import org.web.module.organization.service.OrganizationSmsInfoService;
import org.web.module.organization.service.OrganizationSmsService;

/**
 * @author <font color="red"><b>Wang.Hua.Qiang</b></font>
 * @Date 2018年11月7日
 * @Version 
 * @Description 机构短信
 * // TODO 待添加到新项目
 */
@RestController
public class OrganizationSmsInfoController {

	@Resource
	private OrganizationSmsInfoService organizationSmsInfoService;
	@Resource
	private OrganizationSmsService organizationSmsService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月3日
	 * @param organizationId
	 * @param organizationSmsInfo
	 * @param result
	 * @return
	 * @description: 机构短信账户详情
	 */
	@RequiresAuthentication(ignore = true,value = { "web-module-organization:organization-sms:get-detail" },level = Level.OPERATION)
	@RequestMapping(value = { "/organization/sms/info/{organizationId}" }, method = RequestMethod.GET)
	public JsonApi getDetail(
			@PathVariable("organizationId")Integer organizationId,
			@Validated({ BaseEntity.SelectOne.class }) OrganizationSmsInfo organizationSmsInfo, BindingResult result) {
		/*设置机构ID*/
		organizationSmsInfo.setOrganizationId(organizationId);
		/*查询机构短信帐户详情*/
		Map<String, Object> organizationSmsMap = organizationSmsInfoService.getOne(organizationSmsInfo);
		if (organizationSmsMap!=null && !organizationSmsMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,organizationSmsMap);
		}
		/*不存在数据 设置值返回*/
		Map<String, Object> organizationSmsMaps = new HashMap<>();
		organizationSmsMaps.put("totalFrequency", 0);
		organizationSmsMaps.put("useFrequency", 0);
		organizationSmsMaps.put("remainNum", 0);
		return new JsonApi(ApiCodeEnum.OK,organizationSmsMaps);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月3日
	 * @param organizationId
	 * @param organizationSmsInfo
	 * @param result
	 * @return
	 * @description: 机构短信修改(充值)
	 */
	@Transactional
	@RequiresAuthentication(ignore = true,value = { "web-module-organization:organization-sms:update" },level = Level.OPERATION)
	@RequestMapping(value = { "/organization/sms/update/{organizationId}" }, method = RequestMethod.PUT)
	public JsonApi update(
			@PathVariable("organizationId")Integer organizationId,
			@Validated({ BaseEntity.Update.class }) @RequestBody OrganizationSmsInfo organizationSmsInfo, BindingResult result) {
		/*设置机构ID*/
		organizationSmsInfo.setOrganizationId(organizationId);
		Map<String, Object> organizationSmsMap = organizationSmsInfoService.getOne(organizationSmsInfo);
		if (organizationSmsMap!=null && !organizationSmsMap.isEmpty()) {
			/*直接修改*/
			if (organizationSmsInfoService.update(organizationSmsInfo) > 0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
		}
		/*添加*/
		organizationSmsInfo.setUseFrequency(0);
		organizationSmsInfo.setCreateDate(new Date());
		if (organizationSmsInfoService.insert(organizationSmsInfo) > 0) {
			/*添加短信充值记录*/
			OrganizationSms organizationSms = new OrganizationSms();
			organizationSms.setOrganizationId(organizationId);
			organizationSms.setType(BaseGlobalEnum.OrganizationSmsType.RECHARGE.getValue());
			organizationSms.setStatus(BaseGlobalEnum.OrganizationSmsStatus.RECHARGE.getValue());
			organizationSms.setConsumeTimes(organizationSmsInfo.getTotalFrequency());
			organizationSms.setCreateDate(new Date());
			if (organizationSmsService.insert(organizationSms) < 0) {
				throw new RuntimeException();
			}
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
}
