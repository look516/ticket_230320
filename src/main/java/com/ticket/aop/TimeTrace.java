package com.ticket.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 무조건 new - annotation으로 만들어줘야
// 커스텀 어노테이션 만들기 검색
@Target({ElementType.METHOD}) // 적용대상
@Retention(RetentionPolicy.RUNTIME) // 언제 적용
public @interface TimeTrace {
	// 추후 메소드 구현 가능
}