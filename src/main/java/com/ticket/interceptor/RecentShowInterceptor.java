package com.ticket.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ticket.show.bo.ShowBO;
import com.ticket.show.domain.ShowView;


@Component
public class RecentShowInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ShowBO showBO;
	
	@Override
    public boolean preHandle(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		Object handler) throws Exception {
        int showId = Integer.parseInt(request.getParameter("showId"));

        HttpSession session = request.getSession();
        List<String> showImageList = (List<String>) session.getAttribute("recentShowImageList");

        if (showImageList == null) {
            showImageList = new ArrayList<>();
        }
        
        ShowView show = showBO.generateShowViewByShowId(showId);
        String showImage = show.getShow().getImagePath();
        
        // 이미 있는 경우 해당 ID를 리스트에서 삭제하고 맨 앞에 추가
        showImageList.remove(showImage);
        showImageList.add(0, showImage);

        // 최대 개수 4개를 초과하는 경우 맨 뒤 항목 제거
        if (showImageList.size() > 4) {
            showImageList.remove(4);
        }

        session.setAttribute("recentShowImageList", showImageList);

        return true;
    }
	
	/*
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
		
	}*/
}
