package org.web.module.base.controller.data.currency;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.service.tools.rsa.RestToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.data.currency.SystemKeyInformation;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.data.currency.SystemKeyInformationService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月15日
 * @description: SystemKeyInformationController
 */
@RestController
public class SystemKeyInformationController {

	@Resource
	private SystemKeyInformationService systemKeyInformationService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param platform
	 * @param systemKeyInformation
	 * @param result
	 * @return {@link JsonApi}
	 * @throws IOException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @description: 根据平台查询系统密钥信息详情
	 */
	@RequiresAuthentication(authc = true, value = {})
	@GetMapping(value = { "/sys/key/{platform}" })
	public JsonApi getOne(@PathVariable("platform") String platform, @Validated({ BaseEntity.SelectOne.class }) SystemKeyInformation systemKeyInformation, BindingResult result)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		/* 设置条件 */
		systemKeyInformation.setPlatform(platform);
		/* 查询详情 */
		Map<String, Object> systemKeyInformationMap = systemKeyInformationService.getOne(systemKeyInformation);

		if (systemKeyInformationMap != null && !systemKeyInformationMap.isEmpty()) {
			/* 定义返回结果集 */
			Map<String, Map<String, Object>> resultTokenMap = new HashMap<String, Map<String, Object>>();
			resultTokenMap.put(systemKeyInformation.getPlatform(),
					RestToken.getInstance().getPermissionToken(systemKeyInformationMap.get("username").toString(), systemKeyInformationMap.get("password").toString(),
							systemKeyInformationMap.get("publicKey").toString(), systemKeyInformationMap.get("url").toString(), (int) systemKeyInformationMap.get("expire")));
			return new JsonApi(ApiCodeEnum.OK, resultTokenMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("system.key.information.empty"));
	}
}
