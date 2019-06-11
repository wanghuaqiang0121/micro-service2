package org.web.module.bone.age.controller;

import java.util.Arrays;
import java.util.Date;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.web.module.bone.age.domain.AiReadFilm;
import org.web.module.bone.age.domain.BaseAgeOrder;
import org.web.module.bone.age.domain.BaseMedicalImagingData;
import org.web.module.bone.age.domain.BoneAgeOrder;
import org.web.module.bone.age.domain.ReadFilmRechargeRecord;
import org.web.module.bone.age.domain.RemoteBoneAgeOrder;
import org.web.module.bone.age.global.BaseGlobal;
import org.web.module.bone.age.global.GlobalEnum;
import org.web.module.bone.age.global.GlobalEnum.AireadFilm;
import org.web.module.bone.age.global.GlobalEnum.Menarche;
import org.web.module.bone.age.global.GlobalEnum.ScoreTableType;
import org.web.module.bone.age.global.GlobalEnum.Sex;
import org.web.module.bone.age.service.AgeOrderService;
import org.web.module.bone.age.service.AiReadFilmService;
import org.web.module.bone.age.service.BaseMedicalImagingDataService;
import org.web.module.bone.age.service.BoneAgeOrderService;
import org.web.module.bone.age.service.HttpIOUtil;
import org.web.module.bone.age.service.ReadFilmRechargeRecordService;
import org.web.module.bone.age.service.RemoteBoneAgeOrderService;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * Copyright © 2019 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2019年3月4日
 * @description: 人工智能评级表
 */

@RestController 
public class AiReadFilmController {
	Logger log = LoggerFactory.getLogger(AiReadFilmController.class);
	private final static Logger logger = LoggerFactory.getLogger(AiReadFilmController.class);
	@Resource
	private AiReadFilmService aiReadFilmService;
	
	@Resource
	private ReadFilmRechargeRecordService readFilmRechargeRecordService;
	@Resource
	private RemoteBoneAgeOrderService remoteBoneAgeOrderService;
	@Resource
	private BaseMedicalImagingDataService baseMedicalImagingDataService;

	@Value("${upload.url}")
	private String uploadUrl;
	@Value("${forensics.url}")
	private String fuploadUrl;
	@Value("${diagnosis.url}")
	private String diagnosisUrl;
	
	@Value("${forensics.diagnosis.url}")
	private String fDiagnosisUrl;
	
	@Value("${plainCredentials}")
	private String plainCredentials;
	@Resource
	private BoneAgeOrderService boneAgeOrderService;
	@Resource
	private AgeOrderService ageOrderService;
	@Resource
	private HttpIOUtil httpIOUtil;

