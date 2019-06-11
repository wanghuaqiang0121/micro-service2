package org.wechat.module.service.controller.contract;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.redis.cache.RedisCacheManager;
import org.service.tools.md5.MD5Util;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.service.domain.contract.UserSign;
import org.wechat.module.service.domain.contract.UserSignLog;
import org.wechat.module.service.domain.user.User;
import org.wechat.module.service.domain.user.UserUserType;
import org.wechat.module.service.global.BaseGlobalEnum;
import org.wechat.module.service.message.Prompt;
import org.wechat.module.service.service.contract.UserSignLogService;
import org.wechat.module.service.service.contract.UserSignService;
import org.wechat.module.service.service.user.UserService;
import org.wechat.module.service.service.user.UserUserTypeService;

import com.alibaba.fastjson.JSON;


/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 用户签约
 */
@RestController
public class UserSignController {

	@Resource
	private UserSignService userSignService;
	@Resource
	private RedisCacheManager cacheManager;
	@Resource
	private UserService userService;
	@Resource
	private UserSignLogService userSignLogService;
	@Resource
	private UserUserTypeService userUserTypeService;

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param userSign
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 线上用户签约和申请包
	 */
	@Transactional
	@RequiresAuthentication(authc = true)
	@PostMapping(value = { "/sign/online" })
	public JsonApi insert(@RequestBody @Validated({ UserSign.onlineSign.class }) UserSign userSign,
			BindingResult result) {
		/* 设置发起方式\n1：线下\n2：线上*/		userSign.setLaunchType(BaseGlobalEnum.LaunchTypeEnum.ONLINE.getValue());
	/*判断用户是否存在*/
		User user = new User();
		user.setId(userSign.getUserId());
		Map<String, Object> userOneResult = userService.getOne(user);
		if (userOneResult==null || userOneResult.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.is.not.exists"));
		}
		 /*判断用户是否签约*/
		Map<String, Object> userSignOneResult = userSignService.getOne(userSign);
		if (userSignOneResult != null && userOneResult.size() > 0) {
			/*判断状态*/
			if (userSignOneResult.get("status").equals(BaseGlobalEnum.StatusEnum.EXECUTIONED)) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.user.sign.is.exists"));
			} else {
				userSign.setId((Integer)userSignOneResult.get("id"));
				userSign.setUpdateDate(new Date());
			/* TODO 数据权限控制 判断被操作者是否是操作者的组成员*/
				userSign.setStatus(BaseGlobalEnum.StatusEnum.WAITRESPONSE.getValue());
				userSign.setSignNo(MD5Util.getInstance().getOrderNo());
				userSign.setUpdateDate(new Date());
				if (userSignService.update(userSign) > 0) {
					/*修改签约 删除人群分类 新增人群分类 新增日志*/
					/*添加日志*/ 
					UserSignLog userSignLog = new UserSignLog();
					userSignLog.setType(BaseGlobalEnum.LogTypeEnum.OPERATIONLOG.getValue());
					userSignLog.setUserSignId(userSign.getId());
					userSignLog.setStatus(userSign.getStatus());
					userSignLog.setExplain("线上签约");
					userSignLog.setContent(JSON.toJSONString(userSign));
					userSignLog.setCreateDate(new Date());
					/* 新增签约日志 */
					if (userSignLogService.insert(userSignLog) > 0) {
						/*删除人群类型关联重新添加*/
						UserUserType userUserTypes = new UserUserType();
						userUserTypes.setUserId(userSign.getUserId());
						userUserTypeService.delete(userUserTypes);
						/*批量添加人群分类*/ 
						if (userSign.getUserUserType()!=null) {
							List<UserUserType> userUserType=userSign.getUserUserType();
								if (userUserTypeService.batchInsert(userUserType)==userSign.getUserUserType().size()) {
									return new JsonApi(ApiCodeEnum.OK);
								}
								throw new RuntimeException();
							}					
						return new JsonApi(ApiCodeEnum.OK);
					}
					throw new RuntimeException();
				}return new JsonApi(ApiCodeEnum.FAIL);
			}
		} else {
			userSign.setUpdateDate(new Date());
			/* TODO 数据权限控制 判断被操作者是否是操作者的组成员*/
			userSign.setStatus(BaseGlobalEnum.StatusEnum.WAITRESPONSE.getValue());
			userSign.setSignNo(MD5Util.getInstance().getOrderNo());
			userSign.setUpdateDate(new Date());
			userSign.setCreateDate(new Date());
			/* 添加签约*/
			if (userSignService.insert(userSign) > 0) {
				/* 新增签约 新增人群分类 新增日志*/
				/*添加日志*/ 
				UserSignLog userSignLog = new UserSignLog();
				userSignLog.setType(BaseGlobalEnum.LogTypeEnum.OPERATIONLOG.getValue());
				userSignLog.setUserSignId(userSign.getId());
				userSignLog.setStatus(userSign.getStatus());
				userSignLog.setExplain("线上签约");
				userSignLog.setContent(JSON.toJSONString(userSign));
				userSignLog.setCreateDate(new Date());
				/* 新增签约日志 */
				if (userSignLogService.insert(userSignLog) > 0) {
					/*批量添加人群分类*/ 
					if (userSign.getUserUserType()!=null) {
						List<UserUserType> userUserType=userSign.getUserUserType();
							if (userUserTypeService.batchInsert(userUserType)==userSign.getUserUserType().size()) {
								return new JsonApi(ApiCodeEnum.OK);
							}
							throw new RuntimeException();
						}					
					return new JsonApi(ApiCodeEnum.OK);
				}
				throw new RuntimeException();
			}
			return new JsonApi(ApiCodeEnum.FAIL);
		}
	}
}
