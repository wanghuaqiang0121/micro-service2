package org.wechat.module.user.controller;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity.SelectAll;
import org.service.core.entity.BaseEntity.SelectOne;
import org.service.tools.date.DateUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.user.domain.OrganizationPackageService;
import org.wechat.module.user.domain.TeamOrganizationServicePackage;
import org.wechat.module.user.domain.User;
import org.wechat.module.user.domain.UserService;
import org.wechat.module.user.domain.UserServicePackageOrder;
import org.wechat.module.user.global.UserStatusCode;
import org.wechat.module.user.message.Prompt;
import org.wechat.module.user.rabbit.IUserRabbitService;
import org.wechat.module.user.service.OrganizationPackageServiceService;
import org.wechat.module.user.service.TeamOrganizationServicePackageService;
import org.wechat.module.user.service.UserServicePackageOrderService;
import org.wechat.module.user.service.UserServiceService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月25日
 * @description: 用户订单表
 */
@RestController
public class UserServicePackageOrderController {

	@Resource
	private UserServicePackageOrderService userServicePackageOrderService;
	@Resource
	private TeamOrganizationServicePackageService teamOrganizationServicePackageService;
	@Resource
	private OrganizationPackageServiceService organizationPackageServiceService;
	@Resource
	private UserServiceService userServiceService;
	@Resource
	private IUserRabbitService iUserRabbitService;
	@Resource
	private  org.wechat.module.user.service.UserService userService;
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userServicePackageOrder
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询用户订单列表
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/user/service/package/order" })
	public JsonApi getUseOrderList(@Validated({ SelectAll.class }) UserServicePackageOrder userServicePackageOrder,
			BindingResult result) {
		Page<?> page = PageHelper.startPage(userServicePackageOrder.getPage(), userServicePackageOrder.getPageSize());
		List<Map<String, Object>> userServicePackageOrderList = userServicePackageOrderService.getList(userServicePackageOrder);
		if (userServicePackageOrderList != null && userServicePackageOrderList.size() > 0) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), userServicePackageOrderList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param id
	 * @param userServicePackageOrder
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 订单详情
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/user/service/package/order/{id}" })
	public JsonApi getUseOrderDetail(@PathVariable("id")Integer id,
			@Validated({ SelectOne.class }) UserServicePackageOrder userServicePackageOrder,BindingResult result) {
		userServicePackageOrder.setId(id);
		Map<String, Object> userServicePackageOrderOneMap = userServicePackageOrderService.getOne(userServicePackageOrder);
		if (userServicePackageOrderOneMap!=null && !userServicePackageOrderOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,userServicePackageOrderOneMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userServicePackageOrder
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 线上新增订单
	 */
	@RequiresAuthentication(authc = true)
	@PostMapping(value = { "/user/order/online" })
	@Transactional
	public JsonApi insertUserOnlineOrder(@Validated({
			UserServicePackageOrder.onlineOrder.class }) @RequestBody UserServicePackageOrder userServicePackageOrder,
			BindingResult result) {
		User user = new User();
		user.setId(userServicePackageOrder.getUserId());
		Map<String, Object> userMap = userService.getOne(user);
		if ((userMap!=null && !userMap.isEmpty()) && (Integer)userMap.get("source") != null) {
			user.setSource(UserStatusCode.Source.BUYSERVICEPACKAGE.getValue());
			if (userService.update(user) <= 0) {
				throw new RuntimeException();
			}
		}
		/* 查询套餐包是否存在*/
		TeamOrganizationServicePackage teamOrganizationServicePackage = new TeamOrganizationServicePackage();
		teamOrganizationServicePackage.setId(userServicePackageOrder.getTeamOrganizationServicePackageId());
		Map<String, Object> teamOrganizationServicePackageOneMap = teamOrganizationServicePackageService.getOne(teamOrganizationServicePackage);
		if (teamOrganizationServicePackageOneMap==null || teamOrganizationServicePackageOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("team.organization.service.package.data.unexist"));
		}
		/*设置订单*/ 
		userServicePackageOrder.setStatus(UserStatusCode.UserServicePackageOrder.Waiting.getValue());
		userServicePackageOrder.setType(UserStatusCode.Channel.OnLineWechat.getValue());
		/* 设置默认值 */
		/* 设置服务包团队ID */
		userServicePackageOrder.setDoctorTeamId(Integer.parseInt(teamOrganizationServicePackageOneMap.get("doctorTeamId").toString()));
		/* 设置机构服务包ID */
		userServicePackageOrder.setOrganizationServicePackageId(Integer.parseInt(teamOrganizationServicePackageOneMap.get("organizationServicePackageId").toString()));
		/* 设置订单价格 */
		userServicePackageOrder.setPrice(new BigDecimal(teamOrganizationServicePackageOneMap.get("price").toString()));
		String OrderNo = UserStatusCode.UserServicePackageOrder.OrderNumber.GetOrderNumber(
				UserStatusCode.Channel.OnLineWechat.getValue(), UserStatusCode.Pay.WeChat.getValue(),
				UserStatusCode.Business.yzg.getValue(), userServicePackageOrder.getUserId());
		/* 设置订单号码 */
		userServicePackageOrder.setOrderNo(OrderNo);
		/* 设置订单创建时间 */
		userServicePackageOrder.setCreateDate(new Date());
		userServicePackageOrder.setMerchantNumber(teamOrganizationServicePackageOneMap.get("merchantNumber") != null ? teamOrganizationServicePackageOneMap.get("merchantNumber").toString() : null);
		userServicePackageOrder.setAppId(teamOrganizationServicePackageOneMap.get("appId") != null ? teamOrganizationServicePackageOneMap.get("appId").toString() : null);
		userServicePackageOrder.setAppSecret(teamOrganizationServicePackageOneMap.get("appSecret") != null ? teamOrganizationServicePackageOneMap.get("appSecret").toString() : null);
		userServicePackageOrder.setPayKey(teamOrganizationServicePackageOneMap.get("payKey").toString() != null ? teamOrganizationServicePackageOneMap.get("payKey").toString() : null);
		userServicePackageOrder.setWechatId(teamOrganizationServicePackageOneMap.get("wechatId") != null ? teamOrganizationServicePackageOneMap.get("wechatId").toString() : null);
		userServicePackageOrder.setWechatKey(teamOrganizationServicePackageOneMap.get("wechatKey") != null ? teamOrganizationServicePackageOneMap.get("wechatKey").toString() : null);
		if (userServicePackageOrderService.insert(userServicePackageOrder) > 0) {
			// 商户号信息
			Map<String, Object> merchantNumberMap = new HashMap<>();
			merchantNumberMap.put("orderId", userServicePackageOrder.getId());
			merchantNumberMap.put("merchantNumber", teamOrganizationServicePackageOneMap.get("merchantNumber") != null ? teamOrganizationServicePackageOneMap.get("merchantNumber").toString() : null);
			merchantNumberMap.put("payKey", teamOrganizationServicePackageOneMap.get("payKey") != null ? teamOrganizationServicePackageOneMap.get("payKey").toString() : null);
			merchantNumberMap.put("wechatId", teamOrganizationServicePackageOneMap.get("wechatId")!= null ? teamOrganizationServicePackageOneMap.get("wechatId").toString() : null);
			merchantNumberMap.put("wechatKey", teamOrganizationServicePackageOneMap.get("wechatKey") != null ? teamOrganizationServicePackageOneMap.get("wechatKey").toString() : null);
			merchantNumberMap.put("price", teamOrganizationServicePackageOneMap.get("price") != null ?teamOrganizationServicePackageOneMap.get("price").toString() : null);
			merchantNumberMap.put("originalPrice", teamOrganizationServicePackageOneMap.get("originalPrice") != null ? teamOrganizationServicePackageOneMap.get("originalPrice").toString() : null);
			Map<String, Object> parm = new HashMap<>();
			parm.put("userId",user.getId());
			parm.put("organizationTeamId", Integer.parseInt(teamOrganizationServicePackageOneMap.get("doctorTeamId").toString()));
			parm.put("isIncrementService", true);
			iUserRabbitService.userRabbit(parm);
			return new JsonApi(ApiCodeEnum.OK,merchantNumberMap);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月31日
	 * @param id
	 * @param userServicePackageOrder
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 确认订单
	 */
	@RequiresAuthentication(authc = true, value = { "user:order:sure" })
	@RequestMapping(value = { "/user/order/sure/{id}" }, method = RequestMethod.PUT)
	@Transactional
	public JsonApi updateUserSureOrder(@PathVariable("id") Integer id,
			@Validated({ UserServicePackageOrder.sureOrder.class }) UserServicePackageOrder userServicePackageOrder,BindingResult result) {
		userServicePackageOrder.setId(id);
		// 订单是否存在
		/* 设置条件 */
		/* 查询订单详情，获取机构服务包ID查询服务列表 */
		Map<String, Object> orderMap = userServicePackageOrderService.getOne(userServicePackageOrder);
		if (orderMap==null || orderMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.service.package.order.data.empty"));
		}
		/* 只有状态：0 待支付， 可以进行确认完成订单 */
		int status = Integer.parseInt(orderMap.get("status").toString());
		if (UserStatusCode.UserServicePackageOrder.Waiting.getValue() != status) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.service.package.order.status.error"));
		}
		/* 设置完成支付状态：2 已支付 */
		userServicePackageOrder.setStatus(UserStatusCode.UserServicePackageOrder.Success.getValue());
		/* 设置完成时间 */
		userServicePackageOrder.setCompleteDate(new Date());
		// 取有效期类型
		int acquisitiveType = (int) orderMap.get("acquisitiveType");
		// 取有效期
		int acquisitive = (int) orderMap.get("acquisitive");
		/* 设置过期时间:根据有效期类型
		1：按年 :则是指从签约当天起向后顺延12个月
		2：按自然年: 自然年就是指到当年的12月31日结束
		3：按月
		4：按自然月
		 */
		if (acquisitiveType == UserStatusCode.OrganizationPackage.YEAROFNATURE.getValue()) {
			userServicePackageOrder.setExpireDate(DateUtils.getYearOrMonthLastDay(UserStatusCode.OrganizationPackage.YEAR.toString(),acquisitive));
		}else if(acquisitiveType == UserStatusCode.OrganizationPackage.MONTHOFNATURE.getValue()){
			userServicePackageOrder.setExpireDate(DateUtils.getYearOrMonthLastDay(UserStatusCode.OrganizationPackage.MONTH.toString(),acquisitive));
		}else if (acquisitiveType == UserStatusCode.OrganizationPackage.YEAR.getValue()) {
			userServicePackageOrder.setExpireDate(DateUtils.getYearOrMonthLater(UserStatusCode.OrganizationPackage.YEAR.toString(), acquisitive));
		}else if (acquisitiveType == UserStatusCode.OrganizationPackage.MONTH.getValue()) {
			userServicePackageOrder.setExpireDate(DateUtils.getYearOrMonthLater(UserStatusCode.OrganizationPackage.MONTH.toString(), acquisitive));
		}
		/* 修改订单状态，过期时间 */
		if (userServicePackageOrderService.updateSureUserServicePackageOrder(userServicePackageOrder) > 0) {
			/* 查询服务包内的服务列表 */
			OrganizationPackageService packageService = new OrganizationPackageService();
			packageService.setOrganizationServicePackageId(Integer.parseInt(orderMap.get("organizationServicePackageId").toString()));
			List<Map<String, Object>> serviceList = organizationPackageServiceService.getList(packageService);
			if (serviceList!=null && !serviceList.isEmpty()) {
				/* 创建用户服务集合:并进行设置值 */
				List<UserService> userServices = new ArrayList<>();
				UserService userService = null;
				for (int i = 0; i < serviceList.size(); i++) {
					userService = new UserService();
					userService.setUserId(Integer.parseInt(orderMap.get("userId").toString()));
					userService.setUserServicePackageOrderId(userServicePackageOrder.getId());
					userService.setServiceTypeId((int) serviceList.get(i).get("serviceTypeId"));
					userService.setTimes((int) serviceList.get(i).get("times"));
					userService.setLockTimes(0);
					userService.setUseTimes(0);
					userService.setCreateTime(new Date());
					userServices.add(userService);
				}
				/* 添加用户服务 */
				if (userServiceService.insertByList(userServices) != userServices.size()) {
					throw new RuntimeException();
				} else {
					return new JsonApi(ApiCodeEnum.OK);
				}
			}
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param id
	 * @param userServicePackageOrder
	 * @param token
	 * @return {@link JsonApi}
	 * @description: 取消订单
	 */
	@RequiresAuthentication(authc = true)
	@PutMapping(value = {"/user/order/refuse/{id}"})
	public JsonApi updateUserRefuseOrder(@PathVariable("id")Integer id,
			@Validated({UserServicePackageOrder.refuseOrder.class}) UserServicePackageOrder userServicePackageOrder,
			BindingResult result) {
		/* 设置条件 */
		userServicePackageOrder.setId(id);
		/* 查询订单详情，获取机构服务包ID查询服务列表 */
		Map<String, Object> orderMap = userServicePackageOrderService.getOne(userServicePackageOrder);
		if (orderMap==null || orderMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.service.package.order.data.empty"));
		}
		/* 只有状态：0 待支付， 可以进行取消订单 */
		int status=Integer.parseInt(orderMap.get("status").toString());
		if (UserStatusCode.UserServicePackageOrder.Waiting.getValue()!=status) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.service.package.order.status.error"));
		}
		/* 设置完成支付状态：1 已取消 */
		userServicePackageOrder.setStatus(UserStatusCode.UserServicePackageOrder.Cancel.getValue());
		/* 修改订单 */
		if (userServicePackageOrderService.updateCancelUserServicePackageOrder(userServicePackageOrder) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
