package com.ticket.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {
		
		// 요청 url path를 가져온다.
		String uri = request.getRequestURI();
		logger.info("[$$$$$$$$$] preHandle uri:{}", uri);
		
		// 로그인 여부 확인 - session 확인
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		
		// main, show, theater 비로그인 로그인 상관없음
		// review list view, review sort 비로그인 로그인 상관없음
		
		// 로그인 && 로그인, 회원가입 창
		if (userId != null && uri.startsWith("/user")) {
			response.sendRedirect("/main/main_view");
			return false; // 컨트롤러 수행 안 함
		}
		
		// 비로그인 && book
		if (userId == null && uri.startsWith("/book")) {
			response.sendRedirect("/user/sign_in_view");
			return false; // 컨트롤러 수행 안 함
		}
		
		// 비로그인 && review
		if (userId == null && uri.startsWith("/review")) {
			response.sendRedirect("/user/sign_in_view");
			return false; // 컨트롤러 수행 안 함
		}
		
		return true;
	}	
		
}
