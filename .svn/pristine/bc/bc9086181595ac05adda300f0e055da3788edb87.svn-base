package org.wechat.module.height.obesity.controller.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.wechat.module.height.obesity.entity.ChildrenFamily;
import org.wechat.module.height.obesity.entity.ChildrenMaternity;
import org.wechat.module.height.obesity.entity.ChildrenMeasure;
import org.wechat.module.height.obesity.entity.Diagnosis;
import org.wechat.module.height.obesity.entity.FoodRecipesConfigHasBaseFood;
import org.wechat.module.height.obesity.entity.FoodRecipesHasBaseFoodRecipesConfig;
import org.wechat.module.height.obesity.entity.StandardEerAmdrRni;
import org.wechat.module.height.obesity.entity.User;
import org.wechat.module.height.obesity.entity.UserDrugsProject;
import org.wechat.module.height.obesity.entity.UserHeightObesityProject;
import org.wechat.module.height.obesity.entity.UserRecipesProject;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.service.ChildrenFamilyService;
import org.wechat.module.height.obesity.service.ChildrenMaternityService;
import org.wechat.module.height.obesity.service.ChildrenMeasureService;
import org.wechat.module.height.obesity.service.DiagnosisService;
import org.wechat.module.height.obesity.service.FoodRecipesConfigHasBaseFoodService;
import org.wechat.module.height.obesity.service.FoodRecipesHasBaseFoodRecipesConfigService;
import org.wechat.module.height.obesity.service.StandardEerAmdrRniService;
import org.wechat.module.height.obesity.service.UserDrugsProjectService;
import org.wechat.module.height.obesity.service.UserHeightObesityProjectService;
import org.wechat.module.height.obesity.service.UserRecipesProjectService;
import org.wechat.module.height.obesity.service.UserService;
import org.wechat.module.height.obesity.service.feign.IUserService;
import org.wechat.module.height.obesity.tools.HeightObesityCalculation;

@RestController
public class ProjectController {
    @Resource
    private UserService userService;
    @Resource
    private ChildrenMeasureService childrenMeasureService;
    @Resource
    private ChildrenFamilyService childrenFamilyService;
    @Resource
    private ChildrenMaternityService childrenMaternityService;
    @Resource
    private DiagnosisService diagnosisService;
    @Resource
    private IUserService iuserService;
    @Resource
    private UserHeightObesityProjectService userHeightObesityProjectService;
    @Resource
    private StandardEerAmdrRniService standardEerAmdrRniService;
    @Resource
    private UserRecipesProjectService userRecipesProjectService;
    @Resource
    private FoodRecipesHasBaseFoodRecipesConfigService foodRecipesHasBaseFoodRecipesConfigService;
    @Resource
    private FoodRecipesConfigHasBaseFoodService foodRecipesConfigHasBaseFoodService;
    @Resource
    private UserDrugsProjectService userDrugsProjectService;
    
    /**
     * @author: ZhangGuangZhi
     * @date: 2018年12月18日
     * @param userId            用户编号
     * @param childrenMeasureId 测量记录编号
     * @return
     * @description: 身高直方图
     */
    @RequiresAuthentication(authc = true, value = {})
    @GetMapping(value = { "/diagnosis/chart/height" })
    public JsonApi getHeightChart(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis, BindingResult resul,
            @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
        /* 获取并设置用户ID */
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token, token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            diagnosis.setUserId(Integer.parseInt(jsonApi.getData().toString()));
        }

