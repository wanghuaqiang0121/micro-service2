package org.web.module.bone.age.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.bone.age.domain.OrganizationChoosedReadFilmDoctor;
import org.web.module.bone.age.domain.ReadFilmDoctorPrice;
import org.web.module.bone.age.domain.ReadFilmRechargeRecord;
import org.web.module.bone.age.domain.RemoteBoneAgeOrder;
import org.web.module.bone.age.global.BaseGlobal;
import org.web.module.bone.age.global.GlobalEnum;
import org.web.module.bone.age.message.Prompt;
import org.web.module.bone.age.service.OrganizationChoosedReadFilmDoctorService;
import org.web.module.bone.age.service.ReadFilmDoctorPriceService;
import org.web.module.bone.age.service.ReadFilmRechargeRecordService;
import org.web.module.bone.age.service.RemoteBoneAgeOrderService;
import org.web.module.bone.age.service.feign.IOrganizationUserRoleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2019 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2019年3月5日
 * @description: 机构选择过的阅片医生记录表
 */
@RestController 
public class OrganizationChoosedReadFilmDoctorController {
	
	@Resource
	private OrganizationChoosedReadFilmDoctorService organizationChoosedReadFilmDoctorService;
	@Resource
	private IOrganizationUserRoleService organizationUserRoleService;
	@Resource
	private RemoteBoneAgeOrderService remoteBoneAgeOrderService;
	@Resource
	private ReadFilmRechargeRecordService readFilmRechargeRecordService;
	@Resource
	private ReadFilmDoctorPriceService readFilmDoctorPriceService;

	/**
	 * @author: ChenYan
	 * @date: 2019年3月5日
	 * @param organizationChoosedReadFilmDoctor
	 * @param result
	 * @return
	 * @description: 新增机构选择过的阅片医生记录表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-bone-age:organization-choosed-read-film-doctor:insert" })
	@PostMapping(value = { "/organization/choosed/read/film/doctor" })
	@Transactional
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationChoosedReadFilmDoctor organizationChoosedReadFilmDoctor, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId
			,@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {
	   /*获取机构用户ID*/
		Integer organizationUserId = null;
		JsonApi jsonApi = organizationUserRoleService.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			organizationUserId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		
		
		/*根据选择的医生价格 判断机构余额是否充足*/
		Double doctorMoney=null;
		ReadFilmDoctorPrice readFilmDoctorPrice=new ReadFilmDoctorPrice();
		readFilmDoctorPrice.setId(organizationChoosedReadFilmDoctor.getReadFilmDoctorPriceId());
		Map<String, Object>  doctorMoneyMap=readFilmDoctorPriceService.getOne(readFilmDoctorPrice);
		if (doctorMoneyMap!=null) {
		/*	取出医生价格价格*/
			 doctorMoney=(Double) doctorMoneyMap.get("price");
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		
		/*查询发起的机构 余额*/
		Double remainingSum=null;
		Integer readFilmRechargeRecordId=null;
		ReadFilmRechargeRecord readFilmRechargeRecord=new ReadFilmRechargeRecord();
		/*设置发起机构id*/
		readFilmRechargeRecord.setOrganizationId(organizationId);
		List<Map<String, Object>> readFilmRechargeRecordList = readFilmRechargeRecordService.getList(readFilmRechargeRecord);
		if (readFilmRechargeRecordList!=null && !readFilmRechargeRecordList.isEmpty()) {
			
			readFilmRechargeRecordId=(Integer) readFilmRechargeRecordList.get(0).get("id");
			if (readFilmRechargeRecordList.get(0).get("remainingSum")!=null) {
				 remainingSum=(Double) readFilmRechargeRecordList.get(0).get("remainingSum");
				if (remainingSum<doctorMoney) {
					/* 提示 余额不足*/
					return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("remaining.sum.is.not.enough"));
				}
			}else {
				/* 提示 余额不足*/
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("remaining.sum.is.not.enough"));
			}
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/*余额充足，机构余额减去医生价格，锁定价格为医生价格+原来锁定的价格*/
		if (readFilmRechargeRecordList.get(0).get("lockMoney")!=null) {
			Double lockMoney=(Double) readFilmRechargeRecordList.get(0).get("lockMoney");
			readFilmRechargeRecord.setLockMoney(doctorMoney+lockMoney);
		}else {
			readFilmRechargeRecord.setLockMoney(doctorMoney);
		}
		readFilmRechargeRecord.setRemainingSum(remainingSum-doctorMoney);
		readFilmRechargeRecord.setId(readFilmRechargeRecordId);
	
		
		/*修改远程阅片充值*/
		if (readFilmRechargeRecordService.update(readFilmRechargeRecord)<0) {
			return new JsonApi(ApiCodeEnum.FAIL);
		}
		
