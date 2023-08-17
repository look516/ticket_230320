package com.ticket.config;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Bean
	public WebClient webClient() {
		String baseUrl = "http://www.kopis.or.kr";
		String url = "/openApi/restful/pblprfr";
		String service = "bd89dc80bd9f43338c9d75e7fae03669";
		String stdate = "19900101"; // 시작일
		String eddate = "20241231"; // 종료일 (추후 BATCH)
		String rows = "10"; // 개수 (추후 변경)
		String cpage = "1"; // 현재 페이지 (추후 반복문)
		
		
		//String parsingUrl = url + "?service=" + serviceStr + "&stdate=" + stdateStr
		//		+ "&eddate=" + eddateStr + "&rows=" + rowsStr + "&cpage=" + cpageStr;
		
		
		
		return WebClient
			.builder()
			.baseUrl(baseUrl)
			.build();
				//.accept(MediaType.APPLICATION_XML)
		        //.acceptCharset(Charset.forName("UTF-8"));
				/*.exchangeToMono(response -> {
					return response.bodyToMono(String.class);
				});*/
	
	
	}

}