	/**
	 * @author: ChenYan
	 * @date: 2019年3月4日
	 * @param aiReadFilm
	 * @param result
	 * @param token
	 * @param organizationTeamId
	 * @return
	 * @description: 人工智能评级记录表新增（每次新增都删除原来的）
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:ai-read-film:insert" },level = Level.OPERATION)
	@PostMapping(value = { "/ai/read/film" })
	@Transactional
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) AiReadFilm aiReadFilm,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {
		aiReadFilm.setOrganizationId(organizationId);
		/*查询数据是否已存在*/
		aiReadFilm.setBaseBoneAgeOrderId(aiReadFilm.getBaseBoneAgeOrderId());
		Map<String, Object> aiReadFilmMap=aiReadFilmService.getOne(aiReadFilm);
		/*存在先删除*/
		if (aiReadFilmMap!=null) {
			if (aiReadFilmService.delete(aiReadFilm)<0) {
				throw new RuntimeException();
			}
		}
		aiReadFilm.setOrganizationId(organizationId);
		aiReadFilm.setCreateTime(new Date());
		if (aiReadFilmService.insert(aiReadFilm)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	

	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月6日
	 * @param aiReadFilm
	 * @param result
	 * @param token
	 * @param organizationId
	 * @param organizationTeamId
	 * @return
	 * @description: 使用次数统计
	 */
	@RequiresAuthentication(value = { "web-module-bone-age:ai-read-film:get-times" },level = Level.OPERATION)
	@GetMapping(value = { "/ai/read/film/statistics/imes" })
	public JsonApi statisticsTimes( @Validated({ BaseEntity.SelectOne.class }) AiReadFilm aiReadFilm,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {
			Map<String, Object> resultMap=new HashMap<>();
			/*设置机构ID*/
			aiReadFilm.setOrganizationId(organizationId);
			aiReadFilm.setStatus(GlobalEnum.Ai.COMPLETED.getValue());
			/*AI使用次数*/
			Map<String, Object> aiMap=aiReadFilmService.getAiReadFilmTimes(aiReadFilm);
			if (aiMap!=null) {
				resultMap.put("aiTimes", aiMap.get("aiTimes")==null ? null : aiMap.get("aiTimes"));
			}else {
				resultMap.put("aiTimes", null);
			}
			/*机构剩余余额*/
			ReadFilmRechargeRecord readFilmRechargeRecord=new ReadFilmRechargeRecord();
			readFilmRechargeRecord.setOrganizationId(organizationId);
			List<Map<String, Object>> readFilmRechargeRecordList=readFilmRechargeRecordService.getLastRechargeRecord(readFilmRechargeRecord);
			if (readFilmRechargeRecordList!=null && !readFilmRechargeRecordList.isEmpty()) {
				resultMap.put("remainingSum", readFilmRechargeRecordList.get(0).get("remainingSum")==null ? null : readFilmRechargeRecordList.get(0).get("remainingSum"));
				Double d=(Double) readFilmRechargeRecordList.get(0).get("remainingSum");
				resultMap.put("remainingTimes",  readFilmRechargeRecordList.get(0).get("remainingSum")==null ? null :  Math.floor(d/BaseGlobal.MONEY.intValue()));
			}else {
				resultMap.put("remainingSum", null);
				resultMap.put("remainingTimes", null);
			}
			/*指定医生阅片使用次数*/
			RemoteBoneAgeOrder remoteBoneAgeOrder=new RemoteBoneAgeOrder();
			remoteBoneAgeOrder.setLaunchOrganizationId(organizationId);
			remoteBoneAgeOrder.setStatus(GlobalEnum.RemoteBoneAgeOrder.COMPLETED.getValue());
			Map<String, Object> doctorMap=remoteBoneAgeOrderService.getRemoteBoneAgeOrderTimes(remoteBoneAgeOrder);
			if (doctorMap!=null) {
				resultMap.put("doctorTimes", doctorMap.get("doctorTimes")==null ? null :doctorMap.get("doctorTimes"));
			}else {
				resultMap.put("doctorTimes", null);
			}
			return new JsonApi(ApiCodeEnum.OK, resultMap);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月6日
	 * @param aiReadFilm
	 * @param result
	 * @param organizationId
	 * @return
	 * @description: 人工智能阅片列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-bone-age:ai-read-film:get-list" })
	@GetMapping(value = { "/ai/read/films" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class })  AiReadFilm aiReadFilm, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		aiReadFilm.setOrganizationId(organizationId);
		aiReadFilm.setStatus(GlobalEnum.Ai.COMPLETED.getValue());
		Page<?> page = PageHelper.startPage(aiReadFilm.getPage(), aiReadFilm.getPageSize());
		List<Map<String, Object>> aiReadFilmList = aiReadFilmService.getList(aiReadFilm);		
		for (Map<String, Object> map : aiReadFilmList) {
			map.put("price", GlobalEnum.AireadFilm.PRICE.getValue());
		}
		if (aiReadFilmList != null && !aiReadFilmList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), aiReadFilmList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	
	
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月15日
	 * @param aiReadFilm
	 * @param result
	 * @param organizationId
	 * @return
	 * @description: 人工智能阅片医生列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-bone-age:ai-read-film:get-doctors" })
	@GetMapping(value = { "/ai/read/film/doctors" })
	public JsonApi getDoctors(@Validated({ BaseEntity.SelectAll.class })  AiReadFilm aiReadFilm, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		aiReadFilm.setOrganizationId(organizationId);
		aiReadFilm.setStatus(GlobalEnum.Ai.COMPLETED.getValue());
		Page<?> page = PageHelper.startPage(aiReadFilm.getPage(), aiReadFilm.getPageSize());
		List<Map<String, Object>> aiReadFilmList = aiReadFilmService.getDoctors(aiReadFilm);
		if (aiReadFilmList != null && !aiReadFilmList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), aiReadFilmList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年3月7日
	 * @param file
	 * @param aiReadFilm
	 * @param result
	 * @param token
	 * @param organizationId
	 * @param baseBoneAgeOrderId
	 * @param organizationTeamId
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @description: 上传骨龄片到人工智能合作公司
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@RequiresAuthentication(value = { "web-module-bone-age:ai-boneage-upload:insert" },level = Level.OPERATION)
	@PostMapping(value = { "/ai/boneage/upload/{baseBoneAgeOrderId}" })
	public JsonApi upload(@RequestParam("file") MultipartFile file,@Validated({ BaseEntity.Insert.class }) AiReadFilm aiReadFilm,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@PathVariable("baseBoneAgeOrderId") Integer baseBoneAgeOrderId,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {
		
		/* 判断机构是否充值和余额是否充足 */
		// 查询机构最新充值的一条记录
		ReadFilmRechargeRecord readFilmRechargeRecord = new ReadFilmRechargeRecord();
		readFilmRechargeRecord.setOrganizationId(organizationId);
		Map<String, Object> rechargeRecordMap = readFilmRechargeRecordService.getNewOne(readFilmRechargeRecord);
		// 最新充值记录为空 或 余额<人工智能单次价格 
		if (rechargeRecordMap == null) {// 提示：未充值
			return new JsonApi(ApiCodeEnum.FAIL).setMsg("所在机构未充值,请充值后使用该功能!");
		}else if (((Double)rechargeRecordMap.get("remainingSum")).intValue() < AireadFilm.PRICE.getValue()){// 提示：余额不足
			return new JsonApi(ApiCodeEnum.FAIL).setMsg("余额不足,请充值!");
		}
		readFilmRechargeRecord.setId(Integer.parseInt(rechargeRecordMap.get("id").toString()));
		
		// 查询骨龄工单详情
		BoneAgeOrder boneAgeOrder = new BoneAgeOrder();
		boneAgeOrder.setId(baseBoneAgeOrderId);
		Map<String, Object>  boneAgeOrderMap=boneAgeOrderService.getOne(boneAgeOrder);
		if (boneAgeOrderMap==null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		if(boneAgeOrderMap.get("birthday") == null){
			return new JsonApi(ApiCodeEnum.FAIL).setMsg("用户出生信息不完整！");
		}
		// 得到用户信息
		float height = (float) boneAgeOrderMap.get("height");
		float weight = (float) boneAgeOrderMap.get("weight");
		Boolean menarcheType = null;
		if(((Integer)boneAgeOrderMap.get("sex")) == Sex.SEX_MAN.getValue()){
			menarcheType = false;
		}
		if(((Integer)boneAgeOrderMap.get("sex")) == Sex.SEX_WOMAN.getValue()){
			if(boneAgeOrderMap.get("menarcheType") != null && (Integer.parseInt(boneAgeOrderMap.get("menarcheType").toString())) == Menarche.MENARCHE_TYPE_TEO.getValue()){
				menarcheType = true;
			}
		}
		Date birthday = (Date) boneAgeOrderMap.get("birthday");
		// 设置请求文本参数
		Map<String,String> requestText = new HashMap<String,String>();
		requestText.put("birth_date", birthday.toString());
		requestText.put("weight", weight+"");
		requestText.put("height", height+"");
		requestText.put("menarche", menarcheType+"");
		// 设置请求文件参数
		Map<String,MultipartFile> requestFile = new HashMap<String,MultipartFile>();
		MultipartFile multipartFile = file;
		requestFile.put("file",multipartFile);
		String requestURL = uploadUrl;// 定义请求地址
		String response = null; // 定义响应字符串
		Map<String,Object> responseMap = new HashMap<>();// 定义响应map
		try {
			// 得到response
			response = httpIOUtil.sendPostRequest(requestURL, requestText, requestFile,plainCredentials);
		} catch (Exception e) {
			logger.warn("Failure to upload pictures to AI");
			if ((responseMap.get("code").toString()).equals("201")) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg("必须上传 'DICM'格式的文件!");
			}
			return new JsonApi(ApiCodeEnum.FAIL);
		}
		
		// 转为map
		JSON responseJson = (JSON) JSON.parse(response);
		responseMap = (Map<String,Object>)responseJson;
		if ((responseMap.get("code").toString()).equals("201")) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg("必须上传 'DICM'格式的文件!");
		}
		// 上传成功 记录人工智能阅片记录表的diagnosis_id
		else if ((responseMap.get("code").toString()).equals("200")) {
			Map<String,Object> resultMap = (Map<String, Object>) responseMap.get("result");
			// 得到上传图片的id
			Integer diagnosisId = (Integer) resultMap.get("diagnosis_id");
			// 查询ai阅片表的最新一条记录
			AiReadFilm newAiReadFilm = new AiReadFilm();
			newAiReadFilm.setBaseBoneAgeOrderId(baseBoneAgeOrderId);
			Map<String,Object> aiMap = aiReadFilmService.getOne(newAiReadFilm);
			if(aiMap == null ){
				// 第一次上传
				// 新增diagnosisId
				aiReadFilm.setBaseBoneAgeOrderId(baseBoneAgeOrderId);
				aiReadFilm.setOrganizationId(organizationId);
				aiReadFilm.setDiagnosisId(diagnosisId.toString());
				aiReadFilm.setStatus(1);// 1.已上传图片 2.已完成
				aiReadFilm.setCreateTime(new Date());
				if(aiReadFilmService.insert(aiReadFilm) > 0)
				{
					//  锁定费用（锁定金额+20且余额-20） 以机构最新一条充值记录为准
					readFilmRechargeRecord.setPrice((double)AireadFilm.PRICE.getValue());
					if(readFilmRechargeRecordService.lockMoney(readFilmRechargeRecord) > 0){
						boneAgeOrder.setxRayIsUpdate(1);
						if (boneAgeOrderService.update(boneAgeOrder) > 0) {
							return new JsonApi(ApiCodeEnum.OK);
						}
					}
				    throw new RuntimeException();
				}
			}
			// 已经上传过图片到人工智能合作公司
			else if(aiMap.get("diagnosisId") != null ){
				/* 判断 是否与上次传的图片相同*/
				// 如果不相同
				if (!diagnosisId.toString().equals(aiMap.get("diagnosisId")) ) {
					// 新增diagnosisId
					aiReadFilm.setBaseBoneAgeOrderId(baseBoneAgeOrderId);
					aiReadFilm.setOrganizationId(organizationId);
					aiReadFilm.setDiagnosisId(diagnosisId.toString());
					aiReadFilm.setStatus(1);// 1.已上传图片 2.已完成
					aiReadFilm.setCreateTime(new Date());
					if(aiReadFilmService.insert(aiReadFilm) > 0)
					{
						//  锁定费用（锁定金额+20且余额-20） 以机构最新一条充值记录为准
						readFilmRechargeRecord.setPrice((double)AireadFilm.PRICE.getValue());
						if(readFilmRechargeRecordService.lockMoney(readFilmRechargeRecord) > 0){
						
							boneAgeOrder.setxRayIsUpdate(1);
							if (boneAgeOrderService.update(boneAgeOrder) > 0) {
								return new JsonApi(ApiCodeEnum.OK);
							}
						}
					    throw new RuntimeException();
					}
				}
				// 如果相同,数据不变
				return new JsonApi(ApiCodeEnum.OK);
			}
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	


	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年3月8日
	 * @param token
	 * @param organizationId
	 * @param baseBoneAgeOrderId
	 * @param organizationTeamId
	 * @return
	 * @description: 查看当次人工智能评测结果
	 */
	@Transactional
	@RequiresAuthentication(value = { "web-module-bone-age:ai-boneage-diagnosis:detail" },level = Level.OPERATION)
	@GetMapping(value = { "/ai/boneage/diagnosis/{baseBoneAgeOrderId}" })
	public JsonApi diagnosis(
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@PathVariable("baseBoneAgeOrderId") Integer baseBoneAgeOrderId,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId){
		/*查询骨龄工单详情*/ 
		BoneAgeOrder boneAgeOrder = new BoneAgeOrder();
		boneAgeOrder.setId(baseBoneAgeOrderId);
		Map<String, Object>  boneAgeOrderMap=boneAgeOrderService.getOne(boneAgeOrder);
		// 如果用户点击重新上传返回空
		if(boneAgeOrderMap==null || Integer.parseInt(boneAgeOrderMap.get("xRayIsUpdate").toString()) == 2){
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("status", 0);// 0未做过人工智能
			resMap.put("msg", null);
			return new JsonApi(ApiCodeEnum.OK,resMap);
		}
		// 查询最新一条AI阅片详情
		AiReadFilm newAiReadFilm = new AiReadFilm();
		newAiReadFilm.setBaseBoneAgeOrderId(baseBoneAgeOrderId);
		Map<String,Object> aiMap = aiReadFilmService.getOne(newAiReadFilm);
		if((Integer.parseInt(aiMap.get("status").toString())) == 2){// 状态为已完成直接返回结果
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("status", 2);// 2有结果
			resMap.put("msg", null);
			resMap.put("aiMap", aiMap);
			return new JsonApi(ApiCodeEnum.OK,resMap);
		}
		if (aiMap == null || aiMap.get("diagnosisId") == null) {
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("status", 1);// 失败
			resMap.put("msg", "人工智能阅片图片未上传！");
			return new JsonApi(ApiCodeEnum.OK,resMap);
		}
		Integer diagnosisId= Integer.parseInt(aiMap.get("diagnosisId").toString());
		// 地址+url参数
		String requestURL = diagnosisUrl+diagnosisId;
		String response = "";
		try {
			// 得到response
			response = httpIOUtil.sendGetRequest(requestURL,plainCredentials);
		} catch (Exception e) {
			logger.warn("Failure to Diagnosis to AI");
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("status", 1);// 1失败
			resMap.put("msg", "诊断失败");
			return new JsonApi(ApiCodeEnum.OK,resMap);
		}
		// 转为map
		JSON responseJson = (JSON) JSON.parse(response);
		@SuppressWarnings("unchecked")
		Map<String,Object> responseMap = (Map<String,Object>)responseJson;
		if((responseMap.get("code").toString()).equals("201")){// 其他错误
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("status", 1);// 1失败
			resMap.put("msg", "诊断失败!");
			return new JsonApi(ApiCodeEnum.OK,resMap);
		}
		else if ((responseMap.get("code").toString()).equals("200")) {
			Map<String,Object> wResultMap = new HashMap<>();
			// 取result
			@SuppressWarnings("unchecked")
			Map<String,Object> resultMap = (Map<String, Object>) responseMap.get("result");
			if(resultMap.get("status").toString().equals("0") || resultMap.get("status").toString().equals("1")){// 排队中|| 诊断中
				wResultMap.put("status", 3);// 3 需要轮询
				wResultMap.put("msg", null);
				return new JsonApi(ApiCodeEnum.OK,wResultMap);
			}
			else if(resultMap.get("status").toString().equals("-1")){// 诊断失败
				wResultMap.put("status", 1);// 1失败
				wResultMap.put("msg", "诊断失败!");
				return new JsonApi(ApiCodeEnum.OK,wResultMap);
			}
			// 状态为2 表示诊断完成
			else if(resultMap.get("status").toString().equals("2")){
				// 取result
				/*@SuppressWarnings("unchecked")
				Map<String,Object> endResultMap = (Map<String, Object>) resultMap.get("scores");*/
				// 取scores
				String demo = resultMap.get("scores").toString().replace("'", "").replace(" ", "");
				String demosub = demo.substring(1,demo.length()-1);
				String demoArray[] = demosub.split(",");
				List<String> scores = Arrays.asList(demoArray);
				
				newAiReadFilm.setRadiusLevel(scores.get(0));
				newAiReadFilm.setUlnaLevel(scores.get(1));
				newAiReadFilm.setMetacarpal1Level(scores.get(2));
				newAiReadFilm.setMetacarpal3Level(scores.get(3));
				newAiReadFilm.setMetacarpal5Level(scores.get(4));
				newAiReadFilm.setNearPhalanges1Level(scores.get(5));
				newAiReadFilm.setNearPhalanges3Level(scores.get(6));
				newAiReadFilm.setNearPhalanges5Level(scores.get(7));
				newAiReadFilm.setInPhalanges3Level(scores.get(8));
				newAiReadFilm.setInPhalanges5Level(scores.get(9));
				newAiReadFilm.setFarPhalanges1Level(scores.get(10));
				newAiReadFilm.setFarPhalanges3Level(scores.get(11));
				newAiReadFilm.setFarPhalanges5Level(scores.get(12));
				newAiReadFilm.setHeadBoneLevel(scores.get(13));
				newAiReadFilm.setHamateLevel(scores.get(14));
				newAiReadFilm.setPyramidalBoneLevel(scores.get(15));
				newAiReadFilm.setMoonBoneLevel(scores.get(16));
				newAiReadFilm.setScaphoidLevel(scores.get(17));
				newAiReadFilm.setMostOfTheHornsLevel(scores.get(18));
				newAiReadFilm.setSmallPolyhornsLevel(scores.get(19));
				newAiReadFilm.setStatus(2);// 1.已上传图片 2.已完成 
				newAiReadFilm.setId((Integer)aiMap.get("id"));
				// 修改分值和 状态为已完成
				if(aiReadFilmService.update(newAiReadFilm) > 0){
					Map<String, Object> resMap = new HashMap<>();
					resMap.put("status", 2);// 2有结果
					resMap.put("msg", null);
					Map<String, Object> map = aiReadFilmService.getOne(newAiReadFilm);
					resMap.put("aiMap", map);
					/* 扣费 */ 
					// 获取发起人工智能的机构id
					Integer organizatioId = (Integer) map.get("organizationId");
					// 远程阅片充值记录 base_read_film_recharge_record 扣费 （锁定金额-20）
					/* 判断机构是否充值 */
					// 查询机构最新充值的一条记录
					ReadFilmRechargeRecord readFilmRechargeRecord = new ReadFilmRechargeRecord();
					readFilmRechargeRecord.setOrganizationId(organizatioId);
					Map<String, Object> rechargeRecordMap = readFilmRechargeRecordService.getNewOne(readFilmRechargeRecord);
					// 最新充值记录为空 
					if (rechargeRecordMap == null) {// 提示：未充值
						return new JsonApi(ApiCodeEnum.FAIL).setMsg("所在机构未充值,请充值后使用该功能!");
					}
					readFilmRechargeRecord.setPrice((double)AireadFilm.PRICE.getValue());
					readFilmRechargeRecord.setId(Integer.parseInt(rechargeRecordMap.get("id").toString()));
					// 扣费
					if(readFilmRechargeRecordService.deductionFee(readFilmRechargeRecord) > 0){
						// 修改x光片是否被修改为：1.没有被修改
						boneAgeOrder.setxRayIsUpdate(1);
						if (boneAgeOrderService.update(boneAgeOrder) > 0) {
							return new JsonApi(ApiCodeEnum.OK,resMap);
						}
					} 
					throw new RuntimeException();
				}
				
				return new JsonApi(ApiCodeEnum.FAIL);
			}
		}
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("status", 1);// 1失败
		resMap.put("msg", "诊断失败,请稍后重试");
		return new JsonApi(ApiCodeEnum.OK,resMap);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年3月7日
	 * @param file
	 * @param aiReadFilm
	 * @param result
	 * @param token
	 * @param organizationId
	 * @param baseBoneAgeOrderId
	 * @param organizationTeamId
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @description: 生物学年龄推断-上传骨龄片到人工智能合作公司
	 */
	@SuppressWarnings("unchecked")
	@RequiresAuthentication(value = { "web-module-bone-age:ai-boneage-upload:insert" },level = Level.OPERATION)
	@PostMapping(value = { "/ai/forensics/upload/{baseMedicalImagingDataId}" })
	public JsonApi uploadForensics(@RequestParam("file") MultipartFile file,@Validated({ BaseEntity.Insert.class }) BaseMedicalImagingData baseMedicalImagingData,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@PathVariable("baseMedicalImagingDataId") Integer baseMedicalImagingDataId,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {
		baseMedicalImagingData.setId(baseMedicalImagingDataId);
		Map<String,Object> baseMedicalImagingDataMap = baseMedicalImagingDataService.getOne(baseMedicalImagingData);
		if (baseMedicalImagingDataMap == null) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		// 判断医学影像资料是否上传
		/*if(baseMedicalImagingDataMap.get("medicalImagingStatus") != null && ((Integer)baseMedicalImagingDataMap.get("medicalImagingStatus")) != 4){
			return new JsonApi(ApiCodeEnum.FAIL).setMsg("医学影像资料未上传");
		}*/
		// 得到用户信息
		float height = (float) baseMedicalImagingDataMap.get("height");
		float weight = (float) baseMedicalImagingDataMap.get("weight");
		Boolean menarcheType = null;
		if(((Integer)baseMedicalImagingDataMap.get("sex")) == Sex.SEX_MAN.getValue()){
			menarcheType = false;
		}
		if(((Integer)baseMedicalImagingDataMap.get("sex")) == Sex.SEX_WOMAN.getValue()){
			if(baseMedicalImagingDataMap.get("menarcheType") != null && (Integer.parseInt(baseMedicalImagingDataMap.get("menarcheType").toString())) == Menarche.MENARCHE_TYPE_TEO.getValue()){
				menarcheType = true;
			}
		}
		// 设置请求文本参数
		Map<String,String> requestText = new HashMap<String,String>();
		requestText.put("weight", weight+"");
		requestText.put("height", height+"");
		requestText.put("menarche", menarcheType+"");
		// 设置请求文件参数
		Map<String,MultipartFile> requestFile = new HashMap<String,MultipartFile>();
		MultipartFile multipartFile = file;
		requestFile.put("file",multipartFile);
		String requestURL = fuploadUrl;// 定义请求地址
		String response = null; // 定义响应字符串
		Map<String,Object> responseMap = new HashMap<>();// 定义响应map
		try {
			// 得到response
			response = httpIOUtil.sendPostRequest(requestURL, requestText, requestFile,plainCredentials);
		} catch (Exception e) {
			logger.warn("Failure to upload pictures to AI");
			if ((responseMap.get("code").toString()).equals("201")) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg("必须上传 'DICM'格式的文件!");
			}
			return new JsonApi(ApiCodeEnum.FAIL);
		}
		// 转为map
		JSON responseJson = (JSON) JSON.parse(response);
		responseMap = (Map<String,Object>)responseJson;
		if ((responseMap.get("code").toString()).equals("201")) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg("必须上传 'DICM'格式的文件!");
		}
		// 上传成功 记录人工智能阅片记录表的diagnosis_id
		else if ((responseMap.get("code").toString()).equals("200")) {
			Map<String,Object> resultMap = (Map<String, Object>) responseMap.get("result");
			// 得到上传图片的id
			Integer diagnosisId = (Integer) resultMap.get("diagnosis_id");
			// 记录diagnosisId
			BaseAgeOrder ageOrder = new BaseAgeOrder();
			ageOrder.setBaseMedicalImagingDataId(baseMedicalImagingDataId);
			ageOrder.setDiagnosisId(diagnosisId);
			//age_order 表与 医学影像资料1对1
			Map<String,Object> ageOrderMap = ageOrderService.getOne(ageOrder);
			if(ageOrderMap != null){//存在就修改
				ageOrder.setId((Integer)ageOrderMap.get("id"));
				if(ageOrderService.update(ageOrder) > 0){
					return new JsonApi(ApiCodeEnum.OK);
				}
			}else{// 不存在就添加
				ageOrder.setCreateTime(new Date());
				ageOrder.setAlgorithm(baseMedicalImagingData.getAlgorithm());
				ageOrder.setCheckPoint(baseMedicalImagingData.getCheckPoint());
				ageOrder.setxRayNo(baseMedicalImagingData.getxRayNo());
				ageOrder.setxRay(baseMedicalImagingData.getxRay());
				if (ageOrderService.insert(ageOrder) > 0) {
					return new JsonApi(ApiCodeEnum.OK);
				}
			}
			return new JsonApi(ApiCodeEnum.FAIL);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年3月8日
	 * @param token
	 * @param organizationId
	 * @param baseBoneAgeOrderId
	 * @param organizationTeamId
	 * @return
	 * @description: 生物学年龄人工智能评测结果
	 */
	@Transactional
	@RequiresAuthentication(value = { "web-module-bone-age:ai-boneage-diagnosis:detail" },level = Level.OPERATION)
	@GetMapping(value = { "/ai/forensics/diagnosis/{baseMedicalImagingDataId}" })
	public JsonApi ageDiagnosis(
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@PathVariable("baseMedicalImagingDataId") Integer baseMedicalImagingDataId,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId){
		/*查询年龄工单详情*/ 
		BaseAgeOrder ageOrder = new BaseAgeOrder();
		ageOrder.setBaseMedicalImagingDataId(baseMedicalImagingDataId);
		//age_order表与 医学影像资料1对1
		Map<String,Object> ageOrderMap = ageOrderService.getOne(ageOrder);
		if (ageOrderMap == null || ageOrderMap.get("diagnosisId") == null) {
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("status", 0);// 未做过人工智能
			resMap.put("msg", null);
			return new JsonApi(ApiCodeEnum.OK,resMap);
			
		}
		// 如果已经获取到生物学年龄人工智能评测结果，直接返回
		if (ageOrderMap.get("conclusion") != null) {
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("status", 2);// 2有结果
			resMap.put("msg", null);
			resMap.put("aiMap", ageOrderMap);
			return new JsonApi(ApiCodeEnum.OK,resMap);
		}
		Integer diagnosisId= Integer.parseInt(ageOrderMap.get("diagnosisId").toString());
		// 地址+url参数
		String requestURL = fDiagnosisUrl+diagnosisId;
		String response = "";
		try {
			// 得到response
			response = httpIOUtil.sendGetRequest(requestURL,plainCredentials);
		} catch (Exception e) {
			logger.warn("Failure to Diagnosis to AI");
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("status", 1);// 1失败
			resMap.put("msg", "诊断失败");
			return new JsonApi(ApiCodeEnum.OK,resMap);
		}
		// 转为map
		JSON responseJson = (JSON) JSON.parse(response);
		@SuppressWarnings("unchecked")
		Map<String,Object> responseMap = (Map<String,Object>)responseJson;
		if((responseMap.get("code").toString()).equals("201")){// 其他错误
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("status", 1);// 1失败
			resMap.put("msg", "诊断失败!");
			return new JsonApi(ApiCodeEnum.OK,resMap);
		}
		else if ((responseMap.get("code").toString()).equals("200")) {
			Map<String,Object> wResultMap = new HashMap<>();
			// 取result
			@SuppressWarnings("unchecked")
			Map<String,Object> resultMap = (Map<String, Object>) responseMap.get("result");
			if(resultMap.get("status").toString().equals("0") || resultMap.get("status").toString().equals("1")){// 排队中|| 诊断中
				wResultMap.put("status", 3);// 3 需要轮询
				wResultMap.put("msg", null);
				return new JsonApi(ApiCodeEnum.OK,wResultMap);
			}
			else if(resultMap.get("status").toString().equals("-1")){// 诊断失败
				wResultMap.put("status", 1);// 1失败
				wResultMap.put("msg", "诊断失败!");
				return new JsonApi(ApiCodeEnum.OK,wResultMap);
			}
			// 状态为2 表示诊断完成
			else if(resultMap.get("status").toString().equals("2")){
				// 取result
				/*@SuppressWarnings("unchecked")
				Map<String,Object> endResultMap = (Map<String, Object>) resultMap.get("scores");*/
				// 取scores转为List
				String demo = resultMap.get("scores").toString().replace("'", "").replace(" ", "");
				String demosub = demo.substring(1,demo.length()-1);
				String demoArray[] = demosub.split(",");
				List<String> scores = Arrays.asList(demoArray);
				ageOrder.setDescription(resultMap.get("description").toString());
				ageOrder.setBase64BonePlot("data:image/png;base64,"+resultMap.get("base64_t2_t_bone_plot").toString());
				ageOrder.setConclusion(resultMap.get("conclusion").toString());
				ageOrder.setExplain(resultMap.get("description").toString());// 检查解释
				
				ageOrder.setConclusions(resultMap.get("conclusion").toString());// 结论
				ageOrder.setRadiusLevel(scores.get(0));
				ageOrder.setUlnaLevel(scores.get(1));
				ageOrder.setMetacarpal1Level(scores.get(2));
				ageOrder.setMetacarpal3Level(scores.get(3));
				ageOrder.setMetacarpal5Level(scores.get(4));
				ageOrder.setNearPhalanges1Level(scores.get(5));
				ageOrder.setNearPhalanges3Level(scores.get(6));
				ageOrder.setNearPhalanges5Level(scores.get(7));
				ageOrder.setInPhalanges3Level(scores.get(8));
				ageOrder.setInPhalanges5Level(scores.get(9));
				ageOrder.setFarPhalanges1Level(scores.get(10));
				ageOrder.setFarPhalanges3Level(scores.get(11));
				ageOrder.setFarPhalanges5Level(scores.get(12));
				ageOrder.setHeadBoneLevel(scores.get(13));
				ageOrder.setHamateLevel(scores.get(14));
				ageOrder.setPyramidalBoneLevel(scores.get(15));
				ageOrder.setMoonBoneLevel(scores.get(16));
				ageOrder.setScaphoidLevel(scores.get(17));
				ageOrder.setMostOfTheHornsLevel(scores.get(18));
				ageOrder.setSmallPolyhornsLevel(scores.get(19));
				
				ageOrder.setId((Integer)ageOrderMap.get("ageId"));
				
				// 计算分值
				BaseMedicalImagingData baseMedicalImagingData = new BaseMedicalImagingData();
				baseMedicalImagingData.setId(baseMedicalImagingDataId);
				Map<String,Object> baseMedicalImagingDataMap = baseMedicalImagingDataService.getOne(baseMedicalImagingData);
				if (baseMedicalImagingDataMap == null || baseMedicalImagingDataMap.get("sex") == null) {
					return new JsonApi(ApiCodeEnum.NOT_FOUND);
				}
				
				// 修改
				if(ageOrderService.update(ageOrder) > 0){
					ageOrder.setSex((Integer)baseMedicalImagingDataMap.get("sex"));
					ageOrder.setScoreTableType(ScoreTableType.T.getValue());
					Map<String, Object> scoresMap = ageOrderService.getTW2BoneScores(ageOrder);
					if (scoresMap != null) {
						ageOrder.setBoneScore(Float.parseFloat(scoresMap.get("totalPoints").toString()));
					}
					// 修改分值
					ageOrderService.update(ageOrder);
					Map<String, Object> resMap = new HashMap<>();
					resMap.put("status", 2);// 2有结果
					resMap.put("msg", null);
					Map<String, Object> map = ageOrderService.getOne(ageOrder);
					resMap.put("aiMap", map);
					return new JsonApi(ApiCodeEnum.OK,resMap);
				}
				return new JsonApi(ApiCodeEnum.FAIL);
			}
		}
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("status", 1);// 1失败
		resMap.put("msg", "诊断失败,请稍后重试");
		return new JsonApi(ApiCodeEnum.OK,resMap);
	}
	
	
	
}
