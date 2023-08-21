package com.ticket.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect // 부가 기능을 정의(advice) + 어디에 적용(pointcut) // singleton 객체로 만들어줌(프로젝트에서 하나)
@Component
public class TimeAop {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 어디에 적용?(pointcut) around
	// pro~ 원래 메소드에 적용하겠다
	//@Around("execution(* com.ticket..*(..))")
	@Around("@annotation(TimeTrace)") // TimeTrace (내가 지은 이름) 어노테이션이 붙어있는 곳은 AOP 수행
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		// System.currentTimeMillis() // 시간 측정해주는 것, 프로그램 시작과 끝에 적용해서 차를 구한다.
		
		// 시간 측정
		StopWatch sw = new StopWatch();
		sw.start();
		
		// 원본 메소드 수행
		Object obj = joinPoint.proceed();
		
		sw.stop();
		logger.info("### 실행 시간(ms): " + sw.getTotalTimeMillis());
		logger.info(sw.prettyPrint());
		return obj;
	}
}
