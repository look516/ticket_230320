package com.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ticket.test.CalcType;
import com.ticket.test.Status;

public class EnumTest {
	
	@Test
	void 계산테스트() {
		// given
		CalcType calcType = CalcType.CALC_C;
		
		// when
		int result = calcType.calculate(500);
		
		// then
		assertEquals(result, 1500);
	}
	
	
	
	
	
	// 일반 클래스로 만든다
	// 클래스 우클릭 후 run as - j unit test
	// 두 번째부터는 초록색 플레이 버튼 눌러서 실행시키면 서버대신 j unit test가 된다.
	
	// enum자체로 DB에 넣으려면 converter를 구현하는 게 좋다.
	
	// DB로 셀렉했는데 Y로 되어있는 경우
	Status getStatus() {
		return Status.Y;
	}
	
	//@Test
	void Status테스트() {
		// given - 준비
		Status status = getStatus(); // Y
		
		// when - 실행
		int v1 = status.getValue1();
		boolean v2 = status.isValue2(); // 필드의 타입이 boolean이면 getter가 get이 아니라 is로 시작
		
		// then - 검증
		// assertThat은 옛날 버전
		assertEquals(v1, 1);
		assertEquals(v2, true);	
		assertEquals(status, Status.Y);	
	}
}
