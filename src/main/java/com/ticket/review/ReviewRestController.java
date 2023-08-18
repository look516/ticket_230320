package com.ticket.review;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.review.bo.ReviewBO;

@RequestMapping("/review")
@RestController
public class ReviewRestController {
	
	@Autowired
	private ReviewBO reviewBO;
	
	// 1) 폼태그 이용
	// 2) 폼데이터 - 객체 저장
	// 3) 다른 방식
	// RequestParam이 너무 많은 거 아닐까? 객체로 넘길 수 있을까?
	// /review/create
	// showId - showName으로 변환해서 가져오기 / controller에서 직접 넣기
	// userId - session에 있는 값 controller에서 직접 넣기
	// subject, content, point - form 필수
	// showDate, showTime - form 비필수
	@PostMapping("/create")
	public Map<String, Object> ReviewCreate(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam("point") int point,
			@RequestParam("showDate") String showDate,
			@RequestParam("showTime") String showTime,
			
			@RequestParam("showId") int showId,
			HttpSession session) {
		
		// 로그인 예외 처리?
		
		// 세션에서 userId를 받아옴
		int userId = (int)session.getAttribute("userId");
		
		// db insert
		int reviewRow = reviewBO.addReview(showId, userId, subject, content, point, showDate, showTime);
		
		// 결과
		Map<String, Object> result = new HashMap<>();
		if (reviewRow > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "리뷰 작성하는데 실패했습니다.");
		}
		
		return result;
	}
	
	
	//@GetMapping("/sort")
	
	
}
