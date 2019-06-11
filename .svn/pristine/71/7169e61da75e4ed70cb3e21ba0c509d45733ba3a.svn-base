package org.service.task.delay.receiver.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.service.task.delay.entity.sms.Recipient;
import org.service.task.delay.entity.sms.Sms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月2日
 * @description: Send
 */
@Component
public class Send {
	private final static Logger logger = LoggerFactory.getLogger(Send.class);
	private static String postUrl;
	private static Integer userId;
	private static String account;
	private static String password;

	@Value("${sms.url}")
	public void setPostUrl(String postUrl) {
		Send.postUrl = postUrl;
	}

	@Value("${sms.userid}")
	public void setUserId(Integer userId) {
		Send.userId = userId;
	}

	@Value("${sms.account}")
	public void setAccount(String account) {
		Send.account = account;
	}

	@Value("${sms.password}")
	public void setPassword(String password) {
		Send.password = password;
	}

	public static Sms SMS(Sms sms) {
		try {
			// 发送POST请求
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			StringBuffer dataStr = new StringBuffer();
			dataStr.append("action=send").append("&account=" + account).append("&userid=" + userId).append("&password=" + password).append("&mobile=").append("&content=").append("【")
					.append(sms.getSign()).append("】").append(sms.getContent()).append("&mobile=");
			for (Recipient recipient : sms.getRecipients().stream()
					.collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(r -> r.getCalledNumber()))), ArrayList::new))) {
				dataStr.append(recipient.getCalledNumber()).append(",");
			}
			dataStr = dataStr.deleteCharAt(dataStr.length() - 1);
			conn.setRequestProperty("Content-Length", "" + dataStr.length());
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(dataStr.toString());
			out.flush();
			out.close();

			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				logger.warn("send sms error connect sms server fail");
				sms.setStatus("Faild");
				return sms;
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			Document doc = DocumentHelper.parseText(result);
			Element rootElt = doc.getRootElement(); // 获取根节点
			sms.setStatus(rootElt.elementText("returnstatus"));
			sms.setMessage(rootElt.elementText("message"));
			return sms;
		} catch (IOException | DocumentException e) {
			logger.error(e.getMessage());
		}
		sms.setStatus("Faild");
		return sms;
	}
}
