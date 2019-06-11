package org.web.module.organization.intercept;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.token.RedisSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.web.module.organization.global.BaseGlobal;
import org.web.module.organization.intercept.permission.IOrganizationUserRoleService;
import org.web.module.organization.message.Prompt;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 权限拦截器
 */
public class UserSecurityInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(UserSecurityInterceptor.class);
	@Resource
	private RedisCacheManager redisCacheManager;
	@Resource
	private IOrganizationUserRoleService organizationUserRoleService;

	private boolean validate(List<Map<String, Object>> list, String[] auths) {
		if (list != null && list.size() > 0) {
			for (String auth : auths) {
				for (Map<String, Object> map : list) {
					if (map != null && map.size() > 0) {
						if (auth.equals(map.get(BaseGlobal.PERMISSION_CODE))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			/* 构造就送处理对象 */
			ObjectMapper om = new ObjectMapper();
			/* 获取类全限定名 */
			String className = ((HandlerMethod) handler).getBeanType().getName();
			/* 获取方法名 */
			String methodName = ((HandlerMethod) handler).getMethod().getName();
			/* 设置响应头字符编码为UTF-8 */
			response.setCharacterEncoding("UTF-8");
			/* 设置响应头为Json格式 */
			response.setContentType("application/json");
			/* 获取token */
			String token = request.getHeader(BaseGlobal.TOKEN_FLAG);
			/* 获取机构ID */
			String organizationId = request.getHeader(BaseGlobal.ORGANIZATION_ID);
			/* 获取模块ID */
			String moduleId = request.getHeader(BaseGlobal.MODULE_ID);
			/* 获取方法 */
			HandlerMethod method = (HandlerMethod) handler;
			/* 获取权限注解 */
			RequiresAuthentication authentication = method.getMethodAnnotation(RequiresAuthentication.class);
			/* 如果权限注解为空不允许访问 */
			if (authentication == null) {
				if (logger.isWarnEnabled()) {
					logger.warn("the target method [{}#{}] is not defined", className, methodName);
				}
				response.getWriter().write(om.writeValueAsString(new JsonApi(ApiCodeEnum.UNIMPLEMENTED)));
				return false;
			}
			/* 权限注解不为空 则进行鉴权判断 */
			if (authentication.ignore()) {
				/* 如果设置忽略校验直接放行 */
				return true;
			} else if (authentication.authc()) {
				/* 判断是否进行登录鉴权 */
				if (token == null) {
					/* token为空则返回参数有误状态 */
					response.getWriter().write(om.writeValueAsString(new JsonApi(ApiCodeEnum.BAD_REQUEST)));
					return false;
				}
				RedisSession session = redisCacheManager.getSession(BaseGlobal.CACHE_USER, token);
				/* 登录信息不为空证明已登录，则放行 */
				if (session != null) {
					return true;
				}
				/* 未登录打印日志 */
				if (logger.isInfoEnabled()) {
					logger.info("the access target method [{}#{}] is not logged in", className, methodName);
				}
				response.getWriter().write(om.writeValueAsString(new JsonApi(ApiCodeEnum.UNAUTHORIZED)));
				return false;
			} else {
				/* 这里进行具体的业务权限判断 */
				switch (authentication.level()) {
				/* 角色判断 */
				case ROLE:
					/* 获取角色列表 */
					JsonApi roleJson = organizationUserRoleService.getOrganizationUserRoleRolesList(1, 1000, token, Integer.valueOf(organizationId), Integer.valueOf(moduleId));
					if (roleJson.getCode() != ApiCodeEnum.OK.getValue()) {
						/* 发生错误返回错误消息 */
						response.getWriter().write(JSON.toJSONString(new JsonApi(ApiCodeEnum.FORBIDDEN).setMsg(Prompt.bundle("organization.user.forbidden"))));
						return false;
					}
					Map<String, Object> roleData = (Map<String, Object>) roleJson.getData();
					List<Map<String, Object>> roleList =(List<Map<String, Object>>) roleData.get("rows");
					if (validate(roleList, authentication.value())) {
						return true;
					}
					break;
				case PERMISSION:
					/* 获取权限列表 */
					JsonApi permissionJson = organizationUserRoleService.getOrganizationUserRolepermissionsList(1, 1000, token, Integer.valueOf(organizationId), Integer.valueOf(moduleId));
					if (permissionJson.getCode() != ApiCodeEnum.OK.getValue()) {
						/* 发生错误返回错误消息 */
						response.getWriter().write(JSON.toJSONString(new JsonApi(ApiCodeEnum.FORBIDDEN).setMsg(Prompt.bundle("organization.user.forbidden"))));
						return false;
					}
					Map<String, Object> permissionMap = (Map<String, Object>) permissionJson.getData();
					List<Map<String, Object>> permissionList = (List<Map<String, Object>>)permissionMap.get("rows");
					if (validate(permissionList, authentication.value())) {
						return true;
					}
					break;
				case OPERATION:
					/* 获取操作列表 */
					JsonApi operationJson = organizationUserRoleService.getOrganizationUserRoleOperationsList(1, 1000, token, Integer.valueOf(organizationId), Integer.valueOf(moduleId));
					if (operationJson.getCode() != ApiCodeEnum.OK.getValue()) {
						/* 发生错误返回错误消息 */
						response.getWriter().write(JSON.toJSONString(new JsonApi(ApiCodeEnum.FORBIDDEN).setMsg(Prompt.bundle("organization.user.forbidden"))));
						return false;
					}
					Map<String, Object> operationData = (Map<String, Object>) operationJson.getData();
					List<Map<String, Object>> operationList = (List<Map<String, Object>>) operationData.get("rows");
					if (validate(operationList, authentication.value())) {
						return true;
					}
					break;
				}
			}
			if (logger.isInfoEnabled()) {
				logger.info("access target method [{}#{}] has no permissions", className, methodName);
			}
			response.getWriter().write(om.writeValueAsString(new JsonApi(ApiCodeEnum.FORBIDDEN)));
			return false;
		}
		return true;
	}
}