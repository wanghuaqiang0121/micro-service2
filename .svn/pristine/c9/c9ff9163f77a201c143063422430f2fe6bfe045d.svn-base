package org.web.module.organization.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.Organization;
import org.web.module.organization.domain.RecommendOrganization;
import org.web.module.organization.global.BaseGlobalEnum;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.RecommendOrganizationService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 推荐机构表
 */
@RestController
public class RecommendOrganizationController {

	@Resource
	private RecommendOrganizationService recommendOrganizationService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param recommendOrganization
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 添加推荐机构
	 */
	@RequiresAuthentication( value = { "web-module-organization:recommend-organization:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/recommend/organization" })
	public JsonApi insert(
			@Validated(BaseEntity.Insert.class) @RequestBody RecommendOrganization recommendOrganization,BindingResult result){
		/*查询数据是否存在重复*/ 
		Map<String, Object> recommendOrganizationRepeatMap = recommendOrganizationService.getRepeat(recommendOrganization);
		if (recommendOrganizationRepeatMap!=null && !recommendOrganizationRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("recommend.organization.site.is.exists"));
		}
		/*获取某一机构的推荐机构最大排序号 */ 
		RecommendOrganization recommendOrganizationMaxSort = new RecommendOrganization();
		recommendOrganizationMaxSort.setOrganizationId(recommendOrganization.getOrganizationId());
		Map<String, Object> organizationMaxSortMap = recommendOrganizationService.getRecommendOrganizationMaxSort(recommendOrganizationMaxSort);
		if (organizationMaxSortMap==null || organizationMaxSortMap.isEmpty()) {
			/*没有推荐机构最大号：设置起始号1开始*/
			recommendOrganization.setSort(1);
		} else {
			/*设置排序号：取最大号+1*/ 
			recommendOrganization.setSort((int) organizationMaxSortMap.get("maxSort") + 1);
		}
		/* 添加推荐机构*/
		if (recommendOrganizationService.insert(recommendOrganization) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param recommendOrganization
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 某机构的推荐机构列表
	 */
	@RequiresAuthentication( value = { "web-module-organization:recommend-organization:get-list" },level=Level.OPERATION)
	@GetMapping(value = { "/recommend/organizations" })
	public JsonApi getList(
			@Validated(RecommendOrganization.GetRecommendOrganizationsList.class) RecommendOrganization recommendOrganization,BindingResult result){
		/*分页设置*/ 
		Page<?> page = PageHelper.startPage(recommendOrganization.getPage(), recommendOrganization.getPageSize());
		/*设置查询条件*/ 
		Organization organization = (recommendOrganization.getOrganization() != null) ? recommendOrganization.getOrganization() : new Organization();
		/*机构建立方式：公立*/ 
		organization.setCreateType(BaseGlobalEnum.OrganizationType.ORGANIZATION_TYPE_PUBLIC.getValue());
		/*机构状态：启用*/ 
		organization.setStatus(BaseGlobalEnum.OrganizationType.ENABLE.getValue());
		recommendOrganization.setOrganization(organization);
		/*查询推荐机构列表*/ 
		List<Map<String, Object>> recommendOrganizationsList = recommendOrganizationService.getRecommendOrganizations(recommendOrganization);
		if (recommendOrganizationsList!=null && !recommendOrganizationsList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(), recommendOrganizationsList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param recommendOrganization
	 * @param result
	 * @return {@link JsonApi}
	 * @description:  未推荐机构列表
	 */
	@RequiresAuthentication( value = { "web-module-organization:recommend-organization:get-not-recommend-organizations" },level=Level.OPERATION)
	@GetMapping(value = { "/not/recommend/organization" })
	public JsonApi getNotrecommendOrganizations(
			@Validated(RecommendOrganization.GetNotRecommendOrganizationsList.class) RecommendOrganization recommendOrganization,BindingResult result){
		/*分页设置*/ 
		Page<?> page = PageHelper.startPage(recommendOrganization.getPage(), recommendOrganization.getPageSize());
		/*查询未推荐机构列表*/ 
		List<Map<String, Object>> notRecommendOrganizationsResult = recommendOrganizationService.getNotRecommendOrganizations(recommendOrganization);
		if (CollectionUtils.isNotEmpty(notRecommendOrganizationsResult)) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(), notRecommendOrganizationsResult));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param recommendOrganization
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除推荐机构
	 */
	@RequiresAuthentication( value = { "web-module-organization:recommend-organization:delete" },level=Level.OPERATION)
	@DeleteMapping(value = { "/recommend/organization/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id,
			@Validated(BaseEntity.Delete.class) RecommendOrganization recommendOrganization,BindingResult result){
		/*设置id*/ 
		recommendOrganization.setId(id);
		/* 删除推荐机构*/
		if (recommendOrganizationService.delete(recommendOrganization) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param recommendOrganization
	 * @param result
	 * @return {@link JsonApi}
	 * @description:  推荐机构上移/下移
	 */
	@Transactional
	@RequiresAuthentication( value = { "web-module-organization:recommend-organization:move" },level=Level.OPERATION)
	@PutMapping(value = { "/recommend/organization/move/{id}" })
	public JsonApi move(@PathVariable("id") Integer id,
			@Validated(RecommendOrganization.MoveRecommendOrganization.class) @RequestBody RecommendOrganization recommendOrganization,BindingResult result){
		/*设置id*/ 
		recommendOrganization.setId(id);
		/*判断移动类型是否正确*/ 
		if((int) recommendOrganization.getMoveType() != BaseGlobalEnum.RecommendOrganization.RECOMMEND_ORGANIZATION_MOVE_UP.getValue()
				&& (int) recommendOrganization.getMoveType() != BaseGlobalEnum.RecommendOrganization.RECOMMEND_ORGANIZATION_MOVE_DOWN.getValue()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("recommend.organization.move.type.error"));
		}
		/* 查询详情*/
		Map<String, Object> recommendOrganizationMap = recommendOrganizationService.getOne(recommendOrganization);
		if (recommendOrganizationMap==null || recommendOrganizationMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/*上移*/ 
		if ((int)recommendOrganization.getMoveType()==BaseGlobalEnum.RecommendOrganization.RECOMMEND_ORGANIZATION_MOVE_UP.getValue()) {
			/*获取上移最近一条记录*/ 
			RecommendOrganization recommendOrganizationMoveUp=new RecommendOrganization();
			recommendOrganizationMoveUp.setId(id);
			recommendOrganizationMoveUp.setOrganizationId((int)recommendOrganizationMap.get("organizationId"));
			Map<String, Object> moveUpMap=recommendOrganizationService.getRecommendOrganizationMinSortByMoveUp(recommendOrganizationMoveUp);
			/*为空表示已达到上移最大限度*/ 
			if (moveUpMap==null || moveUpMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("recommend.organization.move.up.error"));
			}
			/* 设置移动数据*/
			/* 当前记录*/
			RecommendOrganization oldRecommendOrganization=new RecommendOrganization();
			oldRecommendOrganization.setId(id);
			oldRecommendOrganization.setSort((int) moveUpMap.get("sort"));
			/* 要交换的数据*/
			RecommendOrganization newRecommendOrganization=new RecommendOrganization();
			newRecommendOrganization.setId((int)moveUpMap.get("id"));
			newRecommendOrganization.setSort((int) recommendOrganizationMap.get("sort"));
			/*进行交换*/  
			if (recommendOrganizationService.update(oldRecommendOrganization)>0 && recommendOrganizationService.update(newRecommendOrganization)>0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
			return new JsonApi(ApiCodeEnum.FAIL);
		}
		/*下移*/  
		else {
			/*获取下移最近一条记录*/ 
			RecommendOrganization recommendOrganizationMoveDown=new RecommendOrganization();
			recommendOrganizationMoveDown.setId(id);
			recommendOrganizationMoveDown.setOrganizationId((int)recommendOrganizationMap.get("organizationId"));
			Map<String, Object> moveDownMap=recommendOrganizationService.getRecommendOrganizationMaxSortByMoveDown(recommendOrganizationMoveDown);
			/*为空表示已达到下移最大限度*/
			if (moveDownMap==null || moveDownMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("recommend.organization.move.down.error"));
			}
			/*设置移动数据*/ 
			/*当前记录*/ 
			RecommendOrganization oldRecommendOrganization=new RecommendOrganization();
			oldRecommendOrganization.setId(id);
			oldRecommendOrganization.setSort((int) moveDownMap.get("sort"));
			/* 要交换的数据*/
			RecommendOrganization newRecommendOrganization=new RecommendOrganization();
			newRecommendOrganization.setId((int)moveDownMap.get("id"));
			newRecommendOrganization.setSort((int) recommendOrganizationMap.get("sort"));
			/* 进行交换 */
			if (recommendOrganizationService.update(oldRecommendOrganization)>0 && recommendOrganizationService.update(newRecommendOrganization)>0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
			return new JsonApi(ApiCodeEnum.FAIL);
		}
	}
	
	
}
