package com.ticket.booking;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.booking.bo.BookingBO;
import com.ticket.booking.domain.Booking;
import com.ticket.show.bo.ShowBO;

@RequestMapping("/book")
@Controller
public class BookingController {
	
	@Autowired
	private BookingBO bookingBO;
	
	private ShowBO showBO;
	
	@GetMapping("/book_list_view")
	public String bookListView(
			@RequestParam(value = "prevId", required = false) Integer prevIdParam,
			@RequestParam(value = "nextId", required = false) Integer nextIdParam,
			HttpSession session, Model model) {
		
		// 로그인 여부 조회
		// 로그인 정보를 이용해 자신의 예약만 가져온다.
		int userId = (int)session.getAttribute("userId");
		
		// DB 예약 목록 조회 (userId로 분류)
		List<Booking> bookingList = bookingBO.getBookingListByUserId(userId);
		
		// 공연명 조회
		
		
		// 페이징
		
		
		model.addAttribute("bookingList", bookingList);
		
		model.addAttribute("view", "booking/bookingList");
		return "template/layout";
	}
	
	
	@GetMapping("/book_detail_view")
	public String bookDetailView(
			@RequestParam("bookingId") int bookingId,
			Model model, HttpSession session) {
		model.addAttribute("view", "booking/bookingDetail");
		return "template/layout";
	}
	
	@GetMapping("/book_page_view")
	public String bookPageView(
			@RequestParam("showId") int showId,
			Model model, HttpSession session) {
		// 공연명 조회 및 넣기
		
		// 부모창에서 가져온 데이터 넣기
		
		
		model.addAttribute("view", "booking/bookingPage");
		return "template/layoutBooking";
	}
	
	@GetMapping("/pay_view")
	public String payView(
			//@RequestParam("bookingId") int bookingId,
			Model model, HttpSession session) {
		model.addAttribute("view", "booking/bookingPay");
		return "template/layoutBooking";
	}
	
	
	// get이 맞나?
	@GetMapping("/booking_done_view")
	public String bookingDoneView(
			//@RequestParam("bookingId") int bookingId,
			//@RequestParam("payId") int payId,
			Model model, HttpSession session) {
		model.addAttribute("view", "booking/bookingDone");
		return "template/layoutBooking";
	}
}
