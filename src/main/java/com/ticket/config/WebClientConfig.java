package com.ticket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Bean
	public WebClient webClient() {
		String baseUrl = "http://www.kopis.or.kr";
		
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