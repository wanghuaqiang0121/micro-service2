package org.web.module.bone.age.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.service.redis.cache.RedisCacheManager;
import org.service.tools.calculate.CalculateUtil;
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
import org.web.module.bone.age.domain.BoneAgeOrder;
import org.web.module.bone.age.domain.BoneAgeOrderLog;
import org.web.module.bone.age.domain.BoneAgeStandardPercentile;
import org.web.module.bone.age.domain.Factor;
import org.web.module.bone.age.domain.ReadFilmDoctorPrice;
import org.web.module.bone.age.domain.ReadFilmRechargeRecord;
import org.web.module.bone.age.domain.RemoteBoneAgeOrder;
import org.web.module.bone.age.domain.User;
import org.web.module.bone.age.global.BaseGlobal;
import org.web.module.bone.age.global.GlobalEnum;
import org.web.module.bone.age.global.GlobalEnum.Menarche;
import org.web.module.bone.age.global.GlobalEnum.ScoreTableType;
import org.web.module.bone.age.global.GlobalEnum.Sex;
import org.web.module.bone.age.message.Prompt;
import org.web.module.bone.age.service.BoneAgeOrderLogService;
import org.web.module.bone.age.service.BoneAgeOrderService;
import org.web.module.bone.age.service.HeightForecastService;
import org.web.module.bone.age.service.ReadFilmDoctorPriceService;
import org.web.module.bone.age.service.ReadFilmRechargeRecordService;
import org.web.module.bone.age.service.RemoteBoneAgeOrderService;
import org.web.module.bone.age.service.UserService;
import org.web.module.bone.age.service.feign.IOrganizationUserRoleService;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author <font color="red"><b>Wang.Hua.Qiang</b></font>
 * @Date 2018年8月17日
 * @Version 
 * @Description 骨龄
 */
@RestController
public class BoneAgeOrderController {

	@Resource
	private BoneAgeOrderService boneAgeOrderService;

	@Resource
	private BoneAgeOrderLogService boneAgeOrderLogService;

	@Resource
	private HeightForecastService heightForecastService;
	@Resource
	private IOrganizationUserRoleService organizationUserRoleService;
	
	@Resource
	private RedisCacheManager cacheManager;
	
