package com.ticket.booking;

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
import com.ticket.booking.domain.BookingInfo;
import com.ticket.booking.domain.BookingView;
import com.ticket.pay.bo.PayBO;
import com.ticket.pay.domain.Pay;
import com.ticket.show.bo.ShowBO;
import com.ticket.show.domain.Show;
import com.ticket.show.entity.ShowEntity;
import com.ticket.user.bo.UserBO;

@RequestMapping("/book")
@Controller
public class BookingController {
	
	@Autowired
	private BookingBO bookingBO;
	
	@Autowired
	private ShowBO showBO;
	
	@Autowired
	private PayBO payBO;
	
	@Autowired
	private UserBO userBO;
	
	@GetMapping("/book_list_view")
	public String bookListView(
			@RequestParam(value = "prevId", required = false) Integer prevIdParam,
			@RequestParam(value = "nextId", required = false) Integer nextIdParam,
			HttpSession session, Model model) {
		
		// 로그인 여부 조회
		// 로그인 정보를 이용해 자신의 예약만 가져온다.
		int userId = (int)session.getAttribute("userId");
		String userName = userBO.getUserEntityById(userId).getName();
		
		// DB 예약 목록 조회 (userId로 분류)
		List<BookingView> bookingViewList = bookingBO.getBookingListByUserId(userId);
		
		
		// 공연명 조회
		
		// 매수
		
		// 페이징
		
		
		model.addAttribute("bookingViewList", bookingViewList);
		model.addAttribute("userName", userName);
		model.addAttribute("view", "booking/bookingList");
		return "template/layout";
	}
	
	
	@GetMapping("/book_detail_view")
	public String bookDetailView(
			@RequestParam("bookingId") int bookingId,
			Model model, HttpSession session) {
		// 공연정보 조회
		BookingView bookingView = bookingBO.generateBookingViewBybookingId(bookingId);
		// 공연장 조회
		
		// 결제정보 조회
		Pay pay = payBO.getPay(bookingView.getBooking().getId());
		
		model.addAttribute("pay", pay);
		model.addAttribute("booking", bookingView);
		model.addAttribute("view", "booking/bookingDetail");
		return "template/layout";
	}
	
	
	
	
	
	// 1 예매 페이지 호출
	@GetMapping("/book_page_view")
	public String bookPageView(
			@RequestParam("showId") int showId,
			Model model, HttpSession session) {
		// 공연명 조회 및 넣기
		ShowEntity show = showBO.getShowNameById(showId);
		
		// 좌석 정보 불러오기
		//List<BookingView> = bookingBO. 
		
		
		model.addAttribute("show", show);
		model.addAttribute("view", "booking/bookingPage");
		return "template/layoutBooking";
	}
	
	
	
	
	// 예약 정보가 있을 때만 접근 가능하다 -> post?
	// 1) booking, pay insert 각 페이지가 넘어갈 때마다 한다.
	// 2) booking에서 넘어온 데이터를 가지고 pay를 꾸린다.
	// 예약 - 결제 - 예약취소 로직을 어떻게 짤까
	
	// 3 pay view에서 데이터 뿌림
	@GetMapping("/pay_view")
	public String payView(
			//@RequestParam("params") String params,
			//@RequestParam("bookingId") int bookingId,
			Model model, HttpSession session) {
		//model.addAttribute("booking", params);
		BookingInfo bookingInfo = (BookingInfo) session.getAttribute("bookingInfo");
		
		// 공연명 조회 및 넣기
		ShowEntity show = showBO.getShowNameById(bookingInfo.getShowId());

		model.addAttribute("booking", bookingInfo);
		model.addAttribute("show", show);
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
