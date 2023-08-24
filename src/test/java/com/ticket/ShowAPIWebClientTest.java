package com.ticket;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@SpringBootTest
class ShowAPIWebClientTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebClient webClient;

	//@Test
	void getShowList() {
		
		String baseUrl = "http://www.kopis.or.kr";
		String url = "/openApi/restful/pblprfr";
		String service = "bd89dc80bd9f43338c9d75e7fae03669";
		String stdate = "19900101"; // 시작일
		String eddate = "20241231"; // 종료일 (추후 BATCH)
		String rows = "10"; // 개수 (추후 변경)
		String cpage = "1"; // 현재 페이지 (추후 반복문)
		
		
		
		Mono<String> exchangeToMono = webClient.get()
			.uri(builder -> builder.path(url)
					.queryParam("service", service)
					.queryParam("stdate", stdate)
					.queryParam("eddate", eddate)
					.queryParam("rows", rows)
					.queryParam("cpage", cpage)
					.build()
			)
			.exchangeToMono(response -> {
				return response.bodyToMono(String.class);
			});
		
		logger.info(exchangeToMono.block());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	void getShow() {
		// 추후 yml에 등록
		String url = "/openApi/restful/pblprfr";
		String service = "bd89dc80bd9f43338c9d75e7fae03669";
		String mt20id = "PF224468";
		String lastUrl = url + "/" + mt20id;
		
		Mono<String> exchangeToMono = webClient.get()
				.uri(builder -> builder.path(lastUrl)
					.queryParam("service", service)
					.build()
			)
			.exchangeToMono(response -> {
				return response.bodyToMono(String.class);
			});

		logger.info(exchangeToMono.block());
		
	}
}
