package org.web.module.bone.age.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 国际化配置
 */
public class Prompt {
	private static String filePath = "i18n.prompt";

	public static String bundle(String key) {
		return ResourceBundle.getBundle(filePath, Locale.getDefault()).getString(key);
	}

	public static String bundle(String key, Object... arguments) {
		return MessageFormat.format(ResourceBundle.getBundle(filePath, Locale.getDefault()).getString(key), arguments);
	}
}
