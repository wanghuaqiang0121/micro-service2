package org.service.tools.code;

import java.util.Random;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 随机码工具类
 */
public class ValidCodeUtils {

	private static char[] numbers = "0123456789".toCharArray();

	private static char[] words = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();

	private static final int MIN_LEN = 4;

	private static final int MAX_LEN = 8;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param len
	 * @return {@link String}
	 * @description: 获取纯数字的随机码
	 */
	public static String generateNumber(int len) {
		len = limitLen(len);
		Random random = new Random();
		char[] cs = new char[len];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = numbers[random.nextInt(numbers.length)];
		}
		return new String(cs);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param len
	 * @return {@link String}
	 * @description: 获取数字混合型随机码
	 */
	public static String generateCode(int len) {
		len = limitLen(len);
		Random random = new Random();
		char[] cs = new char[len];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = words[random.nextInt(words.length)];
		}
		return new String(cs);
	}

	private static int limitLen(int len) {
		if (len < MIN_LEN) {
			return MIN_LEN;
		} else if (len > MAX_LEN) {
			return MAX_LEN;
		} else {
			return len;
		}
	}
}
