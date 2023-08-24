package com.ticket.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.ticket.aop.TimeTrace;

import reactor.core.publisher.Mono;

//@RequiredArgsConstructor
//@RequestMapping("")
@RestController
public class ShowAPIRestController {
	//private ShowAPI showAPI
	@Autowired
	private WebClient webClient;
	
	// 추후 위치 옮기기
	@TimeTrace
	@RequestMapping("/api")
	public String getShowList(
			/*@RequestParam("service") String service,
			@RequestParam("stdate") String stdate,
			@RequestParam("eddate") String eddate,
			@RequestParam("rows") String rows,
			@RequestParam("cpage") String cpage*/) {

		// 추후 yml에 등록
		String url = "/openApi/restful/pblprfr";
		String service = "bd89dc80bd9f43338c9d75e7fae03669";
		String stdate = "20230101"; // 시작일
		String eddate = "20231231"; // 종료일 (추후 BATCH)
		String rows = "100"; // 개수 (추후 변경)
		String cpage = "1"; // 현재 페이지 (추후 반복문)
		
		String shcate = "AAAA"; // AAAA연극 CCCD 대중음악 GGGA 뮤지컬
		// 82388건 // 연극 뮤지컬 대중음악 -> 37950건
		// 1page 100개 가져오는데 34311ms
		
		/*
		 * String parsingUrl = url + "?service=" + service + "&stdate=" + stdate +
		 * "&eddate=" + eddate + "&rows=" + rows + "&cpage=" + cpage;
		 */


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
	
	
	
	
	
	
	@TimeTrace
	@RequestMapping("/api5")
	public String getShow(
			/*@RequestParam("service") String service,
			@RequestParam("stdate") String stdate,
			@RequestParam("eddate") String eddate,
			@RequestParam("rows") String rows,
			@RequestParam("cpage") String cpage*/) {

		// 추후 yml에 등록
		String url = "/openApi/restful/pblprfr";
		String service = "bd89dc80bd9f43338c9d75e7fae03669";
		String mt20id = "PF224468";

		Mono<String> exchangeToMono = webClient.get()
			.uri(builder -> builder.path(url + "/{mt20id}")
					.queryParam("service", service)
					.build(mt20id)
			)
			.exchangeToMono(response -> {
				return response.bodyToMono(String.class);
			});

		return exchangeToMono.block();
	}
	
	
}