        diagnosis = getUserDiagnosis(diagnosis);
        if (diagnosis == null || diagnosis.getChildrenFamily() == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND);
        }
        return new JsonApi(ApiCodeEnum.OK, diagnosisService.getHeightChart(diagnosis));
    }

    /**
     * @author: ZhangGuangZhi
     * @date: 2018年12月18日
     * @param userId            用户编号
     * @param childrenMeasureId 测量记录编号
     * @return
     * @description: 身高曲线图
     */
    @RequiresAuthentication(authc = true, value = {})
    @GetMapping(value = { "/diagnosis/chart/diagram/height" })
    public JsonApi getHeightDiagramChart(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis,
            BindingResult resul, @RequestHeader(required = false, value = BaseGlobal.TOKEN_FLAG) String token) {
        /* 获取并设置用户ID */
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token, token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            diagnosis.setUserId(Integer.parseInt(jsonApi.getData().toString()));
        }
        diagnosis = getUserDiagnosis(diagnosis);
        if (diagnosis == null || diagnosis.getHeight() == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND);
        }
        return new JsonApi(ApiCodeEnum.OK, diagnosisService.getHeightDiagramChart(diagnosis));
    }

    /**
     * @author: ZhangGuangZhi
     * @date: 2018年12月20日
     * @param userId            用户编号
     * @param childrenMeasureId 测量记录编号
     * @return
     * @description: 体重曲线图
     */
    @RequiresAuthentication(authc = true, value = {})
    @GetMapping(value = { "/diagnosis/chart/diagram/weight" })
    public JsonApi getWeightDiagramChart(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis,
            BindingResult resul, @RequestHeader(required = false, value = BaseGlobal.TOKEN_FLAG) String token) {

        /* 获取并设置用户ID */
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token, token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            diagnosis.setUserId(Integer.parseInt(jsonApi.getData().toString()));
        }

        diagnosis = getUserDiagnosis(diagnosis);
        if (diagnosis == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND);
        }

        Map<String, Object> map = diagnosisService.getWeightDiagramChart(diagnosis);

        return new JsonApi(ApiCodeEnum.OK, map);
    }

    /**
     * @author: ZhangGuangZhi
     * @date: 2018年12月20日
     * @param userId            用户编号
     * @param childrenMeasureId 测量记录编号
     * @return
     * @description: 体重bmi曲线图
     */
    @RequiresAuthentication(authc = true, value = {})
    @GetMapping(value = { "/diagnosis/chart/diagram/bmi" })
    public JsonApi getBMIDiagramChart(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis,
            BindingResult resul, @RequestHeader(required = false, value = BaseGlobal.TOKEN_FLAG) String token) {

        /* 获取并设置用户ID */
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token, token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            diagnosis.setUserId(Integer.parseInt(jsonApi.getData().toString()));
        }
        diagnosis = getUserDiagnosis(diagnosis);
        if (diagnosis == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND);
        }
        return new JsonApi(ApiCodeEnum.OK, diagnosisService.getBMIDiagramChart(diagnosis));
    }

    // /**
    // * @author: ZhangGuangZhi
    // * @date: 2018年12月18日
    // * @param userId 用户编号
    // * @param childrenMeasureId 测量记录编号
    // * @return
    // * @description:头围曲线图
    // */
    // @RequiresAuthentication(authc = true, value = {})
    // @GetMapping(value = { "/diagnosis/chart/diagram/head" })
    // public JsonApi getHeadCircumferenceDiagramChart(@Validated({
    // BaseEntity.SelectAll.class }) Diagnosis diagnosis,
    // BindingResult resul) {

    // diagnosis = getUserDiagnosis(diagnosis);
    // if (diagnosis == null) {
    // return new JsonApi(ApiCodeEnum.NOT_FOUND);
    // }
    // return new JsonApi(ApiCodeEnum.OK,
    // diagnosisService.getHeadCircumferenceDiagramChart(diagnosis));
    // }

    /**
     * @author: ZhangGuangZhi
     * @date: 2019年1月25日
     * @param userId
     * @param childrenMeasureId
     * @return
     * @description: 获取本次方案内容
     */
    @RequiresAuthentication(authc = true, value = {})
    @GetMapping(value = { "/project/content" })
    public JsonApi getProject(@Validated({ BaseEntity.SelectAll.class }) Diagnosis diagnosis, BindingResult resul,
            @RequestHeader(required = false, value = BaseGlobal.TOKEN_FLAG) String token) {

        /* 获取并设置用户ID */
        JsonApi jsonApi = iuserService.getSession(BaseGlobal.CACHE_USER, token, token);
        if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
            diagnosis.setUserId(Integer.parseInt(jsonApi.getData().toString()));
        }

        diagnosis = getUserDiagnosis(diagnosis);
        if (diagnosis == null) {
            return new JsonApi(ApiCodeEnum.NOT_FOUND);
        }

        Map<String, Object> map = new HashMap<>();
        ChildrenMeasure childrenMeasure = new ChildrenMeasure();
        childrenMeasure.setUserId(Integer.parseInt(jsonApi.getData().toString()));
        childrenMeasure.setStatus(3);
        Map<String, Object> newOneChildrenMeasureMap = childrenMeasureService.getNewOne(childrenMeasure);
        /* 查询本次是否已生成方案内容 */
        UserHeightObesityProject userHeightObesityProject = new UserHeightObesityProject();
        userHeightObesityProject.setChildrenMeasureId(newOneChildrenMeasureMap != null ? Integer.parseInt(newOneChildrenMeasureMap.get("id").toString()) : null);
        Map<String, Object> projectMap = userHeightObesityProjectService.getOne(userHeightObesityProject);
        if (projectMap != null) {
            /* 查询方案内容 */
            /* 饮食 */
            /* 营养干预 */
            List<Map<String, Object>> nutritionList = new ArrayList<>();
            Map<String, Object> nutritionMap = new HashMap<>();
            nutritionMap.put("methodContent", projectMap.get("nutritionMethodContent"));
            nutritionMap.put("details", projectMap.get("nutritionDetails"));
            nutritionMap.put("remark", projectMap.get("nutritionRemark"));
            nutritionList.add(nutritionMap);
            map.put("nutrition", nutritionList);

            /* 推荐摄入 */
            List<Map<String, Object>> eerList = new ArrayList<>();
            StandardEerAmdrRni standardEerAmdrRni = new StandardEerAmdrRni();
            standardEerAmdrRni.setId((Integer) projectMap.get("eerId"));
            eerList.add(standardEerAmdrRniService.getOne(standardEerAmdrRni));
            map.put("eer", eerList);

            /* 食谱 */
            UserRecipesProject userRecipesProject = new UserRecipesProject();
            userRecipesProject.setChildrenMeasureId(diagnosis.getChildrenMeasureId());
            Map<String, Object> dietResultMap = userRecipesProjectService.getOne(userRecipesProject);

            if (dietResultMap != null && dietResultMap.get("name") != null) {

                List<Map<String, Object>> dietMapList = new ArrayList<>();
                /* 根据配置名查询菜谱 */
                FoodRecipesHasBaseFoodRecipesConfig foodRecipesHasBaseFoodRecipesConfig = new FoodRecipesHasBaseFoodRecipesConfig();
                foodRecipesHasBaseFoodRecipesConfig.setName(dietResultMap.get("name").toString());

                /* 查询餐次 */
                List<Map<String, Object>> mealTimesList = foodRecipesHasBaseFoodRecipesConfigService
                        .getMealTimes(foodRecipesHasBaseFoodRecipesConfig);
                for (Map<String, Object> childMap : mealTimesList) {

                    Map<String, Object> dietMap = new HashMap<>();
                    foodRecipesHasBaseFoodRecipesConfig.setCodeType(childMap.get("code").toString());
                    List<Map<String, Object>> foodResultList = foodRecipesHasBaseFoodRecipesConfigService
                            .getList(foodRecipesHasBaseFoodRecipesConfig);
                    dietMap.put("codeName", childMap.get("value"));
                    dietMap.put("codeType", childMap.get("code"));

                    /* 查询菜对应的食物 */
                    List<Map<String, Object>> recipesMapList = new ArrayList<>();
                    for (Map<String, Object> foodMap : foodResultList) {
                        Map<String, Object> recipesMap = new HashMap<>();
                        FoodRecipesConfigHasBaseFood hasBaseFood = new FoodRecipesConfigHasBaseFood();
                        hasBaseFood.setFoodRecipesHasBaseFoodRecipesConfigId((Integer) foodMap.get("id"));

                        dietMap.put("foodRecipesConfigId", foodMap.get("foodRecipesConfigId"));
                        recipesMap.put("recipesName", foodMap.get("foodRecipesName"));
                        recipesMap.put("food", foodRecipesConfigHasBaseFoodService.getList(hasBaseFood));
                        recipesMapList.add(recipesMap);
                    }
                    dietMap.put("data", recipesMapList);
                    dietMapList.add(dietMap);
                }

                map.put("recipes", dietMapList);
            } else {
                map.put("recipes", new ArrayList<Map<String, Object>>());
            }
            /* 睡眠 */
            List<Map<String, Object>> sleepList = new ArrayList<>();
            Map<String, Object> sleepMap = new HashMap<>();
            sleepMap.put("content", projectMap.get("sleepContent"));
            sleepList.add(sleepMap);
            map.put("sleep", sleepList);

            /* 运动 */
            List<Map<String, Object>> motionList = new ArrayList<>();
            Map<String, Object> motionMap = new HashMap<>();
            motionMap.put("name", projectMap.get("motionName"));
            motionMap.put("remark", projectMap.get("motionRemark"));
            motionMap.put("picture", projectMap.get("motionPicture"));
            motionList.add(motionMap);
            map.put("motion", motionList);

            /* 情绪 */
            List<Map<String, Object>> emotionList = new ArrayList<>();
            Map<String, Object> emotionMap = new HashMap<>();
            emotionMap.put("content", projectMap.get("emotionContent"));
            emotionList.add(emotionMap);
            map.put("emotion", emotionList);

            /* 营养素指导（检测药品记录） */
            UserDrugsProject userDrugsProject = new UserDrugsProject();
            userDrugsProject.setChildrenMeasureId(diagnosis.getChildrenMeasureId());
            map.put("userDrugs", userDrugsProjectService.getList(userDrugsProject));
            /* 注意事项 */
            map.put("mattersNeedingAttention", projectMap.get("mattersNeedingAttention"));

        }

        return new JsonApi(ApiCodeEnum.OK, map);
    }

    /**
     * @author: ZhangGuangZhi
     * @date: 2018年12月19日
     * @param userId            用户编号
     * @param childrenMeasureId 测量记录编号
     * @return
     * @description:获取用户信息
     */
    private Diagnosis getUserDiagnosis(Diagnosis diagnosis) {
        /* 查询用户信息 */
        User user = new User();
        user.setId(diagnosis.getUserId());
        Map<String, Object> userMap = userService.getOne(user);
        if (userMap == null || !userMap.containsKey("id")) {
            /* 用户信息不存在 */
            return null;
        }
        diagnosis.setSex(Integer.parseInt(userMap.get("sex").toString()));
        diagnosis.setName(userMap.get("name").toString());

        /* 查询家庭信息 */
        ChildrenFamily childrenFamily = new ChildrenFamily();
        childrenFamily.setUserId(diagnosis.getUserId());
        Map<String, Object> childrenFamilyMap = childrenFamilyService.getChildrenFamilyByUserId(childrenFamily);
        if (childrenFamilyMap != null && childrenFamilyMap.get("fatherHeight") != null
                && childrenFamilyMap.get("motherHeight") != null) {
            diagnosis.setFatherHeight(new Double(childrenFamilyMap.get("fatherHeight").toString()));
            diagnosis.setMotherHeight(new Double(childrenFamilyMap.get("motherHeight").toString()));
            diagnosis.setChildrenFamily(childrenFamilyMap);
        }

        /* 查询出生信息 */
        ChildrenMaternity childrenMaternity = new ChildrenMaternity();
        childrenMaternity.setUserId(diagnosis.getUserId());
        Map<String, Object> childrenMaternityMap = childrenMaternityService
                .getChildrenMaternityByUserId(childrenMaternity);
        if (childrenMaternityMap != null) {
            diagnosis.setChildrenMaternity(childrenMaternityMap);
            if (childrenMaternityMap.get("birthGestational") != null) {
                diagnosis.setBirthGestational(new Double(childrenMaternityMap.get("birthGestational").toString()));
            }
            if (childrenMaternityMap.get("birthHeight") != null) {
                diagnosis.setBirthHeight(new Double(childrenMaternityMap.get("birthHeight").toString()));
            }
            if (childrenMaternityMap.get("birthWeight") != null) {
                diagnosis.setBirthWeight(new Double(childrenMaternityMap.get("birthWeight").toString()));
            }
        }

        ChildrenMeasure childrenMeasure = new ChildrenMeasure();
        Map<String, Object> childrenMeasureMap;

        if (diagnosis.getChildrenMeasureId() != null) {
            /* 查询记录 */
            childrenMeasure.setId(diagnosis.getChildrenMeasureId());
            childrenMeasureMap = childrenMeasureService.getOne(childrenMeasure);
        } else {
            /* 查询用户最新记录信息 */
            childrenMeasure.setUserId(diagnosis.getUserId());
            childrenMeasureMap = childrenMeasureService.getNewOne(childrenMeasure);
        }

        if (childrenMeasureMap == null || !childrenMeasureMap.containsKey("id")) {
            /* 用户测量信息不存在 */
            return null;
        }

        /* 查询相应的数据 测量信息 */
        diagnosis.setChildrenMeasureId((Integer) childrenMeasureMap.get("id"));
        diagnosis.setMonthAge(Integer.parseInt(childrenMeasureMap.get("monthAge").toString()));
        diagnosis.setHeight(new Double(childrenMeasureMap.get("currentHeight").toString()));
        diagnosis.setWeight(new Double(childrenMeasureMap.get("currentWeight").toString()));
        if (childrenMeasureMap.get("headCircumference") != null) {
            diagnosis.setHeadCircumference(new Double(childrenMeasureMap.get("headCircumference").toString()));
        }

        if (childrenMeasureMap.get("abdomenCircumference") != null) {
            diagnosis.setAbdomenCircumference(new Double(childrenMeasureMap.get("abdomenCircumference").toString()));
        }

        /* 根据月龄查询当前显示数据范围 */
        Map<String, Integer> monthAgeRange = HeightObesityCalculation.getMonthAgeRange(diagnosis.getMonthAge());
        diagnosis.setStartMonthAge(monthAgeRange.get("startMonthAge"));
        diagnosis.setEndMonthAge(monthAgeRange.get("endMonthAge"));
        if (childrenMeasureMap.get("idealHeight") != null) {
            diagnosis.setIdealHeight(new Double(childrenMeasureMap.get("idealHeight").toString()));
        }

        return diagnosis;
    }
}
