package com.ticket.book;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/book")
@Controller
public class BookController {
	
	// 단 로그인 정보를 이용해 자신의 예약만 가져온다.
	@GetMapping("/book_list_view")
	public String bookListView(
			@RequestParam(value = "prevId", required = false) Integer prevIdParam,
			@RequestParam(value = "nextId", required = false) Integer nextIdParam,
			HttpSession session, Model model) {
		
		// 로그인 여부 조회 (통합해서 해도 될까?)
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			// 비로그인이면 로그인 페이지로 이동
			return "redirect:/user/sign_in_view";
		}
		// DB 예약 목록 조회
		List<book> bookList = bookBO.
		
		// 페이징
		int nextId = 0;
		int prevId = 0; // 원래는 직접 로직을 사용하지 않고 페이징 bo를 통해 리턴되는 페이징 객체를 담는 게 좋다.
		
		if (bookList.isEmpty() == false) {
			// bookList가 비어있을 때 오류 방지
			nextId = bookList.get(bookList.size() - 1).getId(); // 가져온 리스트의 가장 끝값(작은 id)
			prevId = bookList.get(0).getId();
			
			// 이전 방향의 끝인가? select해서 가져온 가장 최신 글 id가 페이지에 포함되어있다면 첫 페이지다.
			// prevId와 book 테이블의 가장 큰 id값과 같다면 이전 페이지 없음
			if (bookBO.isPrevLastPage(prevId, userId)) {
				prevId = 0;
			}
			// 다음 방향의 끝인가? select해서 가져온 가장 예전 글 id가 페이지에 포함되어있다면 끝 페이지다.
			// nextId와 book 테이블의 가장 작은 id값과 같다면 다음 페이지 없음
			if (bookBO.isNextLastPage(nextId, userId)) {
				nextId = 0;
			}
		}
		
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		
		model.addAttribute("bookList", bookList);
		
		model.addAttribute("view", "book/bookList");
		return "template/layout";
	}
}
