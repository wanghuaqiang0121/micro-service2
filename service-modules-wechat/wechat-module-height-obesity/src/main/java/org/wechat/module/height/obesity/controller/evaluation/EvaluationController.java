package org.wechat.module.height.obesity.controller.evaluation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.height.obesity.entity.Diagnosis;
import org.wechat.module.height.obesity.entity.StandardHeightPercentile;
import org.wechat.module.height.obesity.entity.User;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.message.Prompt;
import org.wechat.module.height.obesity.service.EvaluationService;
import org.wechat.module.height.obesity.service.UserService;
import org.wechat.module.height.obesity.service.feign.IEvaluationService;
import org.wechat.module.height.obesity.service.feign.IUserService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: XiePeng
 * @date: 2019/1/21
 * @description:身高体重测评
 */
@RestController
public class EvaluationController {
    @Resource
    private IUserService iuserService;
    @Resource
    private UserService userService;
    @Resource
    private EvaluationService evaluationService;
    @Resource
    private IEvaluationService ieevaluationService;
    
    /** 
     * @author: WangHuaQiang
     * @date: 2019年2月21日
     * @param diagnosis
     * @param resul
     * @return
     * @description: 高危儿预警(调用web端)
     */
    @RequiresAuthentication(authc = true, value = {})
    @GetMapping(value = { "/evaluation/highrisk" })
	public JsonApi getEvaluationHighriskWechat(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis,
			BindingResult resul,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		return ieevaluationService.getEvaluationHighriskWechat(diagnosis.getUserId());
    	//return iuserService.getSession(BaseGlobal.CACHE_USER, token, token);
    }
    
