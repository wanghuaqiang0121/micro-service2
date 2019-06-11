package org.wechat.module.height.obesity.controller.index;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wechat.module.height.obesity.entity.User;
import org.wechat.module.height.obesity.entity.UserMeasurementRecord;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.global.GlobalEnum;
import org.wechat.module.height.obesity.message.Prompt;
import org.wechat.module.height.obesity.service.UserMeasurementService;
import org.wechat.module.height.obesity.service.UserService;
import org.wechat.module.height.obesity.service.feign.IUserService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: XiePeng
 * @date: 2018/12/17
 * @description: 用户测量
 */
@RestController
public class UserMeasurementController {
    @Resource
    private UserMeasurementService userMeasurementService;
    @Resource
    private IUserService iuserService;
    @Resource
    private UserService userService;
    /**
     * @author: XiePeng
     * @date: 2018年12月17日
     * @param userMeasurementRecord
     * @return
     * @description:  新增用户测量记录
     */
    @RequiresAuthentication(authc = true, value = { })
    @PostMapping(value = { "/user/measurement/record" })
    public JsonApi insert(
            @RequestBody @Validated({ BaseEntity.Insert.class }) UserMeasurementRecord userMeasurementRecord, BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
        /*获取并设置用户ID  */
        Integer userId = null;
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token,token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            userId = Integer.parseInt(jsonApi.getData().toString());
        }else if (jsonApi.getCode() != ApiCodeEnum.OK.getValue()) {
            return jsonApi;
        }
        userMeasurementRecord.setUserId(userId);
        userMeasurementRecord.setCreateDateTime(new Date());
        if(userMeasurementRecord.getIsHistory()==null){
            userMeasurementRecord.setIsHistory((byte)0);
        }
        if(userMeasurementService.insert(userMeasurementRecord)>0){
            return new JsonApi(ApiCodeEnum.OK);
        }
        return new JsonApi(ApiCodeEnum.FAIL);
    }
    /**
     * @author: XiePeng
     * @date: 2018年12月17日
     * @param userMeasurementRecord
     * @return
     * @description:  用户历史测量记录
     */
    @RequiresAuthentication(authc = true, value = { })
    @GetMapping(value = { "/user/history/measurement/records/{type}/{dayOrMonthType}"})
    public JsonApi getList(UserMeasurementRecord userMeasurementRecord, BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
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
            return new JsonApi(ApiCodeEnum.NOT_FOUND, Prompt.bundle("user.is.null"));
        }
        userMeasurementRecord.setUserId(userId);
        List<Map<String,Object>> yList=new ArrayList<>();
        Map<String,Object> resultMap = new HashMap<>();
        List<Integer> dayList=new ArrayList<>();
        Calendar c = Calendar.getInstance(Locale.CHINA);
        Date start= null; Date end= null;
        //获取年
        int year = c.get(Calendar.YEAR);
        //获取月份，0表示1月份
        int month = c.get(Calendar.MONTH) + 1;
        //日视图
        if(userMeasurementRecord.getDayOrMonthType()== GlobalEnum.UserMeasurementRecordType.DAY.getValue()){
            int days = c.getActualMaximum(Calendar.DATE);
            for(int i=1;i<=days;i++){
                dayList.add(i);
                String time=year+"-"+month+"-"+i+" 00:00:00";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    start = sdf.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.setTime(start);
                c.add(Calendar.DATE, 1);
                end=c.getTime();
                userMeasurementRecord.setStart(start);
                userMeasurementRecord.setEnd(end);
                Map<String,Object> measurementvalueMap=userMeasurementService.getHistoryList(userMeasurementRecord);
                if(measurementvalueMap!=null){
                    measurementvalueMap.get("measurementValue");
                }
                Map<String,Object> yMap = new HashMap<>();
                yMap.put("X",i);
                yMap.put("Y",measurementvalueMap==null ? null: measurementvalueMap.get("measurementValue"));
                yList.add(yMap);
            }
            resultMap.put("x",dayList);
            resultMap.put("y",yList);
            return new JsonApi(ApiCodeEnum.OK,resultMap);
        }
        //月视图
        else if(userMeasurementRecord.getDayOrMonthType()== GlobalEnum.UserMeasurementRecordType.MONTH.getValue()){
            for(int i=1;i<=12;i++){
                dayList.add(i);
                String time=year+"-"+i+"-"+1+" 00:00:00";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    start = sdf.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.setTime(start);
                c.add(Calendar.MONTH,1);
                end=c.getTime();
                userMeasurementRecord.setStart(start);
                userMeasurementRecord.setEnd(end);
                Map<String,Object> measurementvalueMap=userMeasurementService.getHistoryList(userMeasurementRecord);
                if(measurementvalueMap!=null){
                    measurementvalueMap.get("measurementValue");
                }
                Map<String,Object> yMap = new HashMap<>();
                yMap.put("X",i);
                yMap.put("Y",measurementvalueMap==null ? null: measurementvalueMap.get("measurementValue"));
                yList.add(yMap);
            }
            resultMap.put("x",dayList);
            resultMap.put("y",yList);
            return new JsonApi(ApiCodeEnum.OK,resultMap);
        }
        return new JsonApi(ApiCodeEnum.NOT_FOUND);
    }
}
