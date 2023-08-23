package com.ticket.test;

import lombok.Getter;

@Getter
public enum Status {
	// enum 배달의민족 문서 참고
	
	// 열거형 정의 1
	//Y,
	//N;
	
	// 열거형 정의 2
	Y(1, true), // 생성자 필요
	N(0, false);

	// enum에 정의된 항목들의 필드 (열거형 안쪽에 들어가는 것들 정의)	
	private int value1; //(1, 0)
	private boolean value2; //(true, false)
	
	// 생성자
	Status(int value1, boolean value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
}
