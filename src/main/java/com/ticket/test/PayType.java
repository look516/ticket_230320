package com.ticket.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public enum PayType {
	// 열거형 정의
	CASH("현금", List.of("REMIITANCE", "ACCOUNT_TRANSFER"))
	, CARD("카드", List.of("CREDIT", "KAKAO", "NAVER"))
	, EMPTY("없음", Collections.emptyList());
	
	// 필드
	private String title;
	private List<String> payList;
	
	// 생성자
	PayType(String title, List<String> payList) {
		this.title = title;
		this.payList = payList;
	}
	
	// 결제수단(예:계좌이체)이 enum에 존재하는지 확인하는 method
	boolean hasPayMethod(String payMethod) {
		return this.payList // this. 생략 가능
		.stream()
		.anyMatch(pay -> pay.equals(payMethod)); // payMethod는 파라미터, pay는 리스트 순회 둘이 같은지 물어봄
	}
	
	// String(결제수단)으로 enum 상수(부모 그룹)를 찾기
	// 안 담고 바로 사용하기 위해 static
	public static PayType findByPayMethod(String payMethod) {
		return Arrays
				.stream(PayType.values()) // PayType의 열거형 변수들을 stream으로 변환 (리스트같은 느낌)
				.filter(payType -> payType.hasPayMethod(payMethod)) // cash, card, empty를 순회하며 haspaymethod 실행
				.findAny() // 찾은 요소 랜덤하게 반환(여기선 하나이므로 하나로 반환됨)
				.orElse(EMPTY); // 찾은 요소가 없다면 PayType.EMPTY로 리턴
	}
}