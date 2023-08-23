package com.ticket.booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.booking.bo.BookingBO;
import com.ticket.booking.domain.BookingInfo;
import com.ticket.pay.bo.PayBO;

@RequestMapping("/book")
@RestController
public class BookingRestController {
	
	@Autowired
	private BookingBO bookingBO;
	
	@Autowired
	private PayBO payBO;
	
	@GetMapping("/booking_seat")
	public List<String> bookingSeat(
			@RequestParam("showId") int showId,
			@RequestParam("selectedDate") String selectedDate,
			@RequestParam("selectedTime") String selectedTime) {
		List<String> selectedSeatNums = bookingBO.getBookingList(showId, selectedDate, selectedTime);
		
		return selectedSeatNums;
	}
	
	// 2 버튼 클릭 시 pay view로 데이터 갖고 이동
	@PostMapping("/booking")
	public Map<String, Object> booking(HttpSession session, BookingInfo bookingInfo,
			Model model) {
		
		session.setAttribute("bookingInfo", bookingInfo);
		
		Map<String, Object> map = new HashMap<>();
		if (session.getAttribute("bookingInfo") != null) {
			map.put("code", 1);
			map.put("forwardUrl", "/book/pay_view");
			map.put("result", "성공");
		} else {
			map.put("code", 500);
			map.put("errorMessage", "예약 정보가 없습니다.");
		}
		return map;
	}
	
	// 4 버튼 클릭 시 submit 하고 성공 시 성공값 리턴 및 페이지 이동 (Rest에서)
	@PostMapping("/pay")
	public Map<String, Object> pay(
			@RequestParam("discount") String discount,
			@RequestParam("payment") String payment,
			@RequestParam("discountName") String discountName,
			HttpSession session) {
		BookingInfo bookingInfo = (BookingInfo) session.getAttribute("bookingInfo");
		int userId = (int)session.getAttribute("userId");
		
		// int showId, String showDate, showTime, seatInput, seatGradeInput, total
		// bookingInfo insert 처리
		
		Integer bookingId = bookingBO.addBooking(userId, bookingInfo);
		
		// payInfo insert 처리
		Integer payId = payBO.addPay(bookingId, discount, payment, discountName);
		
		// 응답
		Map<String, Object> result = new HashMap<>();
		if (payId != null && bookingId != null) {
		//if (true) {
			// response
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원가입에 실패했습니다.");
		}
		return result;
	}
	
	
	@PutMapping("/update_status")
	public Map<String, Object> updateStatus(
			@RequestParam("bookingId") int bookingId,
			@RequestParam("payId") int payId) {
		bookingBO.updateBooking(bookingId);
		payBO.updatePay(payId);
		
		// 응답
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// REQUEST MAP으로 묶어서 보내는 방법도 추후 적용해보자.
	/*@PostMapping("/booking_insert")
	public ResponseEntity<String> booking(
			@RequestParam("showId") int showId,
			@RequestParam("showDate") String showDate,
			@RequestParam("showTime") String showTime,
			@RequestParam("totalInput") String totalInput,
			@RequestParam("seatGradeInput") String seatGrade,
			@RequestParam("seatInput") String seat,
			HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		
		// db insert
		Integer bookingId = bookingBO.addBooking(userId, showId, showDate, showTime, seatGrade, seat);
		
		boolean isSuccess = (bookingId != null ? true : false);
		// 응답
		if (isSuccess) {
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
		}
	}
	*/	

}