		/*机构选择过的阅片医生记录表 设置创建时间*/
		organizationChoosedReadFilmDoctor.setCreateTime(new Date());
		organizationChoosedReadFilmDoctor.setReadFilmDoctorPriceId(organizationChoosedReadFilmDoctor.getReadFilmDoctorPriceId());
		organizationChoosedReadFilmDoctor.setOrganizationId(organizationId);
		/*远程阅片骨龄工单 设置默认值*/
		RemoteBoneAgeOrder remoteBoneAgeOrder=new RemoteBoneAgeOrder();
		remoteBoneAgeOrder.setCreateTime(new Date());
		remoteBoneAgeOrder.setStatus(GlobalEnum.RemoteBoneAgeOrder.WAITINGFORORDER.getValue());
		remoteBoneAgeOrder.setReminder(false);
		remoteBoneAgeOrder.setLaunchOrganizationTeamId(organizationTeamId);
		remoteBoneAgeOrder.setLaunchOrganizationId(organizationId);
		remoteBoneAgeOrder.setLaunchOrganizationUserId(organizationUserId);
		remoteBoneAgeOrder.setBaseBoneAgeOrderId(organizationChoosedReadFilmDoctor.getBaseBoneAgeOrderId());
		remoteBoneAgeOrder.setReceiveOrganizationId(Integer.parseInt(doctorMoneyMap.get("organizationId").toString()));
		remoteBoneAgeOrder.setReceiveOrganizationUserId(Integer.parseInt(doctorMoneyMap.get("doctorId").toString()));
		/*判断远程阅片骨龄工单数据是否重复*/
		/*Map<String, Object> remoteBoneAgeOrderMap=remoteBoneAgeOrderService.getRepeat(remoteBoneAgeOrder);
		if (remoteBoneAgeOrderMap!=null) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}*/
		
		/*判断机构选择过的阅片医生记录表是否存在 存在不新增*/
		Map<String, Object> map=organizationChoosedReadFilmDoctorService.getRepeat(organizationChoosedReadFilmDoctor);
		if (map==null) {
			/*设置次数为1*/
			      organizationChoosedReadFilmDoctor.setNum(1);
			if (organizationChoosedReadFilmDoctorService.insert(organizationChoosedReadFilmDoctor) < 0) {
				throw new RuntimeException();
			}
		}else {
			// 次数+1
				organizationChoosedReadFilmDoctor.setNum(Integer.parseInt(map.get("num").toString())+1);
				if (organizationChoosedReadFilmDoctorService.update(organizationChoosedReadFilmDoctor)<0) {
					throw new RuntimeException();
				}
		}
		/*判断远程阅片骨龄工单是否存在*/
		Map<String, Object> remoteBoneAgeOrderOneMap=remoteBoneAgeOrderService.getOne(remoteBoneAgeOrder);
		if (remoteBoneAgeOrderOneMap==null) {// 不存在新增
			/*新增远程阅片骨龄工单*/
			if (remoteBoneAgeOrderService.insert(remoteBoneAgeOrder)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
			/*存在修改*/
		}else {
			remoteBoneAgeOrder.setId(Integer.parseInt(remoteBoneAgeOrderOneMap.get("id").toString()));
			if (remoteBoneAgeOrderService.update(remoteBoneAgeOrder)>0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
		}
		
		throw new RuntimeException();
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2019年3月5日
	 * @param organizationChoosedReadFilmDoctor
	 * @param result
	 * @return
	 * @description: 机构选择过的阅片医生记录列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-bone-age:organization-choosed-read-film-doctor:list" })
	@GetMapping(value = { "/organization/choosed/read/film/doctors" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class })  OrganizationChoosedReadFilmDoctor organizationChoosedReadFilmDoctor, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		organizationChoosedReadFilmDoctor.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(organizationChoosedReadFilmDoctor.getPage(), organizationChoosedReadFilmDoctor.getPageSize());
		List<Map<String, Object>> organizationChoosedReadFilmDoctorList = organizationChoosedReadFilmDoctorService.getList(organizationChoosedReadFilmDoctor);
		if (organizationChoosedReadFilmDoctorList != null && !organizationChoosedReadFilmDoctorList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationChoosedReadFilmDoctorList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

}
