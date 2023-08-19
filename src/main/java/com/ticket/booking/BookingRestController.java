package com.ticket.booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.booking.bo.BookingBO;

@RequestMapping("/book")
@RestController
public class BookingRestController {
	
	@Autowired
	private BookingBO bookingBO;
	
	@GetMapping("/booking_seat")
	public List<String> bookingSeat(
			@RequestParam("showId") int showId,
			@RequestParam("selectedDate") String selectedDate,
			@RequestParam("selectedTime") String selectedTime) {
		List<String> selectedSeatNums = bookingBO.getBookingList(showId, selectedDate, selectedTime);
		
		return selectedSeatNums;
	}
	
	
	@PostMapping("/booking")
	public Map<String, Object> booking(
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
		
		// 응답
		Map<String, Object> result = new HashMap<>();
		if (bookingId != null) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "좌석선택에 실패했습니다. 다시 선택해주세요.");
		}
		return result;
	}
		

}
