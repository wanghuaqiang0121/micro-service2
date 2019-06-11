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
import org.web.module.bone.age.domain.ReadFilmDoctorPrice;
import org.web.module.bone.age.global.BaseGlobal;
import org.web.module.bone.age.service.ReadFilmDoctorPriceService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author <font color="red"><b>Chen.Yan</b></font>
 * @Date 2019年2月25日
 * @Version
 * @Description 阅片医生价格配置
 */
@RestController
public class ReadFilmDoctorPriceController {
	@Resource
	private ReadFilmDoctorPriceService readFilmDoctorPriceService;
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月14日
	 * @param readFilmDoctorPrice
	 * @param result
	 * @return
	 * @description: 阅片医生价格配置列表
	 */
	@RequiresAuthentication(level=Level.OPERATION,value = { "web-module-bone-age:read-film-doctor-price:get-list" })
	@GetMapping(value = { "/read/film/doctor/prices" })
	public JsonApi getList( @Validated({ BaseEntity.SelectAll.class })  ReadFilmDoctorPrice readFilmDoctorPrice, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		readFilmDoctorPrice.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(readFilmDoctorPrice.getPage(), readFilmDoctorPrice.getPageSize());
		List<Map<String, Object>> readFilmDoctorPriceList = readFilmDoctorPriceService.getList(readFilmDoctorPrice);
		if (readFilmDoctorPriceList != null && !readFilmDoctorPriceList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), readFilmDoctorPriceList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
