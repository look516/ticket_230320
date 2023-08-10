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
		// DB 예약 목록 조회 (userId로 분류)
		//List<book> bookList = bookBO.
		
		// 페이징
		
		
		//model.addAttribute("bookList", bookList);
		
		model.addAttribute("view", "book/bookList");
		return "template/layout";
	}
	
	
	@GetMapping("/book_detail_view")
	public String bookDetailView(
			@RequestParam("bookingId") int bookingId,
			Model model, HttpSession session) {
		model.addAttribute("view", "book/bookDetail");
		return "template/layout";
	}
}
