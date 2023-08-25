package com.ticket.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class ShowAPIWebClient {
	@Autowired
	private WebClient webClient;
	
	public String getShowList(String rows, String cpage) {
		
		String baseUrl = "http://www.kopis.or.kr";
		String url = "/openApi/restful/pblprfr";
		String service = "bd89dc80bd9f43338c9d75e7fae03669";
		String stdate = "19900101"; // 시작일
		String eddate = "20241231"; // 종료일 (추후 BATCH)
		//rows = "10"; // 개수 (추후 변경)
		//cpage = "1"; // 현재 페이지 (추후 반복문)
		
		
		String parsingUrl = url + "?service=" + service + "&stdate=" + stdate
				+ "&eddate=" + eddate + "&rows=" + rows + "&cpage=" + cpage;
		
		
		
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
		
		return exchangeToMono.block();
	}
	
	
	
	
	
	
	public String getShow(String mt20id) {

		// 추후 yml에 등록
		String url = "/openApi/restful/pblprfr";
		String lastUrl = url + "/" + mt20id;
		String service = "bd89dc80bd9f43338c9d75e7fae03669";
		//String mt20id = "PF224468";

		Mono<String> exchangeToMono = webClient.get()
			.uri(builder -> builder.path(lastUrl)
					.queryParam("service", service)
					.build()
			)
			.exchangeToMono(response -> {
				return response.bodyToMono(String.class);
			});

		return exchangeToMono.block();
	}
	
}
