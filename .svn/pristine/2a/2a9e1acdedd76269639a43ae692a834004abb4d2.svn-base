package org.wechat.module.service.controller.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.token.RedisSession;
import org.service.tools.date.DateUtils;
import org.service.tools.md5.MD5Util;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.service.domain.order.WorkOrder;
import org.wechat.module.service.domain.order.WorkOrderLog;
import org.wechat.module.service.domain.user.UserService;
import org.wechat.module.service.global.BaseGlobal;
import org.wechat.module.service.global.BaseGlobalEnum;
import org.wechat.module.service.message.Prompt;
import org.wechat.module.service.service.order.WorkOrderLogService;
import org.wechat.module.service.service.order.WorkOrderService;
import org.wechat.module.service.service.user.UserServiceService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author <font color="red"><b>Wang.Hua.Qiang</b></font>
 * @Date 2018年8月15日
 * @Version
 * @Description 工单
 */
@RestController
public class WorkOrderController {

	@Resource
	private WorkOrderService workOrderService;
	@Resource
	private UserServiceService userServiceService;
	@Resource
	private WorkOrderLogService workOrderLogService;
	@Resource
	private RedisCacheManager cacheManager;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月19日
	 * @param workOrder
	 * @param result
	 * @param token
	 * @return  {@link JsonApi}
	 * @description: 发起工单
	 */
	@Transactional
	@RequiresAuthentication(authc = true)
	@PostMapping(value = { "/work/order" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) WorkOrder workOrder, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 获取用户缓存信息 */
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		Integer operationUserId = (Integer) session.get(Map.class).get("id");
		/* 判断服务是否存在 */
		UserService userService = new UserService();
		/* 设置用户服务类型id */
		userService.setId(workOrder.getUserServiceId());
		/* 根据服务类型id查询用户服务详情 */
		Map<String, Object> userServiceMap = userServiceService.getOne(userService);
		if (userServiceMap==null || userServiceMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.userService.unexist"));
		}
		/* 存在 判断用户购买的服务包是否过期 */
		if (!DateUtils.isBeforeNow((Date) userServiceMap.get("expireDate"))) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.service.package.order.is.expired"));
		}
		/* 查询用户是否存在未完成的相同服务的工单 */
		WorkOrder notFinishWorkOrder = new WorkOrder();
		notFinishWorkOrder.setUserId(workOrder.getUserId());
		notFinishWorkOrder.setUserServiceId(workOrder.getUserServiceId());
		Integer[] statuss = new Integer[] { BaseGlobalEnum.WorkOrderStatus.PENDINGRESPONSE.getValue(), BaseGlobalEnum.WorkOrderStatus.TOBEEXECUTED.getValue(), BaseGlobalEnum.WorkOrderStatus.INTHEEXECUTION.getValue() };
		notFinishWorkOrder.setStatuss(statuss);
		List<Map<String, Object>> notFinishWorkordersList = workOrderService.getList(notFinishWorkOrder);
		if (notFinishWorkordersList!=null && !notFinishWorkordersList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("exists.user.workorder.is.not.finished"));
		}
		/* 判断 剩余次数 */
		int times = Integer.parseInt(userServiceMap.get("availableTimes").toString());
		/* 判断服务次数是否大于0 */
		if (times <= 0) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.service.times.less.than.zero"));
		}
		// TODO 数据权限控制 判断被操作者是否是操作者的组成员
		/* 设置工单详细信息 */
		workOrder.setLaunchUserId(operationUserId);
		workOrder.setNumber(MD5Util.getInstance().getOrderNo());
		workOrder.setOrganizationTeamId(Integer.parseInt(userServiceMap.get("doctorTeamId").toString()));
		workOrder.setOrderSources(BaseGlobalEnum.WorkOrderStatus.ORDERRESOURCEONLINEWECHAT.getValue());
		workOrder.setStatus(BaseGlobalEnum.WorkOrderStatus.PENDINGRESPONSE.getValue());
		workOrder.setCreateDate(new Date());
		workOrder.setUpdateDate(new Date());
		/* 添加工单 */
		if (workOrderService.insert(workOrder) > 0) {
			/* 锁定次数+1 且 总次数-1 */
			if (userServiceService.lockTime(userService) > 0) {
				/* 添加日志 */
				WorkOrderLog workOrderLog = new WorkOrderLog();
				workOrderLog.setOperationUserId(operationUserId);
				workOrderLog.setLogType(BaseGlobalEnum.WorkOrderStatus.OPERATION_LOG.getValue());
				workOrderLog.setLaunchType(BaseGlobalEnum.WorkOrderStatus.USER_SPONSOR.getValue());
				workOrderLog.setWorkOrderId(workOrder.getId());
				workOrderLog.setStatus(BaseGlobalEnum.WorkOrderStatus.PENDINGRESPONSE.getValue());
				workOrderLog.setCreateDate(new Date());
				if (workOrderLogService.insert(workOrderLog) > 0) {
					return new JsonApi(ApiCodeEnum.OK);
				}
			}
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月19日
	 * @param id
	 * @param workOrder
	 * @param result
	 * @param token
	 * @return   {@link JsonApi}
	 * @description: 用户评价工单
	 */
	@Transactional
	@RequiresAuthentication(authc = true)
	@PutMapping(value = { "/work/order/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @RequestBody @Validated({ BaseEntity.Update.class }) WorkOrder workOrder, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 获取用户缓存信息 */
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		/* 登录用户的id */
		Integer operationUserId = (Integer) session.get(Map.class).get("id");
		workOrder.setId(id);
		/* 查询工单详情 */
		Map<String, Object> workOrderMap = workOrderService.getWorkOrderDetail(workOrder);
		if (workOrderMap==null || workOrderMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("workorder.is.not.exists"));
		}
		/* 工单状态为 已执行（待评价）:5-才能评价 */
		if (Integer.valueOf(workOrderMap.get("status").toString()) != BaseGlobalEnum.WorkOrderStatus.EXECUTED.getValue()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("workorder.status.fail"));
		}
		workOrder.setEvaluateDate(new Date());
		workOrder.setUpdateDate(new Date());
		workOrder.setStatus(BaseGlobalEnum.WorkOrderStatus.EVALUATED.getValue());
		/* 用户评价工单 */
		if (workOrderService.updateUserWorkOrder(workOrder) > 0) {
			/* 添加日志 */
			WorkOrderLog workOrderLog = new WorkOrderLog();
			workOrderLog.setOperationUserId(operationUserId);
			workOrderLog.setLogType(BaseGlobalEnum.WorkOrderStatus.OPERATION_LOG.getValue());
			workOrderLog.setLaunchType(BaseGlobalEnum.WorkOrderStatus.USER_SPONSOR.getValue());
			workOrderLog.setWorkOrderId(id);
			workOrderLog.setStatus(BaseGlobalEnum.WorkOrderStatus.EVALUATED.getValue());
			workOrderLog.setCreateDate(new Date());
			if (workOrderLogService.insert(workOrderLog) > 0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月19日
	 * @param workOrder
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 用户工单列表
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/work/orders" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) WorkOrder workOrder, BindingResult result) {
		// TODO 数据权限控制 判断被操作者是否是操作者的组成员
		Page<?> page = PageHelper.startPage(workOrder.getPage(), workOrder.getPageSize());
		/* 查询用户工单列表 */
		List<Map<String, Object>> workOrderList = workOrderService.getListByUserId(workOrder);
		if (workOrderList!=null && !workOrderList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), workOrderList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月19日
	 * @param workOrder
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 查询每天预约的人数
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/work/order/reservation/number" })
	public JsonApi getReservationNumber(@Validated({ WorkOrder.GetReservationNumber.class }) WorkOrder workOrder, BindingResult result) {
		// TODO 数据权限控制 判断被操作者是否是操作者的组成员
		Page<?> page = PageHelper.startPage(workOrder.getPage(), workOrder.getPageSize());
		/* 查询每天预约的人数 */
		List<Map<String, Object>> workOrdersResultList = workOrderService.getReservationNumber(workOrder);
		if (workOrdersResultList!=null && !workOrdersResultList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), workOrdersResultList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
