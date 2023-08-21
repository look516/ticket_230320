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
	
	//@RequestMapping("/api")
	public String getShowList(
			@RequestParam("service") String service,
			@RequestParam("stdate") String stdate,
			@RequestParam("eddate") String eddate,
			@RequestParam("rows") String rows,
			@RequestParam("cpage") String cpage) {
		
		String baseUrl = "http://www.kopis.or.kr";
		String url = "/openApi/restful/pblprfr";
		String serviceStr = "bd89dc80bd9f43338c9d75e7fae03669";
		String stdateStr = "19900101"; // 시작일
		String eddateStr = "20241231"; // 종료일 (추후 BATCH)
		String rowsStr = "10"; // 개수 (추후 변경)
		String cpageStr = "1"; // 현재 페이지 (추후 반복문)
		
		
		String parsingUrl = url + "?service=" + serviceStr + "&stdate=" + stdateStr
				+ "&eddate=" + eddateStr + "&rows=" + rowsStr + "&cpage=" + cpageStr;
		
		
		
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
	
	
	
	
	
	
	//@RequestMapping("/api")
	public String getShowList2(
			@RequestParam("service") String service,
			@RequestParam("stdate") String stdate,
			@RequestParam("eddate") String eddate,
			@RequestParam("rows") String rows,
			@RequestParam("cpage") String cpage) {
		
		String baseUrl = "http://www.kopis.or.kr";
		String url = "/openApi/restful/pblprfr";
		String serviceStr = "bd89dc80bd9f43338c9d75e7fae03669";
		String stdateStr = "19900101"; // 시작일
		String eddateStr = "20241231"; // 종료일 (추후 BATCH)
		String rowsStr = "10"; // 개수 (추후 변경)
		String cpageStr = "1"; // 현재 페이지 (추후 반복문)
		
		
		String parsingUrl = url + "?service=" + serviceStr + "&stdate=" + stdateStr
				+ "&eddate=" + eddateStr + "&rows=" + rowsStr + "&cpage=" + cpageStr;
		
		
		
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
	
}
