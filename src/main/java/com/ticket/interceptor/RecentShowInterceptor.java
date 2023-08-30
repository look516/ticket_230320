package com.ticket.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;


@Component
public class RecentShowInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	//@Override
	public void postHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			ModelAndView modelAndView) {
		
		// 요청 url path를 가져오기
		String uri = request.getRequestURI();
		logger.info("[$$$$$$$$] postHandle uri:{}", uri);
		
		if (uri.startsWith("/show/show_detail_view")) {
			// 쿼리 스트링 따오기
			//uri.
			// 리스트 존재 여부 확인
			
			// 쿼리 스트링이 겹치지 않는지 확인
			
			// 겹치지 않으면 세션에 저장
			
			
			
			
			// 세션에 저장한 걸 model에 담아서 모든 header에 뿌림
			HttpSession session = request.getSession();
			List<String> recentShowImage = (List<String>) session.getAttribute("recentShowImage");
			
			if (recentShowImage == null) {
				recentShowImage = new ArrayList<>();
			}
			
			//recentShowImage.add();
		}
		
	}
}
