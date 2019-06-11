package org.wechat.module.service.controller.bespeak;

import java.text.SimpleDateFormat;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.service.domain.bespeak.TeamAppointmentConfigureDetail;
import org.wechat.module.service.domain.bespeak.TeamAppointmentOrder;
import org.wechat.module.service.domain.bespeak.WechatApi;
import org.wechat.module.service.domain.inquire.ServiceTimeout;
import org.wechat.module.service.domain.user.User;
import org.wechat.module.service.domain.user.UserService;
import org.wechat.module.service.global.BaseGlobal;
import org.wechat.module.service.global.BaseGlobalEnum;
import org.wechat.module.service.global.BaseGlobalEnum.CycleNum;
import org.wechat.module.service.global.BaseGlobalEnum.IWechatApiType;
import org.wechat.module.service.global.BaseGlobalEnum.OrderStatus;
import org.wechat.module.service.global.ServiceType;
import org.wechat.module.service.message.Prompt;
import org.wechat.module.service.service.bespeak.TeamAppointmentConfigureDetailService;
import org.wechat.module.service.service.bespeak.TeamAppointmentOrderService;
import org.wechat.module.service.service.user.UserServiceService;
import org.wechat.module.service.task.IAppointmentService;
import org.wechat.module.service.task.IWeChatService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author <font color="red"><b>Wang.Hua.Qiang</b></font>
 * @Date 2018年10月11日
 * @Version 
 * @Description 用户预约订单
 */
