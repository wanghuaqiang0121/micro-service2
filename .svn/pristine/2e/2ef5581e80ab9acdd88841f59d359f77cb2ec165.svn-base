package org.web.module.organization.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.MedicalOrganizationType;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.MedicalOrganizationTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author <font color="red"><b>Wang.Hua.Qiang</b></font>
 * @Date 2018年8月6日
 * @Version
 * @Description 医疗机构类别
 */
@RestController
public class MedicalOrganizationTypeController {

	@Resource
	private MedicalOrganizationTypeService medicalOrganizationTypeService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param medicalOrganizationType
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 新增医疗机构类别表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-organization:medical-organization-type:insert" })
	@PostMapping(value = { "/medical/organization/type" })
	public JsonApi insert(
			@Validated({ BaseEntity.Insert.class }) @RequestBody MedicalOrganizationType medicalOrganizationType,
			BindingResult result) {
		medicalOrganizationType.setCreateDate(new Date());
		Map<String, Object> medicalOrganizationTypeRepeatMap = medicalOrganizationTypeService
				.getRepeat(medicalOrganizationType);
		if (MapUtils.isNotEmpty(medicalOrganizationTypeRepeatMap)) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("medica.organization.type.code.is.exists"));
		}
		if (medicalOrganizationTypeService.insert(medicalOrganizationType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param medicalOrganizationType
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 修改医疗机构类别表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-organization:medical-organization-type:update" })
	@PutMapping(value = { "/medical/organization/type/{id}" })
	public JsonApi update(@PathVariable("id") Integer id,
			@Validated({ BaseEntity.Update.class }) @RequestBody MedicalOrganizationType medicalOrganizationType,
			BindingResult result) {
		medicalOrganizationType.setId(id);
		Map<String, Object> medicalOrganizationTypeDetailMap = medicalOrganizationTypeService
				.getDetail(medicalOrganizationType);
		if (MapUtils.isEmpty(medicalOrganizationTypeDetailMap)) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		if (null != medicalOrganizationType.getCode()) {
			Map<String, Object> medicalOrganizationTypeRepeatMap = medicalOrganizationTypeService
					.getRepeat(medicalOrganizationType);
			if (MapUtils.isNotEmpty(medicalOrganizationTypeRepeatMap)) {
				if (!medicalOrganizationTypeDetailMap.get("id").equals(medicalOrganizationTypeRepeatMap.get("id"))) {
					return new JsonApi(ApiCodeEnum.CONFLICT)
							.setMsg(Prompt.bundle("medica.organization.type.code.is.exists"));
				}
			}
		}
		if (medicalOrganizationTypeService.update(medicalOrganizationType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param medicalOrganizationType
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 查询医疗机构类型表列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = {"web-module-organization:medical-organization-type:get-list" })
	@GetMapping(value = { "/medical/organization/types" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) MedicalOrganizationType medicalOrganizationType,
			BindingResult result) {
		Page<?> page = PageHelper.startPage(medicalOrganizationType.getPage(), medicalOrganizationType.getPageSize());
		List<Map<String, Object>> medicalOrganizationTypeList = medicalOrganizationTypeService
				.getList(medicalOrganizationType);
		if (medicalOrganizationTypeList != null && !medicalOrganizationTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), medicalOrganizationTypeList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

}
