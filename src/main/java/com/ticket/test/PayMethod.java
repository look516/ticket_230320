package com.ticket.test;

import lombok.Getter;

@Getter
public enum PayMethod {
	REMIITANCE("무통장입금")
	, ACCOUNT_TRANSFER("계좌이체")
	, CREDIT("신용카드")
	, KAKAO("카카오페이")
	, NAVER("네이버페이")
	;
	
	// field
	private String title;
	
	// 생성자
	PayMethod(String title) {
		this.title = title;
	}
}