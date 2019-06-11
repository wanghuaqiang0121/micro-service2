package org.service.redis.token;

import java.util.Base64;
import java.util.UUID;

import org.service.redis.terminal.TerminalEnum;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 登录令牌工具类
 */
public class RedisToken {

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param terminal
	 * @param identity
	 * @return {@link String}
	 * @description: 创建token
	 */
	public static String createToken(TerminalEnum terminal, Object identity) {
		StringBuffer token = new StringBuffer();
		token.append(terminal.getFlag()).append(":");
		token.append(UUID.randomUUID()).append(":");
		token.append(identity.toString());
		return Base64.getEncoder().encodeToString(token.toString().getBytes());
	};

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param token
	 * @return {@link String}
	 * @throws RuntimeException
	 * @description: 解密token
	 */
	public static String decodeToekn(String token) throws RuntimeException {
		String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
		if (token.matches(base64Pattern)) {
			return new String(Base64.getDecoder().decode(token.getBytes()));
		} else {
			throw new RuntimeException("It's not legal token");
		}
	}
}
