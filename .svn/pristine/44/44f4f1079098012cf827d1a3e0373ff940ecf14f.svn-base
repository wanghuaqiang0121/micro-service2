package org.wechat.module.height.obesity.controller.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.height.obesity.entity.FoodCompositionRecord;
import org.wechat.module.height.obesity.entity.UserFoodCompositionRecord;
import org.wechat.module.height.obesity.entity.UserFoodCompositionRecordChild;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.message.Prompt;
import org.wechat.module.height.obesity.service.FoodCompositionRecordService;
import org.wechat.module.height.obesity.service.UserFoodCompositionRecordChildService;
import org.wechat.module.height.obesity.service.UserFoodCompositionRecordService;
import org.wechat.module.height.obesity.service.feign.IUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年12月18日
 * @description: 用户饮食记录表
 */
@RestController
public class UserFoodCompositionRecordController {
	@Resource
	private UserFoodCompositionRecordService  userFoodCompositionRecordService;
	@Resource
	private FoodCompositionRecordService foodCompositionRecordService;
	@Resource
	private UserFoodCompositionRecordChildService  userFoodCompositionRecordChildService;
	@Resource
	private IUserService useruService;
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月18日
	 * @param userFoodCompositionRecord
	 * @param result
	 * @param token
	 * @return
	 * @description: 新增用户饮食记录
	 */
	@RequiresAuthentication(authc = true, value = { })
	@PostMapping(value = { "/user/food/composition/record" })
	@Transactional
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) UserFoodCompositionRecord userFoodCompositionRecord, BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		/*设置用户饮食记录默认值*/
		userFoodCompositionRecord.setCreateDateTime(new Date());
		userFoodCompositionRecord.setUserId(userId);
		/*新增用户饮食记录*/
		if (userFoodCompositionRecordService.insert(userFoodCompositionRecord)>0) {
			/*创建设置用户吃食含量记录List*/
			List<UserFoodCompositionRecordChild> userFoodCompositionRecordChilds =new ArrayList<>();
			/*查询某种食物的成分含量*/
			FoodCompositionRecord foodCompositionRecord=new FoodCompositionRecord();
			foodCompositionRecord.setFoodCompositionCode(userFoodCompositionRecord.getFoodCompositionCode());
			List<Map<String, Object>> foodCompositionRecordList =foodCompositionRecordService.getList(foodCompositionRecord);
			Double value=null;
			/*设置用户吃食含量记录默认值*/
			UserFoodCompositionRecordChild userFoodCompositionRecordChild=null;
			for (Map<String, Object> map : foodCompositionRecordList) {
				userFoodCompositionRecordChild = new UserFoodCompositionRecordChild();				
				userFoodCompositionRecordChild.setUserFoodCompositionRecordId(userFoodCompositionRecord.getId());
				userFoodCompositionRecordChild.setUserId(userId);
				userFoodCompositionRecordChild.setCreateDateTime(new Date());
				userFoodCompositionRecordChild.setCode(map.get("code").toString());
				userFoodCompositionRecordChild.setName(map.get("codeName").toString());
				/*计算出的实际吃的食物含量*/
				value=	Double.parseDouble(map.get("value").toString())/100*userFoodCompositionRecord.getValue();
				userFoodCompositionRecordChild.setValue(new Float(value));
				/*把每个对象放在List里*/
				userFoodCompositionRecordChilds.add(userFoodCompositionRecordChild);
			}
			/*批量新增用户吃食含量记录*/
			if (userFoodCompositionRecordChildService.batchInsert(userFoodCompositionRecordChilds)==userFoodCompositionRecordChilds.size()) {
				return new JsonApi(ApiCodeEnum.OK);
			}
			throw new RuntimeException();
		}
		
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月17日
	 * @param userFoodCompositionRecord
	 * @param result
	 * @return
	 * @description: 饮食记录列表
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/user/food/composition/records" })
	public JsonApi getList(@Validated(BaseEntity.SelectAll.class) UserFoodCompositionRecord userFoodCompositionRecord,
			BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		userFoodCompositionRecord.setUserId(userId);
		Page<?> page = PageHelper.startPage(userFoodCompositionRecord.getPage(), userFoodCompositionRecord.getPageSize());
		List<Map<String, Object>> codeTableList = userFoodCompositionRecordService.getList(userFoodCompositionRecord);
		if (codeTableList != null && !codeTableList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), codeTableList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年12月17日
	 * @param userFoodCompositionRecord
	 * @param result
	 * @param token
	 * @return
	 * @description:  饮食历史记录(每天)
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/user/food/composition/record/day" })
	public JsonApi getUserFoodCompositionRecordEveryDay(@Validated(BaseEntity.SelectAll.class) UserFoodCompositionRecord userFoodCompositionRecord,
			BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		userFoodCompositionRecord.setUserId(userId);
		Page<?> page = PageHelper.startPage(userFoodCompositionRecord.getPage(), userFoodCompositionRecord.getPageSize());
		List<Map<String, Object>> codeTableList = userFoodCompositionRecordService.getUserFoodCompositionRecordEveryDay(userFoodCompositionRecord);
		if (codeTableList != null && !codeTableList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), codeTableList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	
}