	@Resource
	private RemoteBoneAgeOrderService remoteBoneAgeOrderService;
	@Resource
	private ReadFilmDoctorPriceService readFilmDoctorPriceService;
	@Resource
	private ReadFilmRechargeRecordService readFilmRechargeRecordService;
	@Resource
	private UserService userService;
	
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param boneAgeOrder
	 * @param result
	 * @param token
	 * @param organizationTeamId
	 * @return
	 * @description: 新建骨龄工单信息
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:bone-age-order:insert" },level = Level.OPERATION)
	@PostMapping(value = { "/bone/age/order" })
	@Transactional
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) BoneAgeOrder boneAgeOrder,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {
		/* 性别为女 初潮类型必填 */
		if (boneAgeOrder.getSex().equals(Sex.SEX_WOMAN.getValue())) {
			/* 初潮类型为空 */
			if (boneAgeOrder.getMenarcheType() == null || "".equals(boneAgeOrder.getMenarcheType())) {
				return new JsonApi(ApiCodeEnum.FAIL,Prompt.bundle("bone.age.order.menarche.type.notblank.valid"));
			}else {/* 初潮类型为 初潮已有 判断初潮年龄不能为空 */
				if(boneAgeOrder.getMenarcheType().equals(String.valueOf(Menarche.MENARCHE_TYPE_TEO.getValue())) && boneAgeOrder.getMenarcheAge() == null) {
					return new JsonApi(ApiCodeEnum.FAIL,Prompt.bundle("bone.age.order.menarche.age.notblank.valid"));
				}
			}
		}
		/* 新redis获取session */
		//RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		//Integer organizationUserId = Integer.parseInt(session.get(Map.class).get("id").toString());
		Integer organizationUserId = null;
		JsonApi jsonApi = organizationUserRoleService.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			organizationUserId = Integer.parseInt(jsonApi.getData().toString());
		}
		boneAgeOrder.setOrganizationUserId(organizationUserId);
		boneAgeOrder.setOrganizationTeamId(organizationTeamId);
		boneAgeOrder.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
		boneAgeOrder.setStatus(GlobalEnum.BoneAgeOrder.BONE_AGE_ORDER_GENERATE.getValue());
		boneAgeOrder.setCreateDate(new Date());
		if (boneAgeOrderService.insert(boneAgeOrder) > 0) {
			Map<String, Object> map= new HashMap<>();
			map.put("id", boneAgeOrder.getId());
			Map<String, Object> boneAgeOrderMap=boneAgeOrderService.getOne(boneAgeOrder);
			BoneAgeOrderLog boneAgeOrderLog=JSON.parseObject(JSON.toJSONString(boneAgeOrderMap), BoneAgeOrderLog.class);
			boneAgeOrderLog.setBoneAgeOrderId(boneAgeOrder.getId());
			boneAgeOrderLog.setOrganizationTeamId(organizationTeamId);
			boneAgeOrderLog.setOrganizationUserId(organizationUserId);
			boneAgeOrderLog.setStatus(GlobalEnum.BoneAgeOrder.BONE_AGE_ORDER_GENERATE.getValue());
			boneAgeOrderLog.setCreateDate(new Date());
			if (boneAgeOrderLogService.insert(boneAgeOrderLog)>0) {
				return new JsonApi(ApiCodeEnum.OK,map);
			}
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	

	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param id
	 * @param boneAgeOrder
	 * @param result
	 * @return
	 * @description: 保存骨龄每节骨评分 图片（修改）
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:bone-age-order:update" }, level = Level.OPERATION)
	@PutMapping(value = { "/bone/age/order/{id}" })
	public JsonApi update(@PathVariable("id") Integer id,
			@RequestBody @Validated({ BaseEntity.Update.class }) BoneAgeOrder boneAgeOrder, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		boneAgeOrder.setId(id);
		boneAgeOrder.setStatus(GlobalEnum.BoneAgeOrder.BONE_AGE_ORDER_SAVE.getValue());
		if(boneAgeOrder.getxRay() != null && !"".equals(boneAgeOrder.getxRay())){
			//x光片是否被修改\n1.没有被修改\n2.被修改过（点击重新上传时状态为2，完成人工智能评测结果时状态为1，默认为1  ）
			boneAgeOrder.setxRayIsUpdate(2);
		}
		if (boneAgeOrderService.update(boneAgeOrder) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param boneAgeOrder
	 * @param result
	 * @param organizationTeamId
	 * @return
	 * @description: 骨龄工单列表
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:bone-age-order:get-list" }, level = Level.OPERATION)
	@GetMapping(value = { "/bone/age/orders" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) BoneAgeOrder boneAgeOrder,
			BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		boneAgeOrder.setOrganizationTeamId(organizationTeamId);
		Page<?> page = PageHelper.startPage(boneAgeOrder.getPage(), boneAgeOrder.getPageSize());
		List<Map<String, Object>> boneAgeOrderList = boneAgeOrderService.getBoneAgeOrderList(boneAgeOrder);
		if (boneAgeOrderList != null && !boneAgeOrderList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), boneAgeOrderList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月30日
	 * @param boneAgeOrder
	 * @param result
	 * @param organizationTeamId
	 * @return
	 * @description: 查询用户上次评分结果
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:bone-age-order:get-last-order" }, level = Level.OPERATION)
	@GetMapping(value = { "/bone/age/last/order" })
	public JsonApi getLastOrder(@Validated({ BoneAgeOrder.LastOrder.class }) BoneAgeOrder boneAgeOrder,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token ) {
		/* 查询用户已完成工单 */
		boneAgeOrder.setStatus(GlobalEnum.BoneAgeOrder.BONE_AGE_ORDER_FINISH.getValue());
		Map<String, Object> boneAgeOrderMap = boneAgeOrderService.getLastOrder(boneAgeOrder);
		if (boneAgeOrderMap != null) {
			return new JsonApi(ApiCodeEnum.OK, boneAgeOrderMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param id
	 * @param boneAgeOrder
	 * @param result
	 * @return
	 * @description: 修改状态为2:评测中
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:bone-age-order:update-status" }, level = Level.OPERATION)
	@PutMapping(value = { "/bone/age/order/status/{id}" })
	@Transactional
	public JsonApi updateStatus(@PathVariable("id") Integer id,
			 @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId,
			@Validated({ BaseEntity.Update.class }) BoneAgeOrder boneAgeOrder, BindingResult result) {
		boneAgeOrder.setId(id);
		boneAgeOrder.setStatus(GlobalEnum.BoneAgeOrder.BONE_AGE_ORDER_SAVE.getValue());
		if (boneAgeOrderService.update(boneAgeOrder) > 0) {
			Map<String, Object> boneAgeOrderMap = boneAgeOrderService.getOne(boneAgeOrder);
			BoneAgeOrderLog boneAgeOrderLog = JSON.parseObject(JSON.toJSONString(boneAgeOrderMap),BoneAgeOrderLog.class);
			boneAgeOrderLog.setBoneAgeOrderId(id);
			boneAgeOrderLog.setOrganizationTeamId(organizationTeamId);
			JsonApi jsonApi = organizationUserRoleService.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token,token);
			if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
				boneAgeOrderLog.setOrganizationUserId(Integer.parseInt(jsonApi.getData().toString()));
			}
			boneAgeOrderLog.setStatus(GlobalEnum.BoneAgeOrder.BONE_AGE_ORDER_SAVE.getValue());
			boneAgeOrderLog.setCreateDate(new Date());
			if (boneAgeOrderLogService.insert(boneAgeOrderLog) > 0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param id
	 * @param boneAgeOrder
	 * @param result
	 * @return
	 * @description: 查询骨龄工单详情
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:bone-age-order:detail" },level = Level.OPERATION)
	@GetMapping(value = { "/bone/age/order/{id}" })
	public JsonApi deatil(@PathVariable("id")Integer id,
			@Validated({ BaseEntity.SelectOne.class }) BoneAgeOrder boneAgeOrder,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		boneAgeOrder.setId(id);
		Map<String, Object>  boneAgeOrderMap=boneAgeOrderService.getOne(boneAgeOrder);
		if (boneAgeOrderMap!=null) {
			/*2远程阅片的报告，显示申请机构名称，下方医生签名显示阅片机构医生签名及本机构（申请机构）医生签名*/
			if (boneAgeOrderMap.get("remoteBoneAgeOrderId")!=null) {
				boneAgeOrderMap.put("organizationName", boneAgeOrderMap.get("launchOrganizationName"));
				boneAgeOrderMap.put("doctorName", boneAgeOrderMap.get("launchOrganizationUserName").toString()+","+boneAgeOrderMap.get("receiveOrganizationUserName").toString());
			}else {
					/*1如果是本机构自己阅片后出的报告，报告表投显示本机构的名称，下方医生签名显示当前医生的签名。*/
				boneAgeOrderMap.put("organizationName", boneAgeOrderMap.get("organizationName"));
				boneAgeOrderMap.put("doctorName", boneAgeOrderMap.get("organizationUserName"));
			}
			return new JsonApi(ApiCodeEnum.OK, boneAgeOrderMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param standardPercentile
	 * @param result
	 * @return
	 * @description: TW2百分位曲线图
	 */
	@RequiresAuthentication( value = { "web-module-bone-age:bone-age-order:get-standard-percentile" },level=Level.OPERATION)
	@GetMapping(value = { "/standard/percentile" })
	public JsonApi getStandardPercentile(
			@Validated(BoneAgeStandardPercentile.getStandardPercentile.class)BoneAgeStandardPercentile standardPercentile, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		Map<String, Object> standardPercentileMap = boneAgeOrderService.getBoneAgeStandardPercentile(standardPercentile);
		if (standardPercentileMap!=null) {
			return new JsonApi(ApiCodeEnum.OK, standardPercentileMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月14日
	 * @param standardPercentile
	 * @param result
	 * @return
	 * @description: TW3百分位曲线图
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:bone-age-order:get-tw3-standard-percentile" },level=Level.OPERATION)
	@GetMapping(value = { "tw3/standard/percentile" })
	public JsonApi getTW3StandardPercentile(
			@Validated(BoneAgeStandardPercentile.getStandardPercentile.class)BoneAgeStandardPercentile standardPercentile, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		Map<String, Object> standardPercentileMap = boneAgeOrderService.getTw3StandardPercentiles(standardPercentile);
		if (standardPercentileMap!=null) {
			return new JsonApi(ApiCodeEnum.OK, standardPercentileMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param boneAgeOrder
	 * @param result
	 * @param organizationTeamId
	 * @return
	 * @description: 返回用户骨龄分值曲线
	 */
	@RequiresAuthentication( value = { "web-module-bone-age:bone-age-order:get-user-score" },level=Level.OPERATION)
	@GetMapping(value = { "/user/score" })
	public JsonApi getUserScore(
			@Validated(BoneAgeOrder.getUserScore.class)BoneAgeOrder boneAgeOrder, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		// 根据工单id查询该用户该工单创建时间之前的数据
		Map<String, Object> boneAgeOrderOneResultMap = boneAgeOrderService.getOne(boneAgeOrder);
		if (null != boneAgeOrderOneResultMap) {
			boneAgeOrder.setUserId(Integer.parseInt(boneAgeOrderOneResultMap.get("userId").toString()));
			boneAgeOrder.setCreateDate((Date)boneAgeOrderOneResultMap.get("createDate"));
			/*判断是否是远程阅片*/
			if (boneAgeOrderOneResultMap.get("remoteBoneAgeOrderId")!=null) {
				boneAgeOrder.setOrganizationTeamId(Integer.parseInt(boneAgeOrderOneResultMap.get("launchOrganizationTeamId").toString()));
			}else {
				boneAgeOrder.setOrganizationTeamId(organizationTeamId);
			}
		}
		
		
		boneAgeOrder.setAlgorithm(Integer.parseInt(boneAgeOrderOneResultMap.get("algorithm").toString()));
		List<Map<String, Object>> userScoreList = boneAgeOrderService.getBoneAgeUserScore(boneAgeOrder);
		if (userScoreList != null && !userScoreList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, userScoreList);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param boneAgeOrder
	 * @param result
	 * @return
	 * @description: TW2法R骨测试结果(TW2-CHN13骨法测试结果)
	 */
	@RequiresAuthentication( value = { "web-module-bone-age:bone-age-order:get-tw2r-result" },level=Level.OPERATION)
	@GetMapping(value = { "/tw2r/test/result" })
	@Transactional
	public JsonApi getTW2RResult(@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId,
			@Validated(BoneAgeOrder.getTW2RBoneTestResult.class)BoneAgeOrder boneAgeOrder, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		//查询工单详情
		Map<String, Object>  boneAgeOrderMap = boneAgeOrderService.getOne(boneAgeOrder);
		if(null != boneAgeOrderMap && !boneAgeOrderMap.isEmpty()){
			Integer sex = Integer.valueOf(boneAgeOrderMap.get("sex").toString());
			Float age = (float) CalculateUtil.round(Double.valueOf(boneAgeOrderMap.get("age").toString()), 0);
			boneAgeOrder.setScoreTableType(ScoreTableType.R.getValue());
			/* 计算骨龄 */
			Map<String, Object> testResultMap = boneAgeOrderService.getTW2BoneTestResult(boneAgeOrder);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Double boneAge = testResultMap.get("boneAge") == null ? 0 : Double.valueOf(testResultMap.get("boneAge").toString());
			/* 判断发育情况 */
			boneAgeOrder.setUserId(Integer.valueOf(boneAgeOrderMap.get("userId").toString()));
			boneAgeOrder.setOrganizationTeamId(organizationTeamId);
			// 查询用户骨龄分值曲线
			List<Map<String, Object>> userScoreList = boneAgeOrderService.getBoneAgeUserScore(boneAgeOrder);
			if (userScoreList == null || userScoreList.size() <= 0) {
				boneAgeOrderMap.put("auxe", null);
			}else {
				/* 取最后一个点判断发育情况 */ 
				Map<String, Object> endScore = userScoreList.get(userScoreList.size() - 1);
				double score = (double) endScore.get("score");
				BigDecimal ageBigDecimal = (BigDecimal) endScore.get("age");
				/* 这里的age 直接取计算出来的age 不能四舍五入 （查询百分位标准值需要精确值） */
				Float ageAuxe = ageBigDecimal.floatValue();
				if (ageAuxe < 1 || ageAuxe >= 18.3 || boneAge < 1 || boneAge >= 18.3) {
					resultMap.put("auxe", null);
				} else if ((ageAuxe >= 1 && ageAuxe <= 18.3) && (boneAge >= 1 && boneAge <= 18.3)) {
					// 查询boneAge对应25百分位和75百分位的值
					BoneAgeStandardPercentile standardPercentile = new BoneAgeStandardPercentile();
					/* 此接口为TW2法R骨测试 所以设置type 为 R */
					standardPercentile.setType(ScoreTableType.R.getValue());
					standardPercentile.setSex(Integer.parseInt(boneAgeOrderMap.get("sex").toString()));
					standardPercentile.setBoneAge(ageAuxe);
					Map<String, Object> standardPercentileMap = boneAgeOrderService.getTwentyFiveAndSeventyFiveStandardPercentile(standardPercentile);
					if (null == standardPercentileMap || standardPercentileMap.isEmpty()) {
						resultMap.put("auxe", null);
						return new JsonApi(ApiCodeEnum.OK, resultMap);
					} else if (!standardPercentileMap.isEmpty() && null != standardPercentileMap) {
						@SuppressWarnings("unchecked")
						Map<String, Object> twentyFivePercentileMap = (Map<String, Object>) standardPercentileMap
								.get("twentyFivePercentile");
						@SuppressWarnings("unchecked")
						Map<String, Object> seventyFivePercentileMap = (Map<String, Object>) standardPercentileMap
								.get("seventyFivePercentile");
						// 25百分位分值
						double twentyFivePercentile = (double) twentyFivePercentileMap.get("coordinateValue");
						// 75百分位分值
						double seventyFivePercentile = (double) seventyFivePercentileMap.get("coordinateValue");
						/* 判断用户分值和标准分值
						 * 低于于25百分位：发育迟缓
						 * 25百分位 =<用户分值<=75百分位：发育正常
						 * 大于75百分位：发育提前
						 * */ 
						if (score < twentyFivePercentile) {
							resultMap.put("auxe", "骨龄位于25百分位以下，骨龄发育迟缓");
						} else if (score >= twentyFivePercentile && score <= seventyFivePercentile) {
							resultMap.put("auxe", "骨龄位于25百分位到75百分位之间，骨龄发育正常");
						} else if (score >= seventyFivePercentile) {
							resultMap.put("auxe", "骨龄位于75百分位之上，骨龄发育提前");
						}
					}
				}
			}
			
			if ((sex == Sex.SEX_MAN.getValue() && (age >= 6 && age <= 17))
					|| (sex == Sex.SEX_WOMAN.getValue() && (age >= 5 && age <= 15))) {
				// 因素法预测身高
				Integer userId =Integer.valueOf(boneAgeOrderMap.get("userId").toString());
				Integer menarcheType = 
						null != boneAgeOrderMap.get("menarcheType") && !"".equals(boneAgeOrderMap.get("menarcheType").toString()) 
						? Integer.valueOf(boneAgeOrderMap.get("menarcheType").toString()) : null;
				Double height = Double.valueOf(boneAgeOrderMap.get("height").toString());
				Double menarcheAge = boneAgeOrderMap.get("menarcheAge") == null ? 0 : Double.valueOf(boneAgeOrderMap.get("menarcheAge").toString());
				Factor factor = new Factor();
				factor.setSex(sex);
				factor.setAge(age);
				factor.setUserId(userId);
				factor.setHeight(height);
				factor.setMenarcheAge(menarcheAge);
				factor.setBoneAge(boneAge);
				factor.setMenarcheType(menarcheType);
				/* 预测身高 */
				Map<String, Object> heightMap = heightForecastService.getHeightForecast(factor);
				resultMap.put("height", heightMap.get("height"));
				resultMap.put("heightMax", heightMap.get("heightMax"));
				resultMap.put("heightMin", heightMap.get("heightMin"));
				resultMap.put("message", heightMap.get("message"));
				resultMap.put("boneAge", boneAge);
				resultMap.put("boneScore", Integer.parseInt(testResultMap.get("boneScore").toString()));
			} else {
				resultMap.put("boneAge", boneAge);
				resultMap.put("boneScore", Integer.parseInt(testResultMap.get("boneScore").toString()));
				resultMap.put("height", 0);
				resultMap.put("heightMax", 0);
				resultMap.put("heightMin", 0);
				resultMap.put("message", "无法计算预测身高，年龄不在可预测范围");
			}
			/* 无法计算预测身高 修改 是否在报告中显示预测身高*/
			if(resultMap.get("message") != null) {
				boneAgeOrder.setIsDisplayForecastHeight(false);
				if(boneAgeOrderService.update(boneAgeOrder) <= 0) {
					throw new RuntimeException();
				}
			}
			return new JsonApi(ApiCodeEnum.OK, resultMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param boneAgeOrder
	 * @param result
	 * @return
	 * @description: TW3法R骨测试结果(TW3-CHN13骨法测试结果)
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:bone-age-order:get-tw3r-result" },level=Level.OPERATION)
	@GetMapping(value = { "/tw3r/test/result" })
	@Transactional
	public JsonApi getTW3Result(@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId,
			@Validated(BoneAgeOrder.getTW2RBoneTestResult.class)BoneAgeOrder boneAgeOrder, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		//查询工单详情
		Map<String, Object>  boneAgeOrderMap = boneAgeOrderService.getOne(boneAgeOrder);
		if(null != boneAgeOrderMap && !boneAgeOrderMap.isEmpty()){
			/*TW3法R计算总分使用TW2R的分值*/
			boneAgeOrder.setScoreTableType(ScoreTableType.R.getValue());
			/* 计算骨龄 */
			Map<String, Object> testResultMap = boneAgeOrderService.getTW3rBoneTestResult(boneAgeOrder);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Double boneAge = testResultMap.get("boneAge") == null ? 0 : Double.valueOf(testResultMap.get("boneAge").toString());
			resultMap.put("boneAge", boneAge);
			resultMap.put("boneScore", testResultMap.get("boneScore"));
			/* 判断发育情况 */
			boneAgeOrder.setUserId(Integer.valueOf(boneAgeOrderMap.get("userId").toString()));
			boneAgeOrder.setOrganizationTeamId(organizationTeamId);
			boneAgeOrder.setAlgorithm(Integer.parseInt(boneAgeOrderMap.get("algorithm").toString()));
			// 查询用户骨龄分值曲线
			List<Map<String, Object>> userScoreList = boneAgeOrderService.getBoneAgeUserScore(boneAgeOrder);
			if (userScoreList == null || userScoreList.size() <= 0) {
				boneAgeOrderMap.put("auxe", null);
			}else {
				/* 取最后一个点判断发育情况 */ 
				Map<String, Object> endScore = userScoreList.get(userScoreList.size() - 1);
				double score = (double) endScore.get("score");
				BigDecimal ageBigDecimal = (BigDecimal) endScore.get("age");
				/* 这里的age 直接取计算出来的age 不能四舍五入 （查询百分位标准值需要精确值） */
				Float ageAuxe = ageBigDecimal.floatValue();
				if (ageAuxe < 1 || ageAuxe >= 18.3 || boneAge < 1 || boneAge >= 18.3) {
					resultMap.put("auxe", null);
					return new JsonApi(ApiCodeEnum.OK, resultMap);
				} else if ((ageAuxe >= 1 && ageAuxe <= 18.3) && (boneAge >= 1 && boneAge <= 18.3)) {
					// 查询boneAge对应标准值
					BoneAgeStandardPercentile standardPercentile = new BoneAgeStandardPercentile();
					/* 此接口为TW3法R骨测试 所以设置type 为 R */
					standardPercentile.setType(ScoreTableType.TW3R.getValue());
					standardPercentile.setSex(Integer.parseInt(boneAgeOrderMap.get("sex").toString()));
					standardPercentile.setBoneAge(ageAuxe);
					Map<String, Object> standardPercentileMap = boneAgeOrderService.getTw3StandardPercentile(standardPercentile);
					if (null == standardPercentileMap || standardPercentileMap.isEmpty()) {
						resultMap.put("auxe", null);
						return new JsonApi(ApiCodeEnum.OK, resultMap);
					} else if (!standardPercentileMap.isEmpty() && null != standardPercentileMap) {
						@SuppressWarnings("unchecked")
						Map<String, Object> standardMap = (Map<String, Object>) standardPercentileMap.get("standard");
						// TW3标准分值
						double coordinateValue = (double) standardMap.get("coordinateValue");
						/* 判断用户分值和标准分值
						 * 骨龄位于标准曲线以下，骨龄发育迟缓
						 * 骨龄位于标准曲线，骨龄发育正常
						 * 骨龄位于标准曲线之上，骨龄发育提前
						 * */ 
						if (score < coordinateValue) {
							resultMap.put("auxe", "骨龄位于标准曲线以下，骨龄发育迟缓");
						} else if (score == coordinateValue) {
							resultMap.put("auxe", "骨龄位于标准曲线，骨龄发育正常");
						} else if (score > coordinateValue) {
							resultMap.put("auxe", "骨龄位于标准曲线之上，骨龄发育提前");
						}
					}
				}
			}
			return new JsonApi(ApiCodeEnum.OK, resultMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param boneAgeOrder
	 * @param result
	 * @return
	 * @description: TW3法C骨测试结果(TW3-CHN7骨法测试结果)
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:bone-age-order:get-tw3c-result" },level=Level.OPERATION)
	@GetMapping(value = { "/tw3c/test/result" })
	@Transactional
	public JsonApi getTW3Cesult(@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId,
			@Validated(BoneAgeOrder.getTW2RBoneTestResult.class)BoneAgeOrder boneAgeOrder, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		//查询工单详情
		Map<String, Object>  boneAgeOrderMap = boneAgeOrderService.getOne(boneAgeOrder);
		if(null != boneAgeOrderMap && !boneAgeOrderMap.isEmpty()){
			/*TW3法C计算总分使用TW3C的分值*/
			boneAgeOrder.setScoreTableType(ScoreTableType.C.getValue());
			/* 计算骨龄 */
			Map<String, Object> testResultMap = boneAgeOrderService.getTW3CBoneTestResult(boneAgeOrder);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Double boneAge = testResultMap.get("boneAge") == null ? 0 : Double.valueOf(testResultMap.get("boneAge").toString());
			resultMap.put("boneAge", boneAge);
			resultMap.put("boneScore", testResultMap.get("boneScore"));
			/* 判断发育情况 */
			boneAgeOrder.setUserId(Integer.valueOf(boneAgeOrderMap.get("userId").toString()));
			boneAgeOrder.setOrganizationTeamId(organizationTeamId);
			boneAgeOrder.setAlgorithm(Integer.parseInt(boneAgeOrderMap.get("algorithm").toString()));
			// 查询用户骨龄分值曲线
			List<Map<String, Object>> userScoreList = boneAgeOrderService.getBoneAgeUserScore(boneAgeOrder);
			if (userScoreList == null || userScoreList.size() <= 0) {
				boneAgeOrderMap.put("auxe", null);
			}else {
				/* 取最后一个点判断发育情况 */ 
				Map<String, Object> endScore = userScoreList.get(userScoreList.size() - 1);
				double score = (double) endScore.get("score");
				BigDecimal ageBigDecimal = (BigDecimal) endScore.get("age");
				/* 这里的age 直接取计算出来的age 不能四舍五入 （查询百分位标准值需要精确值） */
				Float ageAuxe = ageBigDecimal.floatValue();
				if (ageAuxe < 1 || ageAuxe >= 18.3 || boneAge < 1 || boneAge >= 18.3) {
					resultMap.put("auxe", null);
					return new JsonApi(ApiCodeEnum.OK, resultMap);
				} else if ((ageAuxe >= 1 && ageAuxe <= 18.3) && (boneAge >= 1 && boneAge <= 18.3)) {
					// 查询boneAge对应标准值
					BoneAgeStandardPercentile standardPercentile = new BoneAgeStandardPercentile();
					/* 此接口为TW3法C骨测试 所以设置type 为 C */
					standardPercentile.setType(ScoreTableType.TW3C.getValue());
					standardPercentile.setSex(Integer.parseInt(boneAgeOrderMap.get("sex").toString()));
					standardPercentile.setBoneAge(ageAuxe);
					Map<String, Object> standardPercentileMap = boneAgeOrderService.getTw3StandardPercentile(standardPercentile);
					if (null == standardPercentileMap || standardPercentileMap.isEmpty()) {
						resultMap.put("auxe", null);
						return new JsonApi(ApiCodeEnum.OK, resultMap);
					} else if (!standardPercentileMap.isEmpty() && null != standardPercentileMap) {
						@SuppressWarnings("unchecked")
						Map<String, Object> standardMap = (Map<String, Object>) standardPercentileMap.get("standard");
						// TW3标准分值
						double coordinateValue = (double) standardMap.get("coordinateValue");
						/* 判断用户分值和标准分值
						 * 骨龄位于标准曲线以下，骨龄发育迟缓
						 * 骨龄位于标准曲线，骨龄发育正常
						 * 骨龄位于标准曲线之上，骨龄发育提前
						 * */ 
						if (score < coordinateValue) {
							resultMap.put("auxe", "骨龄位于标准曲线以下，骨龄发育迟缓");
						} else if (score == coordinateValue) {
							resultMap.put("auxe", "骨龄位于标准曲线，骨龄发育正常");
						} else if (score > coordinateValue) {
							resultMap.put("auxe", "骨龄位于标准曲线之上，骨龄发育提前");
						}
					}
				}
			}
			return new JsonApi(ApiCodeEnum.OK, resultMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param boneAgeOrder
	 * @param result
	 * @return
	 * @description: TW2T骨测试结果(TW2-CHN20骨法测试结果)
	 */
	@RequiresAuthentication( value = { "web-module-bone-age:bone-age-order:get-tw2t-result" },level=Level.OPERATION)
	@GetMapping(value = { "/tw2t/test/result" })
	public JsonApi getTW2TResult(@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId,
			@Validated(BoneAgeOrder.getTW2TBoneTestResult.class)BoneAgeOrder boneAgeOrder, BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		//查询工单详情
		Map<String, Object>  boneAgeOrderMap = boneAgeOrderService.getOne(boneAgeOrder);
		if(null != boneAgeOrderMap && !boneAgeOrderMap.isEmpty()){
			boneAgeOrder.setIsTW2T(true);
			boneAgeOrder.setScoreTableType(ScoreTableType.T.getValue());
			Map<String, Object> testResultMap = boneAgeOrderService.getTW2BoneTestResult(boneAgeOrder);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Double boneAge = testResultMap.get("boneAge") == null ? 0 : Double.valueOf(testResultMap.get("boneAge").toString());
			resultMap.put("boneAge", boneAge);
			resultMap.put("boneScore", Integer.parseInt(testResultMap.get("boneScore").toString()));
			/* 判断发育情况 */
			boneAgeOrder.setUserId(Integer.valueOf(boneAgeOrderMap.get("userId").toString()));
			boneAgeOrder.setOrganizationTeamId(organizationTeamId);
			// 查询用户骨龄分值曲线
			List<Map<String, Object>> userScoreList = boneAgeOrderService.getBoneAgeUserScore(boneAgeOrder);
			if (userScoreList == null || userScoreList.size() <= 0) {
				boneAgeOrderMap.put("auxe", null);
			}else {
				// 取最后一个点判断发育情况
				Map<String, Object> endScore = userScoreList.get(userScoreList.size() - 1);
				double score = (double) endScore.get("score");
				/* 这里的age 直接取计算出来的age 不能四舍五入 （查询百分位标准值需要精确值） */
				BigDecimal ageBigDecimal = (BigDecimal) endScore.get("age");
				Float age = ageBigDecimal.floatValue();
				if (age < 1 || age >= 18.3 || boneAge < 1 || boneAge >= 18.3) {
					resultMap.put("auxe", null);
				} else if ((age >= 1 && age <= 18.3) && (boneAge >= 1 && boneAge <= 18.3)) {
					// 查询boneAge对应25百分位和75百分位的值
					BoneAgeStandardPercentile standardPercentile = new BoneAgeStandardPercentile();
					/* 此接口为TW2法T骨测试 所以设置type 为 T */
					standardPercentile.setType(ScoreTableType.T.getValue());
					standardPercentile.setSex(Integer.parseInt(boneAgeOrderMap.get("sex").toString()));
					standardPercentile.setBoneAge(age);
					Map<String, Object> standardPercentileMap = boneAgeOrderService.getTwentyFiveAndSeventyFiveStandardPercentile(standardPercentile);
					if (null == standardPercentileMap || standardPercentileMap.isEmpty()) {
						boneAgeOrderMap.put("auxe", null);
					} else if (!standardPercentileMap.isEmpty() && null != standardPercentileMap) {
						@SuppressWarnings("unchecked")
						Map<String, Object> twentyFivePercentileMap = (Map<String, Object>) standardPercentileMap
								.get("twentyFivePercentile");
						@SuppressWarnings("unchecked")
						Map<String, Object> seventyFivePercentileMap = (Map<String, Object>) standardPercentileMap
								.get("seventyFivePercentile");
						// 25百分位分值
						double twentyFivePercentile = (double) twentyFivePercentileMap.get("coordinateValue");
						// 75百分位分值
						double seventyFivePercentile = (double) seventyFivePercentileMap.get("coordinateValue");
						/* 判断用户分值和标准分值
						 * 低于于25百分位：发育迟缓
						 * 25百分位 =<用户分值<=75百分位：发育正常
						 * 大于75百分位：发育提前
						 * */
						if (score < twentyFivePercentile) {
							resultMap.put("auxe", "骨龄位于25百分位以下，骨龄发育迟缓");
						} else if (score >= twentyFivePercentile && score <= seventyFivePercentile) {
							resultMap.put("auxe", "骨龄位于25百分位到75百分位之间，骨龄发育正常");
						} else if (score >= seventyFivePercentile) {
							resultMap.put("auxe", "骨龄位于75百分位之上，骨龄发育提前");
						}
					}
				}
			}
			return new JsonApi(ApiCodeEnum.OK,resultMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年11月29日
	 * @param id
	 * @param boneAgeOrder
	 * @param result
	 * @return
	 * @description: 保存骨龄检测结果
	 */
	@RequiresAuthentication( value = { "web-module-bone-age:bone-age-order:save-result" },level=Level.OPERATION)
	@PutMapping(value = { "/bone/age/order/{id}/check" })
	@Transactional
	public JsonApi saveResult(@PathVariable("id")Integer id,
			@RequestBody @Validated(BoneAgeOrder.updateCheckWorkOrder.class)BoneAgeOrder boneAgeOrder, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId){
		boneAgeOrder.setId(id);
		boneAgeOrder.setGeneratingReportDate(new Date());
		boneAgeOrder.setStatus(GlobalEnum.BoneAgeOrder.BONE_AGE_ORDER_FINISH.getValue());
		//默认医生预测身高=标准预测值
		if(boneAgeOrder.getDoctorForecastHeight() == null){
			boneAgeOrder.setDoctorForecastHeight(boneAgeOrder.getForecastHeight());
		}
		//默认医生预测最小身高=标准预测最小值
		if(boneAgeOrder.getDoctorMinForecastHeight() == null){
			boneAgeOrder.setDoctorMinForecastHeight(boneAgeOrder.getMinForecastHeight());
		}
		//默认医生预测最大身高=标准预测最大值
		if(boneAgeOrder.getDoctorMaxForecastHeight() == null){
			boneAgeOrder.setDoctorMaxForecastHeight(boneAgeOrder.getMaxForecastHeight());
		}
		/*修改骨龄工单*/
		if (boneAgeOrderService.updateBoneAgeOrder(boneAgeOrder) > 0) {
			Map<String, Object> boneAgeOrderMap = boneAgeOrderService.getOne(boneAgeOrder);
			BoneAgeOrderLog boneAgeOrderLog=JSON.parseObject(JSON.toJSONString(boneAgeOrderMap), BoneAgeOrderLog.class);
			boneAgeOrderLog.setBoneAgeOrderId(id);
			boneAgeOrderLog.setOrganizationTeamId(organizationTeamId);
			Integer organizationUserId = null;
			JsonApi jsonApi = organizationUserRoleService.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token,token);
			if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
				organizationUserId = Integer.parseInt(jsonApi.getData().toString());
			}
		/*	新增骨龄工单日志*/
			boneAgeOrderLog.setOrganizationUserId(organizationUserId);
			boneAgeOrderLog.setStatus(GlobalEnum.BoneAgeOrder.BONE_AGE_ORDER_FINISH.getValue());
			if (boneAgeOrderLogService.insert(boneAgeOrderLog)>0) {
				/*修改远程阅片骨龄工单*/
				if (boneAgeOrderMap.get("remoteBoneAgeOrderId")!=null) {
					RemoteBoneAgeOrder remoteBoneAgeOrder=new RemoteBoneAgeOrder();
					remoteBoneAgeOrder.setId(Integer.parseInt(boneAgeOrderMap.get("remoteBoneAgeOrderId").toString()));
					remoteBoneAgeOrder.setStatus(GlobalEnum.RemoteBoneAgeOrder.COMPLETED.getValue());
					remoteBoneAgeOrder.setEndTime(new Date());
					if (remoteBoneAgeOrderService.update(remoteBoneAgeOrder)>0) {
						/*修改远程阅片充值记录锁定金额*/
						/*接收医生价格*/
						Integer receiveOrganizationUserId=(Integer) boneAgeOrderMap.get("receiveOrganizationUserId");
						Integer receiveOrganizationId=(Integer) boneAgeOrderMap.get("receiveOrganizationId");
						Integer launchOrganizationId=(Integer) boneAgeOrderMap.get("launchOrganizationId");
						/*医生价格*/
						ReadFilmDoctorPrice readFilmDoctorPrice=new ReadFilmDoctorPrice();
						readFilmDoctorPrice.setDoctorId(receiveOrganizationUserId);
						readFilmDoctorPrice.setOrganizationId(receiveOrganizationId);
						Map<String, Object> readFilmDoctorPriceMap=readFilmDoctorPriceService.getOne(readFilmDoctorPrice);
						if (readFilmDoctorPriceMap==null) {
							return new JsonApi(ApiCodeEnum.NOT_FOUND);
						}
						/*获取医生价格*/
						Double doctorPrice=(Double) readFilmDoctorPriceMap.get("price");
						/*接收的机构id*/
						
						 ReadFilmRechargeRecord readFilmRechargeRecord=new ReadFilmRechargeRecord();
							/*设置接受机构id*/
							readFilmRechargeRecord.setOrganizationId(launchOrganizationId);
						 List<Map<String, Object>> readFilmRechargeRecordList = readFilmRechargeRecordService.getList(readFilmRechargeRecord);
							if (readFilmRechargeRecordList==null || readFilmRechargeRecordList.isEmpty()) {
								return new JsonApi(ApiCodeEnum.NOT_FOUND);
							} 
							Double lockMoney=(Double) readFilmRechargeRecordList.get(0).get("lockMoney");
					    	Integer readFilmRechargeRecordId=(Integer) readFilmRechargeRecordList.get(0).get("id");
							readFilmRechargeRecord.setId(readFilmRechargeRecordId);
							readFilmRechargeRecord.setLockMoney(lockMoney-doctorPrice);
							if (readFilmRechargeRecordService.update(readFilmRechargeRecord)<0) {
								throw new RuntimeException();
							}
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
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月13日
	 * @param userId
	 * @param boneAgeOrder
	 * @param result
	 * @return
	 * @description: 儿保同步生长发育数据
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:bone-age-order:get-synchronization-data" },level=Level.OPERATION)
	@GetMapping(value = { "/bone/age/order/synchronization/{userId}" })
	public JsonApi getSynchronizationDate(@PathVariable("userId") Integer userId, BoneAgeOrder boneAgeOrder , BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		boneAgeOrder.setUserId(userId);
		/*封装测量数据*/
		Map<String, Object> resultMap=new HashMap<>();
		/*封装骨龄数据*/
		Map<String, Object> boneMap=new HashMap<>();
		/* 同步测量基本数据 */
		Map<String, Object> synchronizationDataMap =boneAgeOrderService.getSynchronizationMeasure(boneAgeOrder);
	    /*同步骨龄基本数据*/
		Map<String, Object> synchronizationBoneMap=boneAgeOrderService.getSynchronizationBone(boneAgeOrder);
		
	/*	测量数据存在，骨龄数据不存在，返回测量数据*/
		if (synchronizationDataMap!=null && synchronizationBoneMap==null) {
			resultMap.put("currentHeight", synchronizationDataMap.get("currentHeight")==null ? null : synchronizationDataMap.get("currentHeight"));
			resultMap.put("currentWeight", synchronizationDataMap.get("currentWeight")==null ? null : synchronizationDataMap.get("currentWeight"));
			resultMap.put("sex", synchronizationDataMap.get("sex")==null ? null : synchronizationDataMap.get("sex"));
			resultMap.put("createDateTime", synchronizationDataMap.get("createDateTime")==null ? null : synchronizationDataMap.get("createDateTime"));
			resultMap.put("menarcheType", synchronizationDataMap.get("menarcheType")==null ? null : synchronizationDataMap.get("menarcheType"));
			resultMap.put("value", synchronizationDataMap.get("value")==null ? null : synchronizationDataMap.get("value"));
			resultMap.put("menarcheAge", synchronizationDataMap.get("menarcheAge")==null ? null : synchronizationDataMap.get("menarcheAge"));
			return new JsonApi(ApiCodeEnum.OK, resultMap);
			/*	测量骨龄存在，测量数据不存在，返回骨龄数据*/
		}else  if(synchronizationDataMap==null && synchronizationBoneMap!=null){
			
			boneMap.put("currentHeight", synchronizationBoneMap.get("currentHeight")==null ? null : synchronizationBoneMap.get("currentHeight"));
			boneMap.put("currentWeight", synchronizationBoneMap.get("currentWeight")==null ? null : synchronizationBoneMap.get("currentWeight"));
			boneMap.put("sex", synchronizationBoneMap.get("sex")==null ? null : synchronizationBoneMap.get("sex"));
			boneMap.put("createDateTime", synchronizationBoneMap.get("createDateTime")==null ? null : synchronizationBoneMap.get("createDateTime"));
			boneMap.put("menarcheType", synchronizationBoneMap.get("menarcheType")==null ? null : synchronizationBoneMap.get("menarcheType"));
			boneMap.put("value", synchronizationBoneMap.get("value")==null ? null : synchronizationBoneMap.get("value"));
			boneMap.put("menarcheAge", synchronizationBoneMap.get("menarcheAge")==null ? null : synchronizationBoneMap.get("menarcheAge"));
			return new JsonApi(ApiCodeEnum.OK, boneMap);
			/*测量数据，骨龄数据都存在，判断时间最新的显示*/
		}else if (synchronizationDataMap!=null && synchronizationBoneMap!=null) {
			resultMap.put("currentHeight", synchronizationDataMap.get("currentHeight")==null ? null : synchronizationDataMap.get("currentHeight"));
			resultMap.put("currentWeight", synchronizationDataMap.get("currentWeight")==null ? null : synchronizationDataMap.get("currentWeight"));
			resultMap.put("sex", synchronizationDataMap.get("sex")==null ? null : synchronizationDataMap.get("sex"));
			resultMap.put("createDateTime", synchronizationDataMap.get("createDateTime")==null ? null : synchronizationDataMap.get("createDateTime"));
			resultMap.put("menarcheType", synchronizationDataMap.get("menarcheType")==null ? null : synchronizationDataMap.get("menarcheType"));
			resultMap.put("value", synchronizationDataMap.get("value")==null ? null : synchronizationDataMap.get("value"));
			resultMap.put("menarcheAge", synchronizationDataMap.get("menarcheAge")==null ? null : synchronizationDataMap.get("menarcheAge"));
			
			boneMap.put("currentHeight", synchronizationBoneMap.get("currentHeight")==null ? null : synchronizationBoneMap.get("currentHeight"));
			boneMap.put("currentWeight", synchronizationBoneMap.get("currentWeight")==null ? null : synchronizationBoneMap.get("currentWeight"));
			boneMap.put("sex", synchronizationBoneMap.get("sex")==null ? null : synchronizationBoneMap.get("sex"));
			boneMap.put("createDateTime", synchronizationBoneMap.get("createDateTime")==null ? null : synchronizationBoneMap.get("createDateTime"));
			boneMap.put("menarcheType", synchronizationBoneMap.get("menarcheType")==null ? null : synchronizationBoneMap.get("menarcheType"));
			boneMap.put("value", synchronizationBoneMap.get("value")==null ? null : synchronizationBoneMap.get("value"));
			boneMap.put("menarcheAge", synchronizationBoneMap.get("menarcheAge")==null ? null : synchronizationBoneMap.get("menarcheAge"));
			
			 Date dataTime=(Date) synchronizationDataMap.get("createDateTime");
			 Date boneTime=(Date) synchronizationBoneMap.get("createDateTime");
			 /*判断时间*/
			 if (dataTime.getTime()>boneTime.getTime()) {
					return new JsonApi(ApiCodeEnum.OK, resultMap);
			}else {
				return new JsonApi(ApiCodeEnum.OK, boneMap);
			}
			 /*数据都不存在 返回404*/
		}else {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月18日
	 * @param diagnosis
	 * @param resul
	 * @return
	 * @description:  身高曲线图
	 */
	@RequiresAuthentication(value = {"web-module-bone-age:bone-age-order:height" }, level = Level.OPERATION)
	@GetMapping(value = { "/diagnosis/chart/diagram/height" })
	public JsonApi getHeightDiagramChart(@Validated({ BoneAgeOrder.Diagram.class }) BoneAgeOrder boneAgeOrder,BindingResult resul,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {

		/* 查询用户信息 */
		User user = new User();
		user.setId(boneAgeOrder.getUserId());
		Map<String, Object> userMap = userService.getOne(user);
		if (userMap == null) {
			/* 用户信息不存在 */
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		boneAgeOrder.setUserId(boneAgeOrder.getUserId());
		Map<String, Object> boneAgeOrderMap= boneAgeOrderService.getOneByTeam(boneAgeOrder);
		if (boneAgeOrderMap!=null) {
			if (boneAgeOrderMap.get("remoteBoneAgeOrderId")!=null) {
				boneAgeOrder.setOrganizationTeamId(Integer.parseInt(boneAgeOrderMap.get("launchOrganizationTeamId").toString()));
			}else {
				boneAgeOrder.setOrganizationTeamId(organizationTeamId);
			}
		}
		List<Map<String, Object>> boneAgeOrderList = boneAgeOrderService.getRemList(boneAgeOrder);
		if (boneAgeOrderList==null || boneAgeOrderList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		Float height= (Float) boneAgeOrderList.get(0).get("height");
		
		boneAgeOrder.setMonthAge(Integer.parseInt(userMap.get("monthAge").toString()));
		boneAgeOrder.setSex(Integer.parseInt(userMap.get("sex").toString()));
		Map<String, Integer> monthAgeRange = boneAgeOrderService.getMonthAgeRange(boneAgeOrder.getMonthAge());
		
		boneAgeOrder.setStartMonthAge(monthAgeRange.get("startMonthAge"));
		boneAgeOrder.setEndMonthAge(monthAgeRange.get("endMonthAge"));
		
		Map<String, Object> map =	boneAgeOrderService.getHeightDiagramChart(boneAgeOrder);
		
		List<Map<String, Object>> lineUser = new ArrayList<>();
		Map<String, Object> children = new HashMap<>();

	children.put("currentHeight", height);
		children.put("monthAge", boneAgeOrder.getMonthAge());
		lineUser.add(children);
		map.put("lineUser", lineUser);
	  return new JsonApi(ApiCodeEnum.OK, map);
	}
	
}
