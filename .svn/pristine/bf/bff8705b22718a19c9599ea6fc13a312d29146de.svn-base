package org.web.module.bone.age.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.bone.age.domain.ReadFilmRechargeRecord;
import org.web.module.bone.age.global.BaseGlobal;
import org.web.module.bone.age.service.ReadFilmRechargeRecordService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author <font color="red"><b>Chen.Yan</b></font>
 * @Date 2019年2月25日
 * @Version
 * @Description 远程阅片充值记录
 */
@RestController
public class ReadFilmRechargeRecordController {
	@Resource
	private ReadFilmRechargeRecordService readFilmRechargeRecordService;
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月15日
	 * @param readFilmRechargeRecord
	 * @param result
	 * @return
	 * @description: 远程阅片充值记录列表
	 */
	@RequiresAuthentication(level=Level.OPERATION,value = { "web-module-bone-age:read-film-recharger-ecord:get-list" })
	@GetMapping(value = { "/read/film/recharge/records" })
	public JsonApi getList( @Validated({ BaseEntity.SelectAll.class })  ReadFilmRechargeRecord readFilmRechargeRecord, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		readFilmRechargeRecord.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(readFilmRechargeRecord.getPage(), readFilmRechargeRecord.getPageSize());
		List<Map<String, Object>> readFilmRechargeRecordList = readFilmRechargeRecordService.getList(readFilmRechargeRecord);
		if (readFilmRechargeRecordList != null && !readFilmRechargeRecordList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), readFilmRechargeRecordList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
