package org.wechat.module.height.obesity.controller.index;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wechat.module.height.obesity.entity.ChildrenMeasure;
import org.wechat.module.height.obesity.entity.ChoiceQuestion;
import org.wechat.module.height.obesity.entity.User;
import org.wechat.module.height.obesity.entity.UserChoiceRecord;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.message.Prompt;
import org.wechat.module.height.obesity.service.ChildrenMeasureService;
import org.wechat.module.height.obesity.service.ChoiceQuestionService;
import org.wechat.module.height.obesity.service.UserChoiceRecordService;
import org.wechat.module.height.obesity.service.UserService;
import org.wechat.module.height.obesity.service.feign.IUserService;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: XiePeng
 * @date: 2018年12月27日
 * @description: 问题表
 */
@RestController
public class ChoiceQuestionController {
	
	@Resource
	private ChoiceQuestionService choiceQuestionService;
	@Resource 
	private ChildrenMeasureService childrenMeasureService;
    @Resource
    private IUserService iuserService;
	@Resource
	private UserChoiceRecordService userChoiceRecordService;
    @Resource
    private UserService userService;
	/** 
	 * @author: XiePeng
	 * @date: 2018年12月27日
	 * @param choiceQuestion
	 * @param result
	 * @return
	 * @description: 问题和选项列表
	 */
	//value = { "wechat-module-height-obesity:choice-question:get-list" },level = Level.OPERATION
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/choice/questions" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) ChoiceQuestion choiceQuestion,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			BindingResult result) {
        /*获取并设置用户ID  */
        Integer userId = null;
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token,token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            userId = Integer.parseInt(jsonApi.getData().toString());
        }else if (jsonApi.getCode() != ApiCodeEnum.OK.getValue()) {
			return jsonApi;
		}
        choiceQuestion.setUserId(userId);
        /* 查询用户月齡 */
        User user=new User();
        user.setId(userId);
        Map<String, Object> userMap = userService.getNewOne(user);
        if (userMap == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND,Prompt.bundle("user.is.null"));
        }
        if(userMap.get("monthAge")!=null){
            choiceQuestion.setMonthAge(Integer.parseInt(userMap.get("monthAge").toString()));
        }else {
            return new JsonApi(ApiCodeEnum.OK,userMap);
        }
		/* 查询用户月龄对应的问题 */
		Page<?> page = PageHelper.startPage(choiceQuestion.getPage(), choiceQuestion.getPageSize());
		List<Map<String, Object>> childrenMeasureList = choiceQuestionService.getList(choiceQuestion);
		if (childrenMeasureList != null){
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(), childrenMeasureList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: XiePeng
	 * @date: 2018年12月27日
	 * @param choiceQuestion
	 * @param result
	 * @return
	 * @description: 获取用户选择的问题和选项
	 */
	//value = { "wecaht-module-height-obesity:choice-question:selected" }
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/choice/questions/selected" })
	public JsonApi getSelected(@Validated({ BaseEntity.SelectAll.class }) ChoiceQuestion choiceQuestion,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			BindingResult result) {
        /*获取并设置用户ID  */
        Integer userId = null;
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token,token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            userId = Integer.parseInt(jsonApi.getData().toString());
        }else if (jsonApi.getCode() != ApiCodeEnum.OK.getValue()) {
			return jsonApi;
		}
        choiceQuestion.setUserId(userId);
		/* 查询用户月齡 */
		User user=new User();
		user.setId(userId);
		Map<String, Object> userMap = userService.getNewOne(user);
		if (userMap == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND,Prompt.bundle("user.is.null"));
		}
        if(userMap.get("monthAge")!=null){
            choiceQuestion.setMonthAge(Integer.parseInt(userMap.get("monthAge").toString()));
        }else {
			return new JsonApi(ApiCodeEnum.OK,userMap);
        }
		Page<?> page = PageHelper.startPage(choiceQuestion.getPage(), choiceQuestion.getPageSize());
		List<Map<String, Object>> childrenMeasureList = choiceQuestionService.getSelectedList(choiceQuestion);
		if (childrenMeasureList != null){
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(), childrenMeasureList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	/**
	 * @author: XiePeng
	 * @date: 2018年1月8日
	 * @param choiceQuestion
	 * @param result
	 * @return
	 * @description:勾选完成情况（完成度）
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/choice/questions/completed" })
	public JsonApi getCompleted(ChoiceQuestion choiceQuestion, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token, BindingResult result) {
        /*获取并设置用户ID  */
        Integer userId = null;
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token,token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            userId = Integer.parseInt(jsonApi.getData().toString());
        }else if (jsonApi.getCode() != ApiCodeEnum.OK.getValue()) {
			return jsonApi;
		}
        choiceQuestion.setUserId(userId);
	    ChildrenMeasure childrenMeasure = new ChildrenMeasure();
		childrenMeasure.setUserId(choiceQuestion.getUserId());
        /* 查询用户月齡 */
        User user=new User();
        user.setId(userId);
        Map<String, Object> userMap = userService.getNewOne(user);
        if (userMap == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND,Prompt.bundle("user.is.null"));
        }
		if(userMap.get("monthAge")!=null){
			choiceQuestion.setMonthAge(Integer.parseInt(userMap.get("monthAge").toString()));
		}else {
			return new JsonApi(ApiCodeEnum.OK,userMap);
		}
		//查询问题总数
		int count = choiceQuestionService.getQuestionCount(choiceQuestion);
		//查询当天已回答问题数
		int choice=choiceQuestionService.getRecordCount(choiceQuestion);
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后0位(四舍五入)
		numberFormat.setMaximumFractionDigits(0);
		String percent = numberFormat.format((float) choice / (float) count * 100)+"%";
        List<Map<String,Object>> recordMap=choiceQuestionService.getRecord(choiceQuestion);
        List<String> days=new ArrayList<>();
        if(recordMap!=null){
            for (Map<String, Object> map : recordMap) {
                for (String createDateTime : map.keySet()) {
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    String[] weekDays = { "七", "一", "二", "三", "四", "五", "六" };
                    Calendar cal = Calendar.getInstance(); // 获得一个日历
                    Date date = null;
                    try {
                        date = f.parse(map.get(createDateTime).toString());
                        cal.setTime(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
                    if (w < 0)
                        w = 0;
                    days.add(weekDays[w]);
                }
            }
        }
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("questionCount",count);
		resultMap.put("choiceItem",choice);
		resultMap.put("percent",percent);
		resultMap.put("week",days);
		return new JsonApi(ApiCodeEnum.OK,resultMap);
	}
    /**
     * @author: XiePeng
     * @date: 2018年1月9日
     * @param choiceQuestion
     * @param result
     * @return
     * @description:坚持记录的天数
     */
    @RequiresAuthentication(authc = true, value = { })
    @GetMapping(value = { "/choice/record/days" })
	public JsonApi getRecordDays(ChoiceQuestion choiceQuestion, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token, BindingResult result){
        /*获取并设置用户ID  */
        Integer userId = null;
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token,token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            userId = Integer.parseInt(jsonApi.getData().toString());
        }else if (jsonApi.getCode() != ApiCodeEnum.OK.getValue()) {
			return jsonApi;
		}
		User user=new User();
		user.setId(userId);
		Map<String, Object> userMap = userService.getNewOne(user);
		if (userMap == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND,Prompt.bundle("user.is.null"));
        }
        choiceQuestion.setUserId(userId);
        choiceQuestion.setCreateDateTime(new Date());
        Map<String,Object> daysMap=new HashMap<>();
        int days = choiceQuestionService.getRecordDays(choiceQuestion);
        daysMap.put("days",days);
        return new JsonApi(ApiCodeEnum.OK,daysMap);
    }/**
	 * @author: XiePeng
	 * @date: 2018年1月14日
	 * @param userChoiceRecord
	 * @param result
	 * @return
	 * @description:新增用户选择记录
	 */
	@RequiresAuthentication(authc = true, value = { })
	@PostMapping(value = { "/choice/record" })
	public JsonApi insertChoiceRecord(@RequestBody @Validated({ BaseEntity.Insert.class })UserChoiceRecord userChoiceRecord, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token, BindingResult result){
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else if (jsonApi.getCode() != ApiCodeEnum.OK.getValue()) {
			return jsonApi;
		}
		User user=new User();
		user.setId(userId);
		Map<String, Object> userMap = userService.getOne(user);
		if (userMap == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND,Prompt.bundle("user.is.null"));
		}
		userChoiceRecord.setUserId(userId);
		userChoiceRecord.setCreateDateTime(new Date());
        ChildrenMeasure childrenMeasure = new ChildrenMeasure();
        childrenMeasure.setUserId(userChoiceRecord.getUserId());
		if(userChoiceRecordService.insert(userChoiceRecord)>0){
			return new JsonApi((ApiCodeEnum.OK));
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
