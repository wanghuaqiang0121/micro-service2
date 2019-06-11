package org.wechat.module.height.obesity.controller.food;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.height.obesity.entity.UserFoodCompositionRecordChild;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.message.Prompt;
import org.wechat.module.height.obesity.service.UserFoodCompositionRecordChildService;
import org.wechat.module.height.obesity.service.feign.IUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月18日
 * @description: 用户吃食含量记录表
 */
@RestController 
public class UserFoodCompositionRecordChildController {
	@Resource
	private UserFoodCompositionRecordChildService  userFoodCompositionRecordChildService;
	@Resource
	private IUserService useruService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月18日
	 * @param userFoodCompositionRecordChild
	 * @param result
	 * @return
	 * @description: 饮食分析列表
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/user/food/composition/record/childs" })
	public JsonApi getList(@Validated(BaseEntity.SelectAll.class) UserFoodCompositionRecordChild userFoodCompositionRecordChild,BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		userFoodCompositionRecordChild.setUserId(userId);
		Page<?> page = PageHelper.startPage(userFoodCompositionRecordChild.getPage(), userFoodCompositionRecordChild.getPageSize());
		List<Map<String, Object>> foodCompositionList = userFoodCompositionRecordChildService.getList(userFoodCompositionRecordChild);
		if (foodCompositionList != null && !foodCompositionList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), foodCompositionList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