    /**
     * @author: XiePeng
     * @date: 2018年12月27日
     * @param standardHeightPercentile
     * @param result
     * @return
     * @description: 当前身高体重评测
     */
    @RequiresAuthentication(authc = true, value = {})
    @GetMapping(value = { "/height/evaluation" })
    public JsonApi getHeightEvaluation(@Validated({
            StandardHeightPercentile.GetHeightEvaluation.class }) StandardHeightPercentile standardHeightPercentile,
            @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token, BindingResult result) {
        double adultHeight = 0;
        Map<String, Object> resultMap = new HashMap<>();
        /* 获取并设置用户ID */
        Integer userId = null;
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token, token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            userId = Integer.parseInt(jsonApi.getData().toString());
        }
        /* 查询用户月齡 */
        User user = new User();
        user.setId(userId);
        Map<String, Object> userMap = userService.getOne(user);
        if (userMap == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND, Prompt.bundle("user.is.null"));
        }
        standardHeightPercentile.setMonthAge(Integer.parseInt(userMap.get("monthAge").toString()));
        standardHeightPercentile.setSex(Integer.parseInt(userMap.get("sex").toString()));
        /* 查询当前身高所在百分位 */
        Map<String, Object> standardHeightPercentileMap = evaluationService.getPercentile(standardHeightPercentile);
        String conclusion = null;
        String evaluation = null;
        if (standardHeightPercentileMap != null && standardHeightPercentileMap.containsKey("percentile")) {

            /* 查询对应成年身高 */
            standardHeightPercentile.setMonthAge(BaseGlobal.MAX_MONTH_AGE);
            standardHeightPercentile
                    .setPercentile(Integer.parseInt(standardHeightPercentileMap.get("percentile").toString()));
            Map<String, Object> standardHeightPercentileMapHeight = evaluationService.getOne(standardHeightPercentile);
            if (standardHeightPercentileMapHeight != null
                    && standardHeightPercentileMapHeight.containsKey("standardValue")) {
                adultHeight = Double.parseDouble(standardHeightPercentileMapHeight.get("standardValue").toString());
            }
            /* 得到评价 */

            if (Integer.parseInt(standardHeightPercentileMap.get("percentile").toString()) < 50) {
                evaluation = Prompt.bundle("height.weight.evaluation.one");
            } else {
                evaluation = Prompt.bundle("height.weight.evaluation.two");
            }
            /* 得到结论 */
            conclusion = String.format(Prompt.bundle("height.weight.evaluation.msg"),
                    standardHeightPercentile.getPercentile(), adultHeight);
        }
        resultMap.put("conclusion", conclusion);
        resultMap.put("evaluation", evaluation);
        return new JsonApi(ApiCodeEnum.OK, resultMap);
    }

    /**
     * @author: XiePeng
     * @date: 2018年12月27日
     * @param diagnosis
     * @param result
     * @return
     * @description: 得到遗传身高评测
     */
    @RequiresAuthentication(authc = true, value = {})
    @GetMapping(value = { "/genetic/height/evaluation" })
    public JsonApi getGeneticHeight(@Validated({ Diagnosis.GetGeneticHeight.class }) Diagnosis diagnosis,
            @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token, BindingResult result) {
        /* 获取并设置用户ID */
        Integer userId = null;
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token, token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            userId = Integer.parseInt(jsonApi.getData().toString());
        }
        /* 查询用户月齡 */
        User user = new User();
        user.setId(userId);
        StandardHeightPercentile standardHeightPercentile = new StandardHeightPercentile();
        standardHeightPercentile.setStandardValue(diagnosis.getStandardValue());
        Map<String, Object> userMap = userService.getOne(user);
        if (userMap == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND, Prompt.bundle("user.is.null"));
        }
        standardHeightPercentile.setMonthAge(Integer.parseInt(userMap.get("monthAge").toString()));
        standardHeightPercentile.setSex(Integer.parseInt(userMap.get("sex").toString()));
        /* 查询当前身高所在百分位 */
        Map<String, Object> standardHeightPercentileMap = evaluationService.getPercentile(standardHeightPercentile);
        /* 计算遗传身高 */
        double geneticHeight = 0;
        if (standardHeightPercentile.getSex() == 1) {
            /* 男 (父亲身高+母亲身高+12)/2 */
            geneticHeight = (diagnosis.getFatherHeight() + diagnosis.getMotherHeight() + 12) / 2;
        } else if (standardHeightPercentile.getSex() == 2) {
            /* 女 (父亲身高+母亲身高-12)/2 */
            geneticHeight = (diagnosis.getFatherHeight() + diagnosis.getMotherHeight() - 12) / 2;
        }
        /* 查询遗传身高所在百分位 */
        standardHeightPercentile.setStandardValue(BigDecimal.valueOf(geneticHeight));
        Map<String, Object> geneticMap = evaluationService.getPercentile(standardHeightPercentile);
        Map<String, Object> resultMap = new HashMap<>();
        String conclusion = null;
        String evaluation = null;
        String tips = null;
        if (geneticMap != null) {
            String str = Prompt.bundle("genetic.height.evaluation.msg");
            /* 得到身高结论 */
            conclusion = String.format(str, geneticHeight);
            if (standardHeightPercentileMap != null && standardHeightPercentileMap.containsKey("percentile")) {
                /* 查询对应成年身高 */
                double adultHeight = 0;
                standardHeightPercentile.setMonthAge(BaseGlobal.MAX_MONTH_AGE);
                standardHeightPercentile
                        .setPercentile(Integer.parseInt(standardHeightPercentileMap.get("percentile").toString()));
                Map<String, Object> standardHeightPercentileMapHeight = evaluationService
                        .getOne(standardHeightPercentile);
                if (standardHeightPercentileMapHeight != null
                        && standardHeightPercentileMapHeight.containsKey("standardValue")) {
                    adultHeight = Double.parseDouble(standardHeightPercentileMapHeight.get("standardValue").toString());
                    /* 当前身高百分位 */
                    Integer nowPercentile = Integer.parseInt(standardHeightPercentileMap.get("percentile").toString());
                    /* 遗传身高百分位 */
                    Integer geneticPercentile = Integer.parseInt(geneticMap.get("percentile").toString());
                    if (geneticPercentile >= 50 && geneticPercentile > nowPercentile) {
                        evaluation = Prompt.bundle("genetic.height.evaluation.one");
                        str = Prompt.bundle("genetic.height.evaluation.one.tips");
                        tips = String.format(str, adultHeight, geneticHeight);
                    } else if (geneticPercentile >= 50 && geneticPercentile == nowPercentile) {
                        evaluation = Prompt.bundle("genetic.height.evaluation.two");
                        str = Prompt.bundle("genetic.height.evaluation.two.tips");
                        tips = String.format(str, adultHeight, geneticHeight);
                    } else if (geneticPercentile >= 50 && geneticPercentile < nowPercentile) {
                        evaluation = Prompt.bundle("genetic.height.evaluation.three");
                        str = Prompt.bundle("genetic.height.evaluation.three.tips");
                        tips = String.format(str, adultHeight, geneticHeight);
                    } else if (geneticPercentile < 50 && geneticPercentile > nowPercentile) {
                        evaluation = Prompt.bundle("genetic.height.evaluation.four");
                        str = Prompt.bundle("genetic.height.evaluation.four.tips");
                        tips = String.format(str, adultHeight, geneticHeight);
                    } else if (geneticPercentile < 50 && geneticPercentile == nowPercentile) {
                        evaluation = Prompt.bundle("genetic.height.evaluation.five");
                        str = Prompt.bundle("genetic.height.evaluation.five.tips");
                        tips = String.format(str, adultHeight, geneticHeight);
                    } else if (geneticPercentile < 50 && geneticPercentile < nowPercentile) {
                        evaluation = Prompt.bundle("genetic.height.evaluation.six");
                        str = Prompt.bundle("genetic.height.evaluation.six.tips");
                        tips = String.format(str, adultHeight, geneticHeight);
                    }
                }
            }
        }
        resultMap.put("conclusion", conclusion);
        resultMap.put("evaluation", evaluation);
        resultMap.put("tips", tips);
        return new JsonApi(ApiCodeEnum.OK, resultMap);
    }

    /**
     * @author: XiePeng
     * @date: 2018年12月27日
     * @param diagnosis
     * @param result
     * @return
     * @description: 得到理想身高评测
     */
    @RequiresAuthentication(authc = true, value = {})
    @GetMapping(value = { "/expect/height/evaluation" })
    public JsonApi getExpectHeight(@Validated({ Diagnosis.GetExpectHeight.class }) Diagnosis diagnosis,
            @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token, BindingResult result) {
        /* 获取并设置用户ID */
        Integer userId = null;
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token, token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            userId = Integer.parseInt(jsonApi.getData().toString());
        }
        /* 查询用户月齡 */
        User user = new User();
        user.setId(userId);
        StandardHeightPercentile standardHeightPercentile = new StandardHeightPercentile();
        standardHeightPercentile.setStandardValue(diagnosis.getStandardValue());
        standardHeightPercentile.setSex(diagnosis.getSex());
        Map<String, Object> userMap = userService.getOne(user);
        if (userMap == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND, Prompt.bundle("user.is.null"));
        }
        standardHeightPercentile.setMonthAge(Integer.parseInt(userMap.get("monthAge").toString()));
        standardHeightPercentile.setSex(Integer.parseInt(userMap.get("sex").toString()));
        /* 查询当前身高所在百分位 */
        Map<String, Object> standardHeightPercentileMap = evaluationService.getPercentile(standardHeightPercentile);
        Map<String, Object> resultMap = new HashMap<>();
        String conclusion = null;
        String tips = null;
        if (standardHeightPercentileMap != null) {
            String str = Prompt.bundle("expect.height.evaluation.msg");
            if (standardHeightPercentileMap != null && standardHeightPercentileMap.containsKey("percentile")) {
                /* 查询对应成年身高 */
                double adultHeight = 0;
                standardHeightPercentile.setMonthAge(BaseGlobal.MAX_MONTH_AGE);
                standardHeightPercentile
                        .setPercentile(Integer.parseInt(standardHeightPercentileMap.get("percentile").toString()));
                Map<String, Object> standardHeightPercentileMapHeight = evaluationService
                        .getOne(standardHeightPercentile);
                if (standardHeightPercentileMapHeight != null
                        && standardHeightPercentileMapHeight.containsKey("standardValue")) {
                    adultHeight = Double.parseDouble(standardHeightPercentileMapHeight.get("standardValue").toString());
                    double cha = diagnosis.getIdealHeight() - adultHeight;
                    if (adultHeight < diagnosis.getIdealHeight()) {
                        if (cha < 0) {
                            cha = 0;
                            conclusion = String.format(str, cha, "易");
                        } else if (cha > 0 && cha < 2) {
                            conclusion = String.format(str, cha, "易");
                        } else if (cha > 2 && cha < 5) {
                            conclusion = String.format(str, cha, "中");
                        } else if (cha > 5 && cha < 10) {
                            conclusion = String.format(str, cha, "难");
                        } else if (cha > 10) {
                            conclusion = String.format(str, cha, "极难");
                        }
                        tips = Prompt.bundle("expect.height.evaluation.two");
                    } else {
                        conclusion = String.format(str, cha, "易");
                        tips = Prompt.bundle("expect.height.evaluation.one");
                    }
                }
            }
        }
        resultMap.put("conclusion", conclusion);
        resultMap.put("tips", tips);
        return new JsonApi(ApiCodeEnum.OK, resultMap);
    }
}
