package org.web.module.organization.controller.doctor;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.user.DoctorCategory;
import org.web.module.organization.service.user.DoctorCategoryService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author <font color="red"><b>Wang.Hua.Qiang</b></font>
 * @Date 2018年8月14日
 * @Version 
 * @Description 医生类别
 */
@RestController
public class DoctorCategoryController {

	@Resource
	private DoctorCategoryService doctorCategoryService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月6日
	 * @param doctorCategory
	 * @param result
	 * @return
	 * @description: 查询医生类别列表
	 */
	@RequiresAuthentication( value = { "web-module-organization:doctor-category:get-list" },level=Level.OPERATION)
	@RequestMapping(value = { "/doctor/categorys" }, method = RequestMethod.GET)
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) DoctorCategory  doctorCategory,BindingResult result ){
		Page<?> page = PageHelper.startPage(doctorCategory.getPage(), doctorCategory.getPageSize());
		List<Map<String, Object>>  doctorCategoryList=doctorCategoryService.getList(doctorCategory);
		if (doctorCategoryList!=null && !doctorCategoryList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), doctorCategoryList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
	}
}
