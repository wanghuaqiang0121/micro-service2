package org.wechat.module.service.controller.inquire;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.service.domain.inquire.Inquiry;
import org.wechat.module.service.domain.inquire.InquiryReply;
import org.wechat.module.service.domain.inquire.ServiceTimeout;
import org.wechat.module.service.global.BaseGlobal;
import org.wechat.module.service.global.BaseGlobalEnum;
import org.wechat.module.service.global.ServiceType;
import org.wechat.module.service.message.Prompt;
import org.wechat.module.service.service.inquire.InquiryReplyService;
import org.wechat.module.service.service.inquire.InquiryService;
import org.wechat.module.service.task.IInquiryService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author <font color="red"><b>Wang.Hua.Qiang</b></font>
 * @Date 2018年10月11日
 * @Version 
 * @Description 问询
 */
@RestController
public class InquiryController {
	
	@Resource
	private InquiryService inquiryService;
	@Resource
	private InquiryReplyService inquiryReplyService;
	@Resource
	private RedisCacheManager cacheManager;
	@Resource
	private IInquiryService iInquiryService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月31日
	 * @param inquiry
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 添加问询
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@PostMapping(value = { "/inquiry" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class })  Inquiry inquiry,BindingResult result){
		inquiry.setStatus(BaseGlobalEnum.Status.TOBEACCEPTED.getValue());
		inquiry.setCreateDate(new Date());
		if (inquiryService.insert(inquiry) > 0) {
			/* 24小时自动结束 */
			ServiceTimeout serviceTimeout = new ServiceTimeout();
			serviceTimeout.setId(inquiry.getId());
			serviceTimeout.setType(ServiceType.INQUIRY);
			serviceTimeout.setTime(DateUtils.calLastedTimes(new Date(),DateUtils.getDayAfter(1)));
			iInquiryService.sendTimeout(serviceTimeout);
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
/**
	 * @author: ChenYan
	 * @date: 2018年10月31日
	 * @param inquiryReply
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 追问
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@PostMapping(value = { "/inquiryReply" })
	public JsonApi insertInquiryReply(@RequestBody @Validated({ BaseEntity.Insert.class })  InquiryReply inquiryReply,BindingResult result){
		inquiryReply.setType(BaseGlobalEnum.Type.INQUIRIES.getValue());
		inquiryReply.setCreateDate(new Date());
		inquiryReply.setInquiriesNum(BaseGlobalEnum.InquiriesNum.TOTAL.getValue());
		/* 判断追问次数 */
		Map<String, Object> map = inquiryReplyService.getInquiriesNum(inquiryReply);
		if (map!=null && !map.isEmpty()) {
			Long num = (Long)map.get("inquiriesNum");
			if (num.intValue() < 1) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("inquiries.num.is.less.than.one"));
			}
			if (inquiryReplyService.insert(inquiryReply) > 0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月31日
	 * @param token
	 * @param inquiry
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 用户问询列表
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@GetMapping(value = { "/inquirys" })
	public JsonApi getList(@RequestHeader(value = BaseGlobal.TOKEN_FLAG) String token, @Validated({ BaseEntity.SelectAll.class })  Inquiry inquiry,BindingResult result){
		/* 获取用户缓存信息 */
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		/* 登录用户的id*/
		Integer operationUserIdId = (Integer) session.get(Map.class).get("id");
		inquiry.setUserId(operationUserIdId);
		Page<?> page = PageHelper.startPage(inquiry.getPage(), inquiry.getPageSize());
		List<Map<String, Object>> inquirysList = inquiryService.getList(inquiry);
		if (inquirysList!=null && !inquirysList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),inquirysList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月31日
	 * @param id
	 * @param inquiry
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 评价问询
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@RequestMapping(value = { "/inquiry/{id}" }, method = RequestMethod.PUT)
	public JsonApi update(@PathVariable("id") Integer id ,@RequestBody @Validated({ BaseEntity.Update.class }) Inquiry inquiry,BindingResult result){
		/* 同时修改状态: */
		inquiry.setId(id);
		Map<String, Object> map = inquiryService.getOne(inquiry);
		if (map!=null && !map.isEmpty()) {
			if (BaseGlobalEnum.Status.REPLIES.getValue() == ((Integer)map.get("status"))) {
				inquiry.setStatus(BaseGlobalEnum.Status.CLOSED.getValue());
				if (inquiryService.update(inquiry) > 0) {
					return new JsonApi(ApiCodeEnum.OK);
				}
			}
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("inquiry.status.is.error"));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月31日
	 * @param id
	 * @param inquiry
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 问询详情
	 */
	@RequiresAuthentication(authc = true, value = { "" })
	@GetMapping(value = { "/inquiry/{id}" })
	public JsonApi detail(@PathVariable("id") Integer id , @Validated({ BaseEntity.SelectOne.class }) Inquiry inquiry,BindingResult result){
		inquiry.setId(id);
		InquiryReply inquiryReply = new InquiryReply();
		inquiryReply.setInquiriesNum(BaseGlobalEnum.InquiriesNum.TOTAL.getValue());
		inquiryReply.setType(BaseGlobalEnum.Type.INQUIRIES.getValue());
		inquiry.setInquiryReply(inquiryReply);
		Map<String, Object> map = inquiryService.getOne(inquiry);
		if (map!=null && !map.isEmpty()){
			return new JsonApi(ApiCodeEnum.OK,map);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	
	
}
