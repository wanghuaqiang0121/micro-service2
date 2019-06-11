package org.wechat.module.height.obesity.controller.index;

import java.util.HashMap;
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
import org.wechat.module.height.obesity.entity.ChildrenMeasure;
import org.wechat.module.height.obesity.entity.User;
import org.wechat.module.height.obesity.entity.UserMeasurementRecord;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.message.Prompt;
import org.wechat.module.height.obesity.service.ChildrenMeasureService;
import org.wechat.module.height.obesity.service.UserMeasurementService;
import org.wechat.module.height.obesity.service.UserService;
import org.wechat.module.height.obesity.service.feign.IUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: XiePeng
 * @date: 2018/12/25
 * @description:儿童测量信息
 */
@RestController
public class ChildrenMeasureController {
    @Resource
    private IUserService iuserService;
    @Resource
    private ChildrenMeasureService childrenMeasureService;
    @Resource
    private UserMeasurementService userMeasurementService;
    @Resource
    private UserService userService;

    @RequiresAuthentication(authc = true, value = { })
    @GetMapping(value = { "/children/measure" })
    public JsonApi getDetail( @Validated({ BaseEntity.SelectOne.class }) ChildrenMeasure childrenMeasure, BindingResult result,
                             @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
        /*获取并设置用户ID  */
        Integer userId = null;
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token,token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            userId = Integer.parseInt(jsonApi.getData().toString());
        }else if (jsonApi.getCode() != ApiCodeEnum.OK.getValue()) {
            return jsonApi;
        }
        /* 查询用户月齡 */
        User user=new User();
        user.setId(userId);
        Map<String, Object> userMap = userService.getNewOne(user);
        if (userMap == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND, Prompt.bundle("user.is.null"));
        }
        childrenMeasure.setUserId(userId);
        Map<String, Object> childrenMeasureMap = childrenMeasureService.getDetail(childrenMeasure);
        UserMeasurementRecord userMeasurementRecord = new UserMeasurementRecord();
        userMeasurementRecord.setUserId(userId);
        userMeasurementRecord.setType(1);
        Map<String,Object> heightMap=userMeasurementService.getOne(userMeasurementRecord);
        userMeasurementRecord.setType(2);
        Map<String,Object> weightMap=userMeasurementService.getOne(userMeasurementRecord);
        userMeasurementRecord.setType(3);
        Map<String,Object> abdomenCircumferenceMap=userMeasurementService.getOne(userMeasurementRecord);
        userMeasurementRecord.setType(4);
        Map<String,Object> bodyFatMap=userMeasurementService.getOne(userMeasurementRecord);
        Map<String,Object> resultMap=new HashMap<>();
        if(userMap.get("monthAge")!=null){
            resultMap.put("monthAge",Integer.parseInt(userMap.get("monthAge").toString()));
        }else {
            return new JsonApi(ApiCodeEnum.OK,userMap);
        }
        if(childrenMeasureMap!=null){
            resultMap.put("idealHeight",childrenMeasureMap.get("idealHeight"));
        }
        if(heightMap!=null){
            resultMap.put("currentHeight",heightMap.get("measurementValue"));
        }
        if(weightMap!=null){
            resultMap.put("currentWeight",weightMap.get("measurementValue"));
        }
        if(abdomenCircumferenceMap!=null){
            resultMap.put("abdomenCircumference",abdomenCircumferenceMap.get("measurementValue"));
        }
        if(bodyFatMap!=null){
            resultMap.put("bodyFat",bodyFatMap.get("measurementValue"));
        }
        return new JsonApi(ApiCodeEnum.OK, resultMap);
    }
    
    /** 
	 * @author: XiePeng
	 * @date: 2018年12月17日
	 * @param childrenMeasure
	 * @param resul
	 * @return
	 * @description: 儿童身高测量历史
	 */
	@RequiresAuthentication(value = { "web-module-height-obesity:children-measure:get-list" },level = Level.OPERATION)
	@GetMapping(value = { "/children/measures" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) ChildrenMeasure childrenMeasure, BindingResult resul,
            @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
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
            return new JsonApi(ApiCodeEnum.NOT_FOUND, Prompt.bundle("user.is.null"));
        }
		/* 设置id */
		childrenMeasure.setUserId(userId);
		Page<?> page = PageHelper.startPage(childrenMeasure.getPage(), childrenMeasure.getPageSize());
		List<Map<String, Object>> childrenMeasureList = childrenMeasureService.getList(childrenMeasure);
		if (childrenMeasureList != null && !childrenMeasureList.isEmpty()){
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(), childrenMeasureList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