@RestController
public class TeamAppointmentOrderController {
	@Resource
	private TeamAppointmentOrderService teamAppointmentOrderService;
	@Resource
	private TeamAppointmentConfigureDetailService teamAppointmentConfigureDetailService;
	@Resource
	private UserServiceService userServiceService;
	@Resource
	private RedisCacheManager cacheManager;
	@Resource
	private IWeChatService iWeChatService;
	@Resource
	private IAppointmentService appointmentService;
	@Resource
	private org.wechat.module.service.service.user.UserService userServices;
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月31日
	 * @param token
	 * @param TeamAppointmentOrder
	 * @param result
	 * @return {@link JsonApi}
	 * @description:  用户组预约列表
	 */
	@RequiresAuthentication(authc = true, value = {})
	@GetMapping(value = { "/team/appointment/orders" })
	public JsonApi getList(@RequestHeader(value = BaseGlobal.TOKEN_FLAG) String token,@Validated({ BaseEntity.SelectAll.class })  TeamAppointmentOrder TeamAppointmentOrder,BindingResult result){
		/* 获取用户缓存信息 */
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		/*登录用户的id*/ 
		Integer operationUserIdId = (Integer) session.get(Map.class).get("id");
		TeamAppointmentOrder.setUserId(operationUserIdId);
		Page<?> page = PageHelper.startPage(TeamAppointmentOrder.getPage(), TeamAppointmentOrder.getPageSize());
		List<Map<String, Object>> TeamAppointmentOrdersList = teamAppointmentOrderService.getList(TeamAppointmentOrder);
		if (TeamAppointmentOrdersList!=null && !TeamAppointmentOrdersList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),TeamAppointmentOrdersList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月31日
	 * @param teamAppointmentOrder
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 预约
	 */
	@Transactional
	@RequiresAuthentication(authc = true, value = {})
	@PostMapping(value = { "/team/appointment/order" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class })  TeamAppointmentOrder teamAppointmentOrder,BindingResult result){
		teamAppointmentOrder.setStatus(OrderStatus.PENDINGNUMBER.getValue());
		/* 限制时间只能在当前到7天后的时间 */
		if (!DateUtils.isEffectiveDate(teamAppointmentOrder.getAppointmentDate(),new Date(),DateUtils.getDayAfter(BaseGlobalEnum.CycleNum.WEEK.getValue()))) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("appointment.date.can.only.be.within.one.week"));
		}
		/* 查询团队预约配置详情是否存在*/
		TeamAppointmentConfigureDetail teamAppointmentConfigureDetail = new TeamAppointmentConfigureDetail();
		teamAppointmentConfigureDetail.setId(teamAppointmentOrder.getTeamAppointmentConfigureDetailId());
		Map<String, Object> appointmentConfigureDetailMap = teamAppointmentConfigureDetailService.getOne(teamAppointmentConfigureDetail);
		if (appointmentConfigureDetailMap==null || appointmentConfigureDetailMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("team.appointment.configure.detail.is.not.exists"));
		}
		UserService userService = new UserService();
		userService.setId(teamAppointmentOrder.getUserServiceId());
		//根据服务类型id查询用户服务详情 
		Map<String, Object> userServiceMap = userServiceService.getOne(userService);
		if(userServiceMap==null || userServiceMap.isEmpty()){
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.userService.unexist"));
		}
		/* 判断服务次数是否大于0 */
		int times = Integer.parseInt(userServiceMap.get("availableTimes").toString());
		if(times <= 0){
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.service.times.less.than.zero"));
		}
		
		/* 判断是否有未取号的预约 */
		Map<String, Object> appointmentMap = teamAppointmentOrderService.getOne(teamAppointmentOrder);
		if (appointmentMap!=null && !appointmentMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.appointment.is.exists"));
		}
		/* 查询预约号 */
		Map<String, Object> appointmentNoMap = teamAppointmentOrderService.getAppointmentNo(teamAppointmentOrder);
		teamAppointmentOrder.setAppointmentNo(Integer.parseInt(appointmentNoMap.get("appointmentNo").toString()));
		teamAppointmentOrder.setCreateDate(new Date());
		if (teamAppointmentOrderService.insert(teamAppointmentOrder) > 0) {
			if (userServiceService.lockTime(userService) > 0) {
				/*写入队列预约时间的第二天*/
				ServiceTimeout serviceTimeout = new ServiceTimeout();
				serviceTimeout.setId(teamAppointmentOrder.getId());
				serviceTimeout.setType(ServiceType.APPOINTMENT);
				/* 设置时间为当前时间到预约时间的第二天的秒数 */
				serviceTimeout.setTime(DateUtils.calLastedTimes(new Date(), DateUtils.getDateDayAfter(teamAppointmentOrder.getAppointmentDate(), 1)));
				appointmentService.sendTimeout(serviceTimeout);
				/*推送微信*/
				User user = new User();
				user.setId(teamAppointmentOrder.getUserId());
				
				Map<String, Object> userWechatMap = userServices.getWechat(user);
				if (appointmentConfigureDetailMap.get("appId") != null && userWechatMap != null && userWechatMap.get("userOpenId") != null) {
					WechatApi wechatApi= new WechatApi();
					wechatApi.setType(IWechatApiType.ONE.getValue());
					wechatApi.setAppId(appointmentConfigureDetailMap.get("appId").toString());
					wechatApi.setOpenId(userWechatMap.get("userOpenId").toString());
					if (userWechatMap.get("userName") != null) {
						wechatApi.setUserName(userWechatMap.get("userName").toString());
					}else{
						wechatApi.setUserName("-");
					}
					String appointmentDate = new SimpleDateFormat("yyyy-MM-dd").format(teamAppointmentOrder.getAppointmentDate())
							+" "+appointmentConfigureDetailMap.get("startDate").toString()+"~"
							+appointmentConfigureDetailMap.get("endDate").toString();
					wechatApi.setContent(Prompt.bundle("appointment.message", appointmentDate,appointmentConfigureDetailMap.get("serviceName").toString(),appointmentNoMap.get("appointmentNo").toString() ));
					/*通知人-推送人*/
					wechatApi.setNotifier(appointmentConfigureDetailMap.get("teamName").toString());
					wechatApi.setTime(0);
					wechatApi.setRecordId(0);
					wechatApi.setRemark("");
					/*预约成功推送微信*/
					iWeChatService.insertWechatNews(wechatApi);
					/* 预约时间在后天及7天后这个区间  推送预约时间的前一天的10:00:00 */
					if (DateUtils.isEffectiveDate(teamAppointmentOrder.getAppointmentDate(),DateUtils.getDayAfter(1),DateUtils.getDayAfter(CycleNum.WEEK.getValue()))) {
						wechatApi.setContent(Prompt.bundle("appointment.tips.message",appointmentConfigureDetailMap.get("organizatioName").toString(),appointmentConfigureDetailMap.get("serviceName").toString(), appointmentDate ));
						/* 计算 当前时间  到  预约时间的前一天的10:00:00相差的秒数*/
						wechatApi.setTime((int)DateUtils.calLastedTimes(new Date(),DateUtils.getDateDayBefore(teamAppointmentOrder.getAppointmentDate(), 1)) );
						/*预约提醒推送微信*/
						iWeChatService.insertWechatNews(wechatApi);
					}
				}
				return new JsonApi(ApiCodeEnum.OK);
			}
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月31日
	 * @param id
	 * @param teamAppointmentOrder
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 取消订单
	 */
	@RequiresAuthentication(authc = true, value = { " " })
	@GetMapping(value = { "/team/appointment/order/{id}" })
	public JsonApi cancelOrder(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class })  TeamAppointmentOrder teamAppointmentOrder,BindingResult result){
		teamAppointmentOrder.setId(id);
		Map<String, Object>  teamAppointmentOrderMap = teamAppointmentOrderService.getOne(teamAppointmentOrder);
		if (teamAppointmentOrderMap!=null && !teamAppointmentOrderMap.isEmpty()) {
			if (OrderStatus.FETCHEDNUMBER.getValue() == (int)teamAppointmentOrderMap.get("status")) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("status.is.fetched.number.cannot.cancle"));
			}
			teamAppointmentOrder.setStatus(OrderStatus.CANCEL.getValue());
			if (teamAppointmentOrderService.cancelOrder(teamAppointmentOrder) > 0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	
}
