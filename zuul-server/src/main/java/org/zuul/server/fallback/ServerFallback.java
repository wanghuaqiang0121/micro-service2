package org.zuul.server.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 全局断路器
 */
@Component
public class ServerFallback implements FallbackProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider#
	 * getRoute()
	 */
	@Override
	public String getRoute() {
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return headers;
			}

			@Override
			public InputStream getBody() throws IOException {
				ObjectMapper mapper = new ObjectMapper();
				return new ByteArrayInputStream(
						mapper.writeValueAsString(new JsonApi(ApiCodeEnum.TIMEOUT)).getBytes("UTF-8"));
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.OK.getReasonPhrase();
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.OK.value();
			}

			@Override
			public void close() {
			}
		};
	}
}
